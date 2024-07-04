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
		var url = "<s:url value='/res/jswjw/teacher'/>/"+action;
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
	<form name="appForm" action="/pdapp/res/jswjw/teacher/remember" method="get">
	<div style="width: 80%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >3.1	教师登录(login)post</option>
						<option value="doctorList" method="get" needDataType="N" <c:if test="${param.reqCode=='doctorList'}">selected</c:if> >3.2	获取学员列表(doctorList)get</option>
						<option value="funcList" method="get" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >3.3	获取功能列表(funcList)get</option>
						<option value="dataList" method="get" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >3.4	数据列表(dataList)get</option>
						<option value="auditData" method="get" needDataType="N" <c:if test="${param.reqCode=='auditData'}">selected</c:if> >3.5	数据审核(auditData)get</option>
						<option value="auditAllData" method="get" needDataType="N" <c:if test="${param.reqCode=='auditAllData'}">selected</c:if> >3.6	数据一键审核(auditAllData)get</option>
						<option value="viewSummary" method="get" needDataType="N" <c:if test="${param.reqCode=='viewSummary'}">selected</c:if> >3.7	查看出科考核表(viewSummary)get</option>
						<option value="saveSummary" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSummary'}">selected</c:if> >3.8	保存出科考核表(saveSummary)post</option>
						<option value="index" method="post" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >3.9待办事项首页(index)post</option>
						<option value="studentList" method="get" needDataType="Y" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >3.10学员列表(studentList)get</option>
						<option value="batchAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='batchAudit'}">selected</c:if> >3.11数据一键审核(batchAudit)post</option>
						<option value="oneAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='oneAudit'}">selected</c:if> >3.12单条审核(oneAudit)post</option>
						<option value="resRecList" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecList'}">selected</c:if> >3.13五种数据列表(resRecList)get</option>
						<option value="resRecDeatil" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecDeatil'}">selected</c:if> >3.14数据详情(resRecDeatil)get</option>
						<option value="afterEvaluation" method="get" needDataType="N" <c:if test="${param.reqCode=='afterEvaluation'}">selected</c:if> >3.15出科考核表(afterEvaluation)get</option>
						<option value="saveRegistryForm" method="get" needDataType="N" <c:if test="${param.reqCode=='saveRegistryForm'}">selected</c:if> >3.16保存三个表(saveRegistryForm)get</option>
						<option value="mini_cex" method="get" needDataType="N" <c:if test="${param.reqCode=='mini_cex'}">selected</c:if> >3.17迷你临床演练评估量化表(mini_cex)get</option>
						<option value="DOPS" method="get" needDataType="N" <c:if test="${param.reqCode=='DOPS'}">selected</c:if> >3.18临床操作技能评估量化表(DOPS)get</option>
						<option value="aboutUs" method="get" needDataType="N" <c:if test="${param.reqCode=='aboutUs'}">selected</c:if> >3.19获取关于我们图片(aboutUs)get</option>
						<option value="fiveDataNoAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='fiveDataNoAudit'}">selected</c:if> >3.20五种数据待审核数量(fiveDataNoAudit)get</option>
						<option value="monthEvalList" method="get" needDataType="N" <c:if test="${param.reqCode=='monthEvalList'}">selected</c:if> >3.21 月度考评(monthEvalList)get</option>
						<option value="showMonthEval" method="post" needDataType="N" <c:if test="${param.reqCode=='showMonthEval'}">selected</c:if> >3.22 查看月度考评(showMonthEval)post</option>
						<option value="saveMonthEval" method="post" needDataType="N" <c:if test="${param.reqCode=='saveMonthEval'}">selected</c:if> >3.23 保存月度考评(saveMonthEval)post</option>
						<option value="skillNoAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='skillNoAudit'}">selected</c:if> >3.20技能评估待审核列表(skillNoAudit)get</option>
						<option value="userList" method="post" needDataType="Y" <c:if test="${param.reqCode=='userList'}">selected</c:if> >3.3.4 学员列表(userList) post</option>

			<option value="getSysNotice" method="post" needDataType="N" <c:if test="${param.reqCode=='getSysNotice'}">selected</c:if> >3.3.13 科教通知列表(getSysNotice)post</option>
			<option value="sysNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='sysNoticeDetail'}">selected</c:if> >3.3.14 科教通知详情(sysNoticeDetail)post</option>
			<option value="dayAttendList" method="post" needDataType="N" <c:if test="${param.reqCode=='dayAttendList'}">selected</c:if> >3.3.15 考勤审核列表(dayAttendList)post</option>
			<option value="modifyAttendance" method="post" needDataType="N" <c:if test="${param.reqCode=='modifyAttendance'}">selected</c:if> >3.3.14 单条签到记录修改状态(modifyAttendance)post</option>
			<option value="batchAttendAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='batchAttendAudit'}">selected</c:if> >3.3.14 一键审核通过签到数据(batchAttendAudit)post</option>
			<option value="activityList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityList'}">selected</c:if> >3.3.15 教学活动(activityList)post</option>
			<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >3.3.15 教学活动二维码(qrCode)post</option>
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
					</select>
						<select id="dataType" name="dataType" style="display:none;">
							<option></option>
							<option value="fiveData">五个基本数据（fiveData）</option>
							<%--<option value="dops">DOPS（dops）</option>--%>
							<option value="docEval">学员考评（docEval）</option>
							<%--<option value="miniCex">MINI-CEX（miniCex）</option>--%>
							<option value="after">出科考核表（after）</option>
							<option value="skill">技能评估（skill）</option>
							<option value="dayAttend">日常考勤（dayAttend）</option>
						</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/jswjw/test'/>">学生端测试工具</a>
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
		响应：	<pre><code id="rsp" class="JSON" style="width: 700px;"></code></pre>
	</div>
	</div>
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=sjwkls1&userPasswd=123456</textarea>
	</div>
	<div id="doctorList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow }</textarea>
	</div>
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow }&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="auditData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&dataFlow=${dataFlow}</textarea>
	</div>	
	<div id="auditAllData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&subDeptFlow=${subDeptFlow}</textarea>
	</div>
	<div id="viewSummary" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&subDeptFlow=${subDeptFlow}</textarea>
	</div>
	<div id="saveSummary" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&subDeptFlow=${subDeptFlow}</textarea>
	</div>
	<div id="index" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&doctorFlow=${doctorFlow}&roleId=Teacher&studentName=${studentName}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="batchAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&recType=${recType}&docFlow=${docFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="oneAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&recFlow=${recFlow}</textarea>
	</div>
	<div id="resRecList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&recType=${recType}&biaoJi=${biaoJi}&docFlow=${docFlow}&processFlow=${processFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="resRecDeatil" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&recType=${recType}&recFlow=${recFlow}&cataFlow=${cataFlow}&deptFlow=${deptFlow }</textarea>
	</div>
	<div id="afterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&recFlow=${recFlow}&processFlow=${processFlow}&deptFlow=${deptFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="saveRegistryForm" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleFlag=teacher&formFileName=${formFileName}&operUserFlow=${operUserFlow}&recFlow=${recFlow}&processFlow=${processFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="mini_cex" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&recFlow=${recFlow}&processFlow=${processFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="DOPS" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&recFlow=${recFlow}&processFlow=${processFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="fiveDataNoAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="skillNoAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&biaoJi=&docFlow=${docFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}</textarea>
	</div>
	<div id="monthEvalList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}&biaoJi=${biaoJi}</textarea>
	</div>
	<div id="dayAttendList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}&biaoJi=${biaoJi}&attendType=1&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="batchAttendAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&schResultFlow=${schResultFlow}&processFlow=${processFlow}&attendType=1</textarea>
	</div>
	<div id="modifyAttendance" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&docFlow=${docFlow}&attendanceFlow=${attendanceFlow}&dateDay=${dateDay}&attendType=为列表中action对应的id值&remarks=</textarea>
	</div>
	<div id="showMonthEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${docFlow}&recordFlow=${recordFlow}&processFlow=${processFlow}&evalMonth=${evalMonth}</textarea>
	</div>
	<div id="saveMonthEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${docFlow}&recordFlow=${recordFlow}&processFlow=${processFlow}&evalMonth=${evalMonth}&haveForm=${haveForm}&configFlow=${configFlow}&evalScore=${evalScore}&absenteeism=${absenteeism}&leave=${leave}&attendance=${attendance}&evalContent=${evalContent}</textarea>
	</div>
	<div id="aboutUs" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="userList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&biaoJi=${biaoJi}&studentName=&trainingTypeId=&trainingSpeId=&doctorTypeId=&sessionNumber=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getSysNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="sysNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&infoFlow=${infoFlow}</textarea>
	</div>
	<div id="activityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="showActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="addActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher</textarea>
	</div>
	<div id="delActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="viewActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="activityEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="activityEvalList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="activityStuList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&typeId=为Y时表示报名，为N时表示签到&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="effectiveResult" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&resultFlow=&isEffective=</textarea>
	</div>
	<div id="addActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&fileName=&其他参数和学员端addImage接口一样</textarea>
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