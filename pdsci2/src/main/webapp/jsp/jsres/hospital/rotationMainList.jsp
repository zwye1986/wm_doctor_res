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
    cycle(null);
});


function deptRotationSearch() {
    jboxLoad("div_table1", "<s:url value='/jsres/base/doc/schDept'/>", true);
}

function temporaryOutSearch() {
    jboxLoad("div_table1", "<s:url value='/res/manager/temporaryOutSearch'/>?roleId=org&biaoJi=Y",true);
}

function cycle(data) {
    var docTypes = "";
    <c:forEach items="${jsResDocTypeEnumList}" var="type">
    if (docTypes == "") {
        docTypes += "docTypes=" + "${type.id}";
    } else {
        docTypes += "&docTypes=" + "${type.id}";
    }
    </c:forEach>
    var url = "<s:url value='/jsres/doctorRecruit/cycle'/>?" + docTypes;
    jboxStartLoading();
    jboxPost(url, data, function (resp) {
        $("#div_table1").html(resp);
        jboxEndLoading();
    }, null, false);
}


</script>

<div class="main_hd">
    <h2>轮转查询</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <c:set value="jsres_hospital_kslzcx_${sessionScope.currUser.orgFlow }" var="key"/>
            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab_select"><a onclick="cycle(null);">学员轮转查询</a></li>
            </c:if>
            <c:set value="jsres_hospital_xylzcx_${sessionScope.currUser.orgFlow }" var="key"/>
            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                <li class="tab" onclick="deptRotationSearch();"><a>科室轮转查询</a></li>
            </c:if>
            <li class="tab" onclick="temporaryOutSearch();"><a>临时出科查询</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table1" >
</div>

