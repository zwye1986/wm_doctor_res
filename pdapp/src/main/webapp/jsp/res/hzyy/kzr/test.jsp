<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>惠州APP-科主任端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
			url = "<s:url value='/res/hzyy'/>/"+action+"?_method="+method;	
		}else{
			url = "<s:url value='/res/hzyy/kzr'/>/"+action+"?_method="+method;
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
	<form name="appForm" action="${ctxPath}/res/hzyy/kzr/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="studentList" method="post" needDataType="N" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >首页对应的学员列表(studentList)post</option>
						<option value="funcList" method="post" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >功能列表(funcList)post</option>
						<option value="dataAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='dataAudit'}">selected</c:if> >数据审核(dataAudit)get</option>
						<option value="dataAuditList" method="get" needDataType="N" <c:if test="${param.reqCode=='dataAuditList'}">selected</c:if> >数据列表(dataAuditList)get</option>
						<option value="dataDetail" method="get" needDataType="N" <c:if test="${param.reqCode=='dataDetail'}">selected</c:if> >数据详情(dataDetail)get</option>

						<option value="selectSkill" method="get" needDataType="N" <c:if test="${param.reqCode=='selectSkill'}">selected</c:if> >选择技能考核(selectSkill)get</option>
						<option value="selectPatient" method="get" needDataType="N" <c:if test="${param.reqCode=='selectPatient'}">selected</c:if> >选择病人评分(selectPatient)get</option>
						<option value="cycleEval" method="get" needDataType="N" <c:if test="${param.reqCode=='cycleEval'}">selected</c:if> >过程评价(cycleEval)get</option>
						<option value="newAfterEvaluation" method="get" needDataType="N" <c:if test="${param.reqCode=='newAfterEvaluation'}">selected</c:if> >出科考核表(newAfterEvaluation)get</option>
						<option value="ownerInfo" method="get" needDataType="N" <c:if test="${param.reqCode=='ownerInfo'}">selected</c:if> >个人信息(ownerInfo)get</option>

						<option value="deptList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >评价管理中-科室列表(deptList)post</option>
						<option value="deptEvalInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='deptEvalInfo'}">selected</c:if> >评价管理中-科室评分信息(deptEvalInfo)post</option>

						<option value="teacherList" method="post" needDataType="N" <c:if test="${param.reqCode=='teacherList'}">selected</c:if> >评价管理中-带教列表(teacherList)post</option>
						<option value="teacherEvalInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='teacherEvalInfo'}">selected</c:if> >评价管理中-带教评分信息(teacherEvalInfo)post</option>
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="deptEvalInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&HosSecID=${HosSecID}&year=2018</textarea>
	</div>
	<div id="teacherList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10&searhStr=${searhStr}</textarea>
	</div>
	<div id="teacherEvalInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&teaFlow=${teaFlow}&startTime=&endTime=</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="dataAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="dataAuditList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UserID=${UserID}&searchData=&cysecId=&pageIndex=1&pageSize=10&dataTypeId=${dataTypeId}</textarea>
	</div>
	<div id="dataDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&dataFlow=${dataFlow}&dataTypeId=${dataTypeId}</textarea>
	</div>
	<div id="selectPatient" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="selectSkill" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="cycleEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>
	<div id="newAfterEvaluation" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&UCSID=${UCSID}</textarea>
	</div>

	<div id="ownerInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>