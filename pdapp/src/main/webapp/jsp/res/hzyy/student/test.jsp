<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>惠州APP-学员端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
			url = "<s:url value='/res/hzyy/student'/>/"+action+"?_method="+method;	
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
	<form name="appForm" action="${ctxPath}/res/hzyy/student/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >登录(login)post</option>
						<option value="schPlanList" method="get" needDataType="N" <c:if test="${param.reqCode=='schPlanList'}">selected</c:if> >获取学员轮转科室列表(schPlanList)get</option>
						<option value="schFuncList" method="get" needDataType="N" <c:if test="${param.reqCode=='schFuncList'}">selected</c:if> >轮转功能清单(schFuncList)get</option>
						<option value="categoryProgress" method="get" needDataType="N" <c:if test="${param.reqCode=='categoryProgress'}">selected</c:if> >查看分类数据(categoryProgress)get</option>
						<option value="dataList" method="get" needDataType="Y" <c:if test="${param.reqCode=='dataList'}">selected</c:if> >获取数据清单(dataList)get</option>
					    <%-- <option value="inputList" method="get" needDataType="N" <c:if test="${param.reqCode=='inputList'}">selected</c:if> >获取输入控件(inputList)get</option> --%>
					    <option value="addData" method="post" needDataType="Y" <c:if test="${param.reqCode=='addData'}">selected</c:if> >新增输入数据(addData)post</option>
					    <option value="viewData" method="get" needDataType="Y" <c:if test="${param.reqCode=='viewData'}">selected</c:if> >查看输入数据(viewData)get</option>
					    <option value="modData" method="put" needDataType="Y" <c:if test="${param.reqCode=='modData'}">selected</c:if> >修改输入数据(modData)put</option>
					    <option value="deleteData" method="delete" needDataType="Y" <c:if test="${param.reqCode=='deleteData'}">selected</c:if> >删除输入数据(deleteData)delete</option>
					    <option value="notice" method="get" needDataType="N" <c:if test="${param.reqCode=='notice'}">selected</c:if>>待办事项(notice)get</option>
					    <option value="noticeCount" method="get" needDataType="N" <c:if test="${param.reqCode=='noticeCount'}">selected</c:if>>待办事项数量(noticeCount)get</option>
					    <option value="joinExam" method="get" needDataType="N" <c:if test="${param.reqCode=='joinExam'}">selected</c:if>>参加出科考试(joinExam)get</option>
					</select>
					<select id="dataType" name="dataType" style="display: none;" onchange="change2(this.value);">
						<option value="0001" <c:if test="${param.funcFlow=='0001'}">selected</c:if> >0001</option>
						<option value="0002" <c:if test="${param.funcFlow=='0002'}">selected</c:if> >0002</option>
						<option value="0003" <c:if test="${param.funcFlow=='0003'}">selected</c:if> >0003</option>
						<option value="0004" <c:if test="${param.funcFlow=='0004'}">selected</c:if> >0004</option>
						<option value="0005" <c:if test="${param.funcFlow=='0005'}">selected</c:if> >0005</option>
						<option value="0006" <c:if test="${param.funcFlow=='0006'}">selected</c:if> >0006</option>
						<option value="0007" <c:if test="${param.funcFlow=='0007'}">selected</c:if> >0007</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			schDeptFlow:<input name="schDeptFlow" type="text" value="${schDeptFlow}">
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=1100011009&userPasswd=123456</textarea>
	</div>
	<div id="schPlanList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10&searchData={"schStatusId":"Entering"}</textarea>
	</div>
	<div id="joinExam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&SecID=&SpecialtyID=</textarea>
	</div>
	<div id="schFuncList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}</textarea>
	</div>
	<div id="categoryProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&pageIndex=1&pageSize=10&dataCount=20</textarea>
	</div>
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1&pageIndex=1&pageSize=10&searchData=</textarea>
	</div>
	<%-- <div id="inputList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1</textarea>
	</div> --%>
	<div id="addData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1</textarea>
	</div>
	<div id="viewData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="modData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&cataFlow=1&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="deleteData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&schDeptFlow=${schDeptFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&dataFlow=${dataFlow}</textarea>
	</div>
	<div id="notice" class="reqParam" style="display: none">
	    <textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="noticeCount" class="reqParam" style="display: none">
	    <textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>