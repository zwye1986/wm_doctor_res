<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        'echarts/chart/bar','echarts/chart/line'],function(ec){
        var myChart = ec.init(document.getElementById('chart7'));
        option = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['登记总数','审核总数'],
                x: 'center',
                y: 'top'
            },
            calculable : true,
            grid: {
                x:200,
                x2:30,
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
                        <c:forEach items="${countryOrgList}" var="org">
                        list.push('${org.orgName}');
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
                    name:'登记总数',
                    type:'bar',
                    data:function (){
                        var list = [];
                        <c:forEach items="${countryOrgList}" var="org">
                        list.push("${registerMap[org.orgFlow]}");
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
                },
                {
                    name:'审核总数',
                    type:'line',
                    data:function (){
                        var list = [];
                        <c:forEach items="${countryOrgList}" var="org">
                        list.push("${auditingMap[org.orgFlow]}");
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
<div class="row-subText">
    <div>轮转数据填写总量：${registerSum}条</div>
    <div>轮转数据审核总量：${auditingSum}条</div>
</div>
<div id="chart7" class="row-item row-itemSub" style="width: 100%; height: 400px;"></div>
