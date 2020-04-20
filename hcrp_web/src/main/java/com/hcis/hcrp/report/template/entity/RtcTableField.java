package com.hcis.hcrp.report.template.entity;

import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class RtcTableField extends BaseEntity {

    private String rtcTableId;

    private Integer tableType;

    private String field;

    private String fieldRelevance;

    private String fieldVal;

    private Integer fieldSort;

    private String key;

    private String keyRelevance;

    private String keyVal;

    private Integer keySort;

    private String fieldShowVal;

    private String keyShowVal;

    /*非数据库字段*/

    private List<RtcTableField> child;

    private List<TempPatent> patentDatas;

    public List<TempPatent> getPatentDatas() {
        return patentDatas;
    }

    public void setPatentDatas(List<TempPatent> patentDatas) {
        this.patentDatas = patentDatas;
    }

    public List<RtcTableField> getChild() {
        return child;
    }

    public void setChild(List<RtcTableField> child) {
        this.child = child;
    }

    public String getFieldShowVal() {
        return fieldShowVal;
    }

    public void setFieldShowVal(String fieldShowVal) {
        this.fieldShowVal = fieldShowVal;
    }

    public String getKeyShowVal() {
        return keyShowVal;
    }

    public void setKeyShowVal(String keyShowVal) {
        this.keyShowVal = keyShowVal;
    }

    public String getRtcTableId() {
        return rtcTableId;
    }

    public void setRtcTableId(String rtcTableId) {
        this.rtcTableId = rtcTableId == null ? null : rtcTableId.trim();
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getFieldRelevance() {
        return fieldRelevance;
    }

    public void setFieldRelevance(String fieldRelevance) {
        this.fieldRelevance = fieldRelevance == null ? null : fieldRelevance.trim();
    }

    public String getFieldVal() {
        return fieldVal;
    }

    public void setFieldVal(String fieldVal) {
        this.fieldVal = fieldVal == null ? null : fieldVal.trim();
    }

    public Integer getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(Integer fieldSort) {
        this.fieldSort = fieldSort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getKeyRelevance() {
        return keyRelevance;
    }

    public void setKeyRelevance(String keyRelevance) {
        this.keyRelevance = keyRelevance == null ? null : keyRelevance.trim();
    }

    public String getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(String keyVal) {
        this.keyVal = keyVal == null ? null : keyVal.trim();
    }

    public Integer getKeySort() {
        return keySort;
    }

    public void setKeySort(Integer keySort) {
        this.keySort = keySort;
    }
}