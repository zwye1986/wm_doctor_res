<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
<script>
    $(function(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportGlobalNew/chart12'/>?monthDate=${monthDate}",false);
    })
    function chart12(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportGlobalNew/chart12'/>?monthDate=${monthDate}",false);
    }
    function chart3(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportGlobalNew/chart3'/>?monthDate=${monthDate}",false);
    }
    function content0_2(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportGlobalNew/content0_2'/>?monthDate=${monthDate}",false);
    }
</script>
<div class="item-content">
    <div class="tab-title">${monthString}住院医师工作月度报表</div>
    <div class="sub-title">数据统计时间：${first}~${last}</div>
    <div class="item-body">
        <ul id="body-tab" class="clearfix">
            <li><a href="javascript:;" class="current" onclick="chart12()">学员信息统计</a></li>
            <li><a href="javascript:;" onclick="chart3()">APP使用情况统计</a></li>
            <li><a href="javascript:;" onclick="content0_2()">学员轮转数据统计</a></li>
        </ul>
        <div class="substance" id="content1">

        </div>
    </div>
</div>