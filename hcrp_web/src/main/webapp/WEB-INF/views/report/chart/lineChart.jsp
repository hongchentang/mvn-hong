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

    /*整理数据*/
    covertChartData();

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;


    app.title = '坐标轴刻度与标签对齐';

    option = {
        /*title: {
            text: '柱状图'
        },*/
        legend: {
            // 标志图形的长度
            itemWidth: 10,
            // 标志图形的宽度
            itemHeight: 10,
            width: '15%',
            left: '85%',
            y: 'center',

            data: ['发明', '实用新型', '外观设计']
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
                        contentToOption(0);
                    }
                }
            }
        },

        grid: {
            left: '1%',
            right: '15%',
            bottom: '1%',
            containLabel: true
        },

        xAxis:
            {
                type: 'category',
                data: ['行1', '行2' , '行3', '行4'],
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    //margin: 30,
                    rotate: 30,
                    formatter: function (value) {
                        //return value.split("").join("\n\t");
                        return value;
                    }
                }
            }
        ,

        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [

            {
                name: '发明',
                type: 'line',
                barWidth: '25%',
                color: ["#ADD6F2"],
                label: {
                    show: true,
                    position: 'top'
                },
                data: [10, 52, 200, 334]
            },
            {
                name: '实用新型',
                type: 'line',
                color: ["#FE7B4D"],
                barWidth: '25%',
                label: {
                    show: true,
                    position: 'top'
                },
                data: [20, 62, 300, 434]
            }, {
                name: '外观设计',
                type: 'line',
                color: ["#FDAD4E"],
                barWidth: '25%',
                label: {
                    show: true,
                    position: 'top'
                },
                data: [30, 72, 400, 534]
            }
        ]
    };

    /*渲染数据*/
    xrChart(0);

    /*完成渲染后*/
    myChart.on('rendered', function () {
        getPngBase64();
    });

</script>
</body>
</html>