<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewStudentTopPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewStudentTopListTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewStudentTopPanel',100),cache:false">
		<div id="viewStudentTodoTopTab" data-options="href:'${ctx}/common/user/employees/auditList.do?tabId=viewStudentTodoTopTab&type=Todo'" title="待审批" ></div>
		<div id="viewStudentDoneTopTab" data-options="href:'${ctx}/common/user/employees/auditList.do?tabId=viewStudentDoneTopTab&type=Done'" title="已审批" ></div>
	</div>
</div>
