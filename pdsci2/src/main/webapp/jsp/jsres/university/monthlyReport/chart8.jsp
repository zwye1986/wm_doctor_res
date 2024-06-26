<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart8'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            <%--legend: {--%>
                <%--orient : 'vertical',--%>
                <%--x : 'right',--%>
                <%--data:function (){--%>
                    <%--var list = [];--%>
                    <%--<c:forEach items="${resultMapList}" var="result">--%>
                    <%--list.push('${result["ORG_NAME"]}');--%>
                    <%--</c:forEach>--%>
                    <%--return list;--%>
                <%--}()--%>
            <%--},--%>
            series : [
                {
                    type:'pie',
                    radius : '70%',
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
                        <c:forEach items="${resultMapList}" var="result">
                        list.push({value:"${result['NUM']}", name:'${result["ORG_NAME"]}'});
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
<div id="chart8" class="row-item" style="width: 600px;"></div>
