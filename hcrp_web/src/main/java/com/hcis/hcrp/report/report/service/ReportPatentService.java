package com.hcis.hcrp.report.report.service;

import com.hcis.hcrp.report.report.entity.ReportPatent;
import com.hcis.ipanther.core.service.IBaseService;


public interface ReportPatentService extends IBaseService<ReportPatent> {
    int insert(ReportPatent reportPatent);
}
