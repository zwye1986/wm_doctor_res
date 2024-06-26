<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>

    linew {
        /*display: list-item;*/
        /*text-align: -webkit-match-parent;*/
    }
</style>
<script type="text/javascript">
$(document).ready(function(){
	<%--$("li").click(function(){--%>
	<%--	$(".tab_select").addClass("tab");--%>
	<%--	$(".tab_select").removeClass("tab_select");--%>
	<%--	$(this).removeClass("tab");--%>
	<%--	$(this).addClass("tab_select");--%>
	<%--});--%>
	<%--if ("${param.liId}" != "") {--%>
	<%--	$("#${param.liId}").addClass("tab_select");--%>
	<%--} else {--%>
	<%--	$('li').first().addClass("tab_select");--%>
	<%--}--%>
    deptActivityStatistics();
});

function deptActivityStatistics() {
    $("#deptActivityStatistics").removeClass("tab");
    $("#deptActivityStatistics").addClass("tab_select");
    $("#teacherActivityStatistics").removeClass("tab_select");
    $("#doctorActivityStatistics").removeClass("tab_select");
    jboxLoad("div_table1", "<s:url value='/jsres/deptActivityStatistics/main'/>", true);
}

function teacherActivityStatistics() {
    $("#teacherActivityStatistics").removeClass("tab");
    $("#teacherActivityStatistics").addClass("tab_select");
    $("#deptActivityStatistics").removeClass("tab_select");
    $("#doctorActivityStatistics").removeClass("tab_select");
    jboxLoad("div_table1", "<s:url value='/jsres/teacherActivityStatistics/main'/>", true);
}

function doctorActivityStatistics() {
    $("#doctorActivityStatistics").removeClass("tab");
    $("#doctorActivityStatistics").addClass("tab_select");
    $("#teacherActivityStatistics").removeClass("tab_select");
    $("#deptActivityStatistics").removeClass("tab_select");
    jboxLoad("div_table1", "<s:url value='/jsres/doctorActivityStatistics/main'/>", true);
}

</script>

<div class="main_hd">
<%--	<h2>教学管理</h2>--%>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li id="deptActivityStatistics" class="tab_select" onclick="deptActivityStatistics();"><a>科室活动统计</a></li>
            <li id="teacherActivityStatistics" class="tab" onclick="teacherActivityStatistics();"><a>师资活动统计</a></li>
            <li id="doctorActivityStatistics" class="tab" onclick="doctorActivityStatistics();"><a>学员活动统计</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table1" >
</div>

