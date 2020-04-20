<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/common/include/taglib.jsp" %>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>

<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=jMwflUfZfSjMF3Qw7AIbTUjS6B89Ag3X&__ec_v__=20190126"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
<script type="text/javascript">
    var xdata = [];
    $.ajax({
        url: "${ctx }/report/listType.do",
        type: 'post',
        async: false,
        success: function (data) {
            var json = data.data;
            if (json != null && json.length > 0) {
                for (var i = 0; i < json.length; i++) {
                    xdata.push(json[i]);
                }
            }
        }
    });

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        color: ['#3398DB'],
        tooltip: {
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            top:"10%",
            left: '5%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['专利导航', '专利预警', '信息分析', '分析评议','专利查新','信息推送'],
                axisLine: {//x轴样式
                    show: true,
                    lineStyle: {
                        color: "#00c7ff",
                        width: 1,
                        type: "solid"
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: "#00c7ff",
                    }
                },
            }
        ],
        yAxis: [
            {
                type: 'value',

            axisLabel: {
        formatter: '{value} '
    },
    axisLine: {//y轴样式
        show: true,
            lineStyle: {
            color: "#00c7ff",
                width: 1,
                type: "solid"
        },
    },
    axisTick: {
        show: false
    },
    splitLine: {//格线样式
        lineStyle: {
            color: "#00c7ff",
        }
    }
    }
        ],
        series: [
            {
                name: '数量',
                type: 'bar',
                barWidth: '60%',
                markPoint : {
                    symbol:'pin',//标记类型
                    symbolSize: 40,//图形大小

                    data : [//配置项
                        {value:'0',xAxis: 0, yAxis: 0},
                        {value:'0',xAxis:1, yAxis: 0},
                        {value:'0',xAxis:2, yAxis: 0},
                        {value:'0',xAxis:3, yAxis: 0},
                        {value:'0',xAxis:4, yAxis: 0},
                        {value:'0',xAxis:5, yAxis: 0},

                    ]
                },
                itemStyle: {//柱样式
                    normal: {
                        barBorderRadius: [5,5,0,0],
                        color: function (params){//渐变色，也可以直接用数组给不同的柱子设置不同的颜色
                            var colorList = [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00e6ff'
                            }, {
                                offset: 1,
                                color: '#018dff'
                            }]),new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00fcae'
                            }, {
                                offset: 1,
                                color: '#006388'
                            }
                            ]),new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00e6ff'
                            }, {
                                offset: 1,
                                color: '#018dff'
                            }
                            ]),new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00fcae'
                            }, {
                                offset: 1,
                                color: '#006388'
                            }
                            ]),new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00e6ff'
                            }, {
                                offset: 1,
                                color: '#018dff'
                            }
                            ]),new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#00fcae'
                            }, {
                                offset: 1,
                                color: '#006388'
                            }
                            ])];
                            return colorList[params.dataIndex];
                        },
                        opacity: 1,
                    }
                },

                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ],
                }

            },
           {
                type:'line',

            },

        ]
    };


    if (option && typeof option === "object") {
        option.series[0].data =xdata;
       option.series[1].data =xdata;
        option.series[0].markPoint.data[0].value=xdata[0];
        option.series[0].markPoint.data[0].yAxis=xdata[0];
        option.series[0].markPoint.data[1].value=xdata[1];
        option.series[0].markPoint.data[1].yAxis=xdata[1];
        option.series[0].markPoint.data[2].value=xdata[2];
        option.series[0].markPoint.data[2].yAxis=xdata[2];
        option.series[0].markPoint.data[3].value=xdata[3];
        option.series[0].markPoint.data[3].yAxis=xdata[3];
        option.series[0].markPoint.data[4].value=xdata[4];
        option.series[0].markPoint.data[4].yAxis=xdata[4];
        option.series[0].markPoint.data[5].value=xdata[5];
        option.series[0].markPoint.data[5].yAxis=xdata[5];


        myChart.setOption(option, true);

    }
</script>
</body>

</html>