package com.hcis.hcrp.report.report.enumeration;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author zhw
 * @date 2020/3/12
 **/
public enum TableTypeEnum {
    /**
     * 每个图表的
     */
    HISTOGRAM(0, "histogramChart","柱状图"),
    LINE_CHART(1, "lineChart","折线图"),
    PIE_CHART(2, "pieChart","饼图"),
    CIRCLE_CHART(3, "circleChart","圆环图"),
    BUBBLE_CHART(4, "bubbleChart","气泡图"),
    BAR_CHART(5, "barChart","条形图"),
    RADAR_CHART(6, "radarChart","雷达图"),
    MAP_CHART(7, "mapChart","地图"),
    SCATTER_DIAGRAM_CHART(8, "scatterDiagramChart","散点图"),
    AREA_CHART(9, "areaChart","面积图");

    private Integer type;

    private String name;

    private String field;

    TableTypeEnum(Integer type, String field, String name) {
        this.type = type;
        this.name = name;
        this.field = field;
    }

    public static TableTypeEnum valueOf(Integer type){
        TableTypeEnum[] typeEnums = TableTypeEnum.values();
        for(TableTypeEnum tableTypeEnum : typeEnums){
            if(tableTypeEnum.getType().equals(type)){
                return tableTypeEnum;
            }
        }
        throw new IllegalArgumentException("找不到图表类型:" + type);
    }

    public String getField() {
        return field;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
