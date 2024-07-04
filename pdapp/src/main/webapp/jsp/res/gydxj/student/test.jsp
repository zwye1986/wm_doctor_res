<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广医大研究生系统学员端APP测试程序</title>
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
		var url = "<s:url value='/res/gydxj/student'/>/"+action+"?_method="+method;
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
						<option value="pushNotice" method="post" <c:if test="${param.reqCode=='pushNotice'}">selected</c:if> >1.0.0 消息推送 pushNotice</option>
						<option value="eduUserFormEdit" method="post" <c:if test="${param.reqCode=='eduUserFormEdit'}">selected</c:if> >1.0.1 学员学籍信息 eduUserFormEdit</option>
						<option value="saveEduUser" method="post" <c:if test="${param.reqCode=='saveEduUser'}">selected</c:if> >1.0.2 学籍信息保存 saveEduUser</option>
						<option value="confirmStatus" method="post" <c:if test="${param.reqCode=='confirmStatus'}">selected</c:if> >1.0.3 学籍状态确认 confirmStatus</option>
						<option value="applyList" method="post" <c:if test="${param.reqCode=='applyList'}">selected</c:if> >2.0.0 异动申请列表 applyList</option>
						<option value="applyTypeList" method="post" <c:if test="${param.reqCode=='applyTypeList'}">selected</c:if> >2.0.1 异动申请类型 applyTypeList</option>
						<option value="editApply" method="post" <c:if test="${param.reqCode=='editApply'}">selected</c:if> >2.0.2 异动申请编辑 editApply</option>
						<option value="saveApply" method="post" <c:if test="${param.reqCode=='saveApply'}">selected</c:if> >2.0.3 异动申请保存 saveApply</option>
						<option value="submitApply" method="post" <c:if test="${param.reqCode=='submitApply'}">selected</c:if> >2.0.4 异动申请提交 submitApply</option>
						<option value="classSchedule" method="post" <c:if test="${param.reqCode=='classSchedule'}">selected</c:if> >3.0.1 课表查询 classSchedule</option>
						<option value="searchCourse" method="post" <c:if test="${param.reqCode=='searchCourse'}">selected</c:if> >3.0.2 课程详情 searchCourse</option>
						<option value="courseType" method="post" <c:if test="${param.reqCode=='courseType'}">selected</c:if> >4.0.1 网上选课类型(暂定) courseType</option>
						<option value="chooseCourse" method="post" <c:if test="${param.reqCode=='chooseCourse'}">selected</c:if> >4.0.2 选课列表(必修课/学位课/选修课) chooseCourse</option>
						<option value="stuCourse" method="post" <c:if test="${param.reqCode=='stuCourse'}">selected</c:if> >4.0.3 已选课程 stuCourse</option>
						<option value="saveCourse" method="post" <c:if test="${param.reqCode=='saveCourse'}">selected</c:if> >4.0.4 选课保存操作 saveCourse</option>
						<option value="signClass" method="post" <c:if test="${param.reqCode=='signClass'}">selected</c:if> >5.0.1 课程签到 signClass</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gydxj/test'/>">学籍测试工具</a>
					<a href="<s:url value='/res/gydxj/teacher/test'/>">教师员端测试工具</a>
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
	<div id="pushNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="eduUserFormEdit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=</textarea>
	</div>
	<div id="saveEduUser" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="confirmStatus" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&partId=</textarea>
	</div>
	<div id="applyList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&searchStr=&applyTypeId=&statusId=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="applyTypeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="editApply" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&applyTypeId=&recordFlow=</textarea>
	</div>
	<div id="saveApply" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=</textarea>
	</div>
	<div id="submitApply" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=</textarea>
	</div>
	<div id="classSchedule" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&classTime=yyyy-MM-dd&pageIndex=&pageSize=</textarea>
	</div>
	<div id="searchCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=&recordFlow=</textarea>
	</div>
	<div id="courseType" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="chooseCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">searchStr=&courseTypeId=&userFlow=&studentPeriod=&majorId=&allFlag=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="stuCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&studentPeriod=&pageIndex=&pageSize=</textarea>
	</div>
	<div id="saveCourse" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=&studentPeriod=&roleId=&courseFlowList=&delFlowList=</textarea>
	</div>
	<div id="signClass" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">courseFlow=&userFlow=</textarea>
	</div>
</body>
</html>