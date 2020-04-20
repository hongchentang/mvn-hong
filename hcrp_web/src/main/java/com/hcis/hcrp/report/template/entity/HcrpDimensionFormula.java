package com.hcis.hcrp.report.template.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

/**
 * @author z
 */
public class HcrpDimensionFormula extends BaseEntity {

    private String dimensionId;

    private String formulaKey;

    private String formulaVal;

    private String formulaHtml;

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId == null ? null : dimensionId.trim();
    }

    public String getFormulaKey() {
        return formulaKey;
    }

    public void setFormulaKey(String formulaKey) {
        this.formulaKey = formulaKey == null ? null : formulaKey.trim();
    }

    public String getFormulaVal() {
        return formulaVal;
    }

    public void setFormulaVal(String formulaVal) {
        this.formulaVal = formulaVal == null ? null : formulaVal.trim();
    }

    public String getFormulaHtml() {
        return formulaHtml;
    }

    public void setFormulaHtml(String formulaHtml) {
        this.formulaHtml = formulaHtml == null ? null : formulaHtml.trim();
    }

}