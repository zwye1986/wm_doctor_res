<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>临床技能管理APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/lcjn'/>/"+action;
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
	function test2() {
		var action = $("#reqCode option:selected").val();
		if(action==""){
			jboxTip("请选择测试的交易");
			return;
		}
		var method = $("#reqCode option:selected").attr("method");
		var url = "<s:url value='/res/lcjn'/>/saveEvaluate";
		//"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		var jsonData = {
			"course": {
				"courseEvaList": [
					{
						"dictId": "1",
						"dictName": "课程难度",
						"evalScore": "3"
					},
					{
						"dictId": "2",
						"dictName": "课堂气氛",
						"evalScore": "2"
					}
				],
				"courseFlow": "201702081521456789",
				"evalContent": "开发课程2"
			},
			"teas": [
				{
					"evalContent": "开发课程2",
					"teacherEvaList": [
						{
							"dictId": "1",
							"dictName": "授课方式",
							"evalScore": "2"
						},
						{
							"dictId": "2",
							"dictName": "工作态度",
							"evalScore": "2"
						}
					],
					"userFlow": "c4d093b6454b4854b8a02123f4566a8c",
					"userName": "华永泉"
				},
				{
					"evalContent": "开发课程2",
					"teacherEvaList": [
						{
							"dictId": "1",
							"dictName": "授课方式",
							"evalScore": "2"
						},
						{
							"dictId": "2",
							"dictName": "工作态度",
							"evalScore": "2"
						}
					],
					"userFlow": "5e936f19ede1453c8913c3928d3086ab",
					"userName": "韩兰叶"
				},
				{
					"evalContent": "开发课程2",
					"teacherEvaList": [
						{
							"dictId": "1",
							"dictName": "授课方式",
							"evalScore": "2"
						},
						{
							"dictId": "2",
							"dictName": "工作态度",
							"evalScore": "2"
						}
					],
					"userFlow": "4ecca8502942463091d5fc8771214557",
					"userName": "王龙"
				},
				{
					"evalContent": "开发课程2",
					"teacherEvaList": [
						{
							"dictId": "1",
							"dictName": "授课方式",
							"evalScore": "2"
						},
						{
							"dictId": "2",
							"dictName": "工作态度",
							"evalScore": "2"
						}
					],
					"userFlow": "12249ea4d23348678109a27779391ef7",
					"userName": "研发经理"
				}
			],
			"userFlow": "a76fcf2c78c94c93bb28468e52b6b74f"
		};
		var formData="jsonData="+JSON.stringify(jsonData);
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
	<form name="appForm" action="${ctxPath}/res/lcjn/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >1.0 查询最新版本号(version)get</option>
						<option value="register" method="post" needDataType="N" <c:if test="${param.reqCode=='register'}">selected</c:if> >1.1 用户注册(register)post</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >1.2 用户登录登录(login)post</option>
						<option value="ownerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >1.3 个人资料(ownerInfo)post</option>
						<option value="saveOwnerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOwnerInfo'}">selected</c:if> >1.4 保存个人资料(saveOwnerInfo)post</option>
						<option value="changePass" method="post" needDataType="N" <c:if test="${param.reqCode=='changePass'}">selected</c:if> >1.5 修改密码(changePass)post</option>
						<option value="getNoticeCount" method="get" needDataType="N" <c:if test="${param.reqCode=='getNoticeCount'}">selected</c:if> >1.6 通知公告数量(getNoticeCount)post</option>
						<option value="notice" method="post" needDataType="N" <c:if test="${param.reqCode=='notice'}">selected</c:if> >1.7 通知公告列表(notice)post</option>
						<option value="noticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='noticeDetail'}">selected</c:if> >1.8 通知公告详情(noticeDetail)post</option>
						<option value="courseInfoList" method="post" needDataType="N" <c:if test="${param.reqCode=='courseInfoList'}">selected</c:if> >1.9 培训预约列表(courseInfoList)post</option>
						<option value="courseInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='courseInfo'}">selected</c:if> >2.0 培训预约详情(courseInfo)post</option>
						<option value="appoint" method="post" needDataType="N" <c:if test="${param.reqCode=='appoint'}">selected</c:if> >2.1 培训预约信息预约(appoint)post</option>
						<option value="cancellations" method="post" needDataType="N" <c:if test="${param.reqCode=='cancellations'}">selected</c:if> >2.2 培训预约信息取消预约(cancellations)post</option>
						<option value="myCourseList" method="post" needDataType="N" <c:if test="${param.reqCode=='myCourseList'}">selected</c:if> >2.3 我的预约列表(myCourseList)post</option>
						<option value="evaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='evaluate'}">selected</c:if> >2.4 评价(evaluate)post</option>
						<option value="showEvaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='showEvaluate'}">selected</c:if> >2.5 查看评价(showEvaluate)post</option>
						<option value="saveEvaluate" method="post" needDataType="N" <c:if test="${param.reqCode=='saveEvaluate'}">selected</c:if> >2.6 保存评价(saveEvaluate)post</option>
						<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >2.7 扫码签到(qrCode)post</option>
						<option value="updateOwnerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='updateOwnerInfo'}">selected</c:if> >2.8 更新个人资料(updateOwnerInfo)post</option>

		</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<input type="button" value="保存评价测试" class="search" onclick="test2();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			courseFlow:<input name="courseFlow" type="text" value="${courseFlow}">
			<br>
			recordFlow:<input name="recordFlow" type="text" value="${recordFlow}">
			clinicalFlow:<input name="clinicalFlow" type="text" value="${clinicalFlow}">
			<br>
			roomRecordFlow:<input name="roomRecordFlow" type="text" value="${roomRecordFlow}">
			scoreFlow:<input name="scoreFlow" type="text" value="${scoreFlow}">
			<br>
			fromFlow:<input name="fromFlow" type="text" value="${fromFlow}">
			codeInfo:<input name="codeInfo" type="text" value="${codeInfo}">
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
	<div id="register" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=test&userPasswd=123456&reUserPasswd=123456</textarea>
	</div>
	<div id="changePass" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&userPasswd=test&newUserPasswd=123456&reNewUserPasswd=123456</textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=test&userPasswd=123456</textarea>
	</div>
	<div id="ownerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="saveOwnerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&userName=${userName}&sexId=${sexId}&idNo=${idNo}&userPhone=${userPhone}&userEmail=${userEmail}&orgName=${orgName}&deptName=${deptName}&titleId=${titleId}&lcjnSpeId=${lcjnSpeId}</textarea>
	</div>
	<div id="updateOwnerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&flag=${flag}&userName=${userName}&sexId=${sexId}&idNo=${idNo}&userPhone=${userPhone}&userEmail=${userEmail}&orgName=${orgName}&deptName=${deptName}&titleId=${titleId}&lcjnSpeId=${lcjnSpeId}</textarea>
	</div>
	<div id="getNoticeCount" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="notice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="noticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&infoFlow=${infoFlow}</textarea>
	</div>
	<div id="courseInfoList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseName=${courseName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="courseInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseFlow=${courseFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="appoint" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseFlow=${courseFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="cancellations" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseFlow=${courseFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="myCourseList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseName=${courseName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="evaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseFlow=${courseFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="showEvaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&courseFlow=${courseFlow}</textarea>
	</div>
	<div id="saveEvaluate" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">jsonData=</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&codeInfo=${codeInfo}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>