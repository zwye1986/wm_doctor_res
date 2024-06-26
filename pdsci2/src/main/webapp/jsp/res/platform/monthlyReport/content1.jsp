<%--<script src="<s:url value='/jsp/res/platform/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>--%>
<%--<script src="<s:url value='/jsp/res/platform/monthlyReport/js/common.js'/>"></script>--%>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(function(){
        jboxLoad("chart123","<s:url value='/res/monthlyReportGlobal/chart123'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart4-0","<s:url value='/res/monthlyReportGlobal/chart4'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart5-0","<s:url value='/res/monthlyReportGlobal/chart5'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart6-0","<s:url value='/res/monthlyReportGlobal/chart6'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart7-0","<s:url value='/res/monthlyReportGlobal/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart8-0","<s:url value='/res/monthlyReportGlobal/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart9-0","<s:url value='/res/monthlyReportGlobal/chart9'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);

        $(".title-tab li a").click(function() {
            $(this).parent('li').parent("ul").find("a").removeClass("active");
            $(this).addClass("active");
        })
    })
    function chart123(isGraduate){
        jboxLoad("chart123","<s:url value='/res/monthlyReportGlobal/chart123'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart4(isGraduate){
        jboxLoad("chart4-0","<s:url value='/res/monthlyReportGlobal/chart4'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart5(isGraduate){
        jboxLoad("chart5-0","<s:url value='/res/monthlyReportGlobal/chart5'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart6(isGraduate){
        jboxLoad("chart6-0","<s:url value='/res/monthlyReportGlobal/chart6'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart7(isGraduate){
        jboxLoad("chart7-0","<s:url value='/res/monthlyReportGlobal/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart8(isGraduate){
        jboxLoad("chart8-0","<s:url value='/res/monthlyReportGlobal/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart9(isGraduate){
        jboxLoad("chart9-0","<s:url value='/res/monthlyReportGlobal/chart9'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
</script>
<style>

</style>
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
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
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
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content" id="chart4-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">三、本月未填写培训数据学员人数</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart5('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart5('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content" id="chart5-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">四、近3月未填写培训数据学员人数</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart6('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart6('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content" id="chart6-0">

        </div>
    </div>
</div>
<div class="row clearfix" style="padding-bottom: 100px;">
    <div class="col12">
        <div class="row-title clearfix">
            <div class="title-txt">五、轮转数据填写与审核概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart7('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart7('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content row-contentSub" id="chart7-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">六、轮转数据人均填写量前10名</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart8('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart8('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content" id="chart8-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">七、轮转数据人均填写量末10名</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart9('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart9('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><%--详细数据--%></a></div>
        <div class="row-content" id="chart9-0">

        </div>
    </div>
</div>
