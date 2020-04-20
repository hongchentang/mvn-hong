var xdata = [];
var legendData = [];
var seriesData = [];
var sourceData = [];
var  secDimensionId = '';

function getSourceData() {

    secDimensionId = $('#secDimensionId').text();
    if (secDimensionId != ''){
        var chartDataDiv =  $("#" + secDimensionId, parent.document).find('div[name="chartData"]');
        var dataText = $(chartDataDiv).text();

        sourceData = JSON.parse(dataText);
    }
}

function setSourceData(data) {
    secDimensionId = $('#secDimensionId').text();
    if (secDimensionId != ''){
        var chartDataDiv =  $("#" + secDimensionId, parent.document).find('div[name="chartData"]');
        $(chartDataDiv).text(JSON.stringify(data));
    }
}

function covertChartData() {

    getSourceData();
    if(secDimensionId != ''){

        seriesData = [];
        legendData = [];
        xdata = [];
        $.each(sourceData, function (idx, val) {

            legendData.push(val.tableTrName);
            var resultDtoList = val.resultDtoList;

            var dataTd = [];
            $.each(resultDtoList, function (ydx, yal) {
                if (idx == 0){
                    xdata.push(yal.key);
                }
                dataTd.push(yal.sum);
            });

            seriesData.push(dataTd);
        });

    }
}

function xrChart(model) {

    if (secDimensionId != '') {
        if (model == 0){
            option.xAxis.data = xdata;
        }else if (model == 1){
            option.yAxis.data = xdata;
        }
        option.legend.data = legendData;
        var initSeries = option.series[0];

        $.each(seriesData, function (idx, val) {
            var seriesX = $.extend(true, {}, initSeries);
            seriesX.data = val;
            seriesX.name = legendData[idx];
            seriesX.color = randomColor();
            option.series[idx] = seriesX;
        });
        if (seriesData.length < 3){
            option.series[1] = null;
            option.series[2] = null;
        }
    }

    myChart.setOption(option, true);
}

function randomColor(){
    var colorValue = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
    var colorArray = colorValue.split(",");
    var color = "#";
    for( var i = 0; i < 6; i++ ){
        color += colorArray[ Math.floor( Math.random() * 16 ) ];
    }
    return color;
}

function xrPieChart() {

    getSourceData();
    if (secDimensionId != '') {

        var serObjData = [];
        var legendData = [];
        $.each(sourceData[0].resultDtoList, function (idx, val) {
            var serObj = {};
            serObj['value'] = val.sum;
            serObj['name'] = val.key;
            serObjData.push(serObj);
            legendData.push(val.key);
        });

        option.legend.data = legendData;
        option.series[0].data = serObjData;
    }

    myChart.setOption(option, true);
}

function xrRadar() {

    getSourceData();
    if (secDimensionId != '') {
        var indicator = [];
        var serData = [];

        $.each(sourceData, function (idx, val) {
            var serObj = {};
            serObj['name'] = val.tableTrName;
            var resultList = val.resultDtoList;
            var serObjData = [];

            $.each(resultList, function (ydx, yal) {
                var indicatorObj = {};

                var key = yal.key;
                var sum = yal.sum;
                serObjData.push(sum);

                if (idx == 0) {
                    indicatorObj['name'] = key;
                    indicatorObj['max'] = sum;
                    indicator.push(indicatorObj);
                } else {
                    $.each(indicator, function (zdx, zal) {
                        if (zal['name'] === key && zal['max'] > sum) {
                            zal['max'] = sum;
                        }
                    });
                }
            });
            serObj['value'] = serObjData;
            serData.push(serObj);
        });

        $.each(indicator, function (idx, val) {
            val['max'] = val['max'] + 2000;
        });

        option.radar.indicator = indicator;
        option.series.data = serData;
    }

    myChart.setOption(option, true);
}

function optionToContent(opt) {
    var xAxis = opt.xAxis[0].data;
    var series = opt.series;
    var table = ' <table id="myChartDataTable" border="1" cellspacing="0" style="width: 100%;">';
    table += '<tr><td></td>';
    for (var i = 0; i < series.length; i++){
        table += '<td>' + series[i].name + '</td>';
    }
    table += '</tr>';

    for (var y = 0; y < xAxis.length; y++){
        table += '<tr>';
        table += '<td style="width: 38px;">' + xAxis[y] + '</td>';
        for (var z = 0; z < series.length; z++){
            table += '<td style="max-width: 100px;"><input class="chart_table_td_input" value="' + series[z].data[y] + '"/></td>';
        }
        table += '</tr>';
    }

    table += '</table>';

    return table;
}

function contentToOption(model) {
    console.log('===>contentToOption');
    var trList = $('#myChartDataTable').find('tr');

    $.each(trList, function (idx, val) {
        if(idx != 0){
            var tdList = $(val).find('td');
            $.each(tdList, function (ydx, yal) {
                if(ydx != 0){

                    var input = $(yal).find('input');
                    var tableData = sourceData[ydx - 1];
                    var tdData = tableData.resultDtoList[idx - 1];
                    tdData['sum'] = parseInt($(input).val());
                }
            });
        }
    });

    /*把数值设置回去*/
    setSourceData(sourceData);
    /*重新渲染*/
    if (model == 0 || model == 1){
        /*条形图，柱状图,折线图*/
        covertChartData();
        xrChart(model);
    }else if (model == 2){
        /* 饼图，圆环图 */
        xrPieChart();
    }


}

function optionToContentRadar(opt) {
    var indicator = opt.radar[0].indicator;

    var table = ' <table id="myChartDataTable" border="1" cellspacing="0" style="width: 100%;">';
    table += '<tr><td></td>';
    $.each(indicator, function (idx, val) {
        table += '<td>' + val.name + '</td>';
    });
    table += '</td>';

    var dataList = opt.series[0].data;

    $.each(dataList, function (idx, val) {
        table += '<tr>';
        table += '<td style="width: 38px;">' + val.name + '</td>';
        var tableValues = val.value;
        $.each(tableValues, function (ydx, yal) {
            table += '<td style="max-width: 100px;"><input class="chart_table_td_input" value="' + yal + '"/></td>';
        });

        table += '<tr>';
    });

    table += '</table>';

    return table;
}

function contentToOptionRadar() {
    var radarDataTable = $('#myChartDataTable');
    var trList = radarDataTable.find('td');
    $.each(trList, function (idx, val) {
       
        if (idx !== 0){
            var tdList = $(val).find('td');
            $.each(tdList, function (ydx, yal) {
                if (ydx !== 0){
                    var trData = sourceData[idx - 1];
                    var sourceTdList = trData.resultDtoList;
                    sourceTdList[ydx - 1] = yal;
                }
            });
        }
    });
    /*重新设值*/
    setSourceData(sourceData);
    /*重新渲染*/
    xrRadar();
}

function getPngBase64() {

    /*获取导出图片的base64*/
    var img =  myChart.getDataURL({
        pixelRatio: 2,
        backgroundColor: '#fff',
        excludeComponents: ['toolbox']
    });

    secDimensionId = $('#secDimensionId').text();
    if (secDimensionId != ''){
        var chartPngDataDiv =  $("#" + secDimensionId, parent.document).find('div[name="chartPngBase64"]');
        $(chartPngDataDiv).text(img);
    }

    /*还原忽略的组件，不设置无法使用已忽略的*/
    myChart.getDataURL({
        pixelRatio: 2,
        backgroundColor: '#fff',
        //excludeComponents: ['toolbox']
    });
}

$(document).ready(function () {
    //console.log('--->' + secDimensionId + '已加载');
    //getPngBase64();
});

