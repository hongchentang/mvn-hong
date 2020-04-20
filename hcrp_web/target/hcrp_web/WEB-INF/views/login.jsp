<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta name="viewport" content="width=device-width" />
  <title>报告导出系统</title>
  <link href="./themes/easyui/themes/skin-blue/css/reset.css" rel="stylesheet" type="text/css"/>
  <link href="./themes/easyui/themes/skin-blue/css/reg.css" rel="stylesheet" type="text/css"/>
  <link href="./themes/easyui/themes/skin-blue/css/slidingvalidation.css" rel="stylesheet" type="text/css"/>
  <!-- jQuery -->
  <script src="./themes/easyui/themes/skin-blue/js/jquery-1.10.2.min.js" type="text/javascript"></script>
  <script src="./themes/easyui/themes/skin-blue/js/jplaceholder.js" type="text/javascript"></script>
  <script src="./themes/easyui/themes/skin-blue/js/jquery.validate.min.js" type="text/javascript"></script>
</head>
<body style="background:url(./themes/easyui/themes/skin-blue/images/bg01.png) repeat-y center top;overflow:hidden;">
<div class="g-reg">
  <div class="g-regradius">
    <img src="./themes/easyui/themes/skin-blue/images/oval01.png" width="80" height="auto" style="left:0; top:30px;">
    <img src="./themes/easyui/themes/skin-blue/images/oval02.png" width="100" height="auto" style="right:0; top:30px;">
    <img src="./themes/easyui/themes/skin-blue/images/oval02.png" width="60" height="auto" style="right:-20px; top:50px;">
    <img src="./themes/easyui/themes/skin-blue/images/oval02.png" width="100" height="auto" style="right:-20px; bottom:-50px;">
    <img src="./themes/easyui/themes/skin-blue/images/oval01.png" width="40" height="auto" style="right:220px; bottom:90px;">
    <img src="./themes/easyui/themes/skin-blue/images/bg02.png" width="777" height="auto" style="left:50%; margin-left:-524px; bottom:-200px;">
  </div>
  <div class="g-regin g-loginin">
    <div class="m-regbox f-clearfix">
      <div class="m-box-left m-regbox-left">
        <div class="m-pictop"><img src="./themes/easyui/themes/skin-blue/images/leftpic03.png" width="66" height="44"></div>
        <div class="m-picbottom"><img src="./themes/easyui/themes/skin-blue/images/leftpic01.png" width="78" height="39"></div>
        <div class="m-picmiddle"><img src="./themes/easyui/themes/skin-blue/images/desk01.png"></div>
      </div>
      <div class="m-box-right m-regbox-right">
        <form id="loginForm" action="${ctx}/login.do" method="post">
          <div class="m-boxr-in">
            <h2 class="m-boxr-title">
              用户登录
             <a onclick="register()" class="m-title-link" href="javascript: void(0)"><strong>立即注册</strong></a>
            </h2>
            <!-- has-error -->
            <div class="m-form form-group">
              <div class="form-input has-icon w-placeholder">
                <span class="m-formicon"><img src="./themes/easyui/themes/skin-blue/images/user01.png"></span>
                <input type="text" class="form-control" placeholder="账号" name="userName" value=""/>

              </div>
            </div>
            <div class="m-form form-group">
              <div class="form-input has-icon w-placeholder">
                <span class="m-formicon"><img src="./themes/easyui/themes/skin-blue/images/lock01.png"></span>
                <input type="password" class="form-control" placeholder="密码"  name="password" value=""/>
              </div>
            </div>
            <div class="m-form form-group">
              <div class="form-input has-icon w-placeholder"  style="color: #eb6565">
                <c:if test="${not empty errorMsg}"> <span class="txt"> ${errorMsg}</span> </c:if>
              </div>


            </div>
            <div class="m-form f-mt5">
              <div class="m-repsw f-clearfix">
               <a onclick="findPassword()" class="m-repsw-link" href="javascript: void(0)">忘记密码？</a>
    <%--            <label class="m-repsw-label">
                  <input type="checkbox" name="memberMe" id="memberMe">记住我
                </label>--%>
              </div>
            </div>
            <div class="m-form m-form-bottom">
              <div class="form-input">
                <input type="hidden"  placeholder="验证码" name="captcha" value="1"/>
<%--                <input type="submit" class="m-btn m-jbbtn m-btn-block m-btn-middle f-relative" style="z-index:4;" onclick="return submitForm();" value="登录">--%>
                <a href="javascript:void(0)" class="m-btn m-jbbtn m-btn-block m-btn-middle f-relative" style="z-index:4;" onclick="submitForm();" id="loginBtn">登录</a>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function initValidate() {
    loginFormValidate = $("#loginForm").validate({
      rules: {
        userName: {
          required: true
        },
        password: {
          required: true,
          rangelength: [6, 32]
        }
      },
      messages: {
        username: {
          required: "请输入账号！",
          isEmailOrMobile: "请输入正确格式的邮箱或手机号"
        },
        password: {
          required: "请输入密码!",
          rangelength: "密码支持6-32个字符"
        }
      },
      showErrors: function (map, list) {
        this.currentElements.parents('.form-group:first').find('.has-error').remove();
        this.currentElements.parents('.form-group:first').removeClass('has-error');
        $.each(list, function (index, error) {
          var ee = $(error.element);
          var eep = ee.parents('.form-group:first');
          eep.addClass('has-error');
          eep.find('.has-error').remove();
          eep.append('<div class="has-error"><i class="icon-warning"><img src="../Content/Register/images/warntip01.png"></i>' + error.message + '</div>');
          return false;
        });
      }

    });
  }


  function submitForm(){

      $("#loginForm").submit();
  }
  function clearForm(){
    $('#loginForm').form('clear');
  }

  function register() {
    easyuiUtils.openWindow('registerWinV2','用户注册',400,670,'${ctx}/site/register/goRegister.do',true);
  }
  function findPassword() {
    easyuiUtils.openWindow('findPasswordWin','找回密码',800,300,'${ctx}/site/register/goFindPassword.do',true);
  }
  function initPage()
  {
    $("#username").focus();
    JPlaceHolder(placeHolder);
  }

  function initEvent()
  {
    $('#loginBtn').off('click').on('click', submitLogin);

    $('#username').off('input').on('input', function () {

    });

    $('#password').off('keyup').on('keyup', function (event) {
      if (event.keyCode == 13) {
        submitLogin();
      }
    });

    $(".g-reg").height($(window).height());
    $(window).bind("resize", function () {
      $(".g-reg").height($(window).height());
    });
  }

  function submitLogin()
  {
    if (!$("#loginBtn").hasClass("disabled")) {
      $("#loginForm").submit();
    }
  }
  function showCustomerError(target, message) {
    $(".has-error.form-group").removeClass("has-error");
    $(".has-error").remove();
    $(target).parents(".form-group").addClass("has-error");
    var html = '<div class="has-error"><i class="icon-warning"><img src="../Content/Register/images/warntip01.png"></i>' + message + '</div>';
    $(target).parents(".form-group").append(html);
  }


  function placeHolder() {
    var placeHeight = $(".g-regin .m-form .form-input.w-placeholder .placeholder-text .placeholder-text-in").height();
    $(".g-regin .m-form .form-input.w-placeholder .placeholder-text .placeholder-text-in").css("lineHeight", placeHeight + "px");
  }

  var weChatWindow = null;
  function showWeChatLoginDialog() {
    if (weChatWindow != null) {
      weChatWindow.focus();
    }
    var $w = $(window);
    var ww = $w.width() / 2 - 450;
    ww = ww < 0 ? 0 : ww;
    var wh = $w.height() / 2 - 290;
    var returnUrl = GetQueryString("returnurl");
    var redirectUrl = "/Customer/WechatWepAppLogin";
    if (returnUrl != null) {
      redirectUrl = redirectUrl + "?returnUrl=" + returnUrl;
    }
    weChatWindow = window.open(redirectUrl, "", "toolbar=no, menubar=no, titlebar=no, scrollbars=yes, location=no, status=no, top=" + wh + ", left=" + ww + ",width=900,height=580");
    if (window.focus) { weChatWindow.focus() }
  }

  $(function () {
    initPage();
    initEvent();
    initValidate();
  });
</script>
</body>
</html>