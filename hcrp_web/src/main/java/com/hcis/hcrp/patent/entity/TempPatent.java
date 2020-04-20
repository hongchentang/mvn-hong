package com.hcis.hcrp.patent.entity;

import com.hcis.ipanther.common.excel.model.ExcelModel;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;


/**
 * @author z
 */
public class TempPatent extends BaseEntity {
   /* private String id;*/

    private String patentName;

/*    private String patentChineseName;

    private String patentEnglishName;

    private String abs;

    private String absChinese;

    private String absEnglish;

    private String claimsCl;*/

    private String claimsQuantity;

    private String independentClaimsQuantity;

    private String pdfFullTextQuantity;

//  private String indexTif;

    private String appNumber;

    /* private String pubNumber;*/
    private String appDate;


    private String pubDate;

    private String firstPubDate;

    private String country;

    private String patentType;

    private String grantDate;

    private String expireDate;

    private String priority;

    private String firstPriorityDate;

    private String iapp;

    private String ipub;

    private String den;

    private String application;

    private String applicationArea;

    private String applicationAddress;

    private String applicationType;

    private String applicationQuantity;

    private String patentee;

    private String patenteeArea;

    private String patenteeAddress;

    private String patenteeType;

    private String patenteeQuantity;

    private String inventor;

    private String inventors;

    private String censor;

    private String agentName;

    private String agencyName;

    private String ipc;

    private String mainIpc;

    private String ipcQuantity;

    private String cpc;

    private String mainCpc;

    private String cpcQuantity;

    private String designClass;

    private String legalStatus;

    private Boolean isGrant;

    private String pkpuStatus;

    private String survivalPeriod;

    private String expectedResidualLife;

    private String transferStatus;

    private String licenseStatus;

    private String pledgeStatus;

    private String reexamInvalidStatus;

    private String lawsuitStatus;

    private String basicPatentFamily;

    private String basicPatentFamilyQuantity;

    private String extendedPatentFamily;

    private String extendedPatentFamilyQuantity;

    private String patentCitation;

    private String patentCitationQuantity;

    private String patentCited;

    private String patentCitedQuantity;

    private String overallMerit;

    private String technicalIndex;

    private String claimsIndex;

    private String marketIndex;


    // 非数据库字段 //

    private String chapterId;

    private Integer sumSor;

    public Integer getSumSor() {
        return sumSor;
    }

    public void setSumSor(Integer sumSor) {
        this.sumSor = sumSor;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName == null ? null : patentName.trim();
    }

/*    public String getPatentChineseName() {
        return patentChineseName;
    }

    public void setPatentChineseName(String patentChineseName) {
        this.patentChineseName = patentChineseName == null ? null : patentChineseName.trim();
    }

    public String getPatentEnglishName() {
        return patentEnglishName;
    }

    public void setPatentEnglishName(String patentEnglishName) {
        this.patentEnglishName = patentEnglishName == null ? null : patentEnglishName.trim();
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs == null ? null : abs.trim();
    }

    public String getAbsChinese() {
        return absChinese;
    }

    public void setAbsChinese(String absChinese) {
        this.absChinese = absChinese == null ? null : absChinese.trim();
    }

    public String getAbsEnglish() {
        return absEnglish;
    }

    public void setAbsEnglish(String absEnglish) {
        this.absEnglish = absEnglish == null ? null : absEnglish.trim();
    }

    public String getClaimsCl() {
        return claimsCl;
    }

    public void setClaimsCl(String claimsCl) {
        this.claimsCl = claimsCl == null ? null : claimsCl.trim();
    }*/

    public String getClaimsQuantity() {
        return claimsQuantity;
    }

    public void setClaimsQuantity(String claimsQuantity) {
        this.claimsQuantity = claimsQuantity == null ? null : claimsQuantity.trim();
    }

    public String getIndependentClaimsQuantity() {
        return independentClaimsQuantity;
    }

    public void setIndependentClaimsQuantity(String independentClaimsQuantity) {
        this.independentClaimsQuantity = independentClaimsQuantity == null ? null : independentClaimsQuantity.trim();
    }

    public String getPdfFullTextQuantity() {
        return pdfFullTextQuantity;
    }

    public void setPdfFullTextQuantity(String pdfFullTextQuantity) {
        this.pdfFullTextQuantity = pdfFullTextQuantity == null ? null : pdfFullTextQuantity.trim();
    }
    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber == null ? null : appNumber.trim();
    }

/*    public String getIndexTif() {
        return indexTif;
    }

    public void setIndexTif(String indexTif) {
        this.indexTif = indexTif == null ? null : indexTif.trim();
    }



    public String getPubNumber() {
        return pubNumber;
    }

    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber == null ? null : pubNumber.trim();
    }*/

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getIapp() {
        return iapp;
    }

    public void setIapp(String iapp) {
        this.iapp = iapp == null ? null : iapp.trim();
    }

    public String getIpub() {
        return ipub;
    }

    public void setIpub(String ipub) {
        this.ipub = ipub == null ? null : ipub.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public void setApplicationArea(String applicationArea) {
        this.applicationArea = applicationArea == null ? null : applicationArea.trim();
    }

    public String getApplicationAddress() {
        return applicationAddress;
    }

    public void setApplicationAddress(String applicationAddress) {
        this.applicationAddress = applicationAddress == null ? null : applicationAddress.trim();
    }

    public String getApplicationQuantity() {
        return applicationQuantity;
    }

    public void setApplicationQuantity(String applicationQuantity) {
        this.applicationQuantity = applicationQuantity == null ? null : applicationQuantity.trim();
    }

    public String getPatentee() {
        return patentee;
    }

    public void setPatentee(String patentee) {
        this.patentee = patentee == null ? null : patentee.trim();
    }

    public String getPatenteeArea() {
        return patenteeArea;
    }

    public void setPatenteeArea(String patenteeArea) {
        this.patenteeArea = patenteeArea == null ? null : patenteeArea.trim();
    }

    public String getPatenteeAddress() {
        return patenteeAddress;
    }

    public void setPatenteeAddress(String patenteeAddress) {
        this.patenteeAddress = patenteeAddress == null ? null : patenteeAddress.trim();
    }

    public String getPatenteeQuantity() {
        return patenteeQuantity;
    }

    public void setPatenteeQuantity(String patenteeQuantity) {
        this.patenteeQuantity = patenteeQuantity == null ? null : patenteeQuantity.trim();
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor == null ? null : inventor.trim();
    }

    public String getInventors() {
        return inventors;
    }

    public void setInventors(String inventors) {
        this.inventors = inventors == null ? null : inventors.trim();
    }

    public String getCensor() {
        return censor;
    }

    public void setCensor(String censor) {
        this.censor = censor == null ? null : censor.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    public String getIpc() {
        return ipc;
    }

    public void setIpc(String ipc) {
        this.ipc = ipc == null ? null : ipc.trim();
    }

    public String getMainIpc() {
        return mainIpc;
    }

    public void setMainIpc(String mainIpc) {
        this.mainIpc = mainIpc == null ? null : mainIpc.trim();
    }

    public String getIpcQuantity() {
        return ipcQuantity;
    }

    public void setIpcQuantity(String ipcQuantity) {
        this.ipcQuantity = ipcQuantity == null ? null : ipcQuantity.trim();
    }

    public String getCpc() {
        return cpc;
    }

    public void setCpc(String cpc) {
        this.cpc = cpc == null ? null : cpc.trim();
    }

    public String getMainCpc() {
        return mainCpc;
    }

    public void setMainCpc(String mainCpc) {
        this.mainCpc = mainCpc == null ? null : mainCpc.trim();
    }

    public String getCpcQuantity() {
        return cpcQuantity;
    }

    public void setCpcQuantity(String cpcQuantity) {
        this.cpcQuantity = cpcQuantity == null ? null : cpcQuantity.trim();
    }

    public String getDesignClass() {
        return designClass;
    }

    public void setDesignClass(String designClass) {
        this.designClass = designClass == null ? null : designClass.trim();
    }

    public Boolean getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Boolean isGrant) {
        this.isGrant = isGrant;
    }

    public String getSurvivalPeriod() {
        return survivalPeriod;
    }

    public void setSurvivalPeriod(String survivalPeriod) {
        this.survivalPeriod = survivalPeriod == null ? null : survivalPeriod.trim();
    }

    public String getExpectedResidualLife() {
        return expectedResidualLife;
    }

    public void setExpectedResidualLife(String expectedResidualLife) {
        this.expectedResidualLife = expectedResidualLife == null ? null : expectedResidualLife.trim();
    }

    public String getBasicPatentFamily() {
        return basicPatentFamily;
    }

    public void setBasicPatentFamily(String basicPatentFamily) {
        this.basicPatentFamily = basicPatentFamily == null ? null : basicPatentFamily.trim();
    }

    public String getBasicPatentFamilyQuantity() {
        return basicPatentFamilyQuantity;
    }

    public void setBasicPatentFamilyQuantity(String basicPatentFamilyQuantity) {
        this.basicPatentFamilyQuantity = basicPatentFamilyQuantity == null ? null : basicPatentFamilyQuantity.trim();
    }

    public String getExtendedPatentFamily() {
        return extendedPatentFamily;
    }

    public void setExtendedPatentFamily(String extendedPatentFamily) {
        this.extendedPatentFamily = extendedPatentFamily == null ? null : extendedPatentFamily.trim();
    }

    public String getExtendedPatentFamilyQuantity() {
        return extendedPatentFamilyQuantity;
    }

    public void setExtendedPatentFamilyQuantity(String extendedPatentFamilyQuantity) {
        this.extendedPatentFamilyQuantity = extendedPatentFamilyQuantity == null ? null : extendedPatentFamilyQuantity.trim();
    }

    public String getPatentCitation() {
        return patentCitation;
    }

    public void setPatentCitation(String patentCitation) {
        this.patentCitation = patentCitation == null ? null : patentCitation.trim();
    }

    public String getPatentCitationQuantity() {
        return patentCitationQuantity;
    }

    public void setPatentCitationQuantity(String patentCitationQuantity) {
        this.patentCitationQuantity = patentCitationQuantity == null ? null : patentCitationQuantity.trim();
    }

    public String getPatentCited() {
        return patentCited;
    }

    public void setPatentCited(String patentCited) {
        this.patentCited = patentCited == null ? null : patentCited.trim();
    }

    public String getPatentCitedQuantity() {
        return patentCitedQuantity;
    }

    public void setPatentCitedQuantity(String patentCitedQuantity) {
        this.patentCitedQuantity = patentCitedQuantity == null ? null : patentCitedQuantity.trim();
    }

    public String getOverallMerit() {
        return overallMerit;
    }

    public void setOverallMerit(String overallMerit) {
        this.overallMerit = overallMerit == null ? null : overallMerit.trim();
    }

    public String getTechnicalIndex() {
        return technicalIndex;
    }

    public void setTechnicalIndex(String technicalIndex) {
        this.technicalIndex = technicalIndex == null ? null : technicalIndex.trim();
    }

    public String getClaimsIndex() {
        return claimsIndex;
    }

    public void setClaimsIndex(String claimsIndex) {
        this.claimsIndex = claimsIndex == null ? null : claimsIndex.trim();
    }

    public String getMarketIndex() {
        return marketIndex;
    }

    public void setMarketIndex(String marketIndex) {
        this.marketIndex = marketIndex == null ? null : marketIndex.trim();
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getFirstPubDate() {
        return firstPubDate;
    }

    public void setFirstPubDate(String firstPubDate) {
        this.firstPubDate = firstPubDate;
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public String getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(String grantDate) {
        this.grantDate = grantDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getFirstPriorityDate() {
        return firstPriorityDate;
    }

    public void setFirstPriorityDate(String firstPriorityDate) {
        this.firstPriorityDate = firstPriorityDate;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getPatenteeType() {
        return patenteeType;
    }

    public void setPatenteeType(String patenteeType) {
        this.patenteeType = patenteeType;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public Boolean getGrant() {
        return isGrant;
    }

    public void setGrant(Boolean grant) {
        isGrant = grant;
    }

    public String getPkpuStatus() {
        return pkpuStatus;
    }

    public void setPkpuStatus(String pkpuStatus) {
        this.pkpuStatus = pkpuStatus;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getLicenseStatus() {
        return licenseStatus;
    }

    public void setLicenseStatus(String licenseStatus) {
        this.licenseStatus = licenseStatus;
    }

    public String getPledgeStatus() {
        return pledgeStatus;
    }

    public void setPledgeStatus(String pledgeStatus) {
        this.pledgeStatus = pledgeStatus;
    }

    public String getReexamInvalidStatus() {
        return reexamInvalidStatus;
    }

    public void setReexamInvalidStatus(String reexamInvalidStatus) {
        this.reexamInvalidStatus = reexamInvalidStatus;
    }

    public String getLawsuitStatus() {
        return lawsuitStatus;
    }

    public void setLawsuitStatus(String lawsuitStatus) {
        this.lawsuitStatus = lawsuitStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempPatent patent = (TempPatent) o;
        return Objects.equals(appNumber, patent.appNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appNumber);
    }

    public Object getValueByFile(String filed) throws IllegalAccessException {

        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field queryFile = null;

        for(Field f : fields){
            if(f.getName().equals(filed)){
                queryFile = f;
                break;
            }
        }

        if (queryFile != null) {
            return queryFile.get(this);
        }

        return null;
    }

    public void setValueByFile(String filed, Object obj) throws IllegalAccessException {

        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field queryFile = null;

        for(Field f : fields){
            if(f.getName().equals(filed)){
                queryFile = f;
                break;
            }
        }

        if (queryFile != null) {
            queryFile.set(this, obj);
        }
    }
}