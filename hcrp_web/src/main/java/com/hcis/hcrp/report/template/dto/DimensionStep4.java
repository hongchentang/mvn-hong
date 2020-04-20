package com.hcis.hcrp.report.template.dto;

import com.hcis.hcrp.report.template.entity.HcrpDimension;

import java.util.List;

/**
 * @author zhw
 * @date 2020/3/27
 **/
public class DimensionStep4 {

    private List<HcrpDimension> list;

    private String first;

    private List<HcrpDimension> allSecDimension;

    public List<HcrpDimension> getList() {
        return list;
    }

    public void setList(List<HcrpDimension> list) {
        this.list = list;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public List<HcrpDimension> getAllSecDimension() {
        return allSecDimension;
    }

    public void setAllSecDimension(List<HcrpDimension> allSecDimension) {
        this.allSecDimension = allSecDimension;
    }
}
