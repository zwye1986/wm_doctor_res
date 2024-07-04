<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广医大研究生系统教师端APP测试程序</title>
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
		var url = "<s:url value='/res/gydxj/teacher'/>/"+action+"?_method="+method;
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
						<option value="searchCourse" method="post" <c:if test="${param.reqCode=='searchCourse'}">selected</c:if> >1.0.0 课程查询 searchCourse </option>
						<option value="scheduleCourse" method="post" <c:if test="${param.reqCode=='scheduleCourse'}">selected</c:if> >1.0.1 授课详情 scheduleCourse </option>
						<option value="signDetail" method="post" <c:if test="${param.reqCode=='signDetail'}">selected</c:if> >1.0.2 课时出勤情况 signDetail </option>
						<option value="signOutOpt" method="post" <c:if test="${param.reqCode=='signOutOpt'}">selected</c:if> >1.0.3 签到签退调整 signOutOpt </option>
						<option value="stuCourseList" method="post" <c:if test="${param.reqCode=='stuCourseList'}">selected</c:if> >2.0.1 成绩管理 stuCourseList </option>
						<option value="gradeDetail" method="post" <c:if test="${param.reqCode=='gradeDetail'}">selected</c:if> >2.0.2 成绩新增及详情 gradeDetail </option>
						<option value="saveGrade" method="post" <c:if test="${param.reqCode=='saveGrade'}">selected</c:if> >2.0.3 成绩保存 saveGrade </option>
						<option value="courseList" method="post" <c:if test="${param.reqCode=='courseList'}">selected</c:if> >2.0.4 课程列表 courseList </option>
						<option value="auditApplyList" method="post" <c:if test="${param.reqCode=='auditApplyList'}">selected</c:if> >3.0.1 异动审核列表 auditApplyList </option>
						<option value="applyDetail" method="post" <c:if test="${param.reqCode=='applyDetail'}">selected</c:if> >3.0.2 异动审核详情 applyDetail </option>
						<option value="auditApply" method="post" <c:if test="${param.reqCode=='auditApply'}">selected</c:if> >3.0.3 异动审核操作 auditApply </option>
						<option value="stuList" method="post" <c:if test="${param.reqCode=='stuList'}">selected</c:if> >4.0.1 选课管理列表 stuList </option>
						<option value="stuCourseDetail" method="post" <c:if test="${param.reqCode=='stuCourseDetail'}">selected</c:if> >4.0.2 选课详情 stuCourseDetail </option>
						<option value="stuCourseChange" method="post" <c:if test="${param.reqCode=='stuCourseChange'}">selected</c:if> >4.0.3 选课修改 stuCourseChange </option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gydxj/test'/>">学籍测试工具</a>
					<a href="<s:url value='/res/gydxj/student/test'/>">学员端测试工具</a>
					<a href="<s:url value='/res/gydxj/admin/test'/>">管理员端测试工具</a>
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
	<div id="searchCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="scheduleCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="signDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=&signFlag=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="signOutOpt" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&recordFlow=&sign=</textarea>
	</div>
	<div id="stuCourseList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&courseFlow=&submitFlag=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="gradeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=</textarea>
	</div>
	<div id="saveGrade" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="courseList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="auditApplyList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&auditFlag=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="applyDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&recordFlow=</textarea>
	</div>
	<div id="auditApply" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=&statusId=</textarea>
	</div>
	<div id="stuList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&userFlow=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="stuCourseDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&period=</textarea>
	</div>
	<div id="stuCourseChange" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&majorId=&allFlag=&pageIndex=&pageSize=</textarea>
	</div>
</body>
</html>