package com.hcis.hcrp.report.report.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;

/**
 * @author z
 */
public class HcrpReportCdResult extends BaseEntity {

    private String reportId;

    private String chapterId;

    private String dimensionId;

    private String chartData;

    private String chartBase64;

    public String getChartData() {
        return chartData;
    }

    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    public String getChartBase64() {
        return chartBase64;
    }

    public void setChartBase64(String chartBase64) {
        this.chartBase64 = chartBase64;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId == null ? null : dimensionId.trim();
    }

}