<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>南医大专业硕士APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/njmu'/>/"+action+"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
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
	<form name="appForm" action="${ctxPath}/res/njmu/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >登录(login)post</option>
						<option value="deptList" method="get" needDataType="N" <c:if test="${param.reqCode=='deptList'}">selected</c:if> >获取科室列表(deptList)get</option>
						<option value="teacherList" method="get" needDataType="N" <c:if test="${param.reqCode=='teacherList'}">selected</c:if> >获取带教老师(teacherList)get</option>
						<option value="deptHeadList" method="get" needDataType="N" <c:if test="${param.reqCode=='deptHeadList'}">selected</c:if> >获取科主任(deptHeadList)get</option>
						<option value="enterDept" method="put" needDataType="N" <c:if test="${param.reqCode=='enterDept'}">selected</c:if> >入科(enterDept)put</option>
						<option value="globalProgress" method="get" needDataType="N" <c:if test="${param.reqCode=='globalProgress'}">selected</c:if> >查看科室整体录入进度(globalProgress)get</option>
						<option value="categoryProgress" method="get" needDataType="Y" <c:if test="${param.reqCode=='categoryProgress'}">selected</c:if> >查看科室分项录入进度(categoryProgress)get</option>
						<option value="dataList" method="get" needDataType="Y" <c:if test="${param.reqCode=='dataList'}">selected</c:if> >查看科室分项数据列表(dataList)get</option>
						<option value="inputList" method="get" needDataType="Y" <c:if test="${param.reqCode=='inputList'}">selected</c:if> >获取输入项(inputList)get</option>
						<option value="addData" method="post" needDataType="Y" <c:if test="${param.reqCode=='addData'}">selected</c:if> >新增输入数据(addData)post</option>
						<option value="viewData" method="get" needDataType="Y" <c:if test="${param.reqCode=='viewData'}">selected</c:if> >查看输入数据(viewData)get</option>
						<option value="modData" method="put" needDataType="Y" <c:if test="${param.reqCode=='modData'}">selected</c:if> >修改数据(modData)put</option>
						<option value="delData" method="delete" needDataType="Y" <c:if test="${param.reqCode=='delData'}">selected</c:if> >删除数据(delData)delete</option>
						<option value="canAddSummary" method="get" needDataType="N" <c:if test="${param.reqCode=='canAddSummary'}">selected</c:if> >是否可以新增出院小结(canAddSummary)get</option>
						<option value="workLogList" method="get" needDataType="N" <c:if test="${param.reqCode=='workLogList'}">selected</c:if> >查询工作日志(workLogList)get</option>
						<option value="viewWorkLog" method="get" needDataType="N" <c:if test="${param.reqCode=='viewWorkLog'}">selected</c:if> >查看工作日志(viewWorkLog)get</option>
						<option value="saveWorkLog" method="post" needDataType="N" <c:if test="${param.reqCode=='saveWorkLog'}">selected</c:if> >保存工作日志(saveWorkLog)post</option>
						<option value="delWorkLog" method="delete" needDataType="N" <c:if test="${param.reqCode=='saveWorkLog'}">selected</c:if> >清空工作日志(delWorkLog)delete</option>
					</select>
					<select id="dataType" name="dataType" style="display: none;" onchange="change2(this.value);">
						<option value="mr" <c:if test="${param.dataType=='mr'}">selected</c:if> >mr</option>
						<option value="disease" <c:if test="${param.dataType=='disease'}">selected</c:if> >disease</option>
						<option value="skill" <c:if test="${param.dataType=='skill'}">selected</c:if> >skill</option>
						<option value="operation" <c:if test="${param.dataType=='operation'}">selected</c:if> >operation</option>
						<option value="activity" <c:if test="${param.dataType=='activity'}">selected</c:if> >activity</option>
						<option value="summary" <c:if test="${param.dataType=='summary'}">selected</c:if> >summary</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
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
	<div id="version" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="login" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=cs001&userPasswd=123456</textarea>
	</div>
	<div id="deptList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="teacherList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&teacherName=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="deptHeadList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&deptHeadName=&pageIndex=1&pageSize=100</textarea>
	</div>
	<div id="enterDept" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&teacherFlow=&deptHeadFlow=</textarea>
	</div>
	<div id="globalProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>	
	<div id="categoryProgress" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
	</div>	
	<div id="dataList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&pageIndex=1&pageSize=100</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">cataFlow=${cataFlow}</textarea>
	</div>	
	<div id="inputList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&cataFlow=${cataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="addData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">&mr_pName=&mr_no=&mr_diagType=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">disease_pDate=&disease_pName=&disease_mrNo=&disease_diagName=&disease_diagType=&disease_isCharge=&disease_isRescue=&disease_treatStep=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">skill_operDate=&skill_pName=&skill_mrNo=&skill_operName=&skill_result=&skill_memo=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">operation_operDate=&operation_pName=&operation_mrNo=&operation_operName=&operation_operRole=&operation_memo=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">activity_date=&activity_content=&activity_way=&activity_period=&activity_speaker=&cataFlow=${cataFlow}</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">summary_content=&cataFlow=${cataFlow}</textarea>
		
	</div>
	<div id="viewData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="modData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly">&mr_pName=&mr_no=&mr_diagType=</textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly">disease_pDate=&disease_pName=&disease_mrNo=&disease_diagName=&disease_diagType=&disease_isCharge=&disease_isRescue=&disease_treatStep=</textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly">skill_operDate=&skill_pName=&skill_mrNo=&skill_operName=&skill_result=&skill_memo=</textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly">operation_operDate=&operation_pName=&operation_mrNo=&operation_operName=&operation_operRole=&operation_memo=</textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly">activity_date=&activity_content=&activity_way=&activity_period=&activity_speaker=</textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly">summary_content=</textarea>
	</div>
	<div id="delData" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}&dataFlow=${dataFlow}</textarea>
		
		<textarea dataType='mr' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='disease' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='skill' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='operation' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='activity' style="width: 350px; height: 400px" readonly="readonly"></textarea>
		<textarea dataType='summary' style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="canAddSummary" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&deptFlow=${deptFlow}</textarea>
	</div>
	<div id="workLogList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&startDate=2015-02-01&endDate=2015-02-28</textarea>
	</div>
	<div id="viewWorkLog" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&workDate=2015-02-01</textarea>
	</div>
	<div id="saveWorkLog" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&workDate=2015-02-01&workTypeId=Attendance&workContent=工作日志内容</textarea>
	</div>
	<div id="delWorkLog" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&workDate=2015-02-01</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>