package com.hcis.hcrp.report.template.dao;

import java.util.List;

import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author z
 */
@Repository
public class RtcTableFieldDao extends MyBatisDao {

    public void deleteByIds(List<String> list) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByIds", list);
    }

    public List<String> getIdsByTemplateId(String templateId) {
        return this.getSqlSession().selectList(this.namespace + ".getIdsByTemplateId", templateId);
    }

    public List<RtcTableField> selectListByTemplateId(String templateId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".selectListByTemplateId", templateId);
    }

    public List<RtcTableField> listFieldByTableId(String id) {
        return this.getSqlSession().selectList(this.getNamespace() + ".listFieldByTableId", id);
    }

    public void insertBatch(List<RtcTableField> list) {
        this.getSqlSession().insert(this.getNamespace() + ".insertBatch", list);
    }

    public void deleteByTableId(String id) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByTableId", id);
    }

    public List<RtcTableField> selectByTableId(String tableId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".selectByTableId", tableId);
    }
}