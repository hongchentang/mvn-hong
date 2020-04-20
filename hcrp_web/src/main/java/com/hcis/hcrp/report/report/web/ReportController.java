package com.hcis.hcrp.report.report.web;

import com.hcis.hcrp.report.report.dao.ReportDao;
import com.hcis.hcrp.report.report.entity.Report;

import com.hcis.hcrp.report.report.enumeration.TemplateType;
import com.hcis.hcrp.report.report.service.ReportService;
import com.hcis.hcrp.report.template.dto.ReportResultDimensionDto;
import com.hcis.hcrp.report.template.dto.ReportResultDto;
import com.hcis.hcrp.report.template.web.ReportTemplateController;
import com.hcis.hcrp.temp.FreeMarkerUtil;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author  hct
 */

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    private ReportDao reportDao;

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;


    @RequestMapping("/lists")
    public ModelAndView list(HttpServletRequest request, SearchParam searchParam) {
        LoginUser user = LoginUser.loginUser(request);
        ModelAndView mv = new ModelAndView("/report/report/lists");
        Integer page = 1;
        Object currentPage = searchParam.getParamMap().get("currentPage");
        Pagination pagination = new Pagination();
        if (currentPage != null && !currentPage.equals("")) {
            page = Integer.valueOf(currentPage.toString());
        }
        pagination.setCurrentPage(page);
        searchParam.getParamMap().put("userId", user.getId());
        int count = reportService.count(searchParam);
        count = count % 10 > 0 ? (count / 10) + 1 : count / 10;

        searchParam.setPagination(pagination);
        searchParam.getParamMap().put("currentPage", page);

        /*查询数据*/
        List<Report> reportList = reportDao.selectBySearchParam(searchParam);

        mv.addObject("list", reportList);
        mv.addObject("paramMap", searchParam.getParamMap());
        mv.addObject("maxPage", count);

        return mv;
    }

    @RequestMapping("/reportReview")
    public ModelAndView saveTable(String reportId, Integer reportModel, Integer goModel) throws Exception {

       ModelAndView mv = new ModelAndView("/report/report/reportReview");
       if(reportModel == null){
           reportModel = 0;
       }
       if(goModel == null){
           goModel = 0;
       }

       mv.addObject("report", reportService.getReportResult(reportId));
       mv.addObject("model", reportModel);
       mv.addObject("goModel", goModel);

       return mv;
    }

    @RequestMapping("/listType")
    @ResponseBody
    public BaseApi listType(HttpServletRequest request){
        LoginUser user = LoginUser.loginUser(request);

        List<Integer> count = reportDao.reportType(user.getId());

        BaseApi baseApi =new BaseApi();
        baseApi.setData(count);
        return baseApi;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseApi delete(@RequestParam(value = "id") String id){

        int count = reportDao.dele(id);
        BaseApi baseApi =new BaseApi();
        return baseApi;
    }

    @RequestMapping("/saveReportResult")
    @ResponseBody
    public BaseApi saveReportResult(@RequestBody ReportResultDto report, HttpServletResponse response){

        BaseApi api =new BaseApi();

        try {
            /*先看图片行不行*/
            for(ReportResultDimensionDto dimension : report.getSecDimensionList()){
                ReportTemplateController.generateImage(dimension.getPic(), dimension.getId());
            }
            /*导出模板*/
            String path = CommonConfig.getProperty("report.local.dir");
            String fileName = report.getReportId() + ".doc";
            freeMarkerUtil.crateFile(report, "reportV4.ftl", (path + fileName));

            /*保存结果的数据*/
            reportService.saveReportResult(report);

            api.setData(report.getReportId()
            );
        }catch (Exception e){
            e.printStackTrace();
            api.setError(e.getMessage());
        }

        return api;
    }

    @RequestMapping("/file")
    public void getReportFile(String reportId, HttpServletResponse response) {
        try {

            String fileUrl = CommonConfig.getProperty("report.local.dir");
            fileUrl += reportId + ".doc";

            Report report = reportDao.selectByPrimaryKey(reportId);
            String reportName = report.getReportName() + ".doc";
            /*导出文件*/
            InputStream fin = null;
            OutputStream out = null;
            File file = new File(fileUrl);

            fin = new FileInputStream(file);

            //需要传递这个长度，不然下载文件后，打开提示内容有问题，如docx等
            response.setContentLength((int) file.length());
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(reportName.getBytes(StandardCharsets.UTF_8), "iso8859-1"));
            out = response.getOutputStream();
            // 缓冲区
            byte[] buffer = new byte[1024];
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }

            /*关闭io*/
            fin.close();
            if (out != null) {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/returnPage")
    public ModelAndView returnPage(String reportId)  {

        ModelAndView mv = new ModelAndView("/patentnavigation/Patentnavigation");

        Report report = reportService.read(reportId);
        mv.addObject("report", report);
        mv.addObject("type", TemplateType.valueOf(report.getType()));

        return mv;
    }
}