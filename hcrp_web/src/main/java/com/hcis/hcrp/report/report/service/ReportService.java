package com.hcis.hcrp.report.report.service;

import com.hcis.hcrp.report.report.entity.Report;
import com.hcis.hcrp.report.template.dto.ReportResultDto;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author zhw
 * @date 2020/3/9
 **/
public interface ReportService extends IBaseService<Report> {
    int insert(Report report);
    /**
     * 查询总行数
     * @param searchParam
     * @return
     */
    int count(SearchParam searchParam);

    /**
     * 获取报告基础信息和关联的渲染后的模板的结果
     * @param reportId 报告id
     * @return 报告的信息
     * @throws Exception x
     */
    Report getReportResult(String reportId) throws Exception;

    /**
     * 保存信息报告结果
     * @param report 结果信息
     */
    void saveReportResult(ReportResultDto report);
}
