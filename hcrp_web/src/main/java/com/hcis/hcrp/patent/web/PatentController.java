package com.hcis.hcrp.patent.web;

        import com.hcis.hcrp.dimension.entity.Dimension;
        import com.hcis.hcrp.dimension.service.DimensionService;
        import com.hcis.hcrp.patent.entity.TempPatent;
        import com.hcis.hcrp.patent.service.PatentService;

        import com.hcis.hcrp.report.report.entity.HcrpReportChapter;
        import com.hcis.hcrp.report.report.entity.Report;
        import com.hcis.hcrp.report.report.entity.ReportPatent;
        import com.hcis.hcrp.report.report.enumeration.TemplateType;
        import com.hcis.hcrp.report.report.service.HcrpReportChapterService;
        import com.hcis.hcrp.report.report.service.ReportService;
        import com.hcis.hcrp.report.template.entity.ReportTemplate;
        import com.hcis.hcrp.report.template.service.ReportTemplateService;
        import com.hcis.ipanther.common.login.vo.LoginUser;
        import com.hcis.ipanther.common.utils.ApiCode;
        import com.hcis.ipanther.common.utils.BaseApi;
        import com.hcis.ipanther.core.spring.mvc.bind.annotation.RequestJsonParam;
        import com.hcis.ipanther.core.utils.Identities;
        import com.hcis.ipanther.core.web.controller.BaseController;
        import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
        import com.hcis.ipanther.core.web.vo.SearchParam;
        import org.apache.commons.lang3.StringUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.multipart.MultipartHttpServletRequest;
        import org.springframework.web.multipart.commons.CommonsMultipartResolver;
        import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;
        import javax.ws.rs.PathParam;
        import java.util.Date;
        import java.util.List;
        import java.util.Map;

/**
 * @author z
 */
@Controller
@RequestMapping("/patent")
public class PatentController extends BaseController {


    @Autowired
    private PatentService patentService;
    @Autowired
    private ReportTemplateService reportTemplateService;
    @Autowired
    private DimensionService dimensionService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private HcrpReportChapterService hcrpReportChapterService;

    @RequestMapping("/importNavigation")
    @ResponseBody
    public BaseApi importNavigation(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle0") String reporttitle,
                                    HttpServletRequest request,@RequestParam(value = "reportTmeplateId0") String templateId,
                                    @RequestParam(value = "sections0") String[] sections, @RequestParam(value = "sectionsFile0") String[] sectionsfile ,
                                    @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile   ){

        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());
        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());
        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
            for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }


    @RequestMapping("/importWarning")
    @ResponseBody
    public BaseApi importWarning(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle1") String reporttitle,
                                 HttpServletRequest request,@RequestParam(value = "reportTmeplateId1") String templateId,
                                 @RequestParam(value = "sections1") String[] sections,@RequestParam(value = "sectionsFile1") String[] sectionsfile ,
                                 @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile ){

        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());
        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());
        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }
            }
            for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }
    @RequestMapping("/importPush")
    @ResponseBody
    public BaseApi importPush(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle2") String reporttitle,
                              HttpServletRequest request,@RequestParam(value = "reportTmeplateId2") String templateId,
                              @RequestParam(value = "sections2") String[] sections,@RequestParam(value = "sectionsFile2") String[] sectionsfile,
                              @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile ){
        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());
        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());
        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }
            }
            for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }
    @RequestMapping("/importSearch")
    @ResponseBody
    public BaseApi importSearch(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle3") String reporttitle,
                                HttpServletRequest request,@RequestParam(value = "reportTmeplateId3") String templateId,
                                @RequestParam(value = "sections3") String[] sections, @RequestParam(value = "sectionsFile3") String[] sectionsfile,
                                @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile  ){
        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());
        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());
        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }
            }
            for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }

        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }
    @RequestMapping("/importQuery")
    @ResponseBody
    public BaseApi importQuery(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle4") String reporttitle,
                               HttpServletRequest request,@RequestParam(value = "reportTmeplateId4") String templateId,
                               @RequestParam(value = "sections4") String[] sections, @RequestParam(value = "sectionsFile4") String[] sectionsfile,
                               @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile  ){
        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());


        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());

        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }
            }
            for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }
    @RequestMapping("/importAnalysis")
    @ResponseBody
    public BaseApi importAnalysis(@RequestParam(value = "type") String type,@RequestParam(value = "reporttitle5") String reporttitle,
                                  HttpServletRequest request,@RequestParam(value = "reportTmeplateId5") String templateId,
                                  @RequestParam(value = "sections5") String[] sections, @RequestParam(value = "sectionsFile5") String[] sectionsfile,
                                  @RequestParam(value = "applyers0") String[] applyers, @RequestParam(value = "applyersFile0") String[] applyersfile  ){

        TemplateType templateType=TemplateType.typeNameOf(type);
        LoginUser user = LoginUser.loginUser(request);
        String userId=user.getId();
        CommonsMultipartResolver cResolver = new CommonsMultipartResolver();
        List<MultipartFile> filelist=null;
        MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;
        /*添加报告*/
        Report report =new Report();
        report.setId(Identities.uuid2());
        report.setReportName(reporttitle);
        report.setTemplateId(templateId);
        report.setType(templateType.getType().toString());
        report.setCreateTime(new Date());
        report.setCreator(userId);
        reportService.insert(report);
        ReportPatent reportPatent=new ReportPatent();

        BaseApi api = new BaseApi();
        api.setData(report.getId());
        try {
            String batchId = null;
            for (int i=0;i<sections.length;i++){
                /*获取章节文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(sectionsfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(sections[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(0);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);
                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }
            }           for (int i=0;i<applyers.length;i++){
                /*获取申请人文件*/
                if (cResolver.isMultipart(request)) {
                    filelist=httpservletrequest.getFiles(applyersfile[i]);
                }
                /*添加章节*/
                HcrpReportChapter chapter =new HcrpReportChapter();
                chapter.setId(Identities.uuid2());
                chapter.setChapterName(applyers[i]);
                chapter.setReportId(report.getId());
                chapter.setChapterType(1);
                chapter.setCreateTime(new Date());
                report.setCreator(userId);
                reportPatent.setReportId(report.getId());
                reportPatent.setChapterId(chapter.getId());
                hcrpReportChapterService.insert(chapter);

                for (MultipartFile file :filelist){
                    /*导入章节数据*/
                    batchId = patentService.importPatent(file, chapter.getId(), report.getId());
                }

            }
        }catch (Exception e){
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return api;
    }

    @RequestMapping("/data")
    @ResponseBody
    public BaseApi getData(String tableId){
        BaseApi api = new BaseApi();
        try {
            api.setData(patentService.convertTableData(tableId, null));
        }catch (Exception e){
            e.printStackTrace();
        }

        return api;
    }

    @RequestMapping("/dataV2")
    @ResponseBody
    public BaseApi getDataV2(){
        BaseApi api = new BaseApi();
        try {
            patentService.testData();
        }catch (Exception e){
            e.printStackTrace();
        }

        return api;
    }


    public BaseApi  add(@ModelAttribute("tempPatent") TempPatent tempPatent,DefaultMultipartHttpServletRequest request ){
        BaseApi api =new BaseApi();
        try {


            LoginUser user = LoginUser.loginUser(request);
            String userId = user.getId();
            /*   String companyId = user.getCompanyId();*/
            if (StringUtils.isBlank(tempPatent.getId())) {
                tempPatent.setCreator(userId);
                patentService.addPatent(tempPatent);
            } else {
                /*存在ID，修改专利*/
                tempPatent.setUpdatedby(userId);
                patentService.updatePatent(tempPatent);

            }
        }catch (Exception e){
            e.printStackTrace();
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());

        }
        return api;
    }
    @RequestMapping("/deletes")
    public String deleteByIds(List<String> patentIds) {
        try {
            patentService.deleteByIds(patentIds);
        } catch (Exception e) {
            return "error";
        }
        return "succe";
    }
    @RequestMapping("/deletePatent")
    @ResponseBody
    public BaseApi deletePatent(@ModelAttribute("tempPatent") TempPatent tempPatent,DefaultMultipartHttpServletRequest request ) {
        LoginUser loginUser = LoginUser.loginUser(request);
        BaseApi api =new BaseApi();
        try {
            patentService.delete(tempPatent, loginUser.getId());
        }catch (Exception e){
            e.printStackTrace();
            api.setCode(ApiCode.ERROR);
            api.setMsg(e.getMessage());
        }
        return api;
    }
    @RequestMapping("/list")
    public ModelAndView listPatent(@ModelAttribute("searchParam") SearchParam searchParam) {
        /* ModelAndView modelAndView = new ModelAndView("/intellectual/patent/list");*/
        ModelAndView modelAndView = new ModelAndView();
        List<TempPatent> list = patentService.patentList(searchParam);
        modelAndView.addObject("listPatent", list);
        return modelAndView;
    }
    /**
     * 专利详情
     */
    @RequestMapping("/detail")
    public ModelAndView detail(String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patentDetail", patentService.getPatentById(id));

        return modelAndView;
    }

    @RequestMapping("/navigation")
    public ModelAndView navigation(Integer type ) {
        ModelAndView modelAndView = new ModelAndView("/patentnavigation/Patentnavigation");
        SearchParam searchParam =new SearchParam();
        searchParam.setPageAvailable(false);
        String dimensionval="";

        TemplateType templateType =  TemplateType.valueOf(type);
        List<ReportTemplate> reportTemplatelist = reportTemplateService.list(searchParam);
        for (ReportTemplate reportTemplate : reportTemplatelist ) {
            List<Dimension> dimensionslist= dimensionService.selectByTemptaleId(reportTemplate.getId());
            for (int i=0;i<dimensionslist.size();i++){
                Dimension dimension=dimensionslist.get(i);

                dimensionval+=dimension.getDimensionStair()+","+dimension.getDimensionStair();
                reportTemplate.setReportAnalysisDimension(dimensionval);
            }

        }

        modelAndView.addObject("type",templateType);
        modelAndView.addObject("reportTemplate",reportTemplatelist);

        return modelAndView;
    }

}
