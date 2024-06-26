<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
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
    // activityTarget();
});
//院级督导    评审专家管理
function hospitalLeaderMain() {
    jboxLoad("div_table","<s:url value='/jsres/supervisio/hospitalLeaderMain'/>",true);
}

//院级督导  评审督导项目管理
function hospitalSubjectMain() {
    jboxLoad("div_table","<s:url value='/jsres/supervisio/hospitalSubjectMain'/>",true);
}
//院级督导   统计
function hospitalStatisticsMain() {
    jboxLoad("div_table","<s:url value='/jsres/supervisio/hospitalStatisticsMain'/>",true);
}
</script>

<div class="main_hd">
	<h2>督导管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select" onclick="hospitalLeaderMain();"><a>评审专家维护</a></li>
            <li class="tab" onclick="hospitalSubjectMain();"><a>评审项目配置</a></li>
            <li class="tab" onclick="hospitalStatisticsMain();"><a>评审结果汇总</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

