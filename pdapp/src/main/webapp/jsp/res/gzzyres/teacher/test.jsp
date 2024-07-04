<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广州中医APP-带教端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
			url = "<s:url value='/res/gzzyres'/>/"+action+"?_method="+method;	
		}else{
			url = "<s:url value='/res/gzzyres/teacher'/>/"+action+"?_method="+method;	
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
		$("#reqParam").text($("#" + divid + " textarea").first().val());
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
	<form name="appForm" action="${ctxPath}/res/gzzyres/teacher/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >登录(login)post</option>
						<option value="schDoctorList" method="get" needDataType="N" <c:if test="${param.reqCode=='schDoctorList'}">selected</c:if> >获取学员轮转科室列表(schDoctorList)get</option>
						<option value="schFuncList" method="get" needDataType="N" <c:if test="${param.reqCode=='schFuncList'}">selected</c:if> >轮转功能清单(schFuncList)get</option>
						<option value="categoryProgress" method="get" needDataType="N" <c:if test="${param.reqCode=='categoryProgress'}">selected</c:if> >查看分类数据(categoryProgress)get</option>
						<option value="dataList" method="get" needDataType="N" <c:if test="${param.reqCode=='dataList'}">selected</c:if> >填报数据查看(dataList)get</option>
					    <option value="addData" method="post" needDataType="N" <c:if test="${param.reqCode=='addData'}">selected</c:if> >新增输入数据(addData)post</option>
					    <option value="viewData" method="get" needDataType="N" <c:if test="${param.reqCode=='viewData'}">selected</c:if> >查看输入数据(viewData)get</option>
					    <option value="modData" method="put" needDataType="N" <c:if test="${param.reqCode=='modData'}">selected</c:if> >修改输入数据(modData)put</option>
					    <option value="deleteData" method="delete" needDataType="N" <c:if test="${param.reqCode=='deleteData'}">selected</c:if> >删除输入数据(deleteData)delete</option>
					    <option value="notice" method="get" needDataType="N" <c:if test="${param.reqCode=='notice'}">selected</c:if>>待办事项(notice)get</option>
					    <option value="noticeCount" method="get" needDataType="N" <c:if test="${param.reqCode=='noticeCount'}">selected</c:if>>待办事项数量(notice)get</option>
			<option value="dataAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='dataAudit'}">selected</c:if> >数据审核(dataAudit)get</option>
			<option value="dataAuditList" method="get" needDataType="N" <c:if test="${param.reqCode=='dataAuditList'}">selected</c:if> >数据列表(dataAuditList)get</option>
			<option value="dataDetail" method="get" needDataType="N" <c:if test="${param.reqCode=='dataDetail'}">selected</c:if> >数据详情(dataDetail)get</option>
			<option value="batchAuditData" method="get" needDataType="N" <c:if test="${param.reqCode=='batchAuditData'}">selected</c:if> >一键审核(batchAuditData)get</option>
			<option value="auditData" method="get" needDataType="N" <c:if test="${param.reqCode=='auditData'}">selected</c:if> >单个审核(auditData)get</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			doctorFlow:<input name="doctorFlow" type="text" value="${doctorFlow}">
			<br>
			funcTypeId:<input name="funcTypeId" type="text" value="${funcTypeId}">
			funcFlow:<input name="funcFlow" type="text" value="${funcFlow}">
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
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=teacher&userPasswd=123456</textarea>
	</div>
	<div id="schDoctorList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10&searchData=</textarea>
	</div>
	<div id="schFuncList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}</textarea>
	</div>
	<div id="categoryProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&pageIndex=1&pageSize=10&dataCount=20</textarea>
	</div>
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&searchData=&cataFlow=1&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="addData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&studentFlow=${studentFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1</textarea>
	</div>
	<div id="viewData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&studentFlow=${studentFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="modData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&studentFlow=${studentFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="deleteData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="notice" class="reqParam" style="display: none">
	    <textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="noticeCount" class="reqParam" style="display: none">
	    <textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>

	<div id="dataAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&studentFlow=${studentFlow}&searchData=&schStatusId=&cysecId=</textarea>
	</div>
	<div id="dataAuditList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&studentFlow=${studentFlow}&searchData=&schStatusId=&cysecId=&pageIndex=1&pageSize=10&dataTypeId=${dataTypeId}</textarea>
	</div>
	<div id="batchAuditData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&studentFlow=${studentFlow}&cysecId=&dataTypeId=${dataTypeId}&isPass=</textarea>
	</div>
	<div id="auditData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&dataFlow=${dataFlow}&dataTypeId=${dataTypeId}&isPass=</textarea>
	</div>
	<div id="dataDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&dataFlow=${dataFlow}&dataTypeId=${dataTypeId}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>