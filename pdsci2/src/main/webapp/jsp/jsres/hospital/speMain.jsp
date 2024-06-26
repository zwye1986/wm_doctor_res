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
});
function searchChangeSpe(){
	<%--var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";--%>
    var url = "<s:url value='/jsres/manage/searchChangeSpeNew'/>?viewFlag=Y";
	jboxLoad("div_table", url, true);
}
function changeSpeMain(){
    <%--var url = "<s:url value='/jsres/manage/changeSpeMain'/>";--%>
	var url = "<s:url value='/jsres/manage/changeSpeMainNew'/>";
	jboxLoad("div_table", url, true);
}
</script>

<div class="main_hd">
	<h2>

		<c:if test="${GlobalConstant.USER_LIST_CHARGE ne sessionScope.userListScope}">
			专业变更管理
		</c:if>
		<c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
			专业变更查询
		</c:if>
	</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
        	<c:if test="${GlobalConstant.USER_LIST_CHARGE != sessionScope.userListScope}">
           		 <li class="tab_select" onclick="changeSpeMain();"><a >专业变更审核</a></li>
        	</c:if>
            <li class="tab" onclick="searchChangeSpe();"><a>专业变更查询</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

