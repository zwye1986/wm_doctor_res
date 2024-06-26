<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        'echarts/chart/bar','echarts/chart/line'],function(ec){
        var myChart = ec.init(document.getElementById('chart9'));
        option = {
            tooltip : {
                trigger: 'axis'
            },
            calculable : true,
            grid: {
                x:160,
                x2:30,
                y:'10%',
                y2:'40%',
                zlevel:0,
                height:200
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : true,
                    name : '基地',
                    data : function (){
                        var list = [];
                        <c:forEach items="${resultMapOrgList}" var="map">
                        list.push('${countryOrgNameMap[map.key]}');
                        </c:forEach>
                        return list;
                    }(),
                    axisLabel: {
                        interval:0,
                        rotate:25,
                        margin:10
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    name:'数据量',
                    stackLabels:{enabled : true}
                }
            ],
            series : [
                {
                    name:'数据量',
                    type:'bar',
                    data:function (){
                        var list = [];
                        <c:forEach items="${resultMapOrgList}" var="map">
                        list.push("${map.value}");
                        </c:forEach>
                        return list;
                    }(),
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,//是否展示
                                position: 'inside'
                            }
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
        window.onresize = function () {
            myChart.resize();
        }
    });
</script>
<div id="chart9" class="row-item" style="width: 100%; height: 400px;"></div>
