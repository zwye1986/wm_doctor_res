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
    zlDocQuery();
});

function zltjOrg() {
    var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("div_table", "<s:url value='/jsres/recruitDoctorInfo/zltjOrgLocal'/>?roleFlag=" + roleFlag, true);
}

function zlDocQuery() {
    var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("div_table", "<s:url value='/jsres/recruitDoctorInfo/main'/>?roleFlag=" + roleFlag, true);
}

</script>

<div class="main_hd">
	<h2>招录查询</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
			<li class="tab_select"><a onclick="zlDocQuery();">招录学员查询</a></li>
            <li class="tab" onclick="zltjOrg();"><a>招录学员统计</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

