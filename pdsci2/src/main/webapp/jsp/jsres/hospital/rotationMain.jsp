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
    afterDataDel();
});

function afterDataDel() {
    jboxLoad("div_table", "<s:url value='/jsres/afterDataManager/afterDataDel'/>", true);
}

function afterDataBack() {
    jboxLoad("div_table", "<s:url value='/jsres/afterDataManager/afterDataBack'/>", true);
}

</script>

<div class="main_hd">
	<h2>轮转数据</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
			<li class="tab_select"><a onclick="afterDataDel();">出科数据删除</a></li>
            <li class="tab" onclick="afterDataBack();"><a>出科数据恢复</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>

