package com.hcis.hcrp.report.template.dao;


import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.hcrp.report.template.entity.HcrpDimensionFormula;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

/**
 * @author z
 */
@Repository
public class HcrpDimensionDao extends MyBatisDao {

    public List<HcrpDimension> listByTemplateId(String tempId) {
       return this.getSqlSession().selectList(this.getNamespace() + ".listByTemplateId", tempId);
    }

    public void insertBatch(List<HcrpDimension> list) {
        this.getSqlSession().insert(this.getNamespace() + ".insertBatch", list);
    }

    public void deleteByTempId(String tempId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByTempId", tempId);
    }

    public void deleteSecByTempId(String tempId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteSecByTempId", tempId);
    }

    public void updateDimensionTemp(SearchParam searchParam) {
        this.getSqlSession().update(this.getNamespace() + ".updateDimensionTemp", searchParam);
    }
}