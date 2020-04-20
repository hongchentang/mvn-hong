package com.hcis.hcrp.report.template.dao;


import com.hcis.hcrp.report.template.entity.ReportTemplateChapter;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author z
 */
@Repository
public class ReportTemplateChapterDao extends MyBatisDao {

    public void deleteByTemplateId(String templateId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByTemplateId", templateId);
    }

    public List<ReportTemplateChapter> selectListByTemplateId(String templateId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".selectListByTemplateId", templateId);
    }
}