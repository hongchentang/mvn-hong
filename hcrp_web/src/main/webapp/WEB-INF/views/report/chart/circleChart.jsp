<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/common/include/taglib.jsp" %>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script src="${ctx}/js/common/my-report-chart.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/echarts/echarts-diy-style.css"/>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<div id="secDimensionId">${secDimensionId}</div>
<script type="text/javascript" src="${ctx}/js/echarts/echarts.min.js"></script>
<script type="text/javascript">

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;


    app.title = '坐标轴刻度与标签对齐';

    option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            // 标志图形的长度
            itemWidth: 10,
            // 标志图形的宽度
            itemHeight: 10,
            top: '10%',
            //width: '15%',
            //left: '85%',
            //y: 'center',

            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        grid: {
            left: '1%',
            right: '15%',
            bottom: '1%',
            containLabel: true
        },
        toolbox: {

            feature: {
                restore: {show: true},
                saveAsImage: {show: true},
                dataView: {
                    show: true,
                    readOnly: false,
                    optionToContent: function (opt) {
                        return optionToContent(opt);
                    },
                    contentToOption: function (opt) {
                        contentToOption(2);
                    }
                }
            }
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                center: ['50%', '60%'],
                radius: ['30%', '50%'],
                avoidLabelOverlap: false,
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ]
            }
        ]
    };

    /*渲染图表的信息*/
    xrPieChart();

    /*完成渲染后*/
    myChart.on('rendered', function () {
        getPngBase64();
    });
</script>
</body>
</html>