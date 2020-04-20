package com.hcis.hcrp.report.report.service.impl;

import com.hcis.hcrp.report.report.dao.HcrpReportChapterDao;
import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
import com.hcis.hcrp.report.report.service.HcrpReportChapterService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**@author  hct
 */
@Service
public class HcrpReportChapterServiceImpl extends BaseServiceImpl<HcrpReportChapter> implements HcrpReportChapterService {

    @Autowired
    private HcrpReportChapterDao hcrpReportChapterDao;

    @Override
    public MyBatisDao getBaseDao() {
        return null;
    }

    @Override
    public int insert(HcrpReportChapter hcrpReportChapter) {
        return hcrpReportChapterDao.insert(hcrpReportChapter);
    }
}
