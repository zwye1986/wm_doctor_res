<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
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
	var url = "<s:url value='/jszy/manage/searchChangeSpe'/>?viewFlag=Y";
	jboxLoad("div_table", url, true);
}
function changeSpeMain(){
	var url = "<s:url value='/jszy/manage/changeSpeMain'/>";
	jboxLoad("div_table", url, true);
}
</script>

<div class="main_hd">
	<h2>学员专业变更</h2>
    <div class="title_tab">
        <ul>
        	<c:if test="${GlobalConstant.USER_LIST_CHARGE != sessionScope.userListScope}">
           		 <li class="tab_select" onclick="changeSpeMain();"><a >变更专业审核</a></li>
        	</c:if>
            <li class="tab" onclick="searchChangeSpe();"><a>变更专业查询</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

