<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/common.js'/>"></script>
<script>
    $(function(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportCity/content1'/>?monthDate=${monthDate}",false);
    })
</script>
<div class="item-content">
    <div class="tab-title">${monthString}住院医师工作月度报表</div>
    <div class="sub-title">数据统计时间：${first}~${last}</div>
    <div class="item-body">
        <ul id="body-tab" class="clearfix">

        </ul>
        <div class="substance" id="content1">

        </div>
    </div>
</div>