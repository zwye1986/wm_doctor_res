<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart9'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'right',
                data:function (){
                    var list = [];
                    <c:forEach items="${resultMap['activityByType']}" var="result">
                    list.push('${result.ACTIVITY_TYPE_NAME}');
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
                        <c:forEach items="${resultMap['activityByType']}" var="result">
                        list.push({value:"${result.ACTIVITYSUM}", name:'${result.ACTIVITY_TYPE_NAME}'});
                        </c:forEach>
                        return list;
                    }(),
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);

        var flag = true;
        <c:forEach items="${resultMap['activityByType']}" var="map">
        if(${map.value != 0}){
            flag = false;
        }
        </c:forEach>
        if(flag){
            $("#chart9").html("<div class='no-date'>暂无数据!</div>");
            $("#chart9-2").hide();
        }
    });
</script>
<div class="item-left1" style="padding-top: 20px;box-sizing: border-box; width:280px;" id="chart9-2">
    <div>
        <div>${resultMap['activitySum']}<span>次</span></div>
        <div>本月活动总量</div>
    </div>
    <div class="clearfix" style="margin-top: 20px;">
        <div class="item-left1" style="width:auto;height: auto;padding-left: 20px;">
            <div>Top1</div>
            <div>${resultMap['dept1FinalList'][0].ACTIVITYSUM}次</div>
            <div>${resultMap['dept1FinalList'][0].DEPT_NAME}
                <c:if test="${resultMap['dept1FinalList'].size()>1}">
                    <a class="showMore-link">更多<div class="moreInfo">
                        <c:forEach items="${resultMap['dept1FinalList']}" var="map" varStatus="s">
                            <c:if test="${s.index != 0}">
                                <span>${map.DEPT_NAME}</span>
                            </c:if>
                        </c:forEach>
                    </div></a>
                </c:if>
            </div>
        </div>
        <div class="item-right1">
            <div>Bottom1</div>
            <div>${resultMap['dept2FinalList'][0].ACTIVITYSUM}次</div>
            <div>${resultMap['dept2FinalList'][0].DEPT_NAME}
                <c:if test="${resultMap['dept2FinalList'].size()>1}">
                    <a class="showMore-link">更多<div class="moreInfo">
                        <c:forEach items="${resultMap['dept2FinalList']}" var="map" varStatus="s">
                            <c:if test="${s.index != 0}">
                                <span>${map.DEPT_NAME}</span>
                            </c:if>
                        </c:forEach>
                    </div></a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="item-right1">
    <div id="chart9" class="row-item" style="width: 700px;"></div>
</div>
