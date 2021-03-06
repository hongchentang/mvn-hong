package com.hcis.hcrp.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.hcrp.cms.channel.service.ICmsChannelService;
import com.hcis.hcrp.cms.common.utils.CmsCommonDirective;
import com.hcis.hcrp.cms.info.entity.CmsInfo;
import com.hcis.hcrp.cms.info.service.ICmsInfoService;
import com.hcis.hcrp.cms.site.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * <p>Title: InfoDirective.java</p>
 * 
 * <p>Description: 信息标签
 * 参数
 * id		
 * 
 * dateFormat	日期格式
 * 
 * 返回值
 * info			信息对象
 * 
 * 
 * 使用示例
<@info id="1c5c3311-62c3-4548-8573-51ba6cd6eb66";info>
${info.title}
</@info>
 * </p>
 * 
 * <p>Date: May 14, 2012</p>
 * 
 * <p>Time: 1:45:03 PM</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: freeteam</p>
 * 
 * @author freeteam
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class InfoDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	protected static ICmsInfoService cmsInfoService=(ICmsInfoService) BeanLocator.getBean("cmsInfoService");
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		//id
		String id=getParam(params, "id");
		//日期格式
		String dateFormat=getParam(params, "dateFormat");
		
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
				//查询信息
				CmsInfo info=cmsInfoService.read(id);
				if(info!=null){
					if(StringUtils.isNotEmpty(info.getImg())){
						try {
							Map<String, Object> temp=JsonUtil.fromJson(info.getImg(), Map.class);
							if(temp!=null&&(!temp.isEmpty())){
								info.setImg(ObjectUtils.toString(temp.get("fileId")));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
					if (dateFormat.trim().length()>0) {
						info.setDateFormat(dateFormat);
					}
					loopVars[0]=new BeanModel(info,new BeansWrapper());  
					body.render(env.getOut()); 
				}
			}
		}
	}
}
