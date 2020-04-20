package com.hcis.hcrp.report.template.dao;

import com.hcis.hcrp.report.template.entity.HcrpDimensionFormula;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhw
 * @date 2020/3/27
 **/

@Repository
public class HcrpDimensionFormulaDao extends MyBatisDao {

    public List<HcrpDimensionFormula> selectAllFormulaByTempId(String tempId){
        return this.getSqlSession().selectList(this.getNamespace() + ".selectAllFormulaByTempId", tempId);
    }

    public void insertBatch(List<HcrpDimensionFormula> list) {
        this.getSqlSession().insert(this.getNamespace() + ".insertBatch", list);
    }

    public List<HcrpDimensionFormula> listFormula(String tempId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".listFormula", tempId);
    }

    public void deleteByTempId(String tempId) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByTempId", tempId);
    }
}
