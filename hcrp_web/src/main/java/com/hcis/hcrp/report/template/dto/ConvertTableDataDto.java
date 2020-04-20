package com.hcis.hcrp.report.template.dto;

import java.util.List;

/**
 * @author zhw
 * @date 2020/3/30
 **/
public class ConvertTableDataDto {

    private String tableTrName;

    private List<TableResultDto> resultDtoList;

    public String getTableTrName() {
        return tableTrName;
    }

    public void setTableTrName(String tableTrName) {
        this.tableTrName = tableTrName;
    }

    public List<TableResultDto> getResultDtoList() {
        return resultDtoList;
    }

    public void setResultDtoList(List<TableResultDto> resultDtoList) {
        this.resultDtoList = resultDtoList;
    }
}
