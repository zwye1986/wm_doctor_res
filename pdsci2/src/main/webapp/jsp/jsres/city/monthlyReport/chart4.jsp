<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart4'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            legend: {
                x:'center',
                y:'bottom',
                data:function (){
                    var list = [];
                    <c:forEach items="${changeTypeMap}" var="map">
                    list.push('${map.key}');
                    </c:forEach>
                    return list;
                }()
            },
            series : [
                {
                    type:'pie',
                    radius : '50%',
                    z:1,
//                    label: {
//
//                        normal: {
//                            position: 'inner'
//                        }
//                    },
//                    labelLine: {
//                        normal: {
//                            show: false
//                        }
//                    },
                    itemStyle : {
                        normal : {
                            label : {
                                show : true,
//                                position:'inner'
                            },
                            labelLine : {
                                show : true,
                                length:0,
                                length2:0
//                                length2:5
                            }
                        }
                    },
                    data:function (){
                        var list = [];
                        <c:forEach items="${changeTypeMap}" var="map">
                        list.push({value:"${map.value}", name:'${map.key}'});
                        </c:forEach>
                        return list;
                    }(),
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);

        var flag = true;
        <c:forEach items="${changeTypeMap}" var="map">
        if(${map.value != 0}){
            flag = false;
        }
        </c:forEach>
        if(flag){
            $("#chart4").html("<div class='no-date'>暂无数据!</div>");
        }
    });

</script>
<div id="chart4" class="row-item" style="width: 360px;"></div>
