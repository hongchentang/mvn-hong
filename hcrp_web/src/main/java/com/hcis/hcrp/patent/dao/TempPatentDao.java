package com.hcis.hcrp.patent.dao;

import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.report.report.entity.ReportPatent;
import com.hcis.hcrp.report.report.entity.ReportPatent;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author z
 */
@Repository("tempPatentDao")
public class TempPatentDao extends MyBatisDao {

    public int batchInsert(List<TempPatent> list){
        return this.getSqlSession().insert(this.getNamespace() + ".insertBatch",list);
    }

    public List<TempPatent> selectChapterData(String chapterId) {
        return this.getSqlSession().selectList("selectChapterData", chapterId);
    }

    public List<TempPatent> reportData(String reportId) {
        return this.getSqlSession().selectList(this.getNamespace() + ".reportPatents", reportId);
    }

    public List<TempPatent> queryByAppNumbers(List<String> list) {
        return this.getSqlSession().selectList(this.getNamespace() + ".queryByAppNumbers", list);
    }

    public void saveReportPatents(List<ReportPatent> list) {
        this.getSqlSession().insert(this.getNamespace() + ".saveReportPatents", list);
    }

    public void deleteByPrimaryKeys(List<String> patentIds) {
        this.selectForList(namespace+".deleteByPrimaryKeys", patentIds);
    }

}
