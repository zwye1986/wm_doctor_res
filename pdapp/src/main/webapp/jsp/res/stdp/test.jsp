<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>标准产品过程APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/stdp'/>/"+action+"?_method="+method;
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
	<form name="appForm" action="${ctxPath}/res/stdp/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >3.1.1登录(login)post</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >3.1.2查询最新版本号(version)get</option>
						<option value="notice" method="get" needDataType="N" <c:if test="${param.reqCode=='notice'}">selected</c:if> >3.1.3	待办事项(notice)get</option>
						<option value="noticeCount" method="get" needDataType="N" <c:if test="${param.reqCode=='noticeCount'}">selected</c:if> >3.1.4代办事项数量(noticeCount)get</option>
						<option value="cataList" method="get" needDataType="N" <c:if test="${param.reqCode=='cataList'}">selected</c:if> >3.1.5分类列表(cataList)get</option>
						<option value="dataList" method="get" needDataType="N" <c:if test="${param.reqCode=='dataList'}">selected</c:if> >3.1.6数据列表(dataList)get</option>
						<option value="viewData" method="get" needDataType="N" <c:if test="${param.reqCode=='viewData'}">selected</c:if> >3.1.7数据列表(viewData)get</option>
						<option value="saveData" method="post" needDataType="N" <c:if test="${param.reqCode=='saveData'}">selected</c:if> >3.1.8数据列表(saveData)post</option>
						<option value="delData" method="get" needDataType="N" <c:if test="${param.reqCode=='delData'}">selected</c:if> >3.1.9数据列表(delData)post</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/stdp/student/test'/>">学生端测试工具</a>
					<a href="<s:url value='/res/stdp/teacher/test'/>">老师端测试工具</a>
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			roleId:<input name="roleId" type="text" value="${roleId}">
			<br>
			deptFlow:<input name="deptFlow" type="text" value="${deptFlow}">
			doctorFlow:<input name="doctorFlow" type="text" value="${doctorFlow}">
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
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=student&userPasswd=123456</textarea>
	</div>
	<div id="notice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="noticeCount" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="cataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&deptFlow=${deptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&deptFlow=${deptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="viewData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&deptFlow=${deptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}</textarea>
	</div>
	<div id="saveData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&deptFlow=${deptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}</textarea>
	</div>
	<div id="delData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&deptFlow=${deptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>