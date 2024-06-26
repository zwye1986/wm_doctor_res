<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
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
	$(".tab_select a").click();
});
function showBackCheck(){
	<%--var url="<s:url value='/jsres/doctor/showBackCheck'/>";--%>
    var url="<s:url value='/jsres/doctor/showBackCheckMain'/>";
	jboxLoad("hosContent",url,true);
}
function backTrainInfo(){
	<%--var url="<s:url value='/jsres/doctor/backTrainInfo?roleFlag=global&currentPage=1'/>";--%>
    var url="<s:url value='/jsres/doctor/backTrainInfoMain?roleFlag=global&currentPage=1'/>";
	jboxLoad("hosContent",url,true);
}
function backTrainStatistics(){
	var url="<s:url value='/jsres/doctor/backTrainStatistics'/>";
	jboxLoad("hosContent",url,true);
}
</script>
<body>
<div class="main_hd">
	<h2>退培学员管理</h2>
	<div class="title_tab" style="margin-top: 0;">
		<ul>
			<li class="tab" onclick="showBackCheck();"style="cursor: pointer;"><a >退培学员审核</a></li>
			<li class="tab" onclick="backTrainInfo();"style="cursor: pointer;"><a >退培学员查询</a></li>
			<li class="tab" onclick="backTrainStatistics();"style="cursor: pointer;"><a >退培人员统计</a></li>
		</ul>
	</div>
</div>
<div class="main_bd">
	<div id="hosContent">
	</div>
</div>
</body>
