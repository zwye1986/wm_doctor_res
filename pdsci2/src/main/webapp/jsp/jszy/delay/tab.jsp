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
function delayManage(operType){
	var url = "<s:url value='/jszy/delayReturn/delayManage?roleId=${roleId}&statusFlag=all'/>" + "&operType=" + operType;
	jboxLoad("div_table", url, true);
}
</script>

<div class="main_hd">
	<h2>延期学员管理</h2>
    <div class="title_tab">
        <ul>
			<li class="tab_select" onclick="delayManage('isQuery');"><a >延期查询</a></li>
			<%--应产品要求，延期流程由需要省厅审核，改为基地管理员直接延期不经省厅审核--%>
            <%--<li class="tab" onclick="delayManage('isCheck');"><a>延期审核</a></li>--%>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

