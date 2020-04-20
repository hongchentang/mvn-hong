package com.hcis.hcrp.report.report.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * @author z
 */
public class ReportPatent extends BaseEntity {
    private String reportId;

    private String patentId;

    private String chapterId;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId == null ? null : patentId.trim();
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }
}