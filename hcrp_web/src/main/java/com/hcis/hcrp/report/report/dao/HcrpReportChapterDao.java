package com.hcis.hcrp.report.report.dao;

import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author z
 */
@Repository
public class HcrpReportChapterDao extends MyBatisDao {

    public List<HcrpReportChapter> listByReportId(String reportId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".listByReportId", reportId);
    }
}