package com.hcis.hcrp.report.report.dao;

import java.awt.*;
import java.util.List;

import com.hcis.hcrp.report.report.entity.Report;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author z
 */
@Repository
public class ReportDao extends MyBatisDao {

    @Override
    public List<Report> selectBySearchParam(SearchParam searchParam) {
        return getSqlSession().selectList(this.namespace + ".selectBySearchParam", searchParam);
    }
    public int count(SearchParam searchParam) {
        return this.getSqlSession().selectOne(this.getNamespace() + ".count", searchParam);
    }
    public List<Integer> reportType(String  userId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".reportType", userId);
    }
    public int dele(String id){
        return this.getSqlSession().delete("dele",id);
    }
}