package com.hcis.hcrp.report.template.web;

import com.alibaba.fastjson.JSON;
import com.hcis.hcrp.report.report.enumeration.TableTypeEnum;
import com.hcis.hcrp.report.report.enumeration.TemplateType;
import com.hcis.hcrp.report.template.dto.DimensionStep4;
import com.hcis.hcrp.report.template.dto.ReportResultDimensionDto;
import com.hcis.hcrp.report.template.dto.ReportResultDto;
import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.hcrp.report.template.entity.ReportTemplateChapter;
import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.service.ReportTemplateService;
import com.hcis.hcrp.report.template.service.ReportTemplateTableService;
import com.hcis.hcrp.temp.FreeMarkerUtil;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.common.utils.MyPageUtils;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;
import net.sf.json.util.JSONUtils;
import org.opensaml.xml.signature.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author zhw
 * @date 2020/3/6
 **/
@Controller
@RequestMapping("/report/template")
public class ReportTemplateController extends BaseController {

    @Autowired
    private ReportTemplateService reportTemplateService;

    @Autowired
    private ReportTemplateTableService reportTemplateTableService;

    @Autowired
    MyPageUtils pageUtils;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;




    @RequestMapping("/saveReportChartImg")
    @ResponseBody
    public BaseApi saveReportChartImg(@RequestBody Map map){

        BaseApi api = new BaseApi();
        try {
            String dimensionId = (String) map.get("dimensionId");
            String base64Str = (String) map.get("img");
            base64Str = base64Str.replaceAll(" ", "+");
            base64Str = base64Str.split("base64,")[1];
            api.setData(generateImage(base64Str, dimensionId));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseApi add(@RequestBody ReportTemplate template){

        BaseApi api = new BaseApi();
        try {
            reportTemplateService.addTemplate(template, null);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/update")
    @ResponseBody
    public BaseApi update(@RequestBody ReportTemplate template){

        BaseApi api = new BaseApi();
        try {
            reportTemplateService.updateTemplate(template);
        }catch (Exception e){
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseApi delete(String templateId){

        BaseApi api = new BaseApi();

        try {
            reportTemplateService.templateDelete(templateId);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/getById")
    @ResponseBody
    public BaseApi getById(String templateId){

        BaseApi api = new BaseApi();

        try {
            api.setData(reportTemplateService.getById(templateId));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/list")
    public ModelAndView list(SearchParam searchParam){
        ModelAndView mv = new ModelAndView("/report/template/list");

        try {

            Integer page = 1;
            Object currentPage = searchParam.getParamMap().get("currentPage");
            Pagination pagination = new Pagination();
            if(currentPage != null){
                page = Integer.valueOf(currentPage.toString()) ;
            }
            pagination.setCurrentPage(page);

            int count = reportTemplateService.count(searchParam);

            pageUtils.pageCovert(mv, count, page);
            searchParam.setPagination(pagination);
            searchParam.getParamMap().put("currentPage", page);
            mv.addObject("list", reportTemplateService.list(searchParam));
            mv.addObject("paramMap", searchParam.getParamMap());
        }catch (Exception e){
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping("/goAddReportTemp")
    public ModelAndView goAddReportTemp(){
        return new ModelAndView("/report/template/addReportTemp");
    }

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(Integer step, Integer tempType, String tempId, String shadowTempId, Integer stepModel) throws IOException {
        if(step == null){
            step = 1;
        }
        if(tempType == null){
            tempType = 0;
        }
        if(stepModel == null){
            stepModel = 0;
        }

        ModelAndView mv = new ModelAndView("/report/template/addTempStep" + step);
        //ModelAndView mv = new ModelAndView("/report/template/ueditorPage");

        SearchParam searchParam = new SearchParam();
        searchParam.getParamMap().put("queryTempType", tempType);
        Pagination pagination = new Pagination();
        pagination.setPageCount(Integer.MAX_VALUE);
        searchParam.setPagination(pagination);

        mv.addObject("tempTypeName", TemplateType.valueOf(tempType).getTypeName());
        mv.addObject("tempType", tempType);
        mv.addObject("tempId", tempId);
        mv.addObject("stepModel", stepModel);
        mv.addObject("temp", reportTemplateService.read(tempId));

        if(StringUtils.isNotBlank(tempId) && StringUtils.isBlank(shadowTempId)){
            //新建一个影子模板用来修改
            shadowTempId = reportTemplateService.copyTemplate(tempId);
        }

        mv.addObject("shadowTempId", shadowTempId);

        if(step == 1){
            mv.addObject("types", TemplateType.values());
        }

        if(step == 2){
            mv.addObject("templates", reportTemplateService.list(searchParam));
        }

        if(step == 3){
            mv.addObject("dimensions", reportTemplateService.getChapterDimensionsV2(shadowTempId).getAllSecDimension());
        }

        if(step == 4){
            DimensionStep4 dimensionStep4 = reportTemplateService.getChapterDimensionsV2(shadowTempId);
            mv.addObject("dimensionList", dimensionStep4.getList());
            mv.addObject("first", dimensionStep4.getFirst());
            mv.addObject("allSecDimension", JSON.toJSONString(dimensionStep4.getAllSecDimension()));
        }

        if(step == 5){
            mv.addObject("template", reportTemplateService.read(shadowTempId));
        }

         return mv;
    }

    @RequestMapping("/{templateId}/saveDimensions")
    @ResponseBody
    public BaseApi saveDimensions(@PathVariable String templateId, @RequestBody List<HcrpDimension> list){
        BaseApi api = new BaseApi();

        try {
            reportTemplateService.saveDimensions(templateId, list);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/{templateId}/saveDimensionFormulas")
    @ResponseBody
    public BaseApi saveDimensionFormulas(@PathVariable String templateId, @RequestBody List<HcrpDimension> list){
        BaseApi api = new BaseApi();

        try {
            reportTemplateService.saveDimensionFormulas(templateId, list);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/{model}/endEdit")
    @ResponseBody
    public BaseApi endEdit(@PathVariable Integer model, @RequestBody Map map){
        BaseApi api = new BaseApi();

        try {
            reportTemplateService.endEdit(model, map);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/allSecList")
    @ResponseBody
    public BaseApi allSecList(String tempId){
        BaseApi api = new BaseApi();

        try {
            DimensionStep4 dimensionStep4 = reportTemplateService.getChapterDimensionsV2(tempId);
            Map map = new HashMap();
            map.put("first", dimensionStep4.getFirst());
            map.put("allSecDimension", dimensionStep4.getAllSecDimension());
            api.setData(map);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/goReplaceTable")
    public ModelAndView goReplaceTable(SearchParam searchParam, Integer type){

        ModelAndView mv = new ModelAndView("/report/template/replaceTable");
        List<RtcTable> tables = reportTemplateTableService.list(searchParam);

        if(!CollectionUtils.isEmpty(tables)){
            mv.addObject("firstId", tables.get(0).getId());
        }

        mv.addObject("listType", TableTypeEnum.values());
        mv.addObject("type", type);
        mv.addObject("list", tables);

        return mv;
    }

    @RequestMapping("/test")
    @ResponseBody
    public BaseApi test(){

        BaseApi api = new BaseApi();

        try {

        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/saveSecChapter")
    @ResponseBody
    public BaseApi saveSecChapter(@RequestBody Map map){

        BaseApi api = new BaseApi();

        try {
            String nextSecChapterId = (String) map.get("nextSecChapter");
            api.setData(reportTemplateService.getChapterById(nextSecChapterId));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }


    /**
     * base64字符串转化成图片
     * @param imgStr x
     * @return x
     */
    public static boolean generateImage(String imgStr, String fileName) {
        //对字节数组字符串进行Base64解码并生成图片
        File imgFile = new File("F:\\temImg\\" + fileName + ".png");
        //图像数据为空
        if (imgStr == null){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            OutputStream out = new FileOutputStream(imgFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

