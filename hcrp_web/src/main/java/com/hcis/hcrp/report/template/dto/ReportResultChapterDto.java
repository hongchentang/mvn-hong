package com.hcis.hcrp.report.template.dto;

import java.util.List;

/**
 * @author zhw
 * @date 2020/4/9
 **/
public class ReportResultChapterDto {

    private String id;

    private String name;

    private List<ReportResultDimensionDto> dimensions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportResultDimensionDto> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<ReportResultDimensionDto> dimensions) {
        this.dimensions = dimensions;
    }
}
