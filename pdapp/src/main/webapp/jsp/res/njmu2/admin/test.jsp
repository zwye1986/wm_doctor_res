<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>南医大实习生APP平台管理员测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/njmu2/admin'/>/"+action+"?_method="+method;
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
	<form name="appForm" action="${ctxPath}/res/njmu2/admin/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			平台管理员动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">

						<option value="" method="">请选择</option>
						<option value="docSchDept" method="get" needDataType="N" <c:if test="${param.reqCode=='docSchDept'}">selected</c:if> >3.3.1科室轮转(docSchDept)get</option>
						<option value="trainDocList" method="get" needDataType="N" <c:if test="${param.reqCode=='trainDocList'}">selected</c:if> >3.3.2培训学员查询(trainDocList)get</option>
						<option value="doctorSchDetail" method="get" needDataType="N" <c:if test="${param.reqCode=='docSchDept'}">selected</c:if> >3.3.3学生轮转详情(doctorSchDetail)get</option>
						<option value="schDeptList" method="get" needDataType="N" <c:if test="${param.reqCode=='schDeptList'}">selected</c:if> >3.3.4科室下拉(schDeptList)get</option>
						<option value="orgList" method="get" needDataType="N" <c:if test="${param.reqCode=='orgList'}">selected</c:if> >3.3.4所有机构下拉(orgList)get</option>

					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/njmu2/test'/>">测试工具</a>
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
	<div id="docSchDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchData={"startDate":"2016-07-07","endDate":"2016-08-04","orgFlow":"","schDeptFlow":"","sessionNumber":""}</textarea>
	</div>
	<div id="doctorSchDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">doctorFlow=${doctorFlow}</textarea>
	</div>

	<div id="trainDocList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10&searchData={"doctorCategoryId":"","orgFlow":"","sessionNumber":"","trainingSpeId":"","userName":""}</textarea>
	</div>
	<div id="schDeptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">orgFlow=${orgFlow}</textarea>
	</div>
	<div id="orgList" class="reqParam" style="display: none">
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