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
        title: {
            /*text: '基础雷达图'*/
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {

            feature: {
                restore: {show: true},
                saveAsImage: {show: true},
                dataView: {
                    show: true,
                    readOnly: false,
                    optionToContent: function (opt) {
                        return optionToContentRadar(opt);
                    },
                    contentToOption: function () {
                        contentToOptionRadar();
                    }
                }
            }
        },
        radar: {
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [1, 30 ]
                }
            },
            indicator: [
                { name: '销售', max: 6500},
                { name: '管理', max: 16000},
                { name: '信息技术', max: 30000},
                { name: '客服', max: 38000},
                { name: '研发', max: 52000},
                { name: '市场', max: 25000}
            ]
        },
        series: [{
            name: '预算 vs 开销',
            type: 'radar',
            data: [
                {
                    value: [4300, 10000, 28000, 35000, 50000, 19000],
                    name: '预算分配'
                },
                {
                    value: [5000, 14000, 28000, 31000, 42000, 21000],
                    name: '实际开销'
                }
            ]
        }]
    };

    xrRadar();

    /*完成渲染后*/
    myChart.on('rendered', function () {
        getPngBase64();
    });
</script>
</body>
</html>