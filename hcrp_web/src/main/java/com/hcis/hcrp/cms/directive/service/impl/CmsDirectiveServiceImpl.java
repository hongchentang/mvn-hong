package com.hcis.hcrp.cms.directive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.hcrp.cms.directive.dao.CmsDirectiveDao;
import com.hcis.hcrp.cms.directive.entity.CmsDirective;
import com.hcis.hcrp.cms.directive.service.ICmsDirectiveService;

@Service("cmsDirectiveService")
public class CmsDirectiveServiceImpl extends BaseServiceImpl<CmsDirective> implements ICmsDirectiveService{

	@Autowired
	private CmsDirectiveDao cmsDirectiveDao;
	
	
	
	
	@Override
	public CmsDirectiveDao getBaseDao() {
		return cmsDirectiveDao;
	}

}
