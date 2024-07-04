<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省厅APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/jswjw'/>/"+action;
		//"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
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
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var dataType = $("#dataType option:selected").val();
		$("#reqParam").val($("#" + divid + " textarea").first().val());
		$("#reqParamDataType").text($("#" + divid + " textarea[dataType='"+dataType+"']").first().val());
		if(needDataType=='Y'){
			$("#dataType").css("display","");
			$("#reqParamDataTypeDiv").css("display","");
		}else{
			$("#dataType").css("display","none");
			$("#reqParamDataTypeDiv").css("display","none");
		}
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
	<form name="appForm" action="${ctxPath}/res/jswjw/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >3.1	用户登录登录(login)post</option>
						<option value="deptList" method="get" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >3.2	获取标准科室列表(deptList)get</option>
						<option value="globalProgress" method="get" needDataType="N" <c:if test="${param.reqCode=='globalProgress'}">selected</c:if> >3.3	查看科室整体录入进度(globalProgress)get</option>
						<option value="subDeptList" method="get" needDataType="N" <c:if test="${param.reqCode=='subDeptList'}">selected</c:if> >3.4	获取实际科室列表(subDeptList)post</option>
						<option value="subDeptSel" method="post" needDataType="N" <c:if test="${param.reqCode=='selDeptList'}">selected</c:if> >3.4.1	选择实际轮转科室(subDeptSel)post</option>
						<option value="subDeptTeacherSel" method="post" needDataType="N" <c:if test="${param.reqCode=='selDeptList'}">selected</c:if> >3.4.2	选择实际轮转科室带教老师(subDeptTeacherSel)post</option>
						<option value="subDeptHeadSel" method="post" needDataType="N" <c:if test="${param.reqCode=='selDeptList'}">selected</c:if> >3.4.3	选择实际轮转科室科主任(subDeptHeadSel)get</option>
						<option value="addSubDept" method="post" needDataType="N" <c:if test="${param.reqCode=='addSubDept'}">selected</c:if> >3.5	新增实际轮转科室(addSubDept)post</option>
						<option value="modSubDept" method="post" needDataType="N" <c:if test="${param.reqCode=='modSubDept'}">selected</c:if> >3.6	修改实际轮转科室(modSubDept)post</option>
						<option value="deleteSubDept" method="get" needDataType="N" <c:if test="${param.reqCode=='deleteSubDept'}">selected</c:if> >3.7	删除实际轮转科室(deleteSubDept)get</option>
						<option value="categoryProgress" method="get" needDataType="Y" <c:if test="${param.reqCode=='categoryProgress'}">selected</c:if> >3.8查看科室分项录入进度(categoryProgress)get</option>
						<option value="dataList" method="get" needDataType="Y" <c:if test="${param.reqCode=='dataList'}">selected</c:if> >3.9查看科室分项数据列表(dataList)get</option>
						<option value="inputList" method="get" needDataType="Y" <c:if test="${param.reqCode=='inputList'}">selected</c:if> >3.10获取输入项(inputList)get</option>
						<option value="addData" method="post" needDataType="Y" <c:if test="${param.reqCode=='addData'}">selected</c:if> >3.11新增输入数据(addData)post</option>
						<option value="viewData" method="get" needDataType="Y" <c:if test="${param.reqCode=='viewData'}">selected</c:if> >3.12查看输入数据(viewData)get</option>
						<option value="modData" method="post" needDataType="Y" <c:if test="${param.reqCode=='modData'}">selected</c:if> >3.13修改数据(modData)post</option>
						<option value="delData" method="get" needDataType="Y" <c:if test="${param.reqCode=='delData'}">selected</c:if> >3.14删除数据(delData)get</option>
						<option value="viewImage" method="get" needDataType="N" <c:if test="${param.reqCode=='viewImage'}">selected</c:if> >3.15查看已上传图片(viewImage)get</option>
						<option value="addImage" method="post" needDataType="N" <c:if test="${param.reqCode=='addImage'}">selected</c:if> >3.16上传图片(addImage)post</option>
						<option value="addCaseImage" method="post" needDataType="N" <c:if test="${param.reqCode=='addCaseImage'}">selected</c:if> >3.16大病上传图片(addCaseImage)post</option>
						<option value="deleteImage" method="get" needDataType="N" <c:if test="${param.reqCode=='deleteImage'}">selected</c:if> >3.17删除已上传图片(deleteImage)get</option>
						<option value="userCenter" method="get" needDataType="N" <c:if test="${param.reqCode=='userCenter'}">selected</c:if> >3.18个人中心(userCenter)get</option>
						<option value="viewGrade" method="get" needDataType="N" <c:if test="${param.reqCode=='viewGrade'}">selected</c:if> >3.19查看评价(viewGrade)get</option>
						<option value="saveGrade" method="post" needDataType="N" <c:if test="${param.reqCode=='saveGrade'}">selected</c:if> >3.20保存评价(saveGrade)post</option>
						<option value="getNewLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getNewLectures'}">selected</c:if> >3.21最新讲座(getNewLectures)post</option>
						<option value="getHistoryLectures" method="post" needDataType="N" <c:if test="${param.reqCode=='getHistoryLectures'}">selected</c:if> >3.22历史讲座(getHistoryLectures)post</option>
						<option value="lectureRegist" method="post" needDataType="N" <c:if test="${param.reqCode=='lectureRegist'}">selected</c:if> >3.23讲座报名(lectureRegist)post</option>
						<option value="lectureCannelRegist" method="post" needDataType="N" <c:if test="${param.reqCode=='lectureCannelRegist'}">selected</c:if> >3.23取消讨论报名(lectureCannelRegist)post</option>
						<option value="evaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='evaluate'}">selected</c:if> >3.24查看评价(evaluate)post</option>
						<option value="saveEvaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEvaluate'}">selected</c:if> >3.24保存评价(saveEvaluate)post</option>
						<option value="suggestions" method="post" needDataType="N" <c:if test="${param.reqCode=='suggestions'}">selected</c:if> >3.25查询意见反馈(suggestions)post</option>
						<option value="delOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='delOpinions'}">selected</c:if> >3.26删除意见反馈(delOpinions)post</option>
						<option value="opinionsDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='opinionsDetail'}">selected</c:if> >3.27意见反馈详情(opinionsDetail)post</option>
						<option value="saveOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOpinions'}">selected</c:if> >3.28保存意见反馈(saveOpinions)post</option>
						<option value="getZhupeiNotices" method="post" needDataType="N" <c:if test="${param.reqCode=='getZhupeiNotices'}">selected</c:if> >3.29住培指南(getZhupeiNotices)post</option>
						<option value="zpNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='zpNoticeDetail'}">selected</c:if> >3.30住培指南详情(zpNoticeDetail)post</option>
						<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >3.31讲座信息扫码(qrCode)post</option>
						<option value="studentSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='studentSignIn'}">selected</c:if> >3.32学员签到查询次数(studentSignIn)post</option>
						<option value="saveSignIn" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSignIn'}">selected</c:if> >3.33保存签到信息(saveSignIn)post</option>
						<option value="osca" method="post" needDataType="N" <c:if test="${param.reqCode=='osca'}">selected</c:if> >3.34 技能考核(osca)post</option>
						<option value="lineUp" method="post" needDataType="N" <c:if test="${param.reqCode=='lineUp'}">selected</c:if> >3.35 排队或取消排队(lineUp)post</option>
						<option value="stationRooms" method="post" needDataType="N" <c:if test="${param.reqCode=='stationRooms'}">selected</c:if> >3.36 站点所有房间(stationRooms)post</option>
						<option value="addPaperOrPart" method="post" needDataType="N" <c:if test="${param.reqCode=='addPaperOrPart'}">selected</c:if> >4.0 添加论文或科研记录(addPaperOrPart)post</option>
						<option value="savePaperOrPart" method="post" needDataType="N" <c:if test="${param.reqCode=='savePaperOrPart'}">selected</c:if> >4.1 保存论文或科研记录(savePaperOrPart)post</option>
						<option value="paperDataList" method="get" needDataType="N" <c:if test="${param.reqCode=='paperDataList'}">selected</c:if> >4.2 论文或科研记录列表(paperDataList)get</option>
						<option value="deleteData" method="post" needDataType="N" <c:if test="${param.reqCode=='deleteData'}">selected</c:if> >4.3 删除论文或科研记录(deleteData)post</option>
						<option value="afterDeptList" method="post" needDataType="N" <c:if test="${param.reqCode=='afterDeptList'}">selected</c:if> >4.4获取出科考试科室列表(afterDeptList)post</option>
						<option value="joinExam" method="post" needDataType="N" <c:if test="${param.reqCode=='joinExam'}">selected</c:if> >4.5 开始考试(joinExam)post</option>
						<option value="toGraduationTest" method="post" needDataType="N" <c:if test="${param.reqCode=='toGraduationTest'}">selected</c:if> >4.5 开始模拟考试(toGraduationTest)post</option>
						<option value="allAfterDept" method="post" needDataType="N" <c:if test="${param.reqCode=='allAfterDept'}">selected</c:if> >4.6获取所有出科考试科室列表(allAfterDept)post</option>
						<option value="viewError" method="get" needDataType="N" <c:if test="${param.reqCode=='viewError'}">selected</c:if> >4.6.1查看错题(viewError)get</option>
						<option value="viewErrorDetail" method="get" needDataType="N" <c:if test="${param.reqCode=='viewErrorDetail'}">selected</c:if> >4.6.2查看错题详情(viewErrorDetail)get</option>
						<option value="gradeDeptList" method="post" needDataType="N" <c:if test="${param.reqCode=='gradeDeptList'}">selected</c:if> >4.7 双向评价(gradeDeptList)post</option>
						<option value="inProcessInfoList" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessInfoList'}">selected</c:if> >4.8 入科教育科室列表(inProcessInfoList)post</option>
						<option value="inProcessInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessInfo'}">selected</c:if> >4.9 科室入科教育详情(inProcessInfo)post</option>
						<option value="resErrorNotices" method="post" needDataType="N" <c:if test="${param.reqCode=='resErrorNotices'}">selected</c:if> >5.0.0 异常出科通知(resErrorNotices)post</option>
						<option value="saveReadNotice" method="post" needDataType="N" <c:if test="${param.reqCode=='saveReadNotice'}">selected</c:if> >5.0.1 更新异常出科通知状态(saveReadNotice)post</option>
						<option value="scoreList" method="post" needDataType="N" <c:if test="${param.reqCode=='scoreList'}">selected</c:if> >5.0.2 学员查询成绩(scoreList)post</option>
						<option value="showCertificate" method="get" needDataType="N" <c:if test="${param.reqCode=='showCertificate'}">selected</c:if> >5.0.3 学员查询结业证书(showCertificate)get</option>
						<option value="findActivityList" method="post" needDataType="N" <c:if test="${param.reqCode=='findActivityList'}">selected</c:if> >5.0.4 教学活动列表或评价活动列表(findActivityList)post</option>
						<option value="joinActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='joinActivity'}">selected</c:if> >5.0.5 报名参加活动(joinActivity)post</option>
						<option value="cannelRegiest" method="post" needDataType="N" <c:if test="${param.reqCode=='cannelRegiest'}">selected</c:if> >5.0.5 取消报名活动(cannelRegiest)post</option>
						<option value="showDocEval" method="post" needDataType="N" <c:if test="${param.reqCode=='showDocEval'}">selected</c:if> >5.0.6 查看活动评价(showDocEval)post</option>
						<option value="saveEvalInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEvalInfo'}">selected</c:if> >5.0.7 保存活动评价(saveEvalInfo)post</option>
						<option value="theoreticalExam" method="post" needDataType="N" <c:if test="${param.reqCode=='theoreticalExam'}">selected</c:if> >6.0.1 年度考试列表(theoreticalExam)post</option>
						<option value="toYearTest" method="post" needDataType="N" <c:if test="${param.reqCode=='toYearTest'}">selected</c:if> >6.0.2 参加年度考试(toYearTest)post</option>
			<option value="changePass" method="post" needDataType="N" <c:if test="${param.reqCode=='changePass'}">selected</c:if> >1.5 修改密码(changePass)post</option>
					</select>
					<select id="dataType" name="dataType" style="display: none;" onchange="change2(this.value);">
						<option value="mr" <c:if test="${param.dataType=='mr'}">selected</c:if> >mr</option>
						<option value="disease" <c:if test="${param.dataType=='disease'}">selected</c:if> >disease</option>
						<option value="skill" <c:if test="${param.dataType=='skill'}">selected</c:if> >skill</option>
						<option value="operation" <c:if test="${param.dataType=='operation'}">selected</c:if> >operation</option>
						<option value="activity" <c:if test="${param.dataType=='activity'}">selected</c:if> >activity</option>
						<option value="summary" <c:if test="${param.dataType=='summary'}">selected</c:if> >summary</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/jswjw/teacher/test'/>">老师端测试工具</a>
					<a href="<s:url value='/res/jswjw/kzr/test'/>">科主任端测试工具</a>
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			deptFlow:<input name="deptFlow" type="text" value="${deptFlow}">
			<br>
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			<input type="submit" value="记住" class="search">
		</div>
	</div>
	</form>
	<div style="width: 60%; float: left">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
		参数&nbsp;：	<textarea id="reqParam" style="width: 600px; height: 200px;" name="reqParam" wrap="soft"></textarea>
	</div>
	<div id="reqParamDataTypeDiv"  style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px;display: none;">
		参数2：<textarea id="reqParamDataType" style="width: 600px; height: 200px;" name="reqParamDataType" wrap="soft"></textarea>
	</div>
	<div style="margin-left: 100px">
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=test&userPasswd=123456</textarea>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="enterDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&teacherFlow=&deptHeadFlow=</textarea>
	</div>
	<div id="globalProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>	
	<div id="subDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="afterDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="allAfterDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="inProcessInfoList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="inProcessInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}</textarea>
	</div>
	<div id="gradeDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="joinExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&resultFlow=${resultFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="toGraduationTest" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
    <div id="viewError" class="reqParam" style="display: none">
        <textarea style="width: 350px; height: 400px" readonly="readonly">processFlow=${processFlow}</textarea>
    </div>
    <div id="viewErrorDetail" class="reqParam" style="display: none">
        <textarea style="width: 350px; height: 400px" readonly="readonly">resultsId=${resultsId}&processFlow=${processFlow}</textarea>
    </div>
	<div id="subDeptSel" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchStr=${searchStr}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="subDeptTeacherSel" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchStr=${searchStr}&sysDeptFlow=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="subDeptHeadSel" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchStr=${searchStr}&sysDeptFlow=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="addSubDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&sysDeptFlow=&headFlow=&teacherFlow=&subDeptName=&startDate=&endDate=</textarea>
	</div>
	<div id="modSubDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&subDeptFlow=&sysDeptFlow=&subDeptName=&headFlow=&teacherFlow=&startDate=&endDate=</textarea>
	</div>
	<div id="deleteSubDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&subDeptFlow=</textarea>
	</div>
	<div id="categoryProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
	</div>	
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
	</div>	
	<div id="inputList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&cataFlow=${cataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="addData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">&mr_pName=&mr_no=&disease_pName=&mr_diagType=&cataFlow=${cataFlow}&json=</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">disease_pDate=&disease_pName=&disease_mrNo=&disease_diagName=&disease_diagType=&disease_isCharge=&disease_isRescue=&disease_treatStep=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">skill_operDate=&skill_pName=&skill_mrNo=&skill_operName=&skill_result=&skill_memo=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">operation_operDate=&operation_pName=&operation_mrNo=&operation_operName=&operation_operRole=&operation_memo=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">activity_date=&activity_content=&activity_way=&activity_period=&activity_speaker=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">summary_content=&cataFlow=${cataFlow}</textarea>
		
	</div>
	<div id="viewData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="modData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">&mr_pName=&disease_pName=&mr_no=&mr_diagType=</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">disease_pDate=&disease_pName=&disease_mrNo=&disease_diagName=&disease_diagType=&disease_isCharge=&disease_isRescue=&disease_treatStep=</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">skill_operDate=&skill_pName=&skill_mrNo=&skill_operName=&skill_result=&skill_memo=</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">operation_operDate=&operation_pName=&operation_mrNo=&operation_operName=&operation_operRole=&operation_memo=</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">activity_date=&activity_content=&activity_way=&activity_period=&activity_speaker=</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">summary_content=</textarea>
	</div>
	<div id="delData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="viewImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="addImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&fileName=</textarea>
	</div>
	<div id="addCaseImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileName=</textarea>
	</div>
	<div id="deleteImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&imageFlow=</textarea>
	</div>
	<div id="userCenter" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="viewGrade" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow }&subDeptFlow=&assessType=</textarea>
	</div>
	<div id="saveGrade" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow }&subDeptFlow=&assessType=</textarea>
	</div>
	<div id="getNewLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getHistoryLectures" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="lectureRegist" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="lectureCannelRegist" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="evaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="saveEvaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&lectureFlow=${lectureFlow}&evaContent=${evaContent}&evaScore=${evaScore}&roleId=${roleId}</textarea>
	</div>
	<div id="suggestions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10
		</textarea>
	</div>
	<div id="delOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="opinionsDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}</textarea>
	</div>
	<div id="saveOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}&opinionUserContent=${opinionUserContent}</textarea>
	</div>
	<div id="getZhupeiNotices" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">noticeTitle=${noticeTitle}&userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="zpNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">recordFlow=${recordFlow}&userFlow=${userFlow}</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&roleId=${roleId}&codeInfo=${codeInfos}</textarea>
	</div>
	<div id="studentSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="saveSignIn" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&date=${date}&time=${time}&local=${local}&remark=${remark}&workingDaySign=${workingDaySign}</textarea>
	</div>
	<div id="osca" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="lineUp" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&roomRecordFlow=${roomRecordFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&waitingFlag=${waitingFlag}</textarea>
	</div>
	<div id="stationRooms" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}</textarea>
	</div>
	<div id="addPaperOrPart" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="paperDataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="deleteData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="savePaperOrPart" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&type=${type}&recordFlow=${recordFlow}&paperDate=&paperTitle=&paperTypeId=&publishedJournals=&author=
			&participationDate=&participationRoom=&participationAuthor=&participationTitle=&participationRole=&participationComplete=</textarea>
	</div>
	<div id="resErrorNotices" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="saveReadNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="scoreList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="showCertificate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="findActivityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&typeId=${typeId}&deptFlow=&isCurrent=Y&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="joinActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=${activityFlow}</textarea>
	</div>
	<div id="cannelRegiest" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=${activityFlow}</textarea>
	</div>
	<div id="showDocEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&resultFlow=${resultFlow}</textarea>
	</div>
	<div id="saveEvalInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&resultFlow=${resultFlow}&evals=${evals}</textarea>
	</div>
    <div id="theoreticalExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
    <div id="toYearTest" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&arrangeFlow=${arrangeFlow}</textarea>
	</div>
	<div id="changePass" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&userPasswd=test&newUserPasswd=123456&reNewUserPasswd=123456</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>