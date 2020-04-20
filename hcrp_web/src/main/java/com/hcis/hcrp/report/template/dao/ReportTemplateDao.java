package com.hcis.hcrp.report.template.dao;

import java.util.List;

import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author z
 */
@Repository
public class ReportTemplateDao extends MyBatisDao {

    public void deleteById(String templateId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByPrimaryKey", templateId);
    }

    public List<TempPatent> getDataList(String templateId) {
        return null;
    }

    public int count(SearchParam searchParam) {
        return this.getSqlSession().selectOne(this.getNamespace() + ".count", searchParam);
    }

    public void updateByShadowIdSelective(ReportTemplate shadowTemplate) {
        this.getSqlSession().update(this.getNamespace() + ".updateByShadowIdSelective", shadowTemplate);
    }
}