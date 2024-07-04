<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>南方医院APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url ="";
		if(action=="login" || action=="version" || action=="notice" || action=="noticeCount"){
			url = "<s:url value='/res/nfyy'/>/"+action+"?_method="+method;	
		}else{
			url = "<s:url value='/res/nfyy/secretarie'/>/"+action+"?_method="+method;
		}
		
		if(method=="put"){
			method = "post";
		}
		if(method=="delete"){
			method = "post";
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
		console.log($("#" + divid + " textarea").first().val());
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
	<form name="appForm" action="${ctxPath}/res/nfyy/secretarie/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="index" method="get" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >首页统计报表(index)get</option>
						<option value="studentList" method="post" needDataType="N" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >首页对应的学员列表(studentList)post</option>
						<option value="leaveInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='leaveInfo'}">selected</c:if> >教学管理考勤信息(leaveInfo)post</option>
						<option value="activityList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityList'}">selected</c:if> >教学活动评分列表(activityList)post</option>
						<option value="inProcessFileList" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessFileList'}">selected</c:if> >入科教育文档列表(inProcessFileList)post</option>
						<option value="inProcessFileDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessFileDetail'}">selected</c:if> >入科教育文档详情(inProcessFileDetail)post</option>
						<option value="zhuPeiInfoList" method="post" needDataType="N" <c:if test="${param.reqCode=='zhuPeiInfoList'}">selected</c:if> >住培信息列表(zhuPeiInfoList)post</option>
						<option value="zhuPeiFileDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='zhuPeiFileDetail'}">selected</c:if> >住培信息详情(zhuPeiFileDetail)post</option>
						<option value="ownerInfo" method="get" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >个人信息(ownerInfo)get</option>
						<option value="deptOrgCost" method="get" needDataType="N" <c:if test="${param.reqCode=='deptOrgCost'}">selected</c:if> >科室基地管理费用(deptOrgCost)get</option>
						<option value="outInManageDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='outInManageDetail'}">selected</c:if> >出入科管理详情(outInManageDetail)post</option>
						<option value="outProcessFuncList" method="post" needDataType="N" <c:if test="${param.reqCode=='outProcessFuncList'}">selected</c:if> >出科管理中功能列表(outProcessFuncList)post</option>
						<option value="AfterSummary" method="post" needDataType="N" <c:if test="${param.reqCode=='AfterSummary'}">selected</c:if> >出科管理【出科小结】(住院医师等)(AfterSummary)post</option>
						<option value="DayEval" method="post" needDataType="N" <c:if test="${param.reqCode=='DayEval'}">selected</c:if> >出科管理【日常评价】(住院医师等)(DayEval)post</option>
						<option value="saveDayEval" method="post" needDataType="N" <c:if test="${param.reqCode=='saveDayEval'}">selected</c:if> >出科管理【保存日常评价】(住院医师等)(saveDayEval)post</option>
						<option value="DOPS1" method="post" needDataType="N" <c:if test="${param.reqCode=='DOPS1'}">selected</c:if> >出科管理【DOPS量化表】(住院医师等)(DOPS1)post</option>
						<option value="MiniCex1" method="post" needDataType="N" <c:if test="${param.reqCode=='MiniCex1'}">selected</c:if> >出科管理【MiniCex量化表】(住院医师等)(MiniCex1)post</option>
						<option value="AfterEvaluation1" method="post" needDataType="N" <c:if test="${param.reqCode=='AfterEvaluation1'}">selected</c:if> >出科管理【出科考核表】(住院医师等)(AfterEvaluation1)post</option>
						<option value="saveAfterEvaluation1" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAfterEvaluation1'}">selected</c:if> >出科管理【出科考核表】(住院医师等)(saveAfterEvaluation1)post</option>
						<option value="Internship" method="post" needDataType="N" <c:if test="${param.reqCode=='Internship'}">selected</c:if> >出科管理【实习情况记录表】(实习生)(Internship)post</option>
						<option value="Appraisal" method="post" needDataType="N" <c:if test="${param.reqCode=='Appraisal'}">selected</c:if> >出科管理【实习鉴定】(实习生)(Appraisal)post</option>
						<option value="saveAppraisal" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAppraisal'}">selected</c:if> >出科管理【保存实习鉴定】(实习生)(saveAppraisal)post</option>
						<option value="DOPS" method="post" needDataType="N" <c:if test="${param.reqCode=='DOPS'}">selected</c:if> >出科管理【DOPS量化表】(实习生)(DOPS)post</option>
						<option value="MiniCex" method="post" needDataType="N" <c:if test="${param.reqCode=='MiniCex'}">selected</c:if> >出科管理【MiniCex量化表】(实习生)(MiniCex)post</option>
						<option value="AfterEvaluation" method="post" needDataType="N" <c:if test="${param.reqCode=='AfterEvaluation'}">selected</c:if> >出科管理【出科考核表】(实习生)(AfterEvaluation)post</option>
						<option value="saveAfterEvaluation" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAfterEvaluation'}">selected</c:if> >出科管理【出科考核表】(实习生)(saveAfterEvaluation)post</option>
						<option value="addTeachPlan" method="post" needDataType="N" <c:if test="${param.reqCode=='addTeachPlan'}">selected</c:if> >教学计划添加或编辑(addTeachPlan)post</option>
						<option value="delTeachPlan" method="post" needDataType="N" <c:if test="${param.reqCode=='delTeachPlan'}">selected</c:if> >教学计划删除(delTeachPlan)post</option>
						<option value="saveTeachPlan" method="post" needDataType="N" <c:if test="${param.reqCode=='saveTeachPlan'}">selected</c:if> >教学计划保存(saveTeachPlan)post</option>
						<option value="getThisTeachers" method="post" needDataType="N" <c:if test="${param.reqCode=='getThisTeachers'}">selected</c:if> >教学计划添加【获取本科室带教】(getThisTeachers)post</option>
						<option value="getNotThisTeachers" method="post" needDataType="N" <c:if test="${param.reqCode=='getNotThisTeachers'}">selected</c:if> >教学计划添加【获取非本科室带教】(getNotThisTeachers)post</option>
						<option value="getDoctors" method="post" needDataType="N" <c:if test="${param.reqCode=='getDoctors'}">selected</c:if> >教学计划添加【获取培训学员】(getDoctors)post</option>
						<option value="planDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='planDetail'}">selected</c:if> >教学计划详情(planDetail)post</option>
						<option value="activityStuList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityStuList'}">selected</c:if> >教学计划详情中参加学员列表(activityStuList)post</option>
						<option value="activityDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='activityDetail'}">selected</c:if> >教学活动评分详情(activityDetail)post</option>
						<option value="saveActivityEval" method="post" needDataType="N" <c:if test="${param.reqCode=='saveActivityEval'}">selected</c:if> >教学活动评分详情保存(saveActivityEval)post</option>
						<option value="cycleDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='cycleDetail'}">selected</c:if> >学员入科详情页(cycleDetail)post</option>
						<option value="saveInProcess" method="post" needDataType="N" <c:if test="${param.reqCode=='saveInProcess'}">selected</c:if> >学员入科详情入科操作(saveInProcess)post</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			roleId:<input name="roleId" type="text" value="${roleId}">
			<br>
			indexType:<input name="indexType" type="text" value="${indexType}">
			inProcessType:<input name="inProcessType" type="text" value="${inProcessType}">
			<br>
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&indexType=${indexType}</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&indexType=${indexType}&inProcessType=${inProcessType}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="leaveInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="activityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="ownerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="deptOrgCost" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&year=</textarea>
	</div>
	<div id="inProcessFileList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="inProcessFileDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&readSecDocumentId=${readSecDocumentId}</textarea>
	</div>
	<div id="zhuPeiInfoList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="zhuPeiFileDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&cIFlow=${CIFlow}</textarea>
	</div>
	<div id="outInManageDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="outProcessFuncList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="AfterSummary" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}&CySecID=${CySecID}&UserID=${UserID}</textarea>
	</div>
	<div id="DayEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="DOPS1" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="MiniCex1" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="DOPS" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="MiniCex" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="AfterEvaluation1" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="AfterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="Internship" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}&pageIndex=1&pageSize=10&dataType=</textarea>
	</div>
	<div id="Appraisal" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="saveAfterEvaluation1" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&ExamineMark=&AssessmentMark=&SickLeaveDay=&AbsenteeismDay=&SecDate=&TecComment=&SecComment=&UCSID=${UCSID}&ProfesserID=&ProfesserDate=&ID=</textarea>
	</div>
	<div id="saveAfterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&ExamineMark=&AssessmentMark=&SickLeaveDay=&AbsenteeismDay=&SecDate=&TecComment=&SecComment=&UCSID=${UCSID}&ProfesserID=&ProfesserDate=&ID=</textarea>
	</div>
	<div id="saveDayEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&resultJson=${resultJson}&stuType=${stuType}&ExamInfoDf=${ExamInfoDf}</textarea>
	</div>
	<div id="planDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}</textarea>
	</div>
	<div id="activityDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}</textarea>
	</div>
	<div id="saveActivityEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}&UmpireScore=${UmpireScore}</textarea>
	</div>
	<div id="addTeachPlan" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}</textarea>
	</div>
	<div id="delTeachPlan" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}</textarea>
	</div>
	<div id="saveTeachPlan" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=&CaDisMainSpeakerTypeId=&HosSecId=&TecID=&CaDisPlayClass=&CaAddress=&CaPeople=&CaDisTime=&CaEndDisTime=</textarea>
	</div>
	<div id="activityStuList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&CaDisID=${CaDisID}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="cycleDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}&stuType=${stuType}</textarea>
	</div>
	<div id="getThisTeachers" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getNotThisTeachers" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="getDoctors" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="saveInProcess" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=&RUserID=&RStartTime=&REndTime=&RCySecID=&RHosCySecID=&UserTecID=&HosSecID=&SectionManagerID=</textarea>
	</div>
	<div id="saveAppraisal" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&resultJson=${resultJson}&stuType=${stuType}&BriefID=&ExamInfoDf=&SecretaryContent=&TecAppraise=&UCSID=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>