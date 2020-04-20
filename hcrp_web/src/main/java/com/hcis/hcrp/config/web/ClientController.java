package com.hcis.hcrp.config.web;

import com.hcis.hcrp.config.entity.ClientConfig;
import com.hcis.hcrp.config.service.ClientConfigService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.common.utils.FileUtils;
import com.hcis.ipanther.core.cache.redis.RedisTemplateService;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2020/1/15
 **/
@Controller
@RequestMapping("/client")
public class ClientController extends BaseController {

    @Autowired
    private ClientConfigService clientConfigService;

    @Autowired
    private RedisTemplateService redisTemplateService;

    @Autowired
    private FileUtils fileUtils;

    @RequestMapping("/verification")
    @ResponseBody
    public BaseApi verification(String token) {
        BaseApi api = new BaseApi();
        try {
            String exist = (String) redisTemplateService.get(token);
            if (StringUtils.isEmpty(exist) || !exist.equals("OK")) {
                api.setError("验证失败,不存在（失效）的令牌");
            }
        } catch (Exception e) {
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/getCenterToken")
    @ResponseBody
    public BaseApi token(String clientId, HttpServletRequest request) {

        BaseApi api = new BaseApi();
        try {

        } catch (Exception e) {
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response, String clientId) {

        try {
            //验证clientId
            String clientUrl = clientConfigService.validClient(clientId);
            if (StringUtils.isEmpty(clientUrl)) {
                throw new IllegalArgumentException("clientId错误");
            }

            //验证登录
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                //跳转到登录页面

                return;
            }
            String userId = loginUser.getId();

            String token = UUIDUtils.getUUId();
            redisTemplateService.set(token, "OK");
            redisTemplateService.expire(token, 1800);

            //登录的系统存起来
            List<String> list = loginUser.getLoginModelList();
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(clientUrl.replace("login","loginOut"));
            loginUser.setLoginModelList(list);

            //跳转回应用登录
            response.sendRedirect(clientUrl + "?userId=" + userId + "&token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/list")
    public ModelAndView clientList(SearchParam searchParam) {
        ModelAndView mv = new ModelAndView("/client/list");

        List<ClientConfig> list = clientConfigService.list(searchParam);
        for(ClientConfig clientConfig : list){
            String atta = clientConfig.getClientIcon();
            if(!StringUtils.isEmpty(atta)){
                clientConfig.setClientIcon(JsonUtil.getJsonMapValue(atta, "attachmentName"));
            }
        }

        mv.addObject("clients", list);
        return mv;
    }

    @RequestMapping("/goAddClient")
    public ModelAndView goAddEdit(String id) {
        ModelAndView mv = new ModelAndView("/client/addClient");
        if (!StringUtils.isEmpty(id)) {
            ClientConfig clientConfig = clientConfigService.read(id);
            String clientIcon = clientConfig.getClientIcon();
            if(!StringUtils.isEmpty(clientIcon)){
                clientConfig.setClientIcon(JsonUtil.getJsonMapValue(clientIcon, "attachmentName"));
            }
            mv.addObject("client", clientConfig);
        }
        return mv;
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseApi addEdit(@ModelAttribute("clientConfig") ClientConfig clientConfig, DefaultMultipartHttpServletRequest request) {
        BaseApi api = new BaseApi();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

            boolean flag = clientConfig.isDeletedAtta();
            if(flag){
                clientConfig.setClientIcon(null);
            }

            MultipartFile file = request.getFile("file");
            String reStr = updateFile(file);
            if(!StringUtils.isEmpty(reStr)){
                clientConfig.setClientIcon(reStr);
            }
            if (StringUtils.isEmpty(clientConfig.getId())) {
                //新增
                clientConfig.setDefaultValue();
                clientConfig.setId(UUIDUtils.getUUId());
                clientConfig.setClientValue(UUIDUtils.getUUId());
                int sort = clientConfigService.maxSort();
                clientConfig.setSortNo(new BigDecimal(sort).add(BigDecimal.ONE));
                clientConfigService.create(clientConfig, loginUser.getId());
            } else {
                //修改
                clientConfigService.update(clientConfig, loginUser.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            api.setError(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/deleteClient")
    @ResponseBody
    public BaseApi deleteClient(String id){
        BaseApi api = new BaseApi();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setId(id);
            clientConfig.setIsDeleted("Y");
            clientConfigService.update(clientConfig, loginUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/viewClient")
    public ModelAndView viewClient(String id){
        ModelAndView mv = new ModelAndView("/client/detail");
        mv.addObject("client", clientConfigService.read(id));
        return mv;
    }


    private String updateFile(MultipartFile file) throws IOException {
        Map<String, String> map = new HashMap<>();
        String reStr = fileUtils.saveFile(map, file, "attachment.default.fileTypes.image", "model/icon");
        if(StringUtils.isEmpty(reStr) && map.size() > 0){
            return JsonUtil.toJson(map);
        }
        return reStr;
    }
}
