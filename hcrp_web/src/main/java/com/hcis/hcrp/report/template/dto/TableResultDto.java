package com.hcis.hcrp.report.template.dto;

import com.hcis.hcrp.report.template.entity.RtcTableField;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author zhw
 * @date 2020/3/9
 **/
public class TableResultDto {

    private String key;

    private Integer sum;

    private BigDecimal percentage;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableResultDto resultDto = (TableResultDto) o;
        return Objects.equals(key, resultDto.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

}
