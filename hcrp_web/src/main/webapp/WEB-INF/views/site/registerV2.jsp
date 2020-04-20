<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>

<div id="register" style="padding-top: 10px;padding-left:0;" class="easyui-panel" data-options="region:'center',title:''">
    <div class="register-mod">
        <form name="registerForm" method="post" id="registerForm" action="${ctx}/site/unitRegister.do" enctype="multipart/form-data" autocomplete="off">

            <label class="login-input-block login-user-block"> <span class="t-bg"></span>
                <input type="text" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'英文及数字组合,6~20个字符'" placeholder="用户名" id="userName" name="userName" value=""/>
            </label>
            <label class="login-input-block login-password-block"> <span class="t-bg"></span>
                <input type="password" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'6~12个字符'" placeholder="密码" id="password" name="password" value=""/>
            </label>
            <label class="login-input-block login-password-block"> <span class="t-bg"></span>
                <input type="password" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'请输入与密码内容一致'" validType="equalTo['password']" placeholder="确认密码" id="confirmPassword" name="confirmPassword" value=""/>
            </label>
            <label class="login-input-block login-user-block"> <span class="t-bg"></span>
                <input type="text" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'请填写手机号', validType:'phone'" placeholder="手机号码" id="unitPhone" name="unitPhone" value=""/>
            </label>
            <label class="login-input-block login-user-block"> <span class="t-bg"></span>
                <input type="text" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'请填写邮箱', validType:'email'" placeholder="电子邮箱" id="unitEmail" name="unitEmail" value=""/>
            </label>
            <label class="login-input-block login-user-block"> <span class="t-bg"></span>
                <input type="text" autocomplete="off"  class="login-input easyui-validatebox" data-options="required:true,missingMessage:'单位名称'" placeholder="单位名称" id="unitName" name="unitName" value=""/>
            </label>

            <div class="btn_div" style="">
                <div class="btn_register" style="" onclick="submit()">
                    <div class="btn_font" style=""> 立即注册 </div>
                </div>
            </div>
        </form>
    </div>

</div>
<style type="text/css">

    .btn_div{
        text-align: center;
        margin-top: 20px;
        display: inline-flex;
        padding-left: 31%;
    }

    .btn_register{
        width: 150px;
        height: 30px;
        border: 1px solid #cccc;
        font-size: 15px;
        border-radius: 9px;
        cursor: pointer;
    }

    .btn_close{
        margin-left: 70px;
        width: 150px;
        height: 30px;
        border: 1px solid #cccc;
        font-size: 15px;
        border-radius: 9px;
        cursor: pointer;
    }

    .btn_font{
        padding-top: 5px;
        height: 30px;
        color: grey;
    }

    .btn_register:hover{
        background-color: #0c7ec0;
    }

    .btn_close:hover{
        background-color: #0c7ec0;
    }

    .btn_font:hover{
        color: #fff0e7;
    }

    .checkCode{
        align: center;
        background: #94bbd4;
        width: 156px;
        height: 30px;
        top: 150px;
        border: 0px;
        cursor: pointer;
        color: #fff;
    }


    .register-mod {
        padding: 15px 25px;
    }

    .register-mod .login-tit {
        font-family:MicrosoftYaHei;
        font-size:22px;
        color:#949494;
        letter-spacing:0;
        text-align:left;
        padding-top: 5px;

    }

    .register-mod .login-alert {
        margin: 15px 0;
        height: 25px;
        line-height: 25px;
        font-size: 12px;
        color: red; }
    .register-mod .login-input-block {
        display: block;
        position: relative;
        height: 25px;
        padding: 10px  10px 10px 50px;
        margin-bottom: 40px;
        border: 1px solid #ccc;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        background-color: #fff;
        box-shadow: inset 0px 2px 5px 0 rgba(0, 0, 0, 0.08);
        cursor: text; }
    .register-mod .login-input-block .t-bg {
        display: block;
        position: absolute;
        top: 10px;
        left: 0;
        width: 38px;
        height: 25px;
        background: url("../../../themes/easyui/themes/skin-blue/images/login-ico.png") 0 0 no-repeat; }
    .register-mode .login-input-block .xx{
        display: block;
        position: absolute;
        top: 10px;
        left: 0;
        width: 38px;
        height: 25px;
        background: url("../../../themes/easyui/themes/skin-blue/images/menuTab.png") 0 0 no-repeat; }
    .register-mod .login-input-block .login-input {
        width: 100%;
        border: none;
        background: #fff;
        outline: none;
        height: 25px;
        line-height: 25px; }
    .register-mod .login-input-block.login-password-block .t-bg {
        background-position: right 0; }
    .register-mod .login-btn {
        display: block;
        width: 100%;
        height: 50px;
        line-height: 50px;
        font-size: 20px;
        font-family: "\5FAE\8F6F\96C5\9ED1";
        color: #fff;
        text-align: center;
        outline: none;
        border: none;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        text-decoration: none;
        background-color: #188dd1;
        background-image: -moz-linear-gradient(#2ba8f0, #188dd1);
        background-image: -o-linear-gradient(#2ba8f0, #188dd1);
        background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #2ba8f0), color-stop(1, #188dd1));
        background-image: -webkit-linear-gradient(#2ba8f0, #188dd1);
        background-image: -ms-linear-gradient(#2ba8f0, #188dd1);
        background-image: linear-gradient(#2ba8f0, #188dd1); }
    .register-mod .login-btn:hover {
        background-color: #2ba8f0;
        background-image: -moz-linear-gradient(#2ba8f0, #2ba8f0);
        background-image: -o-linear-gradient(#2ba8f0, #2ba8f0);
        background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #2ba8f0), color-stop(1, #2ba8f0));
        background-image: -webkit-linear-gradient(#2ba8f0, #2ba8f0);
        background-image: -ms-linear-gradient(#2ba8f0, #2ba8f0);
        background-image: linear-gradient(#2ba8f0, #2ba8f0); }
    .register-mod .login-btn:active {
        line-height: 55px; }


</style>

<script>

    function submit() {

        //校验用户名
        var  userName = $('#userName').val();
        debugger
        checkUserRegex(userName);

        $('#registerForm').form('submit', {
            success: function (data) {
                $.messager.alert('成功', '用户注册成功!');
                closeWindow('registerWinV2');
            },
            error: function (data) {
                $.messager.alert('失败', '用户注册失败!');
            }
        })
    }

    $('#userName').validatebox({
        onChange: function (newVal, oldVal) {
            checkUserRegex(newVal);
        }
    });

    function test() {
       var x = $('#registerWinV2');
       debugger
       var y = x.panel('close');
       debugger
        //closeWindow('registerWinV2');
    }
    
    function checkUserRegex(newVal) {
        if(newVal.length > 0 && newVal.length < 6){
            $('#userName').val('');
            $.messager.alert('用户名错误','请输入大于6个字符的标题');
        }else if(newVal.length > 16){
            $('#userName').val('');
            $.messager.alert('用户名错误','请输入小于于16个字符的标题');
        }else if (newVal.length > 0) {
            //只能输入应用数组和下划线
            var regex = /^[a-zA-Z]([-_a-zA-Z0-9]{6,16})$/;

            if(newVal.match(regex) == '' || newVal.match(regex) == null){
                $('#userName').val('');
                $.messager.alert('用户名错误','错误的用户名:' + newVal + ' ,请按规则输入');
            }
        }
    }

    $.extend($.fn.validatebox.defaults.rules, {
        phone: {
            validator: function (value, param) {
                return /^1[3456789]\d{9}$/.test(value);
            },
            message: '请输入正确的手机号码。'
        }
    });
</script>