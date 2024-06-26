<%--<script src="<s:url value='/jsp/res/platform/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>--%>
<%--<script src="<s:url value='/jsp/res/platform/monthlyReport/js/common.js'/>"></script>--%>
<script>
    $(function(){
        jboxLoad("content1","<s:url value='/res/monthlyReportGlobal/content1'/>?baseRange=1&monthDate=${monthDate}",false);
        $("#body-tab li a").click(function(){
            $("#body-tab li a").removeClass("current");
            $(this).addClass("current");
        })
    })
    function content1(baseRange){
        jboxLoad("content1","<s:url value='/res/monthlyReportGlobal/content1'/>?baseRange="+baseRange+"&monthDate=${monthDate}",false);
    }
</script>
<div class="item-content">
    <div class="tab-title">${monthString}住院医师工作月度报表</div>
    <div class="sub-title">数据统计时间：${first}~${last}</div>
    <div class="item-body">
        <ul id="body-tab" class="clearfix">
            <li><a href="javascript:;" class="current" onclick="content1('1')">国家基地</a></li>
            <li><a href="javascript:;" onclick="content1('2')">国家基地（含协同）</a></li>
        </ul>
        <div class="substance" id="content1">

        </div>
    </div>
</div>