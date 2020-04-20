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
<script type="text/javascript" src="${ctx}/js/echarts/echarts.min.js"></script>
<script type="text/javascript">
    var xdata = [];
    var invent = [];
    var appearance = [];
    var utility = [];

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;

    app.title = '坐标轴刻度与标签对齐';

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {

            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: {},
        yAxis: {},
        series: [{

            symbolSize: 20,
            data: [
                [10.0, 8.04, 5.0],
                [8.0, 6.95, 0],
                [13.0, 7.58, 0],
                [9.0, 8.81, 0],
                [11.0, 8.33, 0],
                [14.0, 9.96, 0],
                [6.0, 7.24, 0],
                [4.0, 4.26, 0],
                [12.0, 10.84, 0],
                [7.0, 4.82, 0],
                [5.0, 5.68, 0]
            ],
            type: 'scatter'
        }]
    };

    myChart.setOption(option, true);

    /*完成渲染后*/
    myChart.on('rendered', function () {
        getPngBase64();
    });
</script>
</body>
</html>