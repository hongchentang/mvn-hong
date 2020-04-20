package com.hcis.hcrp.report.report.service.impl;

import com.alibaba.fastjson.JSON;
import com.hcis.hcrp.patent.service.PatentService;
import com.hcis.hcrp.report.report.dao.HcrpReportCdResultDao;
import com.hcis.hcrp.report.report.dao.HcrpReportChapterDao;
import com.hcis.hcrp.report.report.dao.ReportDao;
import com.hcis.hcrp.report.report.entity.HcrpReportCdResult;
import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
import com.hcis.hcrp.report.report.entity.Report;
import com.hcis.hcrp.report.report.service.ReportService;
import com.hcis.hcrp.report.template.dto.*;
import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.hcrp.report.template.entity.HcrpDimensionFormula;
import com.hcis.hcrp.report.template.service.ReportTemplateService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zhw
 * @date 2020/3/9
 **/
@Service
public class ReportServiceImpl extends BaseServiceImpl<Report> implements ReportService {

    private static final Pattern NUMBER_PATTERN =  Pattern.compile("[0-9]*");

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private HcrpReportChapterDao reportChapterDao;

    @Autowired
    private ReportTemplateService reportTemplateService;

    @Autowired
    private PatentService patentService;

    @Autowired
    private HcrpReportCdResultDao hcrpReportCdResultDao;

    @Override
    public MyBatisDao getBaseDao() {
        return null;
    }
    @Override
    public int insert(Report report) {
        return reportDao.insert(report);
    }

    @Override
    public int count(SearchParam searchParam) {
        searchParam.setPagination(null);
        return reportDao.count(searchParam);
    }

    @Override
    public Report getReportResult(String reportId) throws Exception {

        Report report = reportDao.selectByPrimaryKey(reportId);

        List<HcrpReportChapter> chapterList = reportChapterDao.listByReportId(reportId);

        List<HcrpReportCdResult> resultSaveList = new ArrayList<>();
        if("Y".equals(report.getIsSaveResult())){
             resultSaveList = hcrpReportCdResultDao.listByReportId(report.getId());

        }
        List<HcrpDimension> dimensions = reportTemplateService.getChapterDimensionsV2(report.getTemplateId()).getList();


        List<HcrpDimension> secDimensionList = new ArrayList<>();
        report.setSecDimensionList(secDimensionList);

        String firstSecDimensionId = "";
        for(HcrpReportChapter chapter : chapterList){
            for(HcrpDimension dimension : dimensions){

                secDimensionList.addAll(dimension.getSecList());
                for(HcrpDimension sec: dimension.getSecList()){

                    if(StringUtils.isEmpty(firstSecDimensionId)){
                        firstSecDimensionId = sec.getId();
                    }

                    /*获取整理后的数据*/
                    List<ConvertTableDataDto> resultList;
                    if("Y".equals(report.getIsSaveResult())){
                        resultList = getAlreadySave(resultSaveList, chapter.getId(), sec.getId());
                    }else {
                     resultList = patentService.convertTableData(sec.getTableId(), chapter.getId());
                    }


                    /*如果找不到数据，跳出循环*/
                    if(CollectionUtils.isEmpty(resultList)){
                        break;
                    }
                    sec.setResultDataList(resultList);
                    sec.setResultDataJson(JSON.toJSONString(resultList));

                    Map<String, List<TableResultDto>> map = new HashMap<>();
                    Map<String, Map<String, TableResultDto>> mapKey = new Hashtable<>();
                    for(ConvertTableDataDto data : resultList){
                        List<TableResultDto> list = data.getResultDtoList();
                        String key = data.getTableTrName();
                        map.put(key, list);

                        Map<String, TableResultDto> mapTable = new Hashtable<>(list.size());
                        for(TableResultDto dto : list){
                            mapTable.put(dto.getKey(), dto);
                        }
                        mapKey.put(key, mapTable);
                    }

                    /*根据公式替换文本内容*/
                    String dimensionHtml = sec.getDimensionHtml();
                    List<HcrpDimensionFormula>  formulaList = sec.getSecFormulaList();
                    for(HcrpDimensionFormula formula : formulaList){
                        String formulaKey = formula.getFormulaHtml();
                        String formulaVal = formula.getFormulaVal();

                        String[] formulaValKeys = formulaVal.split("-");
                        String resultStr = "";
                        if(formulaValKeys.length == 3){
                                /*这里是去表数据*/
                            try {
                                String trName = formulaValKeys[0];
                                String formulaValKey1 = formulaValKeys[1];
                                String model = formulaValKeys[2];
                                if(isNumeric(formulaValKey1) && Integer.parseInt(formulaValKey1) < 20){
                                    int tdIdx = Integer.parseInt(formulaValKey1) - 1;
                                    switch (model){
                                        case "key":
                                            resultStr = resultStr + map.get(trName).get(tdIdx).getKey();
                                            break;
                                        case "sum":
                                            resultStr = resultStr + map.get(trName).get(tdIdx).getSum();
                                            break;
                                        case "percentage":
                                            String percentage = map.get(trName).get(tdIdx).getPercentage().toString();
                                            percentage  = percentage.substring(0, percentage.length() - 2);
                                            resultStr = resultStr + percentage + "%";
                                            break;
                                        default:
                                            break;
                                    }
                                }else {
                                    Map<String, TableResultDto> mapTable = mapKey.get(trName);
                                    switch (model){
                                        case "key":
                                            resultStr = resultStr + mapTable.get(formulaValKey1).getKey();
                                            break;
                                        case "sum":
                                            resultStr = resultStr + mapTable.get(formulaValKey1).getSum();
                                            break;
                                        case "percentage":
                                            String percentage = mapTable.get(formulaValKey1).getPercentage().toString();
                                            percentage  = percentage.substring(0, percentage.length() - 2);
                                            resultStr = resultStr + percentage + "%";
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            } catch (IndexOutOfBoundsException e){
                                logger.debug("---->超出长度的值：" + formulaKey);
                            }catch (Exception e){
                                logger.debug("---->存在不能转换的：" + formulaKey);
                            }
                        }

                        dimensionHtml = dimensionHtml.replaceAll(formulaKey, resultStr);
                    }
                    sec.setDimensionHtml(dimensionHtml);

                }
            }
            chapter.setDimensionList(dimensions);
        }
        report.setFirstSecDimensionId(firstSecDimensionId);

        report.setChapterList(chapterList);

        return report;
    }

    private List<ConvertTableDataDto> getAlreadySave(List<HcrpReportCdResult> resultSaveList, String chapterId, String secDimensionId) {

        for(HcrpReportCdResult result :resultSaveList ){
            if(result.getChapterId().equals(chapterId) && result.getDimensionId().equals(secDimensionId)){
                return JSON.parseArray(result.getChartData(), ConvertTableDataDto.class);
            }
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReportResult(ReportResultDto report) {

        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        /*保存关系*/
        String reportId = report.getReportId();
        Report rp = reportDao.selectByPrimaryKey(reportId);
        rp.setIsSaveResult("Y");

        List<ReportResultChapterDto> chapters = report.getChapters();

        List<HcrpReportCdResult> results = new ArrayList<>();
        for(ReportResultChapterDto chapter : chapters){
            String chartId = chapter.getId();
            List<ReportResultDimensionDto> dimensions = chapter.getDimensions();
            for(ReportResultDimensionDto dimension : dimensions){
                List<ReportResultDimensionDto> secDimensions = dimension.getSecDimensions();
                for(ReportResultDimensionDto secDim : secDimensions){
                    HcrpReportCdResult result = new HcrpReportCdResult();
                    result.setId(UUIDUtils.getUUId());
                    result.setCreator(loginUser.getId());
                    result.setDefaultValue();
                    result.setReportId(reportId);
                    result.setChapterId(chartId);
                    result.setDimensionId(secDim.getId());
                    result.setChartData(secDim.getChartData());
                    result.setChartBase64(secDim.getPic());
                    results.add(result);
                }
            }
        }

        /*删除旧关系*/
        hcrpReportCdResultDao.deleteByReportId(rp.getId());

        /*保存新关系*/
        reportDao.updateByPrimaryKeySelective(rp);
        hcrpReportCdResultDao.batchSave(results);
    }


    private boolean isNumeric(String string){

        return NUMBER_PATTERN.matcher(string).matches();
    }
}
