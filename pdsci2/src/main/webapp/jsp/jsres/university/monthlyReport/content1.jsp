<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(function(){
        jboxLoad("chart123","<s:url value='/jsres/monthlyReportUniversity/chart123'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart4-0","<s:url value='/jsres/monthlyReportUniversity/chart4'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart5-0","<s:url value='/jsres/monthlyReportUniversity/chart5'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart6-0","<s:url value='/jsres/monthlyReportUniversity/chart6'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReportUniversity/chart7'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReportUniversity/chart8'/>?monthDate=${monthDate}&isGraduate=Graduate",false);
        jboxLoad("chart9-0","<s:url value='/jsres/monthlyReportUniversity/chart9'/>?monthDate=${monthDate}",false);
    })
    function exportExl123(){
        var url="<s:url value='/jsres/monthlyReportUniversity/export123'/>?monthDate=${monthDate}&isGraduate=Graduate";
        window.open(url);
    }
    function exportExl4(){
        var url="<s:url value='/jsres/monthlyReportUniversity/export4'/>?monthDate=${monthDate}&isGraduate=Graduate";
        window.open(url);
    }
</script>
<div class="row clearfix">
    <div class="col8">
        <div class="row-title clearfix">
            <div class="title-txt">一、在培学员信息</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart123('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart123('Graduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="exportExl123()">详细数据</a></div>
        <div class="row-content" id="chart123">

        </div>
    </div>
    <div class="col4">
        <div class="row-title clearfix">
            <div class="title-txt">二、异动学员信息</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart4('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart4('Graduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="exportExl4()">详细数据</a></div>
        <div class="row-content" id="chart4-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col5">
        <div class="row-title clearfix">
            <div class="title-txt">三、出入科人数</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart5('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart5('notGraduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart5-0">

        </div>
    </div>
    <div class="col7">
        <div class="row-title clearfix">
            <div class="title-txt">四、出科考核情况</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart6('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart6('notGraduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"></a></div>
        <div class="row-content clearfix" id="chart6-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">五、轮转数据登记/审核概况</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart7('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart7('notGraduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart7-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">六、前3个月内未使用APP学员人数</div>
            <div class="title-tab">
                <ul>
                    <%--<li><a href="javascript:;" class="active" onclick="chart8('notGraduate')">非专硕</a></li>--%>
                    <%--<li><a href="javascript:;" onclick="chart8('notGraduate')">在校专硕</a></li>--%>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart8-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col12">
        <div class="row-title clearfix">
            <div class="title-txt">七、教学活动统计</div>
            <!-- <div class="title-tab">
              <ul>
                <li><a href="javascript:;" class="active">非专硕</a></li>
                <li><a href="javascript:;">在校专硕</a></li>
              </ul>
            </div> -->
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content clearfix" id="chart9-0">

        </div>
    </div>
</div>