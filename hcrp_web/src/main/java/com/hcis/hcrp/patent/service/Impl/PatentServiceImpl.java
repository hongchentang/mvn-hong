package com.hcis.hcrp.patent.service.Impl;

import com.alibaba.fastjson.JSON;
import com.hcis.hcrp.patent.dao.TempPatentDao;
import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.patent.excelModel.PatentModel;
import com.hcis.hcrp.patent.service.PatentService;
import com.hcis.hcrp.report.report.entity.ReportPatent;
import com.hcis.hcrp.report.report.enumeration.PatentFieldEnum;
import com.hcis.hcrp.report.report.enumeration.RelevanceEnum;
import com.hcis.hcrp.report.report.enumeration.TemplateType;
import com.hcis.hcrp.report.report.service.ReportPatentService;
import com.hcis.hcrp.report.template.dao.RtcTableDao;
import com.hcis.hcrp.report.template.dao.RtcTableFieldDao;
import com.hcis.hcrp.report.template.dto.ConvertTableDataDto;
import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.dto.TableResultDto;
import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.ipanther.common.excel.convert.ExcelTOModelService;
import com.hcis.ipanther.common.excel.model.ImportFailModel;
import com.hcis.ipanther.common.user.excelmodel.EmployeeModel;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author z
 */
@Service
public class PatentServiceImpl extends BaseServiceImpl<TempPatent> implements PatentService {

    private final static String SORT_TOP = "top";

    private final static String ALL_DATA = "ALL";

    private final static String OTHER_DATA = "OTHER";

    @Autowired
    private ExcelTOModelService excelToModelService;

    @Autowired
    private TempPatentDao tempPatentDao;

    @Autowired
    private RtcTableDao rtcTableDao;

    @Autowired
    private RtcTableFieldDao rtcTableFieldDao;
    @Autowired
    private ReportPatentService reportPatentService;

    @Override
    public MyBatisDao getBaseDao() {
        return null;
    }

    @Override
    public String importPatent(MultipartFile file, String chapterId, String reportId) throws Exception {

        InputStream in = file.getInputStream();
        //execelIS为InputStream流
/*      byte[] buf = org.apache.commons.io.IOUtils.toByteArray(in);

        //在需要用到InputStream的地方再封装成InputStream
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);*/


        String modelName = "patentModel";
        //得到工作表
        //校验失败的
        HSSFWorkbook book = new HSSFWorkbook(in);
        List<ImportFailModel> failList = new ArrayList<ImportFailModel>();
        List<EmployeeModel> successList = new ArrayList<EmployeeModel>();
        HSSFSheet sheet = book.getSheetAt(0);

        List<Object> list = excelToModelService.getModelList(book, modelName, failList);

        List<TempPatent> patents = new ArrayList<>();
        List<String> appNumberList = new ArrayList<>();
        for(Object obj : list){

            PatentModel patentModel = (PatentModel) obj;

            String patentId = Identities.uuid2();

            TempPatent patent = new TempPatent();

            HSSFRow row = sheet.getRow(patentModel.getRownum());
            //用于保存导入结果的单元格
            HSSFCell resultCell = row.createCell((short) (row.getPhysicalNumberOfCells()));
            //结果描述
            HSSFRichTextString rts = null;

            BeanUtils.copyProperties(patentModel, patent);

            patent.setId(patentId);

            patent.setDefaultValue();


            appNumberList.add(patent.getAppNumber());
            patents.add(patent);
        }

        //查重，保存
        List<TempPatent> alreadySaveList = tempPatentDao.queryByAppNumbers(appNumberList);

    /*    patents.removeAll(alreadySaveList);*/
        int x =  tempPatentDao.batchInsert(patents);

        //保存关系
        List<ReportPatent> reportPatentList = new ArrayList<>();
/*        for(TempPatent patent : alreadySaveList){
            ReportPatent reportPatent = new ReportPatent();
            reportPatent.setPatentId(patent.getId());
            reportPatent.setReportId(reportId);
            reportPatent.setChapterId(chapterId);
            reportPatentService.insert(reportPatent);
        }*/
        for(TempPatent patent :patents){
            ReportPatent reportPatent = new ReportPatent();
            reportPatent.setPatentId(patent.getId());
            reportPatent.setReportId(reportId);
            reportPatent.setChapterId(chapterId);
            reportPatentService.insert(reportPatent);
        }



        /*       tempPatentDao.saveReportPatents(reportPatentList);*/

        return null;
    }

    @Override
    public Object getData(Map<String, Object> map) throws Exception {

        map.put("max-application-country", "");
        List<TempPatent> list = tempPatentDao.selectChapterData("");
        List<TempPatent> sortList = new ArrayList<>();

        Set<String> stringSet = map.keySet();
        for(String key : stringSet){

            if(key.contains("max") || key.contains("min")){
                String filedStr = "";
                Integer sortDesc = 1;
                if(key.contains("max")){
                    filedStr = key.replace("max", "");
                    sortDesc = -1;
                }else if (key.contains("min")){
                    filedStr = key.replace("min", "");
                }
                String[] fileds = filedStr.split("-");
                List<String> filedList = new ArrayList<>();
                for(String f : fileds){
                    if(StringUtils.isNotEmpty(f)){
                        filedList.add(f);
                    }
                }

                //行的数据的字段
                String filedQuery = filedList.get(1);
                //整理的字段
                String filedSort = filedList.get(0);

                //统计要排序的字段
                Map<String, Integer> statMap = new HashMap<>();
                for(TempPatent p : list){
                    String value = p.getValueByFile(filedQuery).toString();
                    Integer sum = statMap.get(value);
                    if(sum == null || sum == 0){
                        sum = 1;
                    }else {
                        sum += 1;
                    }
                    statMap.put(value, sum);
                }

                Set<String> keys = statMap.keySet();
                for(String k : keys){
                    TempPatent p = new TempPatent();
                    p.setValueByFile(filedQuery, k);
                    p.setSumSor(statMap.get(k));
                    sortList.add(p);
                }



                //进行排序，取出需要的数据
                final Integer finalSortDesc = sortDesc;
                Collections.sort(sortList, new Comparator<TempPatent>() {
                    @Override
                    public int compare(TempPatent o1, TempPatent o2) {
                        try {
                            return (o1.getSumSor() - o2.getSumSor()) * finalSortDesc;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

            }else if(key.contains("percentage")){

                final String file = key.replace("percentage", "");
                Collections.sort(list, new Comparator<TempPatent>() {
                    @Override
                    public int compare(TempPatent o1, TempPatent o2) {
                        return 0;
                    }
                });

            }



        }

        return sortList;
    }

    @Override
    public List<TableResultDto> getData(RtcTableFiledGroupDto filedGroup, List<TempPatent> tableData, List<TableResultDto> firstSortList) throws Exception {

        String filed = filedGroup.getField();
        List<RtcTableField> fieldList = filedGroup.getFields();
        List<TableResultDto> resultList = new ArrayList<>();

        Set<String> fieldValSet = new HashSet<>();
        List<String> fieldSortList = new ArrayList<>();
        for(RtcTableField field : fieldList){
            String keyVal = field.getKeyVal();
            if(StringUtils.isNotBlank(keyVal) && keyVal.contains(SORT_TOP)){

                fieldSortList.add(keyVal);
            } else if (StringUtils.isNotBlank(keyVal)){

                fieldValSet.add(keyVal);
            }
        }

        //统计数据
        int sumData = 0;
        filed = PatentFieldEnum.valueOfV2(filed).getField();
        for(TempPatent patent : tableData){
            TableResultDto result = new TableResultDto();
            String patentVal = patent.getValueByFile(filed).toString();

            /*设置key*/
            if(CollectionUtils.isEmpty(fieldValSet)){
                result.setKey(patentVal);
            }else {
                setAlreadySaveKey(result, fieldList, patentVal);
            }


            int idx = resultList.indexOf(result);
            if(idx != -1){
                //存在
                result = resultList.get(idx);
                sumData++;
            }else {
                //不存在
                if(CollectionUtils.isEmpty(fieldValSet)){
                    //全部数据
                    resultList.add(result);
                    sumData++;
                }else {
                    //部分数据
                    if(isContainKeys(fieldList, patentVal)){
                        resultList.add(result);
                        sumData++;
                    }
                }
            }

            int sum = result.getSum() == null ? 0 : result.getSum();
            result.setSum(++sum);
        }

        //排序
        if(!CollectionUtils.isEmpty(fieldSortList) && !CollectionUtils.isEmpty(firstSortList)){
            sumData = 0;
            List<TableResultDto> alreadySortList = new ArrayList<>();
            for(TableResultDto dto : firstSortList ){
                for(TableResultDto reDto : resultList){
                    if(dto.getKey().equals(reDto.getKey())){
                        alreadySortList.add(reDto);
                        sumData = sumData + reDto.getSum();
                    }
                }
            }
            resultList = alreadySortList;
        }else {
            Collections.sort(resultList, new Comparator<TableResultDto>() {
                @Override
                public int compare(TableResultDto o1, TableResultDto o2) {
                    return o2.getSum() - o1.getSum();
                }
            });
        }


        if(!CollectionUtils.isEmpty(fieldSortList) && CollectionUtils.isEmpty(firstSortList)){
            sumData = 0;
            List<TableResultDto> sortResult = new ArrayList<>();
            for(String field: fieldSortList){
                try {
                    int idx = Integer.parseInt(field.replace(SORT_TOP, "")) - 1;
                    sortResult.add(resultList.get(idx));
                    sumData = sumData + resultList.get(idx).getSum();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            /*返回排序的值*/
            resultList = sortResult;
        }

        //统计百分比
        BigDecimal sd = new BigDecimal(sumData);
        BigDecimal hd = new BigDecimal(100);
        BigDecimal rd ;
        for(TableResultDto result : resultList){

            rd = new BigDecimal(result.getSum());
            result.setPercentage(rd.divide(sd, 4, BigDecimal.ROUND_HALF_UP).multiply(hd));
        }

        //返回值
         return resultList;
    }

    private void setAlreadySaveKey(TableResultDto result, List<RtcTableField> fieldList, String patentVal) {
        for(RtcTableField field : fieldList){
            String keyRelevance = field.getKeyRelevance();
            String keyVal = field.getKeyVal();
            if(isContain(patentVal, keyVal, keyRelevance)){
                result.setKey(keyVal);
            }
        }
    }

    private boolean isContainKeys(List<RtcTableField> fieldList, String patentVal) {

        for(RtcTableField field : fieldList){
            String keyRelevance = field.getKeyRelevance();
            String keyVal = field.getKeyVal();
            if(isContain(patentVal, keyVal, keyRelevance)){
                return true;
            }
        }

        return false;
    }

    @Override
    public void testData(){
        /*{
        List<TempPatent> list = tempPatentDao.selectProjectData();

        RtcTableFiledGroupDto dto = new RtcTableFiledGroupDto();
        dto.setField("country");
        dto.setFieldType(0);

        RtcTableField field = new RtcTableField();
        field.setField("country");
        field.setSortNo(0);

        RtcTableField field1 = new RtcTableField();
        field1.setField("country");
        field1.setSortNo(3);

        RtcTableField field2 = new RtcTableField();
        field2.setField("country");
        field2.setSortNo(7);

        List<RtcTableField> fieldList = new ArrayList<>();
        fieldList.add(field);
        fieldList.add(field1);
        fieldList.add(field2);
        dto.setFields(fieldList);

        try {
            logger.debug("------->" + JSONUtils.getJSONString(getData(dto, list)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPatent(TempPatent tempPatent) {
        String tempPatentId = UUIDUtils.getUUId();
        tempPatent.setId(tempPatentId);
        tempPatent.setDefaultValue();
        tempPatentDao.insert(tempPatent);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePatent(TempPatent tempPatent) {
        tempPatent.setUpdateTime(new Date());
        tempPatentDao.updateByPrimaryKey(tempPatent);

    }

    @Override
    public void deleteByIds(List<String> patentIds) {

        tempPatentDao.deleteByPrimaryKeys(patentIds);

    }

    @Override
    public List<TempPatent> patentList(SearchParam searchParam) {
        return tempPatentDao.selectBySearchParam(searchParam);    }

    @Override
    public TempPatent getPatentById(String id) {
        TempPatent tempPatent = tempPatentDao.selectByPrimaryKey(id);
        return tempPatent;
    }

    @Override
    public List<ConvertTableDataDto> convertTableData(String tableId, String chapterId) throws Exception {

        //返回的结果
        List<ConvertTableDataDto> resultList = new ArrayList<>();

        //临时数据
        List<TempPatent> dataList = tempPatentDao.selectChapterData(chapterId);
        //图表规则
        List<RtcTableField> allFields = rtcTableFieldDao.selectByTableId(tableId);

        //整理出所有的子表格
        Map<Integer, RtcTableField> trMap = convertTrMap(allFields);

        Set<Integer> set = trMap.keySet();

        //处理子表格
        List<TempPatent> exitList = new ArrayList<>();
        List<TableResultDto> firstSortList = null;
        for(Integer key : set){

            RtcTableField field = trMap.get(key);

            String fieldStr = field.getField();
            String showVal = field.getFieldShowVal();
            String fieldRelevance = field.getFieldRelevance();
            String fieldVal = field.getFieldVal();
            String patentField = PatentFieldEnum.valueOfV2(fieldStr).getField();

            //转换出当前表格的专利数据
            List<TempPatent> thisData = thisTablePatent(fieldVal, patentField, fieldRelevance, dataList, exitList);

            RtcTableFiledGroupDto minTable = new RtcTableFiledGroupDto();
            minTable.setField(field.getKey());
            minTable.setFields(field.getChild());
            minTable.setFieldType(field.getTableType());

            //增加返回结果
            List<TableResultDto> tableList = getData(minTable, thisData, firstSortList);
            //第一个的排序
            if(CollectionUtils.isEmpty(resultList)){
                firstSortList = tableList;
            }
            ConvertTableDataDto dataDto = new ConvertTableDataDto();
            dataDto.setTableTrName(showVal);
            dataDto.setResultDtoList(tableList);
            resultList.add(dataDto);
        }

        return resultList;
    }

    private Map<Integer, RtcTableField> convertTrMap(List<RtcTableField> allFields){

        Map<Integer, RtcTableField> trMap = new HashMap<>();
        for(RtcTableField field : allFields){

            Integer fieldSort = field.getFieldSort();
            RtcTableField tr = trMap.get(fieldSort);
            if(tr == null){
                trMap.put(fieldSort, field);
            }

            List<RtcTableField> child = new ArrayList<>();
            for(RtcTableField yield : allFields){
                if(yield.getFieldSort().equals(fieldSort)){
                    child.add(yield);
                }
            }
            field.setChild(child);
        }

        return trMap;
    }

    private List<TempPatent> thisTablePatent(String fieldVal, String patentField, String fieldRelevance, List<TempPatent> dataList, List<TempPatent> exitList) throws Exception {

        List<TempPatent> thisData = new ArrayList<>();

        if(ALL_DATA.equals(fieldVal)){
            thisData = dataList;
        }else if(OTHER_DATA.equals(fieldVal)){
            dataList.removeAll(exitList);
            thisData = dataList;
        }else{
            for(TempPatent patent : dataList){
                String value = (String) patent.getValueByFile(patentField);

                if(isContain(value, fieldVal, fieldRelevance)){
                    thisData.add(patent);
                }
            }
            exitList.addAll(thisData);
        }
        return thisData;
    }

    private boolean isContain(String patentVal, String fieldVal, String fieldRelevance){

        if(RelevanceEnum.EQ.getVal().equals(fieldRelevance)){
            return patentVal.contains(fieldVal);
        }else if(RelevanceEnum.NOT_EQ.getVal().equals(fieldRelevance)){
            return !patentVal.contains(fieldVal);
        }else if(RelevanceEnum.IN.getVal().equals(fieldRelevance)){
            if(StringUtils.isNotBlank(fieldVal)){
                String[] keys = fieldVal.split(",");
                for(String key : keys){
                    if(patentVal.contains(key)){
                        return true;
                    }
                }
            }
        }else if(RelevanceEnum.NOT_IN.getVal().equals(fieldRelevance)){
            if(StringUtils.isNotBlank(fieldVal)){
                String[] keys = fieldVal.split(",");
                for(String key : keys){
                    if(patentVal.contains(key)){
                        return false;
                    }
                }
                return true;
            }
        }

        return false;



    }
}
