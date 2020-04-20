package com.hcis.hcrp.cms.htmlquartz.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.hcrp.cms.htmlquartz.dao.CmsHtmlquartzDao;
import com.hcis.hcrp.cms.htmlquartz.entity.CmsHtmlquartz;
import com.hcis.hcrp.cms.htmlquartz.service.ICmsHtmlquartzService;


@Service("cmsHtmlquartzService")
public class CmsHtmlquartzServiceImpl extends BaseServiceImpl<CmsHtmlquartz> implements ICmsHtmlquartzService {

	@Autowired
	private CmsHtmlquartzDao baseDao;
	
	
	@Override
	public CmsHtmlquartzDao getBaseDao() {
		return baseDao;
	}
}
