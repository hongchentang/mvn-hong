package com.hcis.hcrp.report.template.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class ReportTemplate extends BaseEntity {

    private String reportName;

    private String reportHtml;

    private String reportSynopsis;

    private String reportAnalysisDimension;

    private String reportTableType;

    private Integer tempType;

    /*  非数据库字段 */

    /**
     * 章节列表
     */
    private List<ReportTemplateChapter> chapterList;

    private String templateTypeName;

    private String shadowTempId;

    public String getShadowTempId() {
        return shadowTempId;
    }

    public void setShadowTempId(String shadowTempId) {
        this.shadowTempId = shadowTempId;
    }

    public Integer getTempType() {
        return tempType;
    }

    public void setTempType(Integer tempType) {
        this.tempType = tempType;
    }

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }

    public String getReportSynopsis() {
        return reportSynopsis;
    }

    public void setReportSynopsis(String reportSynopsis) {
        this.reportSynopsis = reportSynopsis;
    }

    public String getReportAnalysisDimension() {
        return reportAnalysisDimension;
    }

    public void setReportAnalysisDimension(String reportAnalysisDimension) {
        this.reportAnalysisDimension = reportAnalysisDimension;
    }

    public String getReportTableType() {
        return reportTableType;
    }

    public void setReportTableType(String reportTableType) {
        this.reportTableType = reportTableType;
    }

    public List<ReportTemplateChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ReportTemplateChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getReportHtml() {
        return reportHtml;
    }

    public void setReportHtml(String reportHtml) {
        this.reportHtml = reportHtml == null ? null : reportHtml.trim();
    }
}