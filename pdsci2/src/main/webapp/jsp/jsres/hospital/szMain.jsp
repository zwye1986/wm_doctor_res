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
    teachTrainMain();
});

function teachTrainMain1() {
    var url = "<s:url value='/jsres/phyAss/plannedReleaseMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function statisticsTeachTrainMain1() {
    var url = "<s:url value='/jsres/phyAss/plannedMaintainMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function searchOldTeachTrain1() {
    var url = "<s:url value='/jsres/phyAss/planUserOrgMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function teachTrainMain() {
    var url = "<s:url value='/jsres/statistic/searchTeachTrainMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function auditTeacherApplicationMain() {
    var url = "<s:url value='/jsres/statistic/auditTeacherApplicationMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function statisticsTeachTrainMain() {
    var url = "<s:url value='/jsres/statistic/statisticsTeachTrainMain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

function searchOldTeachTrain() {
    var url = "<s:url value='/jsres/statistic/searchOldTeachTrain'/>?roleFlag=local";
    currentJboxLoadNoData("div_table", url, true);
}

</script>

<div class="main_hd">
	<h2>师资管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
			<li class="tab_select"><a onclick="teachTrainMain();">师资信息管理</a></li>
            <li class="tab" onclick="auditTeacherApplicationMain();"><a>审核师资申请</a></li>
            <li class="tab" onclick="statisticsTeachTrainMain();"><a>师资培训统计</a></li>
            <li class="tab" onclick="searchOldTeachTrain();"><a>师资历史数据</a></li>
            <li class="tab" onclick="teachTrainMain1();"><a>师资名单录入</a></li>
            <li class="tab" onclick="statisticsTeachTrainMain1();"><a>师资信息维护</a></li>
            <li class="tab" onclick="searchOldTeachTrain1();"><a>师资培训证书</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

