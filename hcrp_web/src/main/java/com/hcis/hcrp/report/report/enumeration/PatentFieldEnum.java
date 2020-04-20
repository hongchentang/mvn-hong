package com.hcis.hcrp.report.report.enumeration;

/**
 * @author zhw
 * @date 2020/3/17
 **/
public enum PatentFieldEnum {
    /**
     * 专利表的专利字段
     */
    PATENT_NAME(0, "专利名称" ,"patentName"),
    PATENT_CHINESE_NAME(1, "专利中文翻译名" ,"patentChineseName"),
    PATENT_ENGLISH_NAME(2, "专利英文翻译名" ,"patentEnglishName"),
    ABS(3, "摘要" ,"ABS"),
    ABS_CHINESE(4, "摘要中文翻译" ,"absChinese"),
    ABS_ENGLISH(5, "摘要英文翻译" ,"absEnglish"),
    CLAIMS_CL(6, "权利要求主权项" ,"claimsCl"),
    CLAIMS_QUANTITY(7, "权利要求项数" ,"claimsQuantity"),
    INDEPENDENT_CLAIMS_QUANTITY(8, "独立权利要求项数（独权数）" ,"independentClaimsQuantity"),
    PDF_FULL_TEXT_QUANTITY(9, "PDF全文页数" ,"pdfFullTextQuantity"),
    INDEX_TIF(10, "首页附图" ,"indexTif"),
    APP_NUMBER(11, "申请号" ,"appNumber"),
    APP_DATE(12, "申请日" ,"appDate"),
    PUB_NUMBER(13, "公开号" ,"pubNumber"),
    PUB_DATE(14, "公开日" ,"pubDate"),
    FIRST_PUB_DATE(15, "首次公开日" ,"firstPubDate"),
    COUNTRY(16, "国家/地区" ,"country"),
    PATENT_TYPE(17, "专利类型" ,"patentType"),
    GRANT_DATE(18, "授权日" ,"grantDate"),
    EXPIRE_DATE(19, "失效日" ,"expireDate"),
    PRIORITY(20, "优先权" ,"priority"),
    FIRST_PRIORITY_DATE(21, "最早优先权日" ,"firstPriorityDate"),
    IAPP(22, "国际申请" ,"iapp"),
    IPUB(23, "国际公布" ,"ipub"),
    DEN(24, "进入国家阶段日" ,"den"),
    APPLICATION(25, "申请人" ,"application"),
    APPLICATION_AREA(26, "申请人归属地" ,"applicationArea"),
    APPLICATION_ADDRESS(27, "申请人地址" ,"applicationAddress"),
    APPLICATION_TYPE(28, "申请人类型" ,"applicationType"),
    APPLICATION_QUANTITY(29, "申请人数量" ,"applicationQuantity"),
    PATENTEE(30, "专利权人" ,"patentee"),
    PATENTEE_AREA(31, "专利权人归属地" ,"patenteeArea"),
    PATENTEE_ADDRESS(32, "专利权人地址" ,"patenteeAddress"),
    PATENTEE_TYPE(33, "专利权人类型" ,"patenteeType"),
    PATENTEE_QUANTITY(34, "专利权人数量" ,"patenteeQuantity"),
    INVENTOR(35, "发明人" ,"inventor"),
    INVENTORS(36, "发明人数量" ,"inventors"),
    CENSOR(37, "审查员" ,"censor"),
    AGENT_NAME(38, "代理人" ,"agentName"),
    AGENCY_NAME(39, "代理机构" ,"agencyName"),
    IPC(40, "IPC分类号" ,"ipc"),
    MAIN_IPC(41, "主IPC分类号" ,"mainIpc"),
    IPC_QUANTITY(42, "IPC分类号数量" ,"ipcQuantity"),
    CPC(43, "CPC分类号" ,"cpc"),
    MAIN_CPC(44, "主CPC分类号" ,"mainCpc"),
    CPC_QUANTITY(45, "CPC分类号数量" ,"cpcQuantity"),
    DESIGN_CLASS(46, "外观设计分类号" ,"designClass"),
    LEGAL_STATUS(47, "法律效力" ,"legalStatus"),
    IS_GRANT(48, "是否曾经授权" ,"isGrant"),
    PKPU_STATUS(49, "公知公用状态" ,"pkpuStatus"),
    SURVIVAL_PERIOD(50, "存活期" ,"survivalPeriod"),
    EXPECTED_RESIDUAL_LIFE(51, "预期剩余寿命" ,"expectedResidualLife"),
    TRANSFER_STATUS(52, "转让状态" ,"transferStatus"),
    LICENSE_STATUS(53, "许可状态" ,"licenseStatus"),
    PLEDGE_STATUS(54, "质押状态" ,"pledgeStatus"),
    REEXAM_INVALID_STATUS(55, "复审/无效状态" ,"reexamInvalidStatus"),
    LAWSUIT_STATUS(56, "诉讼状态" ,"lawsuitStatus"),
    BASIC_PATENT_FAMILY(57, "基本专利族" ,"basicPatentFamily"),
    BASIC_PATENT_FAMILY_QUANTITY(58, "基本专利族专利数量" ,"basicPatentFamilyQuantity"),
    EXTENDED_PATENT_FAMILY(59, "扩展专利族" ,"extendedPatentFamily"),
    EXTENDED_PATENT_FAMILY_QUANTITY(60, "扩展专利族专利数量" ,"extendedPatentFamilyQuantity"),
    PATENT_CITATION(61, "本专利引用" ,"patentCitation"),
    PATENT_CITATION_QUANTITY(62, "本专利引用数量" ,"patentCitationQuantity"),
    PATENT_CITED(63, "本专利被引" ,"patentCited"),
    PATENT_CITED_QUANTITY(64, "本专利被引用数量" ,"patentCitedQuantity"),
    OVERALL_MERIT(65, "综合评价" ,"overallMerit"),
    TECHNICAL_INDEX(66, "技术指标" ,"technicalIndex"),
    CLAIMS_INDEX(67, "权利指标" ,"claimsIndex"),
    MARKET_INDEX(68, "市场指标" ,"marketIndex");


    private Integer sort;

    private String fieldName;

    private String field;

    public static PatentFieldEnum valueOfV2(String fieldName){
        PatentFieldEnum[] fieldEnums = PatentFieldEnum.values();

        for(PatentFieldEnum f : fieldEnums){

            if(f.fieldName.equals(fieldName)){
                return f;
            }
        }

        throw new IllegalArgumentException("不存在的字段");
    }

    PatentFieldEnum(Integer sort, String fieldName, String field) {
        this.sort = sort;
        this.fieldName = fieldName;
        this.field = field;
    }

    public Integer getSort() {
        return sort;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getField() {
        return field;
    }
}
