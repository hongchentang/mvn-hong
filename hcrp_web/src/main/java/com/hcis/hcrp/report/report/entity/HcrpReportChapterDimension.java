package com.hcis.hcrp.report.report.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

/**
 * @author z
 */
public class HcrpReportChapterDimension extends BaseEntity {

    private String chapterId;

    private String dimensionStair;

    private String dimensionSecond;

    private String dimensionHtml;



    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
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

    public String getDimensionHtml() {
        return dimensionHtml;
    }

    public void setDimensionHtml(String dimensionHtml) {
        this.dimensionHtml = dimensionHtml == null ? null : dimensionHtml.trim();
    }
}