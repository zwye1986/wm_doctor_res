<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(function(){
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReportGlobal/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReportGlobal/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart9-0","<s:url value='/jsres/monthlyReportGlobal/chart9'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
    })

    function chart7(isGraduate){
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReportGlobal/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart8(isGraduate){
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReportGlobal/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart9(isGraduate){
        jboxLoad("chart9-0","<s:url value='/jsres/monthlyReportGlobal/chart9'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }

</script>


<div class="row clearfix" style="padding-bottom: 70px;">
    <div class="col12">
        <div class="row-title clearfix">
            <div class="title-txt">一、轮转数据填写与审核概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart7('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart7('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><!--详细数据-->&nbsp;</a></div>
        <div class="row-content row-contentSub" id="chart7-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">二、轮转数据人均填写量前10名</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart8('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart8('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><!--详细数据-->&nbsp;</a></div>
        <div class="row-content" id="chart8-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">三、轮转数据人均填写量末10名</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart9('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart9('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a href="javascript:;"><!--详细数据-->&nbsp;</a></div>
        <div class="row-content" id="chart9-0">

        </div>
    </div>
</div>
