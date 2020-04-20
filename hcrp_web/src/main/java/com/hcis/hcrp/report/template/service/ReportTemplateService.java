package com.hcis.hcrp.report.template.service;

import com.hcis.hcrp.report.template.dto.DimensionStep4;
import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.entity.HcrpDimension;
import com.hcis.hcrp.report.template.entity.ReportTemplate;
import com.hcis.hcrp.report.template.entity.ReportTemplateChapter;
import com.hcis.hcrp.report.template.entity.RtcTableField;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @date 2020/3/6
 **/
public interface ReportTemplateService extends IBaseService<ReportTemplate> {

    /**
     * 新增模板
     * @param template 模板对象
     * @param id 模板id 可以传空
     */
    void addTemplate(ReportTemplate template, String id);

    /**
     * 修改模板信息
     * @param template 模板对象
     */
    void updateTemplate(ReportTemplate template);

    /**
     * 删除模板
     * @param templateId 模板id
     */
    void templateDelete(String templateId);

    /**
     * 查询模板详情
     * @param templateId 模板id
     * @return 模板数据
     */
    ReportTemplate getById(String templateId);

    /**
     * 图表数据
     * @param tableId 图表id
     * @param list 当前表格的字段list，（两个参数传任意一个即可，优先取list）
     * @return 图表数据需要的数组，每个对象代表一个图表数据,可能存在多个表合并的情况
     */
    List<RtcTableFiledGroupDto> templateTableList(String tableId, List<RtcTableField> list);

    /**
     * 获取模板内容和模板的数据
     * @param templateId 模板id
     * @return 模板对象
     * @throws Exception 抛出异常
     */
    ReportTemplate templateData(String templateId) throws Exception;

    /**
     * 查询总行数
     * @param searchParam
     * @return
     */
    int count(SearchParam searchParam);

    /**
     *
     * @param nextSecChapterId
     * @return
     */
    ReportTemplateChapter getChapterById(String nextSecChapterId);

    /**
     * 获取维度信息
     * @param id 模板id
     * @return 维度列表
     */
    List<Map<String, Object>> getChapterDimensions(String id);

    /**
     * 获取维度信息
     * @param id 模板id
     * @return 维度列表
     */
    DimensionStep4 getChapterDimensionsV2(String id);

    /**
     * 根据模板id获取维度信息
     * @param tempId 模板id
     * @return 维度列表
     */
    List<HcrpDimension> listDimension(String tempId);

    /**
     * 根据模板id创建影子模板做修改用
     * @param tempId x
     * @return 影子模板id
     */
    String copyTemplate(String tempId);

    /**
     * 保存维度信息
     * @param templateId 模板id
     * @param list 维度列表
     */
    void saveDimensions(String templateId, List<HcrpDimension> list);

    /**
     * 保存二级维度信息级所属的公式信息
     * @param templateId 模板id
     * @param list 维度列表
     */
    void saveDimensionFormulas(String templateId, List<HcrpDimension> list);

    /**
     * 模板修改最后的保存和发布
     * @param model 模式 0：保存 1：发布
     * @param map 模板参数
     */
    void endEdit(Integer model, Map map);
}
