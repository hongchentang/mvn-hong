package com.hcis.hcrp.dimension.service.Impl;

import com.hcis.hcrp.dimension.dao.DimensionDao;
import com.hcis.hcrp.dimension.entity.Dimension;
import com.hcis.hcrp.dimension.service.DimensionService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*@Author hct
 */
@Service
public class DimensionServiceImpl extends BaseServiceImpl<Dimension> implements DimensionService {
    @Override
    public MyBatisDao getBaseDao() {
        return null;
    }
    @Autowired
   private DimensionDao dimensionDao;

    @Override
    public int deleteByReportID(String reportID) {
        return dimensionDao.deleteByPrimaryKey(reportID);
    }

    @Override
    public int batchInsert(List<Dimension> list) {
        return dimensionDao.batchInsert(list);
    }

    @Override
    public int insert(Dimension dimension) {
        return dimensionDao.insert(dimension);
    }

    @Override
    public List<Dimension> selectByTemptaleId(String templateId) {
        {
            return dimensionDao.selectByTemptaleId(templateId);
        }
    }
}
