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

        legend: {
            // 标志图形的长度
            itemWidth: 10,
            // 标志图形的宽度
            itemHeight: 10,
            top: '10%',
            //left: '85%',
            //y: 'center',

            data: ['澳大利亚', '韩国', '俄罗斯','瑞典', '法国', '德国', '英国', '日本', '美国', '中国']
        },
        grid: {
            left: '1%',
            right: '15%',
            bottom: '1%',
            containLabel: true
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
                        return optionToContent(opt);
                    },
                    contentToContent: function (opt) {
                        contentToOption(2);
                    }
                }
            }
        },

        series: [
            {
                name: '',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                data: [
                    {value: 10, name: '澳大利亚'},
                    {value: 20, name: '韩国'},
                    {value: 30, name: '俄罗斯'},
                    {value: 40, name: '瑞典'},
                    {value: 50, name: '法国'},
                    {value: 60, name: '德国'},
                    {value: 70, name: '英国'},
                    {value: 80, name: '日本'},
                    {value: 90, name: '美国'},
                    {value: 100, name: '中国'}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    /*渲染饼图*/
    xrPieChart();

    /*完成渲染后*/
    myChart.on('rendered', function () {
        getPngBase64();
    });
</script>
</body>
</html>