<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>广州肿瘤住院医师APP老师端测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		var url = "<s:url value='/res/gzzl/teacher'/>/"+action+"?_method="+method;
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
	<form name="appForm" action="${ctxPath}/res/gzzl/teacher/remember" method="get">
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

			<option value="getSysNotice" method="post" needDataType="N" <c:if test="${param.reqCode=='getSysNotice'}">selected</c:if> >3.3.13 科教通知列表(getSysNotice)post</option>
			<option value="sysNoticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='sysNoticeDetail'}">selected</c:if> >3.3.14 科教通知详情(sysNoticeDetail)post</option>
			<option value="activityList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityList'}">selected</c:if> >3.3.15 教学活动(activityList)post</option>
			<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >3.3.15 教学活动二维码(qrCode)post</option>
			<option value="delActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='delActivity'}">selected</c:if> >3.3.15 删除教学活动(delActivity)post</option>
			<option value="showActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='showActivity'}">selected</c:if> >3.3.15 查看或编辑教学活动(showActivity)post</option>
			<option value="addActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='addActivity'}">selected</c:if> >3.3.15 新增教学活动(addActivity)post</option>
			<option value="saveActivity" method="post" needDataType="N" <c:if test="${param.reqCode=='saveActivity'}">selected</c:if> >3.3.15 保存教学活动(saveActivity)post</option>
			<option value="viewActivityImage" method="post" needDataType="N" <c:if test="${param.reqCode=='viewActivityImage'}">selected</c:if> >3.3.15 查看教学活动现场图片(viewActivityImage)post</option>
			<option value="addActivityImage" method="post" needDataType="N" <c:if test="${param.reqCode=='addActivityImage'}">selected</c:if> >3.3.15 上传教学活动现场图片(addActivityImage)post</option>
			<option value="deleteActivityImage" method="get" needDataType="N" <c:if test="${param.reqCode=='deleteActivityImage'}">selected</c:if> >3.3.15 删除教学活动现场图片(deleteActivityImage)get</option>
			<option value="activityEval" method="post" needDataType="N" <c:if test="${param.reqCode=='activityEval'}">selected</c:if> >3.3.15 教学活动评价均分(activityEval)post</option>
			<option value="activityEvalList" method="post" needDataType="N" <c:if test="${param.reqCode=='activityEvalList'}">selected</c:if> >3.3.15 教学活动评价详情列表(activityEvalList)post</option>
			<option value="effectiveResult" method="post" needDataType="N" <c:if test="${param.reqCode=='effectiveResult'}">selected</c:if> >3.3.15 教学活动评价详情列表-修改状态(effectiveResult)post</option>
		</select>
					<select id="dataType" name="dataType" style="display:none;">
						<option></option>
						<option value="fiveData">数据审核（fiveData）</option>
						<option value="DOPS">DOPS（DOPS）</option>
						<option value="Mini_CEX">MINI-CEX（Mini_CEX）</option>
						<option value="AfterSummary,AfterEvaluation">出科考核（AfterSummary,AfterEvaluation）</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<a href="<s:url value='/res/gzzl/test'/>">测试工具</a>
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&recTypeId=${recTypeId}&recFlow=${recFlow}&resultFlow=${resultFlow }</textarea>
	</div>
	<div id="getSysNotice" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="sysNoticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&infoFlow=${infoFlow}</textarea>
	</div>
	<div id="activityList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="showActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="addActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher</textarea>
	</div>
	<div id="delActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="viewActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="activityEval" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=</textarea>
	</div>
	<div id="activityEvalList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="effectiveResult" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&resultFlow=&isEffective=</textarea>
	</div>
	<div id="addActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=Teacher&activityFlow=&fileName=&其他参数和学员端addImage接口一样</textarea>
	</div>
	<div id="deleteActivityImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=${activityFlow}&imageFlow=</textarea>
	</div>
	<div id="saveActivity" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&activityFlow=&speakerFlow=&deptFlow=&activityTypeId=&activityName=&activityAddress=&speakerPhone=&startTime=&endTime=&activityRemark=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>