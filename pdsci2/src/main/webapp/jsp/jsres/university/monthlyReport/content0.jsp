<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/common.js'/>"></script>
<script>
    $(function(){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportUniversity/content1'/>?monthDate=${monthDate}",false);
    })
    function content1(baseRange){
        jboxLoad("content1","<s:url value='/jsres/monthlyReportUniversity/content1'/>?monthDate=${monthDate}",false);
    }
</script>
<div class="item-content">
    <div class="tab-title">${monthString}住院医师工作月度报表</div>
    <div class="sub-title">数据统计时间：${first}~${last}</div>
    <div class="item-body">
        <ul id="body-tab" class="clearfix">
            <%--<li><a href="javascript:;" class="current" onclick="content1('1')">本基地</a></li>--%>
        <%--<c:if test="${orgLevelId eq 'CountryOrg'}">--%>
            <%--<li><a href="javascript:;" onclick="content1('2')">本基地含协同</a></li>--%>
            <%--<li><a href="javascript:;" onclick="content1('3')">仅协同</a></li>--%>
        <%--</c:if>--%>
        </ul>
        <div class="substance" id="content1">

        </div>
    </div>
</div>