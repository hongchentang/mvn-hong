package com.hcis.hcrp.report.template.entity;

import com.hcis.hcrp.report.template.dto.RtcTableFiledGroupDto;
import com.hcis.hcrp.report.template.dto.TableResultDto;
import com.hcis.ipanther.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * @author z
 */
public class RtcTable extends BaseEntity {


    private String tableName;

    private Integer tableType;

    /* 非数据库字段 */

    /**
     * 章节图标的每个字段
    */
    private List<RtcTableField> filedList;

    private List<RtcTableFiledGroupDto> filedGroupList;

    private List<List<TableResultDto>> dataResult;

    private String tableTypeName;

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }

    public List<List<TableResultDto>> getDataResult() {
        return dataResult;
    }

    public void setDataResult(List<List<TableResultDto>> dataResult) {
        this.dataResult = dataResult;
    }

    public List<RtcTableFiledGroupDto> getFiledGroupList() {
        return filedGroupList;
    }

    public void setFiledGroupList(List<RtcTableFiledGroupDto> filedGroupList) {
        this.filedGroupList = filedGroupList;
    }

    public List<RtcTableField> getFiledList() {
        return filedList;
    }

    public void setFiledList(List<RtcTableField> filedList) {
        this.filedList = filedList;
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}