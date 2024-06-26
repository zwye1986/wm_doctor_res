<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart1'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            <%--legend: {--%>
                <%--x:'center',--%>
                <%--y:'bottom',--%>
                <%--data:function (){--%>
                    <%--var list = [];--%>
                    <%--<c:forEach items="${orgMap}" var="map">--%>
                    <%--list.push('${map.key}');--%>
                    <%--</c:forEach>--%>
                    <%--return list;--%>
                <%--}()--%>
            <%--},--%>
            title : {
                text: '按培训基地统计',
                x:'center',
                y:'top'
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
                        <c:forEach items="${orgMap}" var="map">
                        list.push({value:"${map.value}", name:'${map.key}'});
                        </c:forEach>
                        return list;
                    }(),
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);
    });
    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart2'));
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
                    <c:forEach items="${sessionNumberMap}" var="map">
                    list.push('${map.key}');
                    </c:forEach>
                    return list;
                }()
            },
            title : {
                text: '按年级统计',
                x:'center',
                y:'top'
            },
            series : [
                {
                    type:'pie',
                    radius : '50%',
                    z:2,
                    itemStyle : {
                        normal : {
                            label : {
                                show : true
                            },
                            labelLine : {
                                show : true,
                                length:0,
                                length2:0
                            }
                        }
                    },
                    data:function (){
                        var list = [];
                        <c:forEach items="${sessionNumberMap}" var="map">
                        list.push({value:"${map.value}", name:'${map.key}'});
                        </c:forEach>
                        return list;
                    }(),
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);
    });
    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart3'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            title : {
                text: '按专业统计',
                x:'center',
                y:'top'
            },
            series : [
                {
                    type:'pie',
                    radius : '55%',
                    z:3,
                    itemStyle : {
                        normal : {
                            label : {
                                show : true
                            },
                            labelLine : {
                                show : true,
                                length:0,
                                length2:0
                            }
                        }
                    },
                    data:function (){
                        var list = [];
                        <c:forEach items="${speMap}" var="map">
                        list.push({value:"${map.value}", name:'${map.key}'});
                        </c:forEach>
                        return list;
                    }(),
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);
    });
</script>
<div id="chart1" class="row-item"></div>
<div id="chart2" class="row-item"></div>
<div id="chart3" class="row-item"></div>
