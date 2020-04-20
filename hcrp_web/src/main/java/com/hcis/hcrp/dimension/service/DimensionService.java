package com.hcis.hcrp.dimension.service;

import com.hcis.hcrp.dimension.entity.Dimension;
import com.hcis.ipanther.core.service.IBaseService;

import java.util.List;

public interface DimensionService extends IBaseService<Dimension> {

    int deleteByReportID(String reportID);
    int batchInsert(List<Dimension> list);
    int insert(Dimension dimension);
    List<Dimension> selectByTemptaleId(String templateId);
}
