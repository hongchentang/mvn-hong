package com.hcis.hcrp.report.template.dto;

import java.util.List;

/**
 * @author zhw
 * @date 2020/4/1
 **/
public class ReportResultDto {

    private String reportId;

    private String reportName;

    private List<ReportResultChapterDto> chapters;

    private List<ReportResultDimensionDto> secDimensionList;

    private ReportResultPublicInfo pubInfo = new ReportResultPublicInfo();

    public List<ReportResultDimensionDto> getSecDimensionList() {
        return secDimensionList;
    }

    public void setSecDimensionList(List<ReportResultDimensionDto> secDimensionList) {
        this.secDimensionList = secDimensionList;
    }

    public ReportResultPublicInfo getPubInfo() {
        return pubInfo;
    }

    public void setPubInfo(ReportResultPublicInfo pubInfo) {
        this.pubInfo = pubInfo;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<ReportResultChapterDto> getChapters() {
        return chapters;
    }

    public void setChapters(List<ReportResultChapterDto> chapters) {
        this.chapters = chapters;
    }
}
