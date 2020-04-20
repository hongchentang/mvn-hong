package com.hcis.hcrp.dimension.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

public class Dimension extends BaseEntity {
  /*  private String id;*/


    private String templateId;

    private String dimensionName;

    private String dimensionStair;

    private String dimensionSecond;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String reportId) {
        this.templateId = reportId == null ? null : reportId.trim();
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName == null ? null : dimensionName.trim();
    }

    public String getDimensionStair() {
        return dimensionStair;
    }

    public void setDimensionStair(String dimensionStair) {
        this.dimensionStair = dimensionStair == null ? null : dimensionStair.trim();
    }

    public String getDimensionSecond() {
        return dimensionSecond;
    }

    public void setDimensionSecond(String dimensionSecond) {
        this.dimensionSecond = dimensionSecond == null ? null : dimensionSecond.trim();
    }

}