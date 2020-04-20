package com.hcis.hcrp.report.template.service;

import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.hcrp.report.template.entity.RtcTable;
import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2020/3/6
 **/
public interface ReportTemplateTableService extends IBaseService<RtcTable> {

    /**
     * 查询图表配置
     * @param id 图表id
     * @return 图表配置List集合
     */
    List<RtcTableField> listFieldByTableId(String id);

    /**
     * 保存/新建图表
     * @param map 图表字段数据
     */
    void saveTable(Map<String, Object> map);

    /**
     * @param tableId 表id
     * @return 表数据（已转json）
     */
    String getByTableId(String tableId);

    /**
     * 查询全部图表
     * @param searchParam x
     * @return 图表总数
     */
    Integer count(SearchParam searchParam);
}
