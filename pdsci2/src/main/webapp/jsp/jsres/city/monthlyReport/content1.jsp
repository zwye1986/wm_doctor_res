<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(function(){
        jboxLoad("chart123","<s:url value='/jsres/monthlyReportCity/chart123'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart4-0","<s:url value='/jsres/monthlyReportCity/chart4'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart5-0","<s:url value='/jsres/monthlyReportCity/chart5'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart6-0","<s:url value='/jsres/monthlyReportCity/chart6'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReportCity/chart7'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReportCity/chart8'/>?monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart9-0","<s:url value='/jsres/monthlyReportCity/chart9'/>?monthDate=${monthDate}",false);
    })
    function chart123(isGraduate){
        jboxLoad("chart123","<s:url value='/jsres/monthlyReportCity/chart123'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart4(isGraduate){
        jboxLoad("chart4-0","<s:url value='/jsres/monthlyReportCity/chart4'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart5(isGraduate){
        jboxLoad("chart5-0","<s:url value='/jsres/monthlyReportCity/chart5'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart6(isGraduate){
        jboxLoad("chart6-0","<s:url value='/jsres/monthlyReportCity/chart6'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart7(isGraduate){
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReportCity/chart7'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart8(isGraduate){
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReportCity/chart8'/>?monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
</script>
<div class="row clearfix">
    <div class="col8">
        <div class="row-title clearfix">
            <div class="title-txt">一、在培学员信息</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart123('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart123('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart123">

        </div>
    </div>
    <div class="col4">
        <div class="row-title clearfix">
            <div class="title-txt">二、异动学员信息</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart4('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart4('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart4-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">三、本月未使用APP学员人数</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart5('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart5('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart5-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">四、近3个月未使用APP学员人数</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart6('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart6('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"></a></div>
        <div class="row-content" id="chart6-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col12">
        <div class="row-title clearfix">
            <div class="title-txt">五、轮转数据人均填写量概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart7('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart7('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content" id="chart7-0">

        </div>
    </div>
    <div class="col12">
        <div class="row-title clearfix">
            <div class="title-txt">六、轮转数据登记总量/审核总量概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart8('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart8('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;">详细数据</a></div>
        <div class="row-content row-contentSub" id="chart8-0">

        </div>
    </div>
</div>
