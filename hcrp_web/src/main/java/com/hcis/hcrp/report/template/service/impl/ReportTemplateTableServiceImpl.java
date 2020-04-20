package com.hcis.hcrp.report.template.service.impl;

import com.alibaba.fastjson.JSON;
import com.hcis.hcrp.patent.dao.TempPatentDao;
import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.patent.service.PatentService;
import com.hcis.hcrp.report.report.dao.ReportDao;
import com.hcis.hcrp.report.report.entity.Report;
import com.hcis.hcrp.report.template.dao.ReportTemplateChapterDao;
import com.hcis.hcrp.report.template.dao.ReportTemplateDao;
import com.hcis.hcrp.report.template.dao.RtcTableDao;
import com.hcis.hcrp.report.template.dao.RtcTableFieldDao;
import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.dto.TableResultDto;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.hcrp.report.template.entity.ReportTemplateChapter;
import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.hcrp.report.template.service.ReportTemplateService;
import com.hcis.hcrp.report.template.service.ReportTemplateTableService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import net.sf.json.util.JSONUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * @author zhw
 * @date 2020/3/6
 **/
@Service
public class ReportTemplateTableServiceImpl extends BaseServiceImpl<RtcTable> implements ReportTemplateTableService {

    @Autowired
    private RtcTableDao rtcTableDao;

    @Autowired
    private RtcTableFieldDao rtcTableFieldDao;

    @Override
    public MyBatisDao getBaseDao() {
        return rtcTableDao;
    }

    @Override
    public List<RtcTableField> listFieldByTableId(String id) {
        return rtcTableFieldDao.listFieldByTableId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTable(Map<String, Object> queryMap) {

        List<Map> list = JSON.parseArray((String)queryMap.get("list"), Map.class);
        String id = (String) queryMap.get("id");
        Integer tableType = (Integer) queryMap.get("type");
        String name = (String) queryMap.get("name");
        //验证信息
        if(CollectionUtils.isEmpty(list)){
            throw new IllegalArgumentException("请先编辑表格内容");
        }
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("请填入名称");
        }
        if(tableType == null){
            throw new IllegalArgumentException("请选择类型");
        }

        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        /*表信息*/
        RtcTable table;
        if(StringUtils.isNotBlank(id)){
            //修改
            table = rtcTableDao.selectByPrimaryKey(id);
            table.setTableName(name);
            table.setTableType(tableType);
            table.setUpdatedby(user.getId());
            table.setUpdateTime(new Date());

            rtcTableFieldDao.deleteByTableId(id);
            rtcTableDao.updateByPrimaryKeySelective(table);
        }else {
            //新建
            id = UUIDUtils.getUUId();
            table = new RtcTable();
            table.setId(id);
            table.setTableName(name);
            table.setTableType(tableType);
            table.setCreator(user.getId());
            table.setDefaultValue();

            rtcTableDao.insert(table);
        }


        /*字段信息*/
        List<Map<String, Object>> listTables = new ArrayList<>();
        List<Map<String, Object>> listFields = new ArrayList<>();

        for(Map<String, Object> map : list){

            /*校验*/
            int type = (Integer) map.get("fieldType");
            int sort = (Integer) map.get("sort");
            Set<String> set = map.keySet();
            for(String key : set){
                if(map.get(key) instanceof String){
                    String val = (String) map.get(key);
                    if(StringUtils.isBlank(val)){
                        if(type == 0){
                            throw new IllegalArgumentException("行" + sort + "存在空值,请检查");
                        }else if(type == 1){
                            throw new IllegalArgumentException("列" + sort + "存在空值,请检查");
                        }else {
                            throw new IllegalArgumentException("表类型错误");
                        }
                    }
                }
            }

            /*放进集合*/
            if(type == 0){
                listTables.add(map);
            }else if(type == 1){
                listFields.add(map);
            }
        }

        List<RtcTableField> saveList = new ArrayList<>();

        for(Map<String, Object> tableMap : listTables){
            String relevance = (String) tableMap.get("relevance");
            String selectedField = (String) tableMap.get("selectedField");
            String fieldVal = (String) tableMap.get("fieldVal");
            String showVal = (String) tableMap.get("showVal");
            int idxTable = listTables.indexOf(tableMap);
            for(Map<String, Object> fieldMap : listFields){

                int idxField = listFields.indexOf(fieldMap);

                String fRelevance = (String) fieldMap.get("relevance");
                String fSelectedField = (String) fieldMap.get("selectedField");
                String fFieldVal = (String) fieldMap.get("fieldVal");
                String fShowVal = (String) fieldMap.get("showVal");

                RtcTableField field = new RtcTableField();
                field.setRtcTableId(id);
                field.setId(UUIDUtils.getUUId());
                field.setField(selectedField);
                field.setFieldRelevance(relevance);
                field.setFieldShowVal(showVal);
                field.setFieldVal(fieldVal);
                field.setFieldSort(idxTable);

                field.setKey(fSelectedField);
                field.setKeyRelevance(fRelevance);
                field.setKeyShowVal(fShowVal);
                field.setKeyVal(fFieldVal);
                field.setKeySort(idxField);

                field.setDefaultValue();
                rtcTableFieldDao.insert(field);
                saveList.add(field);
            }
        }

        //rtcTableFieldDao.insertBatch(saveList);
    }

    private String convertJsonStr(List<RtcTableField> list){

        List<Map<String, Object>> trList = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        Set<String> tdKeys = new HashSet<>();
        for(RtcTableField field : list){

            String selectField =  field.getField();
            Integer fieldSort = field.getFieldSort();
            String fieldShowVal = field.getFieldShowVal();
            String fieldVal = field.getFieldVal();
            String fieldRelevance = field.getFieldRelevance();

            String uniqueKey = selectField + fieldRelevance + fieldVal;
            if(!keys.contains(uniqueKey)){
                keys.add(uniqueKey);
                Map<String, Object> map = new HashMap<>();
                map.put("selectedField", selectField);
                map.put("showVal", fieldShowVal);
                map.put("fieldVal", fieldVal);
                map.put("sort", fieldSort);
                map.put("relevance", fieldRelevance);
                map.put("fieldType", 0);
                trList.add(map);
            }

            String fSelectField =  field.getKey();
            Integer fFieldSort = field.getKeySort();
            String fFieldShowVal = field.getKeyShowVal();
            String fFieldVal = field.getKeyVal();
            String fFieldRelevance = field.getKeyRelevance();

            String uniqueTdKey = fSelectField + fFieldRelevance + fFieldVal;
            if(!tdKeys.contains(uniqueTdKey)){
                tdKeys.add(uniqueTdKey);
                Map<String, Object> map = new HashMap<>();
                map.put("selectedField", fSelectField);
                map.put("showVal", fFieldShowVal);
                map.put("fieldVal", fFieldVal);
                map.put("sort", fFieldSort);
                map.put("relevance", fFieldRelevance);
                map.put("fieldType", 1);
                trList.add(map);
            }
        }

        if(CollectionUtils.isEmpty(trList)){
            return "";
        }else {
            return JSON.toJSONString(trList);
        }
    }

    @Override
    public String getByTableId(String tableId){
        SearchParam searchParam = new SearchParam();
        searchParam.getParamMap().put("tableId", tableId);
        List<RtcTableField> fieldList = rtcTableFieldDao.selectBySearchParam(searchParam);
        return convertJsonStr(fieldList);
    }

    @Override
    public Integer count(SearchParam searchParam) {
        return rtcTableDao.count(searchParam);
    }

}
