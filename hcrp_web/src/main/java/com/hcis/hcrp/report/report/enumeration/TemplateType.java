package com.hcis.hcrp.report.report.enumeration;

/**

 * @date 2020/3/24
 **/
public enum TemplateType {
    /**
     *
     */
    REPORT_NAVIGATION(0, "专利导航报告"),
    REPORT_WARNING(1, "专利预警报告"),
    REPORT_PUSH(2, "专利信息推送"),
    REPORT_SEARCH(3, "专利性检索报告"),
    REPORT_QUERY(4, "专利查询报告"),
    REPORT_ANALYSIS(5, "知识产权分析评议报告");


    public static TemplateType valueOf(Integer type){
        TemplateType[] types = TemplateType.values();

        for(TemplateType t : types){

            if(t.type.equals(type)){
                return t;
            }
        }

        throw new IllegalArgumentException("不存在的类型");
    }
    public static TemplateType typeNameOf(String typeName){
        TemplateType[] types = TemplateType.values();

        for(TemplateType t : types){

            if(t.typeName.equals(typeName)){
                return t;
            }
        }

        return null;
    }

    TemplateType(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    private Integer type;

    private String typeName;

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
