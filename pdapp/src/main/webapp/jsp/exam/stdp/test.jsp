<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>标准产品考试APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/exam/stdp'/>/"+action+"?_method="+method;
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
	<form name="appForm" action="${ctxPath}/exam/stdp/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >登录(login)post</option>
						<option value="infoList" method="get" needDataType="N" <c:if test="${param.reqCode=='infoList'}">selected</c:if> >资讯列表(infoList)get</option>
						<option value="examList" method="get" needDataType="N" <c:if test="${param.reqCode=='examList'}">selected</c:if> >考试列表(examList)get</option>
						<option value="startExam" method="get" needDataType="N" <c:if test="${param.reqCode=='startExam'}">selected</c:if> >开始考试(startExam)get</option>
						<option value="questionInfo" method="get" needDataType="N" <c:if test="${param.reqCode=='questionInfo'}">selected</c:if> >题目内容(questionInfo)get</option>
						<option value="submitAnswer" method="post" needDataType="N" <c:if test="${param.reqCode=='submitAnswer'}">selected</c:if> >提交内容(submitAnswer)post</option>
						<option value="submitWrongQuestion" method="post" needDataType="N" <c:if test="${param.reqCode=='submitWrongQuestion'}">selected</c:if> >提交错题(submitWrongQuestion)post</option>
						<option value="endExam" method="get" needDataType="N" <c:if test="${param.reqCode=='endExam'}">selected</c:if> >结束考试(endExam)get</option>
						<option value="historyList" method="get" needDataType="N" <c:if test="${param.reqCode=='historyList'}">selected</c:if> >考试历史列表(historyList)get</option>
						<option value="viewExam" method="get" needDataType="N" <c:if test="${param.reqCode=='viewExam'}">selected</c:if> >查看考试(viewExam)get</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			<br>
			examType:<input name="examType" type="text" value="${examType}">
			<br>
			examFlow:<input name="examFlow" type="text" value="${examFlow}">
			<br>
			answerFlow:<input name="answerFlow" type="text" value="${answerFlow}">
			<br>
			questionFlow:<input name="questionFlow" type="text" value="${questionFlow}">
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=11139&userPasswd=1</textarea>
	</div>
	<div id="infoList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="examList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examType=${examType}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="startExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}</textarea>
	</div>
	<div id="questionInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&answerFlow=${answerFlow}&questionFlow=${questionFlow}</textarea>
	</div>
	<div id="submitAnswer" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&answerFlow=${answerFlow}&questionFlow=${questionFlow}&values=123</textarea>
	</div>
	<div id="submitWrongQuestion" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&answerFlow=${answerFlow}&questionFlow=${questionFlow}&reason=123</textarea>
	</div>
	<div id="endExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&answerFlow=${answerFlow}</textarea>
	</div>
	<div id="historyList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="viewExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&examFlow=${examFlow}&answerFlow=${answerFlow}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>