<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){

    docProcessEval2();
});
function docProcessEval2() {
    $("#docProcessEval2").removeClass("tab");
    $("#docProcessEval2").addClass("tab_select");
    $("#cycleResults").removeClass("tab_select");
    $("#cycleErrorResults").removeClass("tab_select");
    var url = "<s:url value='/jsres/hospital/tabMain'/>?isQuery=Y";
    jboxLoad("div_table", url, true);
}

function cycleResults(data) {
    $("#cycleResults").removeClass("tab");
    $("#cycleResults").addClass("tab_select");
    $("#docProcessEval2").removeClass("tab_select");
    $("#cycleErrorResults").removeClass("tab_select");
    var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitResult'/>?trainingTypeId=DoctorTrainingSpe";
    jboxLoad("div_table", url, true);
}

function cycleErrorResults(data) {
    $("#cycleErrorResults").removeClass("tab");
    $("#cycleErrorResults").addClass("tab_select");
    $("#docProcessEval2").removeClass("tab_select");
    $("#cycleResults").removeClass("tab_select");
    var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitErrorResult'/>";
    jboxLoad("div_table", url, true);
}
</script>

<div class="main_hd">
	<h2>出科管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li id="docProcessEval2" class="tab_select" onclick="docProcessEval2();"><a>出科评价</a></li>
            <c:set value="jsres_hospital_xyckcjcx_${sessionScope.currUser.orgFlow }" var="key"/>
            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                <li id="cycleResults" class="tab"><a onclick="cycleResults(null);">学员出科查询</a></li>
                <li id="cycleErrorResults" class="tab"><a onclick="cycleErrorResults(null);">学员出科异常查询</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

