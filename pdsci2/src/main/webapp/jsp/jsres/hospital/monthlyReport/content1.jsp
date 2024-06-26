<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/jquery-1.10.2.min.js'/>"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReport/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(function(){
        jboxLoad("chart123","<s:url value='/jsres/monthlyReport/chart123'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart4-0","<s:url value='/jsres/monthlyReport/chart4'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart5-0","<s:url value='/jsres/monthlyReport/chart5'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart6-0","<s:url value='/jsres/monthlyReport/chart6'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReport/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReport/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate=notGraduate",false);
        jboxLoad("chart9-0","<s:url value='/jsres/monthlyReport/chart9'/>?baseRange=${baseRange}&monthDate=${monthDate}",false);
    })
    function chart123(isGraduate){
        jboxLoad("chart123","<s:url value='/jsres/monthlyReport/chart123'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart4(isGraduate){
        jboxLoad("chart4-0","<s:url value='/jsres/monthlyReport/chart4'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart5(isGraduate){
        jboxLoad("chart5-0","<s:url value='/jsres/monthlyReport/chart5'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart6(isGraduate){
        jboxLoad("chart6-0","<s:url value='/jsres/monthlyReport/chart6'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart7(isGraduate){
        jboxLoad("chart7-0","<s:url value='/jsres/monthlyReport/chart7'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function chart8(isGraduate){
        jboxLoad("chart8-0","<s:url value='/jsres/monthlyReport/chart8'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate,false);
    }
    function exportExl123(param){
        var isGraduate = $("."+param).find(".active").attr("param");
        var url="<s:url value='/jsres/monthlyReport/export123'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate;
        window.open(url);
    }
    function exportExl4(param){
        var isGraduate = $("."+param).find(".active").attr("param");
        var url="<s:url value='/jsres/monthlyReport/export4'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate;
        window.open(url);
    }
    function detail5(param){
        var isGraduate = $("."+param).find(".active").attr("param");
        var url="<s:url value='/jsres/monthlyReport/detail5Main'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate;
        jboxOpen(url,"详细信息",1000,800,true);
    }
    function detail6(){
        var url="<s:url value='/jsres/monthlyReport/detail6Main'/>?monthDate=${monthDate}";
        jboxOpen(url,"详细信息",1300,800,true);
    }
    function detail6_2(param){
        var isGraduate = $("."+param).find(".active").attr("param");
        var url="<s:url value='/jsres/monthlyReport/exportDetail6_2'/>?baseRange=${baseRange}&monthDate=${monthDate}&isGraduate="+isGraduate;
        window.open(url);
    }
    function detail7(){
        var url="<s:url value='/jsres/monthlyReport/detail7Main'/>?monthDate=${monthDate}";
        jboxOpen(url,"详细信息",1300,800,true);
    }
    function detail8(){
        var url="<s:url value='/jsres/monthlyReport/detail8Main'/>?monthDate=${monthDate}";
        jboxOpen(url,"详细信息",1300,800,true);
    }
    function detail9(){
        var url="<s:url value='/jsres/monthlyReport/detail9Main'/>?monthDate=${monthDate}";
        jboxOpen(url,"详细信息",1300,800,true);
    }
</script>
<div class="row clearfix">
    <div class="col8">
        <div class="row-title clearfix">
            <div class="title-txt">一、在培学员信息</div>
            <div class="title-tab">
                <ul class="ul123">
                    <li><a href="javascript:;" param="notGraduate" class="active" onclick="chart123('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" param="Graduate" onclick="chart123('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="exportExl123('ul123')">详细数据</a></div>
        <div class="row-content" id="chart123">

        </div>
    </div>
    <div class="col4">
        <div class="row-title clearfix">
            <div class="title-txt">二、异动学员信息</div>
            <div class="title-tab">
                <ul class="ul4">
                    <li><a href="javascript:;" param="notGraduate" class="active" onclick="chart4('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" param="Graduate" onclick="chart4('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="exportExl4('ul4')">详细数据</a></div>
        <div class="row-content" id="chart4-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col5">
        <div class="row-title clearfix">
            <div class="title-txt">三、出入科人数</div>
            <div class="title-tab">
                <ul class="ul5">
                    <li><a href="javascript:;" param="notGraduate" class="active" onclick="chart5('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" param="Graduate" onclick="chart5('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="detail5('ul5')">详细数据</a></div>
        <div class="row-content" id="chart5-0">

        </div>
    </div>
    <div class="col7">
        <div class="row-title clearfix">
            <div class="title-txt">四、出科考核情况</div>
            <div class="title-tab">
                <ul class="ul6">
                    <li><a href="javascript:;" param="notGraduate" class="active" onclick="chart6('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" param="Graduate" onclick="chart6('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a></a></div>
        <div class="row-content clearfix" id="chart6-0">

        </div>
    </div>
</div>
<div class="row clearfix">
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">五、轮转数据登记概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart7('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart7('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="detail7()">详细数据</a></div>
        <div class="row-content" id="chart7-0">

        </div>
    </div>
    <div class="col6">
        <div class="row-title clearfix">
            <div class="title-txt">六、轮转数据审核概况</div>
            <div class="title-tab">
                <ul>
                    <li><a href="javascript:;" class="active" onclick="chart8('notGraduate')">非专硕</a></li>
                    <li><a href="javascript:;" onclick="chart8('Graduate')">在校专硕</a></li>
                </ul>
            </div>
        </div>
        <div class="row-sub-title"><a onclick="detail8()">详细数据</a></div>
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
        <div class="row-sub-title"><a onclick="detail9()">详细数据</a></div>
        <div class="row-content clearfix" id="chart9-0">

        </div>
    </div>
</div>