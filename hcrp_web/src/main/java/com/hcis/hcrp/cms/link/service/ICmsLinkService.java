package com.hcis.hcrp.cms.link.service;

import java.util.List;
import java.util.Map;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.hcrp.cms.link.entity.CmsLink;

public interface ICmsLinkService extends IBaseService<CmsLink>{

	List<CmsLink> listLink(Map<String, Object> map);

	List<CmsLink> listLink(Map<String, Object> map, int siezNum);

}
