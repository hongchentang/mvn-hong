package com.hcis.hcrp.report.report.service;

import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
import com.hcis.ipanther.core.service.IBaseService;

public interface HcrpReportChapterService extends IBaseService<HcrpReportChapter> {
    int insert(HcrpReportChapter hcrpReportChapter);
}
