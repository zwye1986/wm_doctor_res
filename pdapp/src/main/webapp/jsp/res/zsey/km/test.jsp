<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>中山二院继教科过程管理系统APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/zsey/km'/>/"+action;
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
	<form name="appForm" action="/pdapp/res/zsey/km/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="index" method="post" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >1.0.0 首页(index)post</option>
						<option value="studentList" method="post" needDataType="Y" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >1.0.1 学员轮转列表(studentList)post</option>
						<option value="deptList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >1.0.2 科室列表(deptList)post</option>
						<option value="inProcessInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='inProcessInfo'}">selected</c:if> >1.0.3 单个科室入科教育文档(inProcessInfo)post</option>
						<option value="funcList" method="post" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >1.0.4 学员登记数据及出科分类功能列表(funcList)post</option>
						<option value="resRecList" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecList'}">selected</c:if> >1.0.5 五种数据列表(resRecList)get</option>
						<option value="resRecDeatil" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecDeatil'}">selected</c:if> >1.0.6 数据详情(resRecDeatil)get</option>
						<option value="afterEvaluation" method="get" needDataType="N" <c:if test="${param.reqCode=='afterEvaluation'}">selected</c:if> >1.0.7 出科考核表(afterEvaluation)get</option>
						<option value="teacherGradeList" method="post" needDataType="N" <c:if test="${param.reqCode=='teacherGradeList'}">selected</c:if> >1.0.11 带教评分列表(teacherGradeList)post</option>
						<option value="deptGrade" method="post" needDataType="N" <c:if test="${param.reqCode=='deptGrade'}">selected</c:if> >1.0.12 科室评价(deptGrade)post</option>
						<option value="deptGradeList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptGradeList'}">selected</c:if> >1.0.13 科室评价查询详情学员列表(deptGradeList)post</option>
						<option value="gradeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='gradeDetail'}">selected</c:if> >1.0.14 带教或科室评分详情(gradeDetail)post</option>
						<option value="evalStudentList" method="post" needDataType="N" <c:if test="${param.reqCode=='evalStudentList'}">selected</c:if> >1.0.15 学员评分列表(evalStudentList)post</option>
						<option value="monthEvalList" method="get" needDataType="N" <c:if test="${param.reqCode=='monthEvalList'}">selected</c:if> >1.0.16 学员评分月度考评列表(monthEvalList)get</option>
						<option value="showMonthEval" method="post" needDataType="N" <c:if test="${param.reqCode=='showMonthEval'}">selected</c:if> >1.0.17 查看月度考评(showMonthEval)post</option>
						<option value="ownerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >1.0.18 用户个人信息(ownerInfo)post</option>
						<option value="saveOwnerInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOwnerInfo'}">selected</c:if> >1.0.19 保存用户个人信息(saveOwnerInfo)post</option>
						<option value="afterFormAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='afterFormAudit'}">selected</c:if> >1.0.20 出科审核学员列表(afterFormAudit)post</option>
						<option value="showRegistryForm" method="get" needDataType="N" <c:if test="${param.reqCode=='showRegistryForm'}">selected</c:if> >1.0.21 出科分类表详情(showRegistryForm)get</option>
						<option value="saveRegistryForm" method="post" needDataType="N" <c:if test="${param.reqCode=='saveRegistryForm'}">selected</c:if> >1.0.22 出科分类表保存(saveRegistryForm)post</option>
						<option value="userList" method="post" needDataType="N" <c:if test="${param.reqCode=='userList'}">selected</c:if> >3.3.4 科室学员列表(userList) post</option>
						<option value="cycleDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='cycleDetail'}">selected</c:if> >3.3.5 轮转详情接口(cycleDetail) post</option>
						<option value="absenceList" method="post" needDataType="N" <c:if test="${param.reqCode=='absenceList'}">selected</c:if> >3.3.6 请假待审核列表(absenceList) post</option>
						<option value="repealAbsenceList" method="post" needDataType="N" <c:if test="${param.reqCode=='repealAbsenceList'}">selected</c:if> >3.3.7 销假待审核列表(repealAbsenceList) post</option>
						<option value="auditAbsenceList" method="post" needDataType="N" <c:if test="${param.reqCode=='auditAbsenceList'}">selected</c:if> >3.3.8 已审核列表(auditAbsenceList) post</option>
						<option value="auditAbsenceDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='auditAbsenceDetail'}">selected</c:if> >3.3.8 请假待审核或销假待审核详情或已审核详情(auditAbsenceDetail) post</option>
						<option value="saveAuditAbsence" method="post" needDataType="N" <c:if test="${param.reqCode=='saveAuditAbsence'}">selected</c:if> >3.3.8 保存请假待审核或保存销假待审核(saveAuditAbsence) post</option>
						<option value="absenceUserList" method="post" needDataType="N" <c:if test="${param.reqCode=='absenceUserList'}">selected</c:if> >3.3.8 统计查询列表(absenceUserList) post</option>
						<option value="absenceUserDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='absenceUserDetail'}">selected</c:if> >3.3.8 统计查询详情(absenceUserDetail) post</option>
						<option value="checkFile" method="post" needDataType="N" <c:if test="${param.reqCode=='checkFile'}">selected</c:if> >3.3.8 下载文件前校验(checkFile) post</option>
						<option value="downFile" method="post" needDataType="N" <c:if test="${param.reqCode=='downFile'}">selected</c:if> >3.3.8 下载文件(downFile) post</option>
						<option value="getBiLiHao" method="post" needDataType="N" <c:if test="${param.reqCode=='getBiLiHao'}">selected</c:if> >3.3.8 获取病历号(getBiLiHao) post</option>
						<option value="studentGradeList" method="post" needDataType="N" <c:if test="${param.reqCode=='studentGradeList'}">selected</c:if> >1.0.13 学员评价列表(studentGradeList)post</option>
						<option value="docSchDeptList" method="post" needDataType="N" <c:if test="${param.reqCode=='docSchDeptList'}">selected</c:if> >1.0.13 单个学员轮转列表(docSchDeptList)post</option>
						<option value="docEvalFuncList" method="post" needDataType="N" <c:if test="${param.reqCode=='docEvalFuncList'}">selected</c:if> >1.0.13 单个轮转科室评分功能列表(docEvalFuncList)post</option>
						<option value="teacherDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='teacherDetail'}">selected</c:if> >1.0.13 带教老师评价详情【数据结构与出科考核表一样】(teacherDetail)post</option>
						<option value="evalInfoList" method="post" needDataType="N" <c:if test="${param.reqCode=='evalInfoList'}">selected</c:if> >1.0.13 护士或患者评价列表(evalInfoList)post</option>
						<option value="evalInfoDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='evalInfoDetail'}">selected</c:if> >1.0.13 教学秘书或护士或患者评价详情(evalInfoDetail)post</option>
						<option value="saveData" method="post" needDataType="N" <c:if test="${param.reqCode=='saveData'}">selected</c:if> >3.1.9保存教学秘书或护士或患者评价数据(saveData)post</option>
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
					<a href="<s:url value='/res/zsey/test'/>">学生端测试工具</a>
					<a href="<s:url value='/res/zsey/teacher/test'/>">带教端测试工具</a>
					<a href="<s:url value='/res/zsey/spe/test'/>">专业管理员端测试工具</a>
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&roleId=Head&seachStr=${seachStr}&typeId=${typeId}&yearMonth=${yearMonth}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="evalStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&seachStr=${seachStr}&pageIndex=1&pageSize=10</textarea>
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

	<div id="afterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&recFlow=${recFlow}&processFlow=${processFlow}</textarea>
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&recFlow=${recFlow}&cataFlow=${cataFlow}</textarea>
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
	<div id="studentGradeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&stuName=${stuName}&schDeptFlow=&sessionNumber=&speId=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="docSchDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="evalInfoList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&processFlow=${processFlow}&typeId=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="evalInfoDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&processFlow=${processFlow}&typeId=&recFlow=</textarea>
	</div>
	<div id="docEvalFuncList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&processFlow=${processFlow}&resultFlow=${resultFlow}</textarea>
	</div>

	<div id="saveData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&processFlow=${processFlow}&resultFlow=&typeId=&recFlow=</textarea>
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
	<div id="saveOwnerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sexId=${sexId}&userName=${userName}&idNo=${idNo}&userPhone=${userPhone}&userEmail=${userEmail}&certificateId=${certificateId}</textarea>
	</div>
	<div id="afterFormAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&userName=${userName}&sessionNumber=${sessionNumber}&orgFlow=${orgFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="showRegistryForm" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recTypeId=${recTypeId}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="teacherDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&typeId=${recTypeId}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="saveRegistryForm" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recTypeId=${recTypeId}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="userList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&speId=${speId}&sessionNumber=${sessionNumber}&studentName=${studentName}&typeId=&isAfter=&isError=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="absenceList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&studentName=${studentName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="repealAbsenceList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&studentName=${studentName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="auditAbsenceList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&studentName=${studentName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="absenceUserList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&yearMonth=${yearMonth}&userName=${userName}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="absenceUserDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&yearMonth=${yearMonth}&doctorFlow=</textarea>
	</div>
	<div id="auditAbsenceDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&absenceFlow=${absenceFlow}&typeId=${typeId}</textarea>
	</div>
	<div id="saveAuditAbsence" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&absenceFlow=${absenceFlow}&teacherAgreeFlag=&teacherAuditInfo=&typeId=${typeId}&repealDate=${repealDate}</textarea>
	</div>
	<div id="cycleDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="checkFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=${fileFlow}</textarea>
	</div>
	<div id="downFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=${fileFlow}</textarea>
	</div>
	<div id="getBiLiHao" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">blh=${blh}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>