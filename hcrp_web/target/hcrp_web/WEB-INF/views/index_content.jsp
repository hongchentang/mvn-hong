<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<style>
    /*初始化*/
    * {margin-top: 0px;padding: 0;list-style: none;}
    /*HI*/
    .top-hello {width: 100%;padding-top: 15px;}
    .top-helloIn {display: flex;justify-content: stretch;align-items: center;width: 90%;height: 150px;border: #dcdfe6 solid 1px;border-radius: 10px;background: #fff;margin: 0 auto 15px}
    .top-helloIn > img {display: block;width: 60px;height: 60px;margin: 5% 1%;}
    .top-helloIn > p {font-size: 16px;color: #909399;}

    /*常用模块*/
    .center-common-h3 {width: 100%;margin-left: 4%;margin-top: 4%}
    .center-common-ul {width: 100%;height:20%;display: flex;justify-items: center;align-items: center;margin: 0 5%;}
    .center-common-ul > li {width: 15%;height: 68px;border-radius: 10px;background: #ffffff;cursor: pointer;margin: 0 1%;display: flex;justify-content: center;align-items: center;font-size: 14px;color: #606266;border: #dcdfe6 solid 1px;}
    .center-common-ul li > img {margin-right: 5%;width: 50px;height: 50px}
    .center-common-ul li > span {font-weight: bold;}
    @media screen and (max-width: 800px) {
        .center-common-ul li > span {
            display: none;
        }
    }
    /*统计图*/
    .btn-canva{width: 100%;height: 60%;display: flex;padding: 0 auto;background: #f2f6fc}
    .btn-canva-lr{width: 50%;height: 100%;margin-left: 20%;}
    .btn-canva-lr >h3{margin: 0 0 20px 0;}

    .btn-canvaIn{height:80%;width:88%;border:1px solid #dcdfe6;border-radius:10px;background:#ffffff}

    @media screen and (max-width: 1000px) {
        .btn-canva{
            flex-direction: column;
        }
        .btn-canva-lr{width: 100%;height: 100%;margin-left: 4%;}
    }
</style>
<div style="width: 100%;height: 100%;background: #f2f6fc;">
    <%--HI--%>

    <%--<div style="padding:5px;height: 15%;background: #f2f6fc;">
        <div style="width:100%">
            <div style="margin-top: 10px;margin-left: 60px; margin-right: 120px ;padding-left: 20px;padding-right: 10px ;background: #ffffff;      border: 1px solid #dcdfe6;   width: 87.2%;height:150px;   float: left;border-radius: 10px;">
                <div style=" padding-top: 20px; height: 50px">
                    <img style="width: 60px;height: 60px;margin-top:15px; " src="/themes/easyui/themes/icons/hi.png">
                    <div style="position: relative; font-size: 16px;color: #909399;left: 70px;top: -60px;">您好：
                        <shiro:principal property="realName"/></div>
                    <div style="position: relative; font-size: 16px;color: #909399;left: 70px;top: -50px">
                        欢迎使用IPbox知识产权管理系统
                    </div>
                </div>
            </div>
        </div>
    </div>--%>
    <%--常用功能模块--%>

    <h3 class="center-common-h3">快捷入口</h3>
    <ul class="center-common-ul">
        <li title="专利导航" class="common-ul-left"　　　
            onclick="patentList('/hcrp/patent/navigation.do?type=0&tabId=16a6273fdbbc4c529d8b5e44216918a3','专利导航','16a6273fdbbc4c529d8b5e44216918a3')">
            <img src="/hcrp/themes/easyui/themes/icons/patent.png" alt="专利导航"><span>专利导航</span>
        </li>
        <li title="专利预警"
            onclick="patentList('/hcrp/patent/navigation.do?type=1&tabId=16a6273fdbbc4c529d8b5e44216918a3','专利预警','16a6273fdbbc4c529d8b5e44216918a3')">
            <img src="/hcrp/themes/easyui/themes/icons/brand.png" alt="专利预警"><span>专利预警</span>
        </li>
        <li title="信息分析"
            onclick="patentList('/hcrp/patent/navigation.do?type=3&tabId=16a6273fdbbc4c529d8b5e44216918a3','信息分析','16a6273fdbbc4c529d8b5e44216918a3')">
            <img src="/hcrp/themes/easyui/themes/icons/guan_cost.png" alt="信息分析"><span>信息分析</span>
        </li>
        <li title="分析评议"
            onclick="patentList('/hcrp/patent/navigation.do?type=5&tabId=16a6273fdbbc4c529d8b5e44216918a3','分析评议','16a6273fdbbc4c529d8b5e44216918a3')">
            <img src="/hcrp/themes/easyui/themes/icons/cost.png" alt="分析评议"><span>分析评议</span>
        </li>

    </ul>

    <%--统计图模块--%>
    <div class="btn-canva">
        <div class="btn-canva-lr btn-canva-left">
            <h3>报告统计</h3>
            <div class="btn-canvaIn" style="">
                <iframe class="rightDIV1" src="/hcrp/themes/skin/ipr/patents.jsp" frameborder="0" height="100%;" width="100%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
            </div>
        </div>

    </div>


</div>

<script type="text/javascript">
    function openListNoticeTab() {
        layout_center_addTabFun({
            id: 'listNoticeIndex',
            title: '通知公告',
            href: '${ctx}/common/notice/listNotice.do?tabId=listNoticeIndex',
            closable: true
        });
    }


    function patentList(url, title, id) {
        layout_center_addTabFun({
            id: id,
            title: title,
            closable: true,
            iconCls: "",
            //cache : false,
            href: url,
            queryParams: {},
            //content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
        });
    }

    //跳转到统计数字对应的页面
    function goStatistics(id, title, url) {
        title = title.trim();
        layout_center_addTabFun({
            id: id,
            title: title,
            href: url,
            closable: true,
            queryParams: {},
        });
    }

    function changeUserInfo() {

        openWindow('editUserInfoWindow', '个人信息修改', 900, 370, '${ctx }/common/user/goChangeUserInfo.do', true);
    }
</script>


