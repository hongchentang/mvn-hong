<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<form id="listClientForm" action="${ctx}/client/list.do?tabId=${param.tabId}" method="post">
	<div class="easyui-panel" title="" data-options="" style="width:100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px" class="model_interval">
				<tr>
					<td style="width: auto;">系统名称：</td>
					<td><input type='text' name="paramMap[clientKey]" class="easyui-validatebox"  value="${searchParam.paramMap.clientKey}"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshPatent()" iconCls="fa fa-search">查询</a>
					</td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" class="">
				<tbody>
					<tr>
						<td><a onclick="addClient()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="viewClient()" href="javascript:void(0)" class="easyui-linkbutton">查看</a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="editClient()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="deleteClient()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table id="listClientTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false" fitColumns="false" >
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="150" data-options="field:'clientName', align:'center'">平台名称</th>
						<th width="300" data-options="field:'clientUrl', align:'center'">访问地址</th>
						<th width="100" data-options="field:'clientType', align:'center'">访问方式</th>
						<th width="200" data-options="field:'isRunning', align:'center'">是否启用</th>
						<th width="200" data-options="field:'clientIcon', align:'center'">平台图标</th>
						<th width="300" data-options="field:'clientDesc', align:'center'">平台描述</th>
					</tr>                                 
				</thead>
				<tbody>
					<c:forEach items="${clients}" var="client">
						<tr>
							<td>${client.id}</td>
							<td>${client.clientKey}</td>
							<td>${client.clientUrl}</td>
							<td>页面跳转</td>
							<td>启用中</td>
							<td>${client.clientIcon}</td>
							<td>${client.clientDesc}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listClientForm" name="pageForm" />
				<jsp:param value="listClientTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value='${param.tabId}' name="divId" />
			</jsp:include>
		</div>
	</div>
</form>

<style type="text/css">
	.model_interval{
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.xu_interval{
		padding-right: 5px;
	}
	.textCenter{
		text-align: center;
	}
</style>

<script type="text/javascript">
	$(document).ready(function () {
		
	});
	
	function addClient() {
		openWindow('addClientWindow','新增子系统',800,300,'${ctx}/client/goAddClient.do',true);
	}
	
	function editClient() {
        var objects = $('#listClientTable').datagrid('getSelections');
        if(objects.length == 0){
            $.messager.alert('消息','请选择要修改的数据');
            return false;
        }

        var id = objects[0].id;
        openWindow('editClientWindow','修改子系统',800,300,'${ctx}/client/goAddClient.do?id=' + id,true);
	}
	
	function viewClient() {
        var objects = $('#listClientTable').datagrid('getSelections');
        if(objects.length == 0){
            $.messager.alert('消息','请选择要查看的数据');
            return false;
        }
        var id = objects[0].id;

        openWindow('viewClientWindow','查看子系统',800,300,'${ctx}/client/viewClient.do?id=' + id,true);
	}
	
	function deleteClient() {
        var objects = $('#listClientTable').datagrid('getSelections');
        if(objects.length == 0){
            $.messager.alert('消息','请选择要删除的数据');
            return false;
        }
        var id = objects[0].id;
        $.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
            if(flag){
                $.ajax({
                    url:'${ctx}/client/deleteClient.do?id='+id,
                    type:'post',
                    success:function(){
                        $.messager.alert('提示','操作成功！');
                        jQuery('#'+getCurrentTabId()).panel('refresh');
                    }
                });
            }
        });
	}
	
	function closeClientWin() {
		closeWindow("addClientWindow");
		closeWindow("editClientWindow");
		closeWindow("viewClientWindow");
	}
	
	function refreshClientList() {
        var tabId = $("#layout_center_tabs").tabs('getSelected').attr('id');
        easyuiUtils.ajaxPostFormForPanel('listClientForm', tabId);
	}
</script>