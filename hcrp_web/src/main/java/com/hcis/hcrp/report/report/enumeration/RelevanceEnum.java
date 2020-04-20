package com.hcis.hcrp.report.report.enumeration;

/**
 * @author zhw
 * @date 2020/3/31
 **/
public enum  RelevanceEnum {

    /**
     *
     */
    EQ("等于","EQ"),
    NOT_EQ("不等于","NOT_EQ"),
    IN("在范围","IN"),
    NOT_IN("不在范围","NOT_IN");

    private String name;

    private String val;

    private RelevanceEnum(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public String getVal() {
        return val;
    }
}
