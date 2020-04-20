package com.hcis.hcrp.report.template.dto;

import com.hcis.hcrp.report.template.entity.RtcTableField;

import java.util.List;

/**
 * @author zhw
 * @date 2020/3/6
 **/
public class RtcTableFiledGroupDto {

    private String field;

    private String fieldVal;

    private Integer fieldType;

    private List<RtcTableField> fields;

    public String getFieldVal() {
        return fieldVal;
    }

    public void setFieldVal(String fieldVal) {
        this.fieldVal = fieldVal;
    }

    public List<RtcTableField> getFields() {
        return fields;
    }

    public void setFields(List<RtcTableField> fields) {
        this.fields = fields;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }
}
