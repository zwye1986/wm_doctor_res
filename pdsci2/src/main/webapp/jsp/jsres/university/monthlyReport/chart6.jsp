<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('chart6'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}:{c} ({d}%)"
            },
            legend: {
                x:'center',
                y:'bottom',
                data:['合格','不合格']
            },
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
                    data:[
                        {value:"${resultMap['passNum']}", name:'合格'},
                        {value:"${resultMap['notPassNum']}", name:'不合格'}
                    ]
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);

        var flag = true;
        if(${resultMap['passNum'] != 0 && resultMap['notPassNum'] != 0}){
            flag = false;
        }
        if(flag){
            $("#chart6").html("<div class='no-date'>暂无数据!</div>");
        }
    });

</script>
<div class="item-left">
    <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
    <div id="chart6" class="row-item" style="width: 530px;"></div>
</div>
<div class="item-right">
    <div class="row-sub-title"><a href="javascript:;">理论考核详情</a></div>
    <div>
        <div>参加理论考试人数</div>
        <div>${resultMap['participateInNum']}<span>人</span></div>
    </div>
    <div>
        <div>理论考试总次数</div>
        <div>${resultMap['theoryAll']}<span>次</span></div>
    </div>
</div>


