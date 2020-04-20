<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="patentDetail">
    <table style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <tr>
            <td style="width: 25%;">平台名称：</td>
            <td style="width: 50%;" colspan="">${client.clientKey}</td>
            <td style="width: 25%;">访问地址：</td>
            <td style="width: 50%;" colspan="">${client.clientUrl}</td>
        </tr>
        <tr>
            <td style="width: 25%">访问方式：</td>
            <td style="width: 40%">xx</td>
            <td>是否启用：</td>
            <td>启用中</td>
        </tr>
        <tr>
            <td >平台图标：</td>
            <td >${client.clientIcon  }</td>
            <td>平台描述	：</td>
            <td>${client.clientDesc}</td>
        </tr>
    </table>

</div>