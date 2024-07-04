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
		var url = "<s:url value='/res/jswjw/admin'/>/"+action;
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
	<form name="appForm" action="/pdapp/res/jswjw/admin/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="deptList" method="post" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >1.0.0 科室列表(deptList)post</option>
						<option value="deptStuDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='deptStuDetail'}">selected</c:if> >1.0.1 科室学员信息三个数字(deptStuDetail)post</option>
						<option value="studentList" method="post" needDataType="Y" <c:if test="${param.reqCode=='studentList'}">selected</c:if> >1.0.2 学员列表(studentList)post</option>
						<option value="errorStudentList" method="post" needDataType="N" <c:if test="${param.reqCode=='errorStudentList'}">selected</c:if> >1.0.3 出科异常(errorStudentList)post</option>
						<option value="saveSchErrorNotice" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSchErrorNotice'}">selected</c:if> >1.0.4 出科异常通过(saveSchErrorNotice)post</option>
						<option value="statisticalQuery" method="post" needDataType="N" <c:if test="${param.reqCode=='statisticalQuery'}">selected</c:if> >1.0.5 统计查询首页(statisticalQuery)post</option>
						<option value="speChangeList" method="post" needDataType="N" <c:if test="${param.reqCode=='speChangeList'}">selected</c:if> >1.0.6 专业变更学员列表(speChangeList)post</option>
						<option value="baseChangeList" method="post" needDataType="N" <c:if test="${param.reqCode=='baseChangeList'}">selected</c:if> >1.0.7 基地变更学员列表(baseChangeList)post</option>
						<option value="delayList" method="post" needDataType="N" <c:if test="${param.reqCode=='delayList'}">selected</c:if> >1.0.8 延期学员列表(delayList)post</option>
						<option value="returnList" method="post" needDataType="N" <c:if test="${param.reqCode=='returnList'}">selected</c:if> >1.0.9 退培学员列表(returnList)post</option>
						<option value="trainingOpinions" method="post" needDataType="N" <c:if test="${param.reqCode=='trainingOpinions'}">selected</c:if> >1.1.0 意见反馈(trainingOpinions)post</option>
						<option value="saveOpinionReply" method="post" needDataType="N" <c:if test="${param.reqCode=='saveOpinionReply'}">selected</c:if> >1.1.1 保存意见反馈回复(saveOpinionReply)post</option>
						<option value="orgDocList" method="post" needDataType="N" <c:if test="${param.reqCode=='orgDocList'}">selected</c:if> >1.1.2 在培，结业，待结业学员列表(orgDocList)post</option>
					</select>
					<select id="typeId" name="typeId" style="display:none;">
						<option value="monthCurrent">1、本月轮转学员（monthCurrent）</option>
						<option value="monthSch">2、本月出科学员（monthSch）</option>
						<option value="waitSch">3、计划入科学员（waitSch）</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/jswjw/test'/>">学生端测试工具</a>
					<a href="<s:url value='/res/jswjw/teacher/test'/>">带教端测试工具</a>
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
		响应：	<pre><code id="rsp" class="JSON"></code></pre>
	</div>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptName=${deptName}&pageIndex=1&pageSize=18</textarea>
	</div>
	<div id="studentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&searchStr=${searchStr}&typeId=${typeId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="deptStuDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="saveSchErrorNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=有多个学员用英文逗号隔开传递&content=${content}</textarea>
	</div>

	<div id="errorStudentList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="speChangeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="baseChangeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&typeId=${typeId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="orgDocList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&statusId=${statusId}&trainingTypeId=&trainingSpeId=&userName=&doctorTypeId=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="delayList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="returnList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="trainingOpinions" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="saveOpinionReply" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&trainingOpinionFlow=${trainingOpinionFlow}&replayContent=${replayContent}</textarea>
	</div>
	<div id="statisticalQuery" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&sessionNumber=${sessionNumber}</textarea>
	</div>

</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>