package com.hcis.hcrp.report.template.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class ReportTemplateChapter extends BaseEntity {

    private String chapterName;

    private String templateId;

    /* 非数据库字段 */

    /**
     * 章节的图表列表
     */
    private List<RtcTable> tableList;

    public List<RtcTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<RtcTable> tableList) {
        this.tableList = tableList;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

}