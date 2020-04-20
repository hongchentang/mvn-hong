package com.hcis.hcrp.report.template.service.impl;

import com.hcis.hcrp.patent.dao.TempPatentDao;
import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.patent.service.PatentService;
import com.hcis.hcrp.report.report.dao.ReportDao;
import com.hcis.hcrp.report.report.entity.Report;
import com.hcis.hcrp.report.report.enumeration.TableTypeEnum;
import com.hcis.hcrp.report.template.dao.*;
import com.hcis.hcrp.report.template.dto.DimensionStep4;
import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.dto.TableResultDto;
import com.hcis.hcrp.report.template.entity.*;
import com.hcis.hcrp.report.template.service.ReportTemplateService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.shiro.SecurityUtils;
import org.h2.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author zhw
 * @date 2020/3/6
 **/
@Service
public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate> implements ReportTemplateService {

    @Autowired
    private ReportTemplateDao reportTemplateDao;

    @Autowired
    private ReportTemplateChapterDao reportTemplateChapterDao;

    @Autowired
    private RtcTableDao rtcTableDao;

    @Autowired
    private RtcTableFieldDao rtcTableFieldDao;

    @Autowired
    private PatentService patentService;

    @Autowired
    private TempPatentDao tempPatentDao;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private HcrpDimensionDao hcrpDimensionDao;

    @Autowired
    private HcrpDimensionFormulaDao hcrpDimensionFormulaDao;

    @Override
    public MyBatisDao getBaseDao() {
        return reportTemplateDao;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTemplate(ReportTemplate template, String id) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //保存模板
        String templateId;
        boolean flag = false;
        if(StringUtils.isBlank(id)){
            templateId = UUIDUtils.getUUId();
        }else {
            templateId = id;
            flag = true;
        }

        String userId = loginUser.getId();
        template.setId(templateId);
        template.setDefaultValue();
        template.setCreator(userId);
        template.setUpdatedby(userId);

        if(flag){
            reportTemplateDao.updateByPrimaryKey(template);
        }else {
            reportTemplateDao.insert(template);
        }


        List<ReportTemplateChapter> list = template.getChapterList();
        //保存章节
        for(ReportTemplateChapter chapter : list){

            String chapterId = UUIDUtils.getUUId();
            chapter.setId(chapterId);
            chapter.setTemplateId(templateId);
            chapter.setDefaultValue();
            chapter.setCreator(userId);
            chapter.setUpdatedby(userId);
            reportTemplateChapterDao.insert(chapter);

            //保存图表
            List<RtcTable> listTable = chapter.getTableList();
            for(RtcTable table : listTable){
                String tableId = UUIDUtils.getUUId();
                table.setId(tableId);
                table.setDefaultValue();
                table.setCreator(userId);
                table.setUpdatedby(userId);

                rtcTableDao.insert(table);

                //保存字段
                List<RtcTableField> filedList = table.getFiledList();
                for(RtcTableField filed : filedList){
                    filed.setId(UUIDUtils.getUUId());
                    filed.setRtcTableId(tableId);
                    filed.setDefaultValue();
                    filed.setCreator(userId);
                    filed.setUpdatedby(userId);

                    rtcTableFieldDao.insert(filed);
                }
            }

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemplate(ReportTemplate template) {

        //删除下级关联
        String templateId = template.getId();
        deleteTemplateComponent(templateId);

        //重新保存,
        addTemplate(template, templateId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void templateDelete(String templateId) {

        //删除下级组件
        deleteTemplateComponent(templateId);

        //删除模板
        reportTemplateDao.deleteById(templateId);
    }

    @Override
    public ReportTemplate getById(String templateId) {

        //基础数据
        ReportTemplate reportTemplate = reportTemplateDao.selectByPrimaryKey(templateId);
        List<ReportTemplateChapter> chapterList = reportTemplateChapterDao.selectListByTemplateId(templateId);
        reportTemplate.setChapterList(chapterList);

        //下级数据
        Map<String, List<RtcTable>> tableMap = getTableMapByTemplateId(templateId);
        Map<String, List<RtcTableField>> fieldMap = getTableFieldByTemplate(templateId);
        for(ReportTemplateChapter chapter : reportTemplate.getChapterList()){

            List<RtcTable> tables = tableMap.get(chapter.getId());
            chapter.setTableList(tables);

            for(RtcTable table : tables){
                table.setFiledList(fieldMap.get(table.getId()));
            }
        }



        return reportTemplate;
    }

    @Override
    public List<RtcTableFiledGroupDto> templateTableList(String tableId, List<RtcTableField> list) {

        //
        if(CollectionUtils.isEmpty(list)){
            SearchParam searchParam = new SearchParam();
            searchParam.getParamMap().put("tableId", tableId);
            list = rtcTableFieldDao.selectBySearchParam(searchParam);
        }

        List<RtcTableFiledGroupDto> groupList = new ArrayList<>();

        for(RtcTableField field : list){

            String fieldKey = field.getField();
            String keyPhrase = field.getFieldRelevance();

            RtcTableFiledGroupDto dto = new RtcTableFiledGroupDto();
            dto.setField(fieldKey);
            dto.setFieldVal(keyPhrase);

            int idx = groupList.indexOf(dto);
            if(idx != -1){
                dto = groupList.get(idx);
            }else {
                groupList.add(dto);
            }

            List<RtcTableField> fieldList = dto.getFields();
            if(fieldList == null){
                fieldList = new ArrayList<>();
                dto.setFields(fieldList);
            }

            fieldList.add(field);
        }

        return groupList;
    }

    @Override
    public ReportTemplate templateData(String reportId) throws Exception {

        Report report = reportDao.selectByPrimaryKey(reportId);
        String templateId = report.getTemplateId();
        //查询数据
        Map<String, List<TempPatent>> dataMap = getTemplateChapterDataMap(reportId);

        //查询模板内容，
        ReportTemplate template = getById(templateId);

        //整理模板规格，查询模板的数据
        for(ReportTemplateChapter chapter : template.getChapterList()){
            List<TempPatent> dataList = dataMap.get(chapter.getId());
            for(RtcTable table : chapter.getTableList()){
                List<RtcTableFiledGroupDto> groupList = templateTableList( null, table.getFiledList());
                List<List<TableResultDto>> tableDataResult = new ArrayList<>();
                for(RtcTableFiledGroupDto group : groupList){
                    tableDataResult.add(patentService.getData(group, dataList, null));
                }

                //设置回去
                table.setDataResult(tableDataResult);
            }
        }


        return template;
    }

    @Override
    public int count(SearchParam searchParam) {
        searchParam.setPagination(null);
        return reportTemplateDao.count(searchParam);
    }

    @Override
    public ReportTemplateChapter getChapterById(String nextSecChapterId) {
        return reportTemplateChapterDao.selectByPrimaryKey(nextSecChapterId);
    }

    @Override
    public List<Map<String, Object>> getChapterDimensions(String id) {

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("dimensionName", "1.XX技术分析");
        map.put("dimensionHtml", "");
        map.put("id", "fdid001");


        List<Map<String, Object>> SecMaplist = new ArrayList<>();
        Map<String, Object> mapSec = new HashMap<>();
        mapSec.put("id", "sdid001");
        mapSec.put("dimensionName", "1.1XX分析");
        mapSec.put("dimensionHtml", "<p>文本<a style=\"background-color: yellow;\">内容1</a></p>");
        SecMaplist.add(mapSec);

        List<Map<String, Object>> formulaList = new ArrayList<>();
        Map<String, Object> formula1 = new HashMap<>();
        formula1.put("id", "fid001");
        formula1.put("formulaKey", "内容1");
        formula1.put("formulaVal", "001");
        formula1.put("formulaHtml", "<a style=\"background-color: yellow;\">内容1</a>");

        formulaList.add(formula1);
        mapSec.put("formulas", formulaList);
        map.put("dimensionSecs", SecMaplist);


        Map<String, Object> map1 = new HashMap<>();
        map1.put("dimensionName", "2.XX技术分析");
        map1.put("dimensionHtml", "");
        map1.put("id", "fdid002");

        List<Map<String, Object>> SecMaplist2 = new ArrayList<>();
        Map<String, Object> mapSec2 = new HashMap<>();
        mapSec2.put("id", "sdid002");
        mapSec2.put("dimensionName", "2.1XX分析");
        mapSec2.put("dimensionHtml", "<p>文本<a style=\"background-color: yellow;\">内容2</a></p>");
        SecMaplist2.add(mapSec2);

        List<Map<String, Object>> formulaList2 = new ArrayList<>();
        Map<String, Object> formula2 = new HashMap<>();
        formula2.put("id", "fid002");
        formula2.put("formulaKey", "内容2");
        formula2.put("formulaVal", "002");
        formula2.put("formulaHtml", "<a style=\"background-color: yellow;\">内容2</a>");

        formulaList2.add(formula2);
        mapSec2.put("formulas", formulaList2);
        map1.put("dimensionSecs", SecMaplist2);

        list.add(map);
        list.add(map1);

        return list;
    }

    @Override
    public DimensionStep4 getChapterDimensionsV2(String id) {

        DimensionStep4 dimensionStep4 = new DimensionStep4();
        List<HcrpDimension> allSecList = new ArrayList<>();

        List<HcrpDimension> all = hcrpDimensionDao.listByTemplateId(id);
        List<HcrpDimensionFormula> allFormula = hcrpDimensionFormulaDao.selectAllFormulaByTempId(id);

        List<HcrpDimension> resultList = new ArrayList<>();
        for(HcrpDimension dimension : all){
            if(dimension.getType() == 0){
                resultList.add(dimension);
            }else if(dimension.getType() == 1){
                List<HcrpDimensionFormula> secFormulaList = new ArrayList<>();
                allSecList.add(dimension);

                Integer tableType = dimension.getTableType();
                if(tableType != null){
                    TableTypeEnum tableTypeEnum = TableTypeEnum.valueOf(tableType);
                    dimension.setTableTypeName(tableTypeEnum.getName());
                    dimension.setTableTypeChartName(tableTypeEnum.getField());
                }

                for(HcrpDimensionFormula formula : allFormula){
                    if(formula.getDimensionId().equals(dimension.getId())){
                        secFormulaList.add(formula);
                    }
                }
                dimension.setSecFormulaList(secFormulaList);

                if(StringUtils.isBlank(dimensionStep4.getFirst())){
                    dimensionStep4.setFirst(dimension.getId());
                }
            }
        }

        for(HcrpDimension fDimension : resultList){
            List<HcrpDimension> list = new ArrayList<>();
            for(HcrpDimension dimension : all){
                if(dimension.getType() == 1 ){
                    String x = dimension.getDimensionStair();
                    String y = fDimension.getDimensionStair();
                    if(x.equals(y)){
                        list.add(dimension);
                    }
                }
            }
            fDimension.setSecList(list);
        }

        dimensionStep4.setList(resultList);
        dimensionStep4.setAllSecDimension(allSecList);
        return dimensionStep4;
    }

    @Override
    public List<HcrpDimension> listDimension(String tempId) {
        return hcrpDimensionDao.listByTemplateId(tempId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String copyTemplate(String tempId) {

        ReportTemplate template = reportTemplateDao.selectByPrimaryKey(tempId);
        List<HcrpDimension> templateDimensionList = hcrpDimensionDao.listByTemplateId(tempId);
        List<HcrpDimensionFormula> listFormula = hcrpDimensionFormulaDao.listFormula(tempId);

        String shadowTempId = UUIDUtils.getUUId();
        template.setId(shadowTempId);

        for(HcrpDimension dimension : templateDimensionList){

            String shadowDimensionId = UUIDUtils.getUUId();
            String dimensionId = dimension.getId();

            for(HcrpDimensionFormula formula : listFormula){
                if(formula.getDimensionId().equals(dimensionId)){
                    formula.setDimensionId(shadowDimensionId);
                    formula.setId(UUIDUtils.getUUId());
                }
            }

            dimension.setId(shadowDimensionId);
            dimension.setTemplateId(shadowTempId);
        }

        template.setIsDeleted("S");
        reportTemplateDao.insert(template);
        if(!CollectionUtils.isEmpty(templateDimensionList)){
            hcrpDimensionDao.insertBatch(templateDimensionList);
        }
        if(!CollectionUtils.isEmpty(listFormula)){
            hcrpDimensionFormulaDao.insertBatch(listFormula);
        }

        return shadowTempId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDimensions(String templateId, List<HcrpDimension> list) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<String> stairList = new ArrayList<>();
        List<HcrpDimension> stairDimensionList = new ArrayList<>();
        for(HcrpDimension dimension : list){
            int idx = list.indexOf(dimension);
            if("test".equals(dimension.getId())){
                dimension.setId(UUIDUtils.getUUId());
            }

            dimension.setDefaultValue();
            dimension.setTemplateId(templateId);
            dimension.setType(1);
            dimension.setSortOn(idx);
            dimension.setCreator(loginUser.getId());

            String stairName = dimension.getDimensionStair();
            if(!stairList.contains(stairName)){
                stairList.add(stairName);
                HcrpDimension stair = new HcrpDimension();
                stair.setId(UUIDUtils.getUUId());
                stair.setDimensionStair(stairName);
                stair.setTemplateId(templateId);
                stair.setType(0);
                stair.setDefaultValue();
                stair.setCreator(loginUser.getId());
                stair.setSortOn(stairList.size());
                stairDimensionList.add(stair);
            }
        }

        list.addAll(stairDimensionList);

        hcrpDimensionDao.deleteByTempId(templateId);
        hcrpDimensionDao.insertBatch(list);
    }

    @Override
    public void saveDimensionFormulas(String templateId, List<HcrpDimension> list) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<HcrpDimensionFormula> formulas = new ArrayList<>();

        /*处理数据*/
        for(HcrpDimension dimension : list){
            String dimensionId = UUIDUtils.getUUId();
            dimension.setId(dimensionId);
            dimension.setTemplateId(templateId);
            dimension.setType(1);
            dimension.setCreator(loginUser.getId());
            dimension.setDefaultValue();
            dimension.setSortOn(list.indexOf(dimension));
            List<HcrpDimensionFormula> childFormula = dimension.getSecFormulaList();
            if(!CollectionUtils.isEmpty(childFormula)){
                for(HcrpDimensionFormula formula : childFormula){
                    formula.setId(UUIDUtils.getUUId());
                    formula.setDimensionId(dimensionId);
                    formula.setDefaultValue();
                    formula.setCreator(loginUser.getId());
                }
                formulas.addAll(childFormula);
            }
        }

        hcrpDimensionDao.deleteSecByTempId(templateId);
        hcrpDimensionFormulaDao.deleteByTempId(templateId);
        hcrpDimensionDao.insertBatch(list);
        hcrpDimensionFormulaDao.insertBatch(formulas);
    }

    @Override
    public void endEdit(Integer model, Map map) {

        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        String tempId = (String) map.get("tempId");
        String shadowTempId = (String) map.get("shadowTempId");
        String templateSynopsis = (String) map.get("templateSynopsis");
        String templateDimension = (String) map.get("templateDimension");
        String templateTableType = (String) map.get("templateTableType");

        ReportTemplate template = reportTemplateDao.selectByPrimaryKey(tempId);
        ReportTemplate shadowTemplate = reportTemplateDao.selectByPrimaryKey(shadowTempId);

        shadowTemplate.setReportSynopsis(templateSynopsis);
        shadowTemplate.setReportAnalysisDimension(templateDimension);
        shadowTemplate.setReportTableType(templateTableType);

        SearchParam searchParam = new SearchParam();
        if(model == 0){
            //保存
            //删除原本的模板以及下属数据
            reportTemplateDao.deleteById(tempId);
            hcrpDimensionDao.deleteByTempId(tempId);
            hcrpDimensionFormulaDao.deleteByTempId(tempId);

            //把影子模板的id改成原来的id,并修改
            shadowTemplate.setId(template.getId());
            shadowTemplate.setReportName(template.getReportName());
            shadowTemplate.setIsDeleted("N");
            shadowTemplate.setShadowTempId(shadowTempId);
            shadowTemplate.setUpdatedby(loginUser.getId());
            shadowTemplate.setUpdateTime(new Date());

            reportTemplateDao.updateByShadowIdSelective(shadowTemplate);
            searchParam.getParamMap().put("tempId", tempId);
            searchParam.getParamMap().put("shadowTempId", shadowTempId);
            hcrpDimensionDao.updateDimensionTemp(searchParam);
        }else if(model == 1){
            //发布
            int max = reportTemplateDao.count(searchParam) + 1;
            shadowTemplate.setReportName("模板" + max);
            shadowTemplate.setIsDeleted("N");
            shadowTemplate.setCreateTime(new Date());
            shadowTemplate.setCreator(loginUser.getId());
            reportTemplateDao.updateByPrimaryKeySelective(shadowTemplate);
        }

    }

    private Map<String, List<TempPatent>> getTemplateChapterDataMap(String templateId){

        Map<String, List<TempPatent>> map = new HashMap<>();

        List<TempPatent> list = tempPatentDao.reportData(templateId);

        for(TempPatent patent : list){
            String chapterId = patent.getChapterId();

            List<TempPatent> patents = map.get(chapterId);
            if(patents == null){
                patents = new ArrayList<>();
                map.put(chapterId, patents);
            }
            patents.add(patent);
        }

        return map;
    }

    private Map<String, List<RtcTableField>> getTableFieldByTemplate(String templateId) {

        Map<String, List<RtcTableField>> map = new HashMap<>();

        List<RtcTableField> fieldList = rtcTableFieldDao.selectListByTemplateId(templateId);

        for(RtcTableField field : fieldList){

            String tableId = field.getRtcTableId();

            List<RtcTableField> list = map.get(tableId);
            if(list == null){
                list = new ArrayList<>();
                map.put(tableId, list);
            }
            list.add(field);
        }


        return map;
    }

    private Map<String, List<RtcTable>> getTableMapByTemplateId(String reportId) {

        Map<String, List<RtcTable>> map = new HashMap<>();

        List<RtcTable> tableList = rtcTableDao.selectListByTemplateId(reportId);

        for(RtcTable table : tableList){

           /* List<RtcTable> list = map.get(chapterId);
            if(list == null){
                list = new ArrayList<>();
                map.put(chapterId, list);
            }*/
            /*list.add(table);*/
        }

        return map;
    }

    private void deleteTemplateComponent(String templateId){
        List<String> fieldIds = rtcTableFieldDao.getIdsByTemplateId(templateId);
        List<String> tableIds = rtcTableDao.getIdsByTemplateId(templateId);

        //删除下级关联
        reportTemplateChapterDao.deleteByTemplateId(templateId);

        rtcTableDao.deleteByIds(tableIds);

        rtcTableFieldDao.deleteByIds(fieldIds);
    }
}
