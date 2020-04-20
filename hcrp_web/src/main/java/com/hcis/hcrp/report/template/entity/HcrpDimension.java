package com.hcis.hcrp.report.template.entity;

import com.hcis.hcrp.report.template.dto.ConvertTableDataDto;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class HcrpDimension extends BaseEntity {

    private String templateId;

    private String tableId;

    private String dimensionName;

    private String dimensionStair;

    private String dimensionSecond;

    private Integer sortOn;

    private Integer type;

    private String dimensionHtml;

    /*非数据库字段*/

    private List<HcrpDimension> secList ;

    private Integer tableType;

    private String tableTypeName;

    private String tableTypeChartName;

    private String chartData;

    private String chartPngBase64;

    private List<ConvertTableDataDto> resultDataList;

    private String resultDataJson;

    public String getChartData() {
        return chartData;
    }

    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    public String getChartPngBase64() {
        return chartPngBase64;
    }

    public void setChartPngBase64(String chartPngBase64) {
        this.chartPngBase64 = chartPngBase64;
    }

    public String getResultDataJson() {
        return resultDataJson;
    }

    public void setResultDataJson(String resultDataJson) {
        this.resultDataJson = resultDataJson;
    }

    public List<ConvertTableDataDto> getResultDataList() {
        return resultDataList;
    }

    public void setResultDataList(List<ConvertTableDataDto> resultDataList) {
        this.resultDataList = resultDataList;
    }

    public String getTableTypeChartName() {
        return tableTypeChartName;
    }

    public void setTableTypeChartName(String tableTypeChartName) {
        this.tableTypeChartName = tableTypeChartName;
    }

    private List<HcrpDimensionFormula> secFormulaList;

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public List<HcrpDimensionFormula> getSecFormulaList() {
        return secFormulaList;
    }

    public void setSecFormulaList(List<HcrpDimensionFormula> secFormulaList) {
        this.secFormulaList = secFormulaList;
    }

    public List<HcrpDimension> getSecList() {
        return secList;
    }

    public void setSecList(List<HcrpDimension> secList) {
        this.secList = secList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName == null ? null : dimensionName.trim();
    }

    public String getDimensionStair() {
        return dimensionStair;
    }

    public void setDimensionStair(String dimensionStair) {
        this.dimensionStair = dimensionStair == null ? null : dimensionStair.trim();
    }

    public String getDimensionSecond() {
        return dimensionSecond;
    }

    public void setDimensionSecond(String dimensionSecond) {
        this.dimensionSecond = dimensionSecond == null ? null : dimensionSecond.trim();
    }

    public Integer getSortOn() {
        return sortOn;
    }

    public void setSortOn(Integer sortOn) {
        this.sortOn = sortOn;
    }

    public String getDimensionHtml() {
        return dimensionHtml;
    }

    public void setDimensionHtml(String dimensionHtml) {
        this.dimensionHtml = dimensionHtml == null ? null : dimensionHtml.trim();
    }
}