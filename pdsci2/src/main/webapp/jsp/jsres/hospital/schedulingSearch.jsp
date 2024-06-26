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
        <%--$("li").click(function(){--%>
        <%--    $(".tab_select").addClass("tab");--%>
        <%--    $(".tab_select").removeClass("tab_select");--%>
        <%--    $(this).removeClass("tab");--%>
        <%--    $(this).addClass("tab_select");--%>
        <%--});--%>
        <%--if ("${param.liId}" != "") {--%>
        <%--    $("#${param.liId}").addClass("tab_select");--%>
        <%--} else {--%>
        <%--    $('li').first().addClass("tab_select");--%>
        <%--}--%>
        <%--$(".tab_select").click();--%>
        searchResultMain('dept');
    });


    function searchResultMain(type){
        if('dept' == type){
            $("#dept").removeClass("tab");
            $("#dept").addClass("tab_select");
            $("#doctor").removeClass("tab_select");
        }
        if('doctor' == type){
            $("#doctor").removeClass("tab");
            $("#doctor").addClass("tab_select");
            $("#dept").removeClass("tab_select");
        }
        var url="<s:url value='/jsres/doctorRecruit/schedulingSearchMain?type='/>"+type;
        jboxPostLoad("achievementContent", url, null, true);
    }

</script>

<div class="main_hd">
    <h2>排班管理 — 选科管理</h2>
    <div class="title_tab">
        <ul>
            <li id ="dept" class="tab_select" onclick="searchResultMain('dept')"><a>科室</a></li>
            <li id ="doctor" class="tab" onclick="searchResultMain('doctor')"><a>学员</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="achievementContent">
    </div>
</div>


