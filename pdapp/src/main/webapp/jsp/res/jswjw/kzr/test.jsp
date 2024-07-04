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
		var url = "<s:url value='/res/jswjw/kzr'/>/"+action;
		//"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		var formData = $("#reqParam").val();
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		var typeId = $("#typeId option:selected").val();
		if(needDataType=='Y'){
			formData = formData+"&typeId="+typeId;
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
		var typeId = $("#typeId option:selected").val();
		$("#reqParam").val($("#" + divid + " textarea").first().val());
		$("#reqParamDataType").text($("#" + divid + " textarea[typeId='"+typeId+"']").first().val());
		if(needDataType=='Y'){
			$("#typeId").css("display","");
			$("#reqParamDataTypeDiv").css("display","");
		}else{
			$("#typeId").css("display","none");
			$("#reqParamDataTypeDiv").css("display","none");
		}
	}
	function change2(typeId) {
		var divid = $("#reqCode option:selected").val();
		$("#reqParamDataType").text($("#" + divid + " textarea[typeId='"+typeId+"']").first().val());
	}
	$(document).ready(function(){
		<c:if test="${not empty param.reqCode}">
		change('${param.reqCode}');
		</c:if>
	});
</script>
</head>
<body>
	<form name="appForm" action="/pdapp/res/jswjw/kzr/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="index" method="post" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >1.0.0 首页(index)post</option>
						<option value="studentList" method="post" needDataType="Y" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >1.0.1 学员列表(studentList)post</option>
						<option value="deptList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >1.0.2 科室列表(deptList)post</option>
						<option value="inProcessInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessInfo'}">selected</c:if> >1.0.3 单个科室入科教育文档(inProcessInfo)post</option>
						<option value="funcList" method="post" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >1.0.4 学员数据功能列表(funcList)post</option>
						<option value="resRecList" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecList'}">selected</c:if> >1.0.5 五种数据列表(resRecList)get</option>
						<option value="resRecDeatil" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecDeatil'}">selected</c:if> >1.0.6 数据详情(resRecDeatil)get</option>
						<option value="mini_cex" method="get" needDataType="N" <c:if test="${param.reqCode=='mini_cex'}">selected</c:if> >1.0.8迷你临床演练评估量化表(mini_cex)get</option>
						<option value="DOPS" method="get" needDataType="N" <c:if test="${param.reqCode=='DOPS'}">selected</c:if> >1.0.9 临床操作技能评估量化表(DOPS)get</option>
						<option value="viewImage" method="get" needDataType="N" <c:if test="${param.reqCode=='viewImage'}">selected</c:if> >1.0.10 出科考核表附件(viewImage)get</option>
						<option value="teacherGradeList" method="post" needDataType="N" <c:if test="${param.reqCode=='teacherGradeList'}">selected</c:if> >1.0.11 带教评分列表(teacherGradeList)post</option>
						<option value="deptGrade" method="post" needDataType="N" <c:if test="${param.reqCode=='deptGrade'}">selected</c:if> >1.0.12 科室评价(deptGrade)post</option>
						<option value="deptGradeList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptGradeList'}">selected</c:if> >1.0.13 科室评价查询详情学员列表(deptGradeList)post</option>
						<option value="gradeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='gradeDetail'}">selected</c:if> >1.0.14 带教或科室评分详情(gradeDetail)post</option>
						<option value="evalStudentList" method="post" needDataType="N" <c:if test="${param.reqCode=='evalStudentList'}">selected</c:if> >1.0.15 学员评分列表(evalStudentList)post</option>
						<option value="monthEvalList" method="get" needDataType="N" <c:if test="${param.reqCode=='monthEvalList'}">selected</c:if> >1.0.16 学员评分月度考评列表(monthEvalList)get</option>
						<option value="showMonthEval" method="post" needDataType="N" <c:if test="${param.reqCode=='showMonthEval'}">selected</c:if> >1.0.17 查看月度考评(showMonthEval)post</option>
						<option value="ownerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >1.0.18 用户个人信息(ownerInfo)post</option>
						<option value="saveOwnerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOwnerInfo'}">selected</c:if> >1.0.19 保存用户个人信息(saveOwnerInfo)post</option>
						<option value="deptUsers" method="post" needDataType="N" <c:if test="${param.reqCode=='deptUsers'}">selected</c:if> >1.0.19 科室人员列表(deptUsers)post</option>
						<option value="deptTeacherUsers" method="post" needDataType="N" <c:if test="${param.reqCode=='deptTeacherUsers'}">selected</c:if> >1.0.19 科室带教学员列表(deptTeacherUsers)post</option>
						<option value="deptTeacherDocList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptTeacherDocList'}">selected</c:if> >1.0.19 科室带教学员轮转列表(deptTeacherDocList)post</option>

			<option value="resManage" method="post" needDataType="N" <c:if test="${param.reqCode=='resManage'}">selected</c:if> >3.3.13 培训管理(resManage)post</option>
			<option value="userList" method="post" needDataType="Y" <c:if test="${param.reqCode=='userList'}">selected</c:if> >3.3.13 培训管理-学员列表(userList)post</option>
			<option value="getSysNotice" method="post" needDataType="N" <c:if test="${param.reqCode=='getSysNotice'}">selected</c:if> >3.3.13 科教通知列表(getSysNotice)post</option>
			<option value="sysNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='sysNoticeDetail'}">selected</c:if> >3.3.14 科教通知详情(sysNoticeDetail)post</option>
			<option value="activityList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityList'}">selected</c:if> >3.3.15 教学活动(activityList)post</option>
			<option value="delActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='delActivity'}">selected</c:if> >3.3.15 删除教学活动(delActivity)post</option>
			<option value="showActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='showActivity'}">selected</c:if> >3.3.15 查看或编辑教学活动(showActivity)post</option>
			<option value="addActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='addActivity'}">selected</c:if> >3.3.15 新增教学活动(addActivity)post</option>
			<option value="saveActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='saveActivity'}">selected</c:if> >3.3.15 保存教学活动(saveActivity)post</option>
			<option value="viewActivityImage" method="post" needDataType="N" <c:if test="${param.reqCode=='viewActivityImage'}">selected</c:if> >3.3.15 查看教学活动现场图片(viewActivityImage)post</option>
			<option value="addActivityImage" method="post" needDataType="N" <c:if test="${param.reqCode=='addActivityImage'}">selected</c:if> >3.3.15 上传教学活动现场图片(addActivityImage)post</option>
			<option value="deleteActivityImage" method="get" needDataType="N" <c:if test="${param.reqCode=='deleteActivityImage'}">selected</c:if> >3.3.15 删除教学活动现场图片(deleteActivityImage)get</option>
			<option value="activityEval" method="post" needDataType="N" <c:if test="${param.reqCode=='activityEval'}">selected</c:if> >3.3.15 教学活动评价均分(activityEval)post</option>
			<option value="activityEvalList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityEvalList'}">selected</c:if> >3.3.15 教学活动评价详情列表(activityEvalList)post</option>
			<option value="effectiveResult" method="post" needDataType="N" <c:if test="${param.reqCode=='effectiveResult'}">selected</c:if> >3.3.15 教学活动评价详情列表-修改状态(effectiveResult)post</option>
			<option value="activityStuList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityStuList'}">selected</c:if> >3.3.15 教学活动报名与扫码学员列表(activityStuList)post</option>
			<option value="attendList" method="post" needDataType="N" <c:if test="${param.reqCode=='attendList'}">selected</c:if> >3.3.15 日常考勤(attendList)post</option>
			<option value="afterUserList" method="post" needDataType="N" <c:if test="${param.reqCode=='afterUserList'}">selected</c:if> >3.3.18 待出科审核学员列表(afterUserList)post</option>
			<option value="afterAuditList" method="post" needDataType="N" <c:if test="${param.reqCode=='afterAuditList'}">selected</c:if> >3.3.18 学员待出科审核列表(afterAuditList)post</option>
			<option value="afterEvaluation" method="get" needDataType="N" <c:if test="${param.reqCode=='afterEvaluation'}">selected</c:if> >3.15出科考核表(afterEvaluation)get</option>
			<option value="saveRegistryForm" method="get" needDataType="N" <c:if test="${param.reqCode=='saveRegistryForm'}">selected</c:if> >3.16保存出科考核表(saveRegistryForm)get</option>
			</select>
					<select id="typeId" name="typeId" style="display:none;">
						<option></option>
						<option value="allDoc">1、培训学员总数学员列表（allDoc）</option>
						<option value="monthCurrent">2、本月轮转学员（monthCurrent）</option>
						<option value="monthSch">3、本月出科学员（monthSch）</option>
						<option value="waitSch">4、计划入科学员（waitSch）</option>
						<option value="errorSch">5、出科异常学员（errorSch）</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/jswjw/test'/>">学生端测试工具</a>
					<a href="<s:url value='/res/jswjw/Head/test'/>">带教端测试工具</a>
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
	<div id="index" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&deptFlow=&doctorFlow=&seachStr=${seachStr}&trainingTypeId=&trainingSpeId=&doctorTypeId=&sessionNumber=&typeId=${typeId}&yearMonth=${yearMonth}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="evalStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&seachStr=${seachStr}&trainingTypeId=&trainingSpeId=&doctorTypeId=&sessionNumber=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="inProcessInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&processFlow=${processFlow}&doctorFlow=${doctorFlow}</textarea>
	</div>

	<div id="mini_cex" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&recFlow=${recFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="DOPS" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&recFlow=${recFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="viewImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recordFlow=${recordFlow}&doctorFlow=${doctorFlow}</textarea>
	</div>
	<div id="resRecList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&doctorFlow=${doctorFlow}&processFlow=${processFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="resRecDeatil" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&recFlow=${recFlow}&cataFlow=${cataFlow}&deptFlow=${deptFlow }</textarea>
	</div>
	<div id="teacherGradeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&teaName=${teaName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="deptGrade" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="deptGradeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="gradeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recFlow=${recFlow}&gradeType=${gradeType}</textarea>
	</div>
	<div id="monthEvalList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="showMonthEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&recordFlow=${recordFlow}&processFlow=${processFlow}&evalMonth=${evalMonth}</textarea>
	</div>
	<div id="ownerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="deptUsers" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&seachStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="deptTeacherUsers" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&teacherFlow=${teacherFlow}&deptFlow=${deptFlow}&seachStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="deptTeacherDocList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&teacherFlow=${teacherFlow}&doctorFlow=${doctorFlow}&seachStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="saveOwnerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sexId=${sexId}&userName=${userName}&idNo=${idNo}&userPhone=${userPhone}&userEmail=${userEmail}&certificateId=${certificateId}</textarea>
	</div>

	<div id="resManage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&yearMonth=2018-05</textarea>
	</div>
	<div id="userList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&deptFlow=&seachStr=${seachStr}&trainingTypeId=&trainingSpeId=&doctorTypeId=&sessionNumber=&typeId=${typeId}&yearMonth=${yearMonth}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="afterUserList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head或者是Seretary&seachStr=&trainingTypeId=&trainingSpeId=&doctorTypeId=&sessionNumber=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="afterAuditList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head或者是Seretary&doctorFlow</textarea>
	</div>
	<div id="afterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head或者是Seretary&docFlow=${docFlow}&recFlow=${recFlow}&processFlow=${processFlow}&deptFlow=${deptFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="saveRegistryForm" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleFlag=Head或者是Seretary&formFileName=${formFileName}&operUserFlow=${operUserFlow}&recFlow=${recFlow}&processFlow=${processFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="attendList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&seachStr=${seachStr}&yearMonth=${yearMonth}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getSysNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="sysNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&infoFlow=${infoFlow}</textarea>
	</div>
	<div id="activityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&isOwner=N&yearMonth=2018-05&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="showActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=</textarea>
	</div>
	<div id="addActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head</textarea>
	</div>
	<div id="delActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=</textarea>
	</div>
	<div id="viewActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=</textarea>
	</div>
	<div id="activityEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=</textarea>
	</div>
	<div id="activityEvalList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="activityStuList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=&typeId=为Y时表示报名，为N时表示签到&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="effectiveResult" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=&resultFlow=&isEffective=</textarea>
	</div>
	<div id="addActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Head&activityFlow=&fileName=&其他参数和学员端addImage接口一样</textarea>
	</div>
	<div id="deleteActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=${activityFlow}&imageFlow=</textarea>
	</div>
	<div id="saveActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=&speakerFlow=&deptFlow=&activityTypeId=&activityName=&activityAddress=&speakerPhone=&startTime=&endTime=&activityRemark=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>