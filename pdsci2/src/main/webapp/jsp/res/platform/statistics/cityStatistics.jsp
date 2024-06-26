<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });
    function showBarChart(myChart,title,lineLabel,lineValue,lineValue2,yLable,xLable,flag){
        var rotate=-90;
        if(flag)
            rotate=0;

        var option = {
            title : {
                text: title,
                x:'center'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data: [ {
                    name:'合计：${sum}',
                    textStyle:{fontWeight:'bold', color:'green'}

                },'含在校专硕', '不含在校专硕'],
                selectedMode: 'single',
                selected: {
                    '含在校专硕': false,
                    '不含在校专硕': true
                },
                x: 'right'
            },
            calculable : true,
            grid:{
                zlevel:0,
                height:350
            },
            xAxis : [
                {
                    type : 'category',
                    axisLabel:{
                        interval:0,
//                        rotate:rotate,
                        margin:10,
                        interval:0,
                        rotate:40
                    },
                    name:xLable,
                    boundaryGap : true,
                    data : lineLabel
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    interval: 'auto',
                    name:yLable,
                    axisLabel : {
                        formatter: function(value)
                        {
                            return parseInt(value);
                        }
                    },
                }
            ],
            series : [
                {
                    name:'含在校专硕',
                    type:'bar',
                    data:lineValue,
                    barWidth : 15,
                    itemStyle: {
                        normal: {
                            label : {
                                show: true, position: 'top',
                                formatter:function(a,b,c){
                                    return c;
                                }
                            },
                            color:'#4F81BD'
                        }
                    }
                },
                {
                    name:'不含在校专硕',
                    type:'bar',
                    data:lineValue2,
                    barWidth : 15,
                    itemStyle: {
                        normal: {
                            label : {
                                show: true, position: 'top',
                                formatter:function(a,b,c){
                                    return c;
                                }
                            },
                            color:'#4F81BD'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
        // 动态添加默认不显示的数据
    }

    // 地市 人员统计 区分专硕和非专硕
    require(['echarts','echarts/chart/bar'],function(ec){
        var myChart = ec.init(document.getElementById('cityDocNum'));
        var lineLabel = []; //x轴文字
        var lineValue = [];//含校专硕
        var lineValue2 = [];//不含在校专硕
        var value = [];
        <c:forEach items="${CityIds}" var="cityId">
        lineLabel.push("${cityMap[cityId]}");
        lineValue.push("${inGraduateMap[cityId]}");
        lineValue2.push("${noGraduateMap[cityId]}");
        </c:forEach>
        if (lineLabel.length==0) {
            lineLabel.push("");
            lineValue.push("");
            lineValue2.push("");
        }
        var title="湖北省住院医师规范化培训\n${title}";
        showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','地区',true);
    });
</script>
</head>
<body>
<div id="cityDocNum" style="height:600px;width:100%;">
</div>
</body>
</html>
