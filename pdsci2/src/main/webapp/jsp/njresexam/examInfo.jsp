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
		var url = "<s:url value='/res/njExam/examInfoList'/>";
		jboxPostLoad("doctorContent", url, $("#paramForm").serialize(), false);
	}
	function editExamInfo(recordFlow) {
		var url = "<s:url value='/res/njExam/editExamInfo'/>?recordFlow=" + recordFlow;
		jboxOpen(url, null != recordFlow?'编辑考试信息':'新增考试信息', '20cm', 500);
	}
	function setExamInfo(recordFlow) {
		var url = "<s:url value='/res/njExam/searchDocInfos'/>?recordFlow=" + recordFlow;
		jboxOpen(url, '分配考试信息', '31cm', 700);
	}
	function selExamInfo(recordFlow) {
		var url = "<s:url value='/res/njExam/searchDocInfoBySpe'/>?speName=" + recordFlow;
		jboxOpen(url, '已分配人员', '31cm', 700);
	}
	function delExamInfo(examFlow)
	{

		var url = '<s:url value="/res/njExam/delExamInfo"/>?examFlow='+examFlow;
		jboxConfirm("确认删除？" , function(){
			jboxGet(url , null , function(resp){

				jboxTip(resp);
				if(resp=="${GlobalConstant.DELETE_SUCCESSED}")
				{
					search();
				}
			} , null , true);
		});
	}
	function toPage(page) {
		$("#currentPage").val(page);
		jboxStartLoading();
		search();
	}

	function examUserImport(examFlow) {
		jboxOpen("<s:url value='/res/njExam/examUserImport'/>?examFlow=" + examFlow, "导入", 600, 180);
	}

</script>
<body>
<div class="main_hd" >
	<form id="paramForm">
		<h2>地点<input class="input" type="text" name="examAddress" value="${param.examAddress}">&#12288;
			培训专业<select name="speName" id="speName" class="select" style="width: 106px;">
				<option value="">请选择</option>
				<option <c:if test="${param.speName eq '全科转岗'}">selected="selected"</c:if> value="全科转岗">全科转岗</option>
				<option <c:if test="${param.speName eq '助理全科'}">selected="selected"</c:if> value="助理全科">助理全科</option>
				<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
					<option <c:if test="${param.speName eq dict.dictName}">selected="selected"</c:if> value="${dict.dictName}">${dict.dictName}</option>
				</c:forEach>
			</select>&#12288;
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input class="btn_green" type="button"
				   onclick="search();" value="查询">
			<input class="btn_green" type="button"
				   onclick="editExamInfo();" value="新增">
		</h2>
	</form>
	<div class="doctorContent" id="doctorContent">

	</div>
</div>
</body>
</html>