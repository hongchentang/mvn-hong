package com.hcis.hcrp.dimension.dao;


import com.hcis.hcrp.dimension.entity.Dimension;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Author hct
 */
@Repository("dimensionDao")
public class DimensionDao extends MyBatisDao {

    public int batchInsert(List<Dimension> list){
        return this.getSqlSession().insert(this.getNamespace() + ".insertBatch",list);
    }

    public List<Dimension> selectByTemptaleId(String  templateId){
        return this.getSqlSession().selectList(this.getNamespace() + ".selectByTemptaleId", templateId);
    }

}
