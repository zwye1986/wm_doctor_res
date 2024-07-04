<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>西南住院医师APP老师端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/xnres/teacher'/>/"+action+"?_method="+method;
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
		var needDataType = $("#reqCode option:selected").attr("needDataType");
		if(needDataType=='Y'){
			$("#dataType").show();
		}else{
			$("#dataType").hide();
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
	<form name="appForm" action="${ctxPath}/res/xnres/teacher/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			老师端动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="doctorList" method="get" needDataType="N" <c:if test="${param.reqCode=='doctorList'}">selected</c:if> >3.3.1获取学员列表(doctorList)get</option>
						<option value="funcList" method="get" needDataType="N" <c:if test="${param.reqCode=='funcList'}">selected</c:if> >3.3.2获取功能列表(funcList)get</option>
						<option value="index" method="get" needDataType="N" <c:if test="${param.reqCode=='index'}">selected</c:if> >3.3.3首页信息(index)get</option>
						<option value="studentList" method="post" needDataType="Y" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >3.3.4学员列表(studentList) post</option>
						<option value="dataNoAudit" method="get" needDataType="N" <c:if test="${param.reqCode=='dataNoAudit'}">selected</c:if> >3.3.5数据待审核数量(dataNoAudit)get</option>
						<option value="recDataList" method="get" needDataType="N" <c:if test="${param.reqCode=='recDataList'}">selected</c:if> >3.3.6数据列表(recDataList)get</option>
						<option value="resRecDeatil" method="get" needDataType="N" <c:if test="${param.reqCode=='resRecDeatil'}">selected</c:if> >3.3.7数据详情(resRecDeatil)get</option>
						<option value="batchAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='batchAudit'}">selected</c:if> >3.3.8数据一键审核(batchAudit)post</option>
						<option value="oneAudit" method="post" needDataType="N" <c:if test="${param.reqCode=='oneAudit'}">selected</c:if> >3.3.9单条审核(oneAudit)post</option>
						<option value="viewFourTable" method="get" needDataType="N" <c:if test="${param.reqCode=='viewFourTable'}">selected</c:if> >3.3.10 出科小结,迷你临床演练评估量化表,临床操作技能评估量化表,出科考核表(viewFourTable)get</option>
						<option value="saveData" method="post" needDataType="N" <c:if test="${param.reqCode=='saveData'}">selected</c:if> >3.3.10 【审核出科小结,迷你临床演练评估量化表,临床操作技能评估量化表,出科考核表】(saveData)post</option>
						<option value="modfiyPass" method="post" needDataType="N" <c:if test="${param.reqCode=='modfiyPass'}">selected</c:if> >3.3.4 修改密码(modfiyPass)post</option>
		</select>
					<select id="dataType" name="dataType" style="display:none;">
						<option></option>
						<option value="fiveData">数据审核（fiveData）</option>
						<option value="DOPS">DOPS（DOPS）</option>
						<option value="Mini_CEX">MINI-CEX（Mini_CEX）</option>
						<option value="after">出科考核（after）</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/xnres/test'/>">测试工具</a>
		</div>
		<div style="margin-left: 100px ;width: 800px;">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			doctorFlow:<input name="doctorFlow" type="text" value="${doctorFlow}">
			<br>
			cataFlow:<input name="cataFlow" type="text" value="${cataFlow}">
			dataFlow:<input name="dataFlow" type="text" value="${dataFlow}">
			<br>
			deptFlow:<input name="deptFlow" type="text" value="${deptFlow}">
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
	<div id="doctorList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="funcList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&doctorFlow=${doctorFlow}</textarea>
	</div>
	<div id="index" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&studentName=${studentName}&biaoJi=${biaoJi}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="dataNoAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&docFlow=${docFlow}&resultFlow=${resultFlow}&processFlow=${processFlow}&dataType=${dataType}</textarea>
	</div>
	<div id="recDataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&docFlow=${docFlow}&biaoJi=${biaoJi}&resultFlow=${resultFlow}&processFlow=${processFlow}&recType=${recType}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="oneAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&statusId=${statusId}&recFlow=${recFlow}</textarea>
	</div>
	<div id="batchAudit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&processFlow=${processFlow}&docFlow=${docFlow}</textarea>
	</div>
	<div id="resRecDeatil" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&recFlow=${recFlow}&cataFlow=${cataFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="viewFourTable" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="saveData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recType=${recType}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="modfiyPass" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&oldPass=&newPass=&reNewPass=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>