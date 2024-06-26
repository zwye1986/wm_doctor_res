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

function activityTarget() {
    var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("div_table", "<s:url value='/jsres/activityTarget/manageMain'/>?roleFlag=" + roleFlag, true);
}
function lectures(data) {
    var url = "<s:url value='/jsres/lecture/getLectures'/>";
    jboxStartLoading();
    jboxPost(url, data, function (resp) {
        $("#div_table").html(resp);
        jboxEndLoading();
    }, null, false);
}
function activityQueryMain() {
    jboxLoad("div_table", "<s:url value='/jsres/menuNew/activityQueryMain'/>", true);
}
function activityQuery() {
    var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("div_table", "<s:url value='/jsres/activityQuery/main'/>?roleFlag=" + roleFlag, true);
}
</script>

<div class="main_hd">
	<h2>教学管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select" onclick="activityTarget();"><a>评价指标维护</a></li>
            <c:set value="jsres_hospital_jzxxgl_${sessionScope.currUser.orgFlow }" var="key"/>
            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab" onclick="lectures(null);"><a>讲座管理</a></li>
            </c:if>
            <c:set value="jsres_hospital_activity_${sessionScope.currUser.orgFlow }" var="key"/>
            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab" onclick="activityQuery();"><a>教学活动</a></li>
                <li class="tab" onclick="activityQueryMain();"><a>查询统计</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

