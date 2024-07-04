<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广医大研究生系统管理员端APP测试程序</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<style type="text/css">
body{ 
	height:100%;
	overflow:scroll;
}
</style>
<script type="text/javascript">
	function test() {
		var action = $("#reqCode option:selected").val();
		if(action==""){
			jboxTip("请选择测试的交易");
			return;
		}
		var method = $("#reqCode option:selected").attr("method");
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		var url = "<s:url value='/res/gydxj/admin'/>/"+action+"?_method="+method;
		var formData = $("#reqParam").val();
		$.ajax({
			type : method,
			url : url,
			data : formData,
			cache : false,
			beforeSend : function(){
				jboxStartLoading();
			},
			success : function(resp) {
				jboxEndLoading();
				jboxTip("测试成功");
				$("#rsp").text(JSON.stringify(resp,null,4));
			},
			error : function() {
				jboxEndLoading();
				jboxTip("操作失败,请刷新页面后重试");
			},
			complete : function(){
				jboxEndLoading();
			},
			statusCode: {405: function() {
				jboxTip("交易方法不正确");
			  }
			}
		});

	}
	function change(divid) {
		$("#reqParam").val($("#" + divid + "  textarea").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="${ctxPath}/res/gydxj/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);" style="min-width:400px;">
						<option value="" method="">请选择</option>
						<option value="eduUserList" method="post" <c:if test="${param.reqCode=='eduUserList'}">selected</c:if> >1.0.0 学员信息列表 eduUserList </option>
						<option value="partStatusList" method="post" <c:if test="${param.reqCode=='partStatusList'}">selected</c:if> >1.0.1 学籍信息确认状态 partStatusList </option>
						<option value="backConfirm" method="post" <c:if test="${param.reqCode=='backConfirm'}">selected</c:if> >1.0.2 学籍信息重新确认 backConfirm </option>
						<option value="gradeList" method="post" <c:if test="${param.reqCode=='gradeList'}">selected</c:if> >2.0.1 成绩审核列表 gradeList </option>
						<option value="gradeDetail" method="post" <c:if test="${param.reqCode=='gradeDetail'}">selected</c:if> >2.0.2 成绩详情 gradeDetail </option>
						<option value="auditGrade" method="post" <c:if test="${param.reqCode=='auditGrade'}">selected</c:if> >2.0.3 成绩审核操作 auditGrade </option>
						<option value="courseList" method="post" <c:if test="${param.reqCode=='courseList'}">selected</c:if> >3.0.1 课程维护列表 courseList </option>
						<option value="courseEdit" method="post" <c:if test="${param.reqCode=='courseEdit'}">selected</c:if> >3.0.2 课程信息 courseEdit </option>
						<option value="courseSave" method="post" <c:if test="${param.reqCode=='courseSave'}">selected</c:if> >3.0.3 课程信息保存 courseSave </option>
						<option value="queryOutline" method="post" <c:if test="${param.reqCode=='queryOutline'}">selected</c:if> >3.0.4 课程大纲教材 queryOutline </option>
						<option value="editOutline" method="post" <c:if test="${param.reqCode=='editOutline'}">selected</c:if> >3.0.5 课程大纲教材编辑 editOutline </option>
						<option value="saveOutline" method="post" <c:if test="${param.reqCode=='saveOutline'}">selected</c:if> >3.0.4 课程大纲教材保存 saveOutline </option>
						<option value="eduTermList" method="post" <c:if test="${param.reqCode=='eduTermList'}">selected</c:if> >4.0.1 排课列表 eduTermList </option>
						<option value="dossierEmployList" method="post" <c:if test="${param.reqCode=='dossierEmployList'}">selected</c:if> >5.0.1 学员信息列表(档案管理/思政就业) dossierEmployList </option>
						<option value="dossierEmployEdit" method="post" <c:if test="${param.reqCode=='dossierEmployEdit'}">selected</c:if> >5.0.2 档案管理/思政就业信息 dossierEmployEdit </option>
						<option value="tutorList" method="post" <c:if test="${param.reqCode=='tutorList'}">selected</c:if> >6.0.1 导师信息列表 tutorList </option>
						<option value="tutorEdit" method="post" <c:if test="${param.reqCode=='tutorEdit'}">selected</c:if> >6.0.2 导师详情(带教端导师共用) tutorEdit </option>
						<option value="tutorSave" method="post" <c:if test="${param.reqCode=='tutorSave'}">selected</c:if> >6.0.3 导师信息(带教端导师共用)保存 tutorSave </option>
						<option value="qrCode" method="post" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >7.0.1 条形码扫描档案信息 qrCode </option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gydxj/test'/>">学籍测试工具</a>
					<a href="<s:url value='/res/gydxj/student/test'/>">学员端测试工具</a>
					<a href="<s:url value='/res/gydxj/teacher/test'/>">教师端测试工具</a>
					<script>
						function reload(){
							jboxGet('<s:url value="/common/reLoadCfg"/>',null,function(resp){
								if(resp=='Y'){
									jboxTip('刷新成功');
								}
							},null,false);
						}
					</script>
					<a href="javascript:reload();">刷新配置</a>
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" ></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="eduUserList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="partStatusList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=</textarea>
	</div>
	<div id="backConfirm" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&partId=</textarea>
	</div>
	<div id="gradeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&roleId=&auditFlag=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="gradeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=</textarea>
	</div>
	<div id="auditGrade" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=&auditStatusId=</textarea>
	</div>
	<div id="courseList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&gradationId=&year=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="courseEdit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=</textarea>
	</div>
	<div id="courseSave" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=</textarea>
	</div>
	<div id="queryOutline" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=&typeId=</textarea>
	</div>
	<div id="editOutline" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=&typeId=</textarea>
	</div>
	<div id="saveOutline" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="eduTermList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&year=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="dossierEmployList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&confirmFlag=&partId=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="dossierEmployEdit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&roleId=</textarea>
	</div>
	<div id="tutorList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="tutorEdit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">doctorFlow=</textarea>
	</div>
	<div id="tutorSave" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">doctorFlow=</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">barCode=&roleId=Admin</textarea>
	</div>
</body>
</html>