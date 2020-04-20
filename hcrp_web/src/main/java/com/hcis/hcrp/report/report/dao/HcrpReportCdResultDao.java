package com.hcis.hcrp.report.report.dao;

import com.hcis.hcrp.report.report.entity.HcrpReportCdResult;
import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author z
 */
@Repository
public class HcrpReportCdResultDao extends MyBatisDao {

    public void batchSave(List<HcrpReportCdResult> list) {
        this.getSqlSession().insert(this.getNamespace() + ".batchSave", list);
    }

    public List<HcrpReportCdResult> listByReportId(String reportId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".listByReportId", reportId);
    }

    public void deleteByReportId(String reportId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByReportId", reportId);
    }
}