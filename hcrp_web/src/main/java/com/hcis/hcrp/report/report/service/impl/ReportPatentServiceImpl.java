package com.hcis.hcrp.report.report.service.impl;

import com.hcis.hcrp.report.report.dao.ReportPatentDao;
import com.hcis.hcrp.report.report.entity.ReportPatent;
import com.hcis.hcrp.report.report.service.ReportPatentService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*@Author hct
 */
@Service
public class ReportPatentServiceImpl extends BaseServiceImpl<ReportPatent> implements ReportPatentService {
    @Override
    public MyBatisDao getBaseDao() {
        return null;
    }
    @Autowired
    private ReportPatentDao reportPatentDao;

    @Override
    public int insert(ReportPatent reportPatent) {
        return reportPatentDao.insert(reportPatent);
    }
}
