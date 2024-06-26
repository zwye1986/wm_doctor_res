<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        require.config({
            paths: {
                echarts:'<s:url value='/js/echarts'/>'
            }
        });
        require(
            ['echarts','echarts/chart/bar'],function(ec){
            var myChart = ec.init(document.getElementById('main'));
            var lineLabel = []; //x轴文字
            var lineValue = [];
            var value = [];
            <c:forEach items="${roomList}" var="rom">
                lineLabel.push("${rom.dictName}");
                lineValue.push("${rom.useNum}");
            </c:forEach>
            if (lineLabel.length==0) {
                lineLabel.push("");
                lineValue.push("");
            }
            var option = {
                title : {
                    text: '培训教室使用统计',
                    x:'center'
                },
                tooltip : {
                    trigger: 'axis'
                },
                calculable : true,
                grid:{
                    height:300
                },
                xAxis : [
                    {
                        type : 'category',
                        axisLabel:{
                            interval:0,
                            rotate:45,
                            margin:10,
                            textStyle:{
                                fontWeight:"bolder",
                                color:"#000000"
                            }
                        },
                        name:'教室',
                        boundaryGap : true,
                        data : lineLabel
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        interval: 'auto',
                        name:'使用次数',
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
                        name:'使用次数',
                        type:'bar',
                        data:lineValue,
                        barWidth : 20,
                        itemStyle: {
                            normal: {
                                label : {
                                    show: true, position: 'top',
                                    formatter:function(a,b,c){
                                        return c;
                                    }
                                }
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option);
        });
    </script>
    <script type="text/javascript">
        function toPage(){
            if (!$("#searchForm").validationEngine("validate")) {
                return;
            }
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始日期不能大于结束日期！");
                obj.value = "";
            }
        }
        function exportInfo(){
            if (!$("#searchForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value='/zsey/base/expRoomUseNum'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/roomStatistics"/>" method="post">
            <div class="choseDivNewStyle">
                <span style=""></span>时间区间：
                <input type="text" readonly="readonly" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainBeginDate" value="${trainBeginDate}" onchange="checkTime(this)"/>
                ~ <input type="text" readonly="readonly" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainEndDate" value="${trainEndDate}" onchange="checkTime(this)"/>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
                </div>
            </div>
        </form>
        <div id="main" style="width:100%;height:550px;"></div>
    </div>
</div>
</body>
</html>