package com.hcis.hcrp.report.template.web;

import com.hcis.hcrp.patent.dao.TempPatentDao;
import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.patent.service.PatentService;
import com.hcis.hcrp.report.report.enumeration.PatentFieldEnum;
import com.hcis.hcrp.report.report.enumeration.TableTypeEnum;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.service.ReportTemplateService;
import com.hcis.hcrp.report.template.service.ReportTemplateTableService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.common.utils.MyPageUtils;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2020/3/6
 **/
@Controller
@RequestMapping("/report/template/table")
public class ReportTemplateTableController extends BaseController {

    @Autowired
    private ReportTemplateTableService reportTemplateTableService;

    @Autowired
    private PatentService patentService;

    @Autowired
    MyPageUtils pageUtils;

    @RequestMapping("/list")
    public ModelAndView list(SearchParam searchParam){

        Object objPage = searchParam.getParamMap().get("currentPage");
        Integer page = 1;
        if(objPage != null){
            page = Integer.valueOf(objPage.toString());
        }

        Object type = searchParam.getParamMap().get("type");
        if(type == null){
            type = 0;
            searchParam.getParamMap().put("type", type);
        }

        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page);
        searchParam.setPagination(pagination);

        ModelAndView mv = new ModelAndView("/report/table/list");
        mv.addObject("listType", TableTypeEnum.values());
        mv.addObject("type", type);
        mv.addObject("list", reportTemplateTableService.list(searchParam));
        mv.addObject("paramMap", searchParam.getParamMap());

        searchParam.getParamMap().put("currentPage", page);
        Integer count = reportTemplateTableService.count(searchParam);
        pageUtils.pageCovert(mv, count, page);
        return mv;
    }


    @RequestMapping("/goAdd")
    public ModelAndView goAdd(SearchParam searchParam, String type){

        ModelAndView mv = new ModelAndView("/report/table/addTable");
        List<RtcTable> tables = reportTemplateTableService.list(searchParam);
        if(!CollectionUtils.isEmpty(tables)){
            mv.addObject("firstId", tables.get(0).getId());
        }
        mv.addObject("listType", TableTypeEnum.values());
        mv.addObject("type", type);
        mv.addObject("list", tables);

        return mv;
    }

    @RequestMapping("/chart")
    public ModelAndView getChart(String chartName, String secDimensionId){
        if(StringUtils.isEmpty(chartName)){
            chartName = TableTypeEnum.HISTOGRAM.getField();
        }
        ModelAndView mv = new ModelAndView("/report/chart/" + chartName);
        mv.addObject("secDimensionId", secDimensionId);
        return mv;
    }

    @RequestMapping("/goUpdateTable")
    public ModelAndView goUpdateTable(String id){
        ModelAndView mv = new ModelAndView("/report/table/updateTable");

        //所有的字段
        mv.addObject("fields", PatentFieldEnum.values());

        //已保存的图表信息
        if(!StringUtils.isEmpty(id)){
            mv.addObject("tableFields", reportTemplateTableService.listFieldByTableId(id));
        }

        mv.addObject("edit", 0);

        return mv;
    }

    @RequestMapping("/saveTable")
    @ResponseBody
    public BaseApi saveTable(@RequestBody Map<String, Object> map){

        BaseApi api = new BaseApi();

        try {
            reportTemplateTableService.saveTable(map);
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/getByTableId")
    @ResponseBody
    public BaseApi getByTableId(String id){

        BaseApi api = new BaseApi();

        try {
            api.setData(reportTemplateTableService.getByTableId(id));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/deleteByTableId")
    @ResponseBody
    public BaseApi deleteByTableId(String id){

        BaseApi api = new BaseApi();

        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            RtcTable table = new RtcTable();
            table.setId(id);
            api.setData(reportTemplateTableService.delete(table, loginUser.getId()));
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/eidtTableChart")
    public ModelAndView eidtTableChart(String id){
        ModelAndView mv = new ModelAndView("/report/table/editTableChart");
        mv.addObject("tableData", reportTemplateTableService.getByTableId(id));
        //所有的字段
        mv.addObject("fields", PatentFieldEnum.values());
        //
        mv.addObject("edit", 1);

        RtcTable table  = reportTemplateTableService.read(id);
        table.setTableTypeName(TableTypeEnum.valueOf(table.getTableType()).getField());
        mv.addObject("table", table);

        return mv;
    }

    @RequestMapping("/showTableChart")
    public ModelAndView showTableChart(String chartName){
        ModelAndView mv = new ModelAndView("/report/table/showTableChart");
        mv.addObject("chartName", chartName);
        return mv;
    }

}