package com.hcis.hcrp.cms.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.hcrp.cms.config.service.ICmsConfigService;


@Controller
@RequestMapping("/cms/config")
public class CmsConfigController extends BaseController {

	@Autowired
	private ICmsConfigService cmsConfigService;
	
	
	
	
	
}
