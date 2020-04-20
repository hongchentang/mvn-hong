package com.hcis.hcrp.config.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author z
 */
public class ClientConfig extends BaseEntity {

    private String clientKey;

    private String clientValue;

    private String clientUrl;

    private BigDecimal sortNo;

    private String clientIcon;

    private String clientDesc;

    /*
    * 非数据库字段
    * */

    private boolean isDeletedAtta;

    public boolean isDeletedAtta() {
        return isDeletedAtta;
    }

    public void setDeletedAtta(boolean deletedAtta) {
        isDeletedAtta = deletedAtta;
    }

    public String getClientDesc() {
        return clientDesc;
    }

    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }

    public String getClientIcon() {
        return clientIcon;
    }

    public void setClientIcon(String clientIcon) {
        this.clientIcon = clientIcon;
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey == null ? null : clientKey.trim();
    }

    public String getClientValue() {
        return clientValue;
    }

    public void setClientValue(String clientValue) {
        this.clientValue = clientValue == null ? null : clientValue.trim();
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl == null ? null : clientUrl.trim();
    }

}