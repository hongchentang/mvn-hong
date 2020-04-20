<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="allocationForm" name="allocationForm" action="${ctx}/common/user/employees/allocation.do" method="post" enctype="multipart/form-data">
	<div class="easyui-panel" title="" collapsible="true">
		<input id="selectIds" name="selectIds" style="display: none;">
		<input id="userId" name="userId" style="display: none;" value="${userId}">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
			<tr>
				<td>子系统名称</td>
				<td>是否分配</td>
			</tr>
			<c:forEach var="client" items="${clients}" >
				<tr>
					<td>${client.clientName}</td>
					<td>
						<c:if test="${client.config > 0}">
							<input id="select" type="checkbox" name="select" clientId="${client.clientId}" checked onchange="selectClient()">
						</c:if>
						<c:if test="${client.config <= 0}">
							<input id="select" type="checkbox" name="select" clientId="${client.clientId}" onchange="selectClient()">
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>

			</tr>
		</table>
	</div>
	
	<div style="text-align: center; margin-top: 10px;">
		<button type="button" onclick="saveAllocation()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重置 </button>
	</div>
</form>
<script type="text/javascript">
 //tableVBg('.alter-table-v');
$(document).ready(function (){
});

function saveAllocation() {

	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			// x
			$("#allocationForm").ajaxSubmit({
				type: 'post',  
				success: function(json){
					debugger
					if(json.code == 200){
						jQuery.messager.alert("提示信息","保存成功");
						closeWindow('allocationDialog');
					} else {
						//错误
						jQuery.messager.alert("提示信息",json.msg);
					}
				}
			});
		}
		
	});
}

function test() {

}

function selectClient() {
	var arr = $('#allocationForm').find("input[type=checkbox]");
	var ids = '';
	arr.each(function (key, val) {
		var v = $(val);
		var select = v.is(":checked");
		if(select){
			var sId = val.getAttribute('clientId');
			ids = ids + sId + ',';
		}
	});

	$('#selectIds').val(ids);
	debugger
}
</script>
