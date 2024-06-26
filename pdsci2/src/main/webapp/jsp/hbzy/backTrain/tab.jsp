<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
function backTrainManage(operType){
	var url = "<s:url value='/hbzy/delayReturn/backTrainManage?roleId=${roleId}'/>" + "&operType=" + operType;
	jboxLoad("div_table", url, true);
}
</script>

<div class="main_hd">
	<h2>退培学员管理</h2>
    <div class="title_tab">
        <ul>
			<li class="tab_select" onclick="backTrainManage('isQuery');"><a >退培查询</a></li>
            <li class="tab" onclick="backTrainManage('isCheck');"><a>退培审核</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

