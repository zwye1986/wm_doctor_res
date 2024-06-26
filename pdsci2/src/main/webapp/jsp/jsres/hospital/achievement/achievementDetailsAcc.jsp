<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
    });

    /**
     * 查询成绩
     */
    function searchResult(type){
        var url ="<s:url value='/jsres/hospital/getAchievementDetailsAcc?scoreType='/>"+type+"&sessionNumber=${sysCfgMap['jsres_local_sessionNumber']}";
        jboxPostLoad("achievementContent", url, null, true);
    }

</script>

<div class="main_hd">
    <h2>招录成绩管理</h2>
    <div class="title_tab">
        <ul>
            <li class="tab_select" onclick="searchResult('exam')"><a>笔试成绩</a></li>
            <li class="tab" onclick="searchResult('audition')"><a>面试成绩</a></li>
            <li class="tab" onclick="searchResult('oper')"><a>操作成绩</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="achievementContent">
    </div>
</div>


