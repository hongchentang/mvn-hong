package com.hcis.hcrp.report.template.dao;

import java.util.List;

import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author z
 */
@Repository
public class RtcTableDao extends MyBatisDao {

    public void deleteByIds(List<String> list) {
        this.getSqlSession().delete(this.getNamespace() + ".deleteByIds", list);
    }

    public List<String> getIdsByTemplateId(String templateId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".getIdsByTemplateId", templateId);
    }

    public List<RtcTable> selectListByTemplateId(String templateId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".selectListByTemplateId", templateId);
    }

    public Integer count(SearchParam searchParam) {
        return this.getSqlSession().selectOne(this.getNamespace() + ".count", searchParam);
    }

}