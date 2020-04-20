<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<div id="layout1" style="padding-top: 10px;padding-left:0px;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="clientForm" method="post" id="clientForm" action="${ctx}/client/add.do" enctype="multipart/form-data">
        <%--放修改时候的ID--%>
        <input type="text" id="id" name="id" style="display: none;" value='' />
        <div class="easyui-panel" title="" collapsible="true">
            <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td>平台名称*</td>
                    <td class="td_long">
                        <input id="clientKey" name="clientKey" class="easyui-textbox" type="text" title="平台名称" data-options="required:true"/>
                    </td>
                    <td>访问地址*</td>
                    <td class="td_long">
                        <input id="clientUrl" name="clientUrl" class="easyui-textbox" type="text" title="访问地址" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>访问方式*</td>
                    <td class="td_long">
                        <input id="clientType" name="clientType" class="easyui-textbox" type="text" title="访问方式" data-options="required:true"/>
                    </td>
                    <td>是否启用*</td>
                    <td class="td_long">
                        <input id="isRunning" name="isRunning" class="easyui-textbox" type="text" title="是否启用" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">平台图标</td>
                    <td colspan="3" class="" id="icon">
                        <div id="upload1">
                            <c:if test="${not empty client.clientIcon}">
                                文件：${client.clientIcon}
                                <a onclick="deleteAttachment()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a>
                            </c:if>
                        </div>
                        <c:if test="${empty client.clientIcon}">
                            <div id="upload2">
                                <input name="file" type="file" id="file" multiple="multiple" style="width: 90%;"/>
                                <input name="isDeletedAtta" id="isDeletedAtta" value="false" type="text" style="display: none;" >
                            </div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">平台描述</td>
                    <td class="td_long" colspan="3">
                        <input id="clientDesc" name="clientDesc" class="easyui-textbox" type="text" title="平台描述" data-options="required:false, width: 560"/>
                    </td>
                </tr>
            </table>
        </div>
        <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
            <button type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton l-btn" style="margin-right: 20px;">确认</button>
            <button type="reset"  value="关闭" id="subClose" class="easyui-linkbutton l-btn" onclick="closeClientWin()" >关闭</button>
        </div>
    </form>
</div>
<style type="text/css">
    .under_border{
        height: auto!important;
        min-height: 30px;
        width: 90%;
        background-color: aliceblue;
        border: inset;
        border-top-width: 2px;
        border-right-width: 2px;
        border-bottom-width: 2px;
        border-left-width: 2px;
    }
    .file_d{
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .file_ul{
        margin-block-start: 0em;
        padding-inline-start: 10px;
    }
    .td_short{
        width: 17%;
    }
    .td_long{
        width: 33%;
    }
    .tip_d{
        width: 40%;
        padding-top: 5px;
        padding-left: 15px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        console.log('document ready!...${client.clientKey}');
        /* 加载修改的数据 */
        if ('${client.id}' !== '') {
            $('#clientForm').form('load', {
                id:'${client.id}',
                clientKey:'${client.clientKey}',
                clientUrl:'${client.clientUrl}',
                clientType:'xx',
                isRunning:'启用中',
                clientDesc:'${client.clientDesc}'
            });
        }
    });



    /*提交表单*/
    function submitForm() {

        //进行id赋值
        var id = "${client.id}";
        console.log("ID = " + id);
        $("#id").val(id);
        debugger
        $("#addClientWindow,#editClientWindow").find('#clientForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
                    /*刷新列表*/
                    refreshClientList();
                    /*关闭窗口*/
                    closeClientWin();
                } else {
                    $.messager.alert('错误','保存失败' + data.msg);
                }
            }
        });
    }
    
    function deleteAttachment() {
        $("#upload1").remove();
        $('#icon').append('<div id="upload2">\n' +
            '<input name="file" type="file" id="file" multiple="multiple" style="width: 90%;"/>\n' +
            '<input name="isDeletedAtta" id="isDeletedAtta" value="true" type="text" style="display: none;" >\n' +
            '</div>');
    }
</script>
