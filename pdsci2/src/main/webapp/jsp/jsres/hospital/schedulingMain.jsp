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
	reductionRotationOper();
});

//排班审核
function schedulingAudit() {
	$("#schedulingAudit").removeClass("tab");
	$("#schedulingAudit").addClass("tab_select");
	$("#schedulingSearch").removeClass("tab_select");
	$("#reductionRotationOper").removeClass("tab_select");
	jboxLoad("div_table1", "<s:url value='/jsres/doctorRecruit/schedulingAudit'/>", true);
}

//排班查询
function schedulingSearch() {
	$("#schedulingSearch").removeClass("tab");
	$("#schedulingSearch").addClass("tab_select");
	$("#schedulingAudit").removeClass("tab_select");
	$("#reductionRotationOper").removeClass("tab_select");
	jboxLoad("div_table1", "<s:url value='/jsres/doctorRecruit/schedulingSearch'/>", true);
}

function reductionRotationOper() {
	$("#reductionRotationOper").removeClass("tab");
	$("#reductionRotationOper").addClass("tab_select");
	$("#schedulingSearch").removeClass("tab_select");
	$("#schedulingAudit").removeClass("tab_select");
	data = $("#searchFormReduction").serialize() || {
		degreeType: "${GlobalConstant.FLAG_Y}",
		currentPage: 1,
	};
	var url = "<s:url value='/jsres/manage/reductionRotationOper'/>";
	jboxStartLoading();
	jboxPost(url, data, function (resp) {
		jboxEndLoading();
		$("#div_table1").html(resp);
	}, null, false);
	var orgFlow = $("#orgFlow3").val();
	getNeedReducation(orgFlow, function (result) {
		if (result == 0) {
			$("#reducationCount").hide();
		}
	});

}

function getNeedReducation(orgFlow, callBack) {
	var url = "<s:url value='/jsres/manage/getNeedReducation'/>";
	jboxPost(url, {orgFlow: orgFlow}, function (resp) {
		callBack(resp);
	}, null, false);
}


</script>

<div class="main_hd">
	<h2>排班管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
			<li id="reductionRotationOper" class="tab_select" onclick="reductionRotationOper();"><a>方案减免维护</a></li>

            <li id="schedulingSearch" class="tab" onclick="schedulingSearch();"><a>选科管理</a></li>
			<c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_org" var="key"/>
			<c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">
				<li id="schedulingAudit" class="tab" onclick="schedulingAudit();"><a >排班安排</a></li>
			</c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table1" >
</div>

