<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		toPage();
	});
	function search() {
		var url = "<s:url value='/res/njExam/docInfoList'/>"
		jboxPostLoad("doctorContent", url, $("#paramForm").serialize(), false);
	}
	function editExamCard(userFlow,idNo) {
		var url = "<s:url value='/res/njExam/editCard'/>?userFlow=" + userId +"&idNo=" + idNo;
		jboxOpen(url, "编辑准考证", '20cm', 500);
	}
	function deleteRecord(userFlow,idNo){
		jboxConfirm("确定删除?",function(){
			var datas = {
				userFlow:userFlow,
				idNo:idNo
			};
			jboxGet("<s:url value='/res/njExam/deleteRecord'/>",datas,function(){
				search();
			},null,true);
		});
	}
	function importDocInfo() {
		jboxOpen("<s:url value='/res/njExam/examCardImportTwo'/>", "导入", 600, 180);
	}
	function toPage(page) {
		$("#currentPage").val(page);
		jboxStartLoading();
		search();
	}
	function exportInfo(){
		var url = "<s:url value='/res/njExam/exportDocInfoList'/>";
		jboxTip("导出中…………");
		jboxExp($("#paramForm"), url);
		jboxEndLoading();
	}
</script>
<body>
<div class="main_hd">
	<form id="paramForm" action="<s:url value="/res/njExam/docInfo"/>" method="post">
		<h2>姓名<input class="input" type="text" name="userName" value="${param.userName}">&#12288;
			证件号<input class="input" type="text" name="idNo" value="${param.idNo}">&#12288;
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input class="btn_green" type="button"
				   onclick="search();" value="查询">
			<input class="btn_green" type="button"
				   onclick="importDocInfo();" value="导入">
			<input class="btn_green" type="button"
				   onclick="exportInfo();" value="导出">
		</h2>
	</form>
	<div class="doctorContent"  id="doctorContent">
	</div>
</div>
</body>
</html>