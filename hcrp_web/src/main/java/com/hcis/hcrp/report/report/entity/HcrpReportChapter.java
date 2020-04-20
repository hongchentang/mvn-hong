package com.hcis.hcrp.report.report.entity;

import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */

public class HcrpReportChapter extends BaseEntity {

    private String chapterName;

    private String reportId;

    private Integer chapterType;

    /*非数据库字段*/


    private List<HcrpDimension> dimensionList;


    public Integer getChapterType() {
        return chapterType;
    }

    public void setChapterType(Integer chapterType) {
        this.chapterType = chapterType;
    }

    public List<HcrpDimension> getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(List<HcrpDimension> dimensionList) {
        this.dimensionList = dimensionList;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }
}