package com.hcis.hcrp.report.report.entity;

import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class Report extends BaseEntity {

    private String reportName;

    private String templateId;

    private String type;

    private String isSaveResult;

    /*非数据库字段*/

    private List<HcrpReportChapter> chapterList;

    private String firstSecDimensionId;

    private List<HcrpDimension> secDimensionList;

    public String getIsSaveResult() {
        return isSaveResult;
    }

    public void setIsSaveResult(String isSaveResult) {
        this.isSaveResult = isSaveResult;
    }

    public List<HcrpDimension> getSecDimensionList() {
        return secDimensionList;
    }

    public void setSecDimensionList(List<HcrpDimension> secDimensionList) {
        this.secDimensionList = secDimensionList;
    }

    public String getFirstSecDimensionId() {
        return firstSecDimensionId;
    }

    public void setFirstSecDimensionId(String firstSecDimensionId) {
        this.firstSecDimensionId = firstSecDimensionId;
    }

    public List<HcrpReportChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<HcrpReportChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}