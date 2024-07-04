<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>中山二院继教科过程管理系统APP学生端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css">
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
		var url = "<s:url value='/res/gzdh/student'/>/"+action+"?_method="+method;
		var formData = $("#reqParam").val();
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var dataType = $("#dataType option:selected").val();
		if(needDataType=='Y'){
			formData = formData+"&dataType="+dataType;
			formData = formData+"&"+$("#reqParamDataType").val();
		}else{
			
		}
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
	function change2(dataType) {
		var divid = $("#reqCode option:selected").val();
		$("#reqParamDataType").text($("#" + divid + " textarea[dataType='"+dataType+"']").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="${ctxPath}/res/gzdh/student/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			学生端动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="deptList" method="get" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >3.2.1获取轮转计划列表(deptList)get</option>
						<option value="funcList" method="get" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >3.2.2获取功能列表(funcList)get</option>
						<option value="standardDeptList" method="get" needDataType="N" <c:if test="${param.reqCode=='standardDeptList'}">selected</c:if> >3.2.2获取医师的标准科室列表(standardDeptList)get</option>
						<option value="addRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='addRotationDept'}">selected</c:if> >3.2.3新增轮转计划(addRotationDept)post</option>
						<option value="modRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='modRotationDept'}">selected</c:if> >3.2.4修改轮转计划(modRotationDept)post</option>
						<option value="deleteRotationDept" method="post" needDataType="N" <c:if test="${param.reqCode=='deleteRotationDept'}">selected</c:if> >3.2.5删除轮转计划(deleteRotationDept)post</option>
						<option value="inProcessInfo" method="get" needDataType="N" <c:if test="${param.reqCode=='inProcessInfo'}">selected</c:if> >3.2.6获取入科信息(inProcessInfo)get</option>
						<option value="joinExam" method="post" needDataType="N" <c:if test="${param.reqCode=='joinExam'}">selected</c:if> >3.2.7 参加出科考试(joinExam)post</option>
						<option value="gradeDeptList" method="post" needDataType="N" <c:if test="${param.reqCode=='gradeDeptList'}">selected</c:if> >3.3.0 双向评价列表(gradeDeptList)post</option>
						<option value="allAfterDept" method="post" needDataType="N" <c:if test="${param.reqCode=='allAfterDept'}">selected</c:if> >3.3.1 出科考试列表(allAfterDept)post</option>
						<option value="getNewLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getNewLectures'}">selected</c:if> >3.3.2 讲座信息-最新(getNewLectures)post</option>
						<option value="getHistoryLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getHistoryLectures'}">selected</c:if> >3.3.3讲座信息-历史(getHistoryLectures)post</option>
						<option value="lectureRegist" method="post" needDataType="N" <c:if test="${param.reqCode=='lectureRegist'}">selected</c:if> >3.3.4 讲座报名(lectureRegist)post</option>
						<option value="lectureCannelRegist" method="post" needDataType="N" <c:if test="${param.reqCode=='lectureCannelRegist'}">selected</c:if> >3.3.4 取消报名讲座(lectureCannelRegist)post</option>
						<option value="evaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='evaluate'}">selected</c:if> >3.3.5 查看讲座评价(evaluate)post</option>
						<option value="saveEvaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEvaluate'}">selected</c:if> >3.3.6 保存讲座评价(saveEvaluate)post</option>
						<option value="suggestions" method="post" needDataType="N" <c:if test="${param.reqCode=='suggestions'}">selected</c:if> >3.3.7 住培反馈(suggestions)post</option>
						<option value="delOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='delOpinions'}">selected</c:if> >3.3.8 删除意见反馈(delOpinions)post</option>
						<option value="opinionsDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='opinionsDetail'}">selected</c:if> >3.3.9意见反馈详情(opinionsDetail)post</option>
						<option value="saveOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOpinions'}">selected</c:if> >3.4.0 住培反馈保存(saveOpinions)post</option>
						<option value="getZhupeiNotices" method="post" needDataType="N" <c:if test="${param.reqCode=='getZhupeiNotices'}">selected</c:if> >3.4.1最新指南列表(getZhupeiNotices)post</option>
						<option value="zpNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='zpNoticeDetail'}">selected</c:if> >3.4.2最新指南详情(zpNoticeDetail)post</option>

						<option value="paperDataList" method="get" needDataType="N" <c:if test="${param.reqCode=='paperDataList'}">selected</c:if> >3.4.3科研记录列表(paperDataList)get</option>
						<option value="addPaperOrPart" method="post" needDataType="N" <c:if test="${param.reqCode=='addPaperOrPart'}">selected</c:if> >3.4.4科研记录文章发表新增(addPaperOrPart)post</option>
						<option value="savePaperOrPart" method="post" needDataType="N" <c:if test="${param.reqCode=='savePaperOrPart'}">selected</c:if> >3.4.5科研记录文章发表保存(savePaperOrPart)post</option>
						<option value="deleteData" method="post" needDataType="N" <c:if test="${param.reqCode=='deleteData'}">selected</c:if> >3.4.6 删除论文或科研记录(deleteData)post</option>
						<option value="userCenter" method="post" needDataType="N" <c:if test="${param.reqCode=='userCenter'}">selected</c:if> >3.4.7 学员首页信息(userCenter)post</option>
						<option value="studentSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='studentSignIn'}">selected</c:if> >3.4.8学员签到查询次数(studentSignIn)post</option>
						<option value="saveSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSignIn'}">selected</c:if> >3.4.9保存签到信息(saveSignIn)post</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gzdh/test'/>">测试工具</a>
					<a href="<s:url value='/res/gzdh/teacher/test'/>">老师端测试工具</a>
					<a href="<s:url value='/res/gzdh/kzr/test'/>">科主任端测试工具</a>
					<a href="<s:url value='/res/gzdh/spe/test'/>">专业管理员端测试工具</a>
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			deptFlow:<input name="deptFlow" type="text" value="${deptFlow}">
			<br>
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			<br>
			funcTypeId:<input name="funcTypeId" type="text" value="${funcTypeId}">
			funcFlow:<input name="funcFlow" type="text" value="${funcFlow}">
			<input type="submit" value="记住" class="search">
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" ></textarea>
	</div>
	<div id="reqParamDataTypeDiv"  style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px;display: none;">
		参数2：<textarea id="reqParamDataType" style="width: 600px; height: 200px;" name="reqParamDataType" wrap="soft"></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="inProcessInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="joinExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="standardDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchData=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&standardDeptFlow=&schDeptFlow=${schDeptFlow}&schStartDate=&schEndDate=&teacherUserFlow=&headUserFlow=</textarea>
	</div>
	<div id="modRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&standardDeptFlow=&schDeptFlow=${schDeptFlow}&schStartDate=&schEndDate=&teacherUserFlow=&headUserFlow=&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="deleteRotationDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="gradeDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="allAfterDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="getNewLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="getHistoryLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="lectureRegist" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}</textarea>
	</div>
	<div id="evaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}</textarea>
	</div>
	<div id="saveEvaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&evaContent=${evaContent}&evaScore=${evaScore}</textarea>
	</div>

	<div id="getZhupeiNotices" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&noticeTitle=${noticeTitle}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="zpNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="suggestions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="delOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="opinionsDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="saveOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">>userFlow=${userFlow}</textarea>
	</div>
	<div id="paperDataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addPaperOrPart" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recrodFlow}</textarea>
	</div>
	<div id="savePaperOrPart" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recordFlow}&paperDate=&paperTitle=&paperTypeId=&publishedJournals=&author=
			&participationDate=&participationRoom=&participationAuthor=&participationTitle=&participationRole=&participationComplete=</textarea>
	</div>
	<div id="deleteData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="userCenter" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="studentSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="saveSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&date=${date}&time=${time}&local=${local}&remark=${remark}</textarea>
	</div>
	<div id="lectureCannelRegist" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&roleId=${roleId}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>