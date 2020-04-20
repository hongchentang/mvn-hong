package com.hcis.hcrp.report.template.dto;

import com.hcis.ipanther.core.utils.CommonConfig;

import java.util.List;

/**
 * @author zhw
 * @date 2020/4/9
 **/
public class ReportResultPublicInfo {
    private String header = CommonConfig.getProperty("report.pubInfo.header");
    private String location = CommonConfig.getProperty("report.pubInfo.location");
    private String encoding = CommonConfig.getProperty("report.pubInfo.encoding");
    private String type = CommonConfig.getProperty("report.pubInfo.type");

    public String getHeader() {
        return header;
    }

    public String getLocation() {
        return location;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getType() {
        return type;
    }
}
