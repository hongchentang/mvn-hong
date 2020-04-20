package com.hcis.hcrp.patent.service;

import com.hcis.hcrp.patent.entity.TempPatent;
import com.hcis.hcrp.report.template.dto.ConvertTableDataDto;
import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.dto.TableResultDto;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PatentService extends IBaseService<TempPatent> {

    /**
     * 导入专利信息
     * @param file x
     * @param chapterId
     * @param reportId
     * @return x
     */
    String importPatent(MultipartFile file, String chapterId, String reportId) throws IOException, Exception;

    /**
     * x
     * @param
     * @return
     * @throws Exception
     */
    Object getData(Map<String, Object> map) throws Exception;

    /**
     * 获取数据结果
     * @param filedGroup 图标规格
     * @param tableData 图标的数据
     * @param firstSortList 已经排序过的顺序（第一次的排序，没有传空）
     * @return 结果数组
     * @throws Exception 抛出异常
     */
    List<TableResultDto> getData(RtcTableFiledGroupDto filedGroup, List<TempPatent> tableData, List<TableResultDto> firstSortList) throws Exception;

    /**
     * 测试
     */
    void testData();
    void addPatent(TempPatent tempPatent);
    void updatePatent(TempPatent tempPatent);
    void deleteByIds(List<String> patentIds);
    List<TempPatent> patentList(SearchParam searchParam);
    TempPatent getPatentById(String id);

    /**
     * 转换数据
     * @param tableId 图表id
     * @param  chapterId 章节的id
     * @return 图表信息
     * @throws Exception x
     */
    List<ConvertTableDataDto> convertTableData(String tableId, String chapterId) throws Exception;
}
