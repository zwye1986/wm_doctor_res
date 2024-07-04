<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>临床技能考核APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
		//"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		var isForm = $("#reqCode option:selected").attr("isForm");
		if(isForm=="Y")
		{
			var url = "<s:url value='/res/osca'/>/" + action ;
			var form=$('<form action="'+url+'" method="'+(method||'post')+'"></form>');
			var formData = $("#reqParam").val();
			var arr=formData.split("&"); //各个参数放到数组里
			for(var i=0;i < arr.length;i++){
				var num=arr[i].indexOf("=");
				if(num>0){
					$(form).append($('<input name="'+arr[i].substring(0,num)+'" value="'+arr[i].substr(num+1)+'" type="hidden"/>'));
				}
			}
			$(form).appendTo('body').submit().remove();
		}else {
			var url = "<s:url value='/res/osca'/>/" + action + "?_method=" + method;
			var formData = $("#reqParam").val();
			var needDataType = $("#reqCode option:selected").attr("needDataType");
			var dataType = $("#dataType option:selected").val();
			if (needDataType == 'Y') {
				formData = formData + "&dataType=" + dataType;
				formData = formData + "&" + $("#reqParamDataType").val();
			} else {

			}
			$.ajax({
				type: method,
				url: url,
				data: formData,
				cache: false,
				beforeSend: function () {
					jboxStartLoading();
				},
				success: function (resp) {
					jboxEndLoading();
					jboxTip("测试成功");
					console.log(resp);
					$("#rsp").text(JSON.stringify(resp,null,4));
				},
				error: function (resp,XMLHttpRequest, textStatus, errorThrown) {

					jboxEndLoading();
					console.log(resp.responseText);
					console.log(resp);
					jboxTip("操作失败,请刷新页面后重试");
				},
				complete: function () {
					jboxEndLoading();
				},
				statusCode: {
					405: function () {
						jboxTip("交易方法不正确");
					}
				}
			});
		}

	}function test2() {
		var action = $("#reqCode option:selected").val();
		if(action==""){
			jboxTip("请选择测试的交易");
			return;
		}
		var method = $("#reqCode option:selected").attr("method");
		var url = "<s:url value='/res/osca'/>/"+action;
		//"?_method="+method;
		if(method=="put"){
			method = "post"
		}
		if(method=="delete"){
			method = "post"
		}
		$.ajax({
			type : method,
			url : url,
			data : {
				userFlow:"9fdd668fff2740f19b28975a392e6f46",
			    roleId:"ExamTea",
				codeInfo:"funcFlow=queryQrCode&clinicalFlow=d1aa72012877482db73aea0c61499bb2&recordFlow=022211160e67466aae8019060daf8611&doctorFlow=272bcb0b0d4449ecaef0b21b88d3981f&tickNum=201800023501mt010001"
			},
			cache : false,
			dataType: "json",
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
	<form name="appForm" action="${ctxPath}/res/osca/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >1.0 查询最新版本号(version)get</option>
						<option value="login" method="post" needDataType="N" <c:if test="${param.reqCode=='login'}">selected</c:if> >1.1 用户登录登录(login)post</option>
						<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >1.2 考官扫码考试(qrCode)post</option>
						<option value="assessRefresh" method="post" needDataType="N" <c:if test="${param.reqCode=='assessRefresh'}">selected</c:if> >1.3 学员考核刷新(assessRefresh)post</option>
						<option value="exam" method="post" needDataType="N" <c:if test="${param.reqCode=='exam'}">selected</c:if> >1.4 考官考核或编辑或查看学员某个站点(exam)post</option>
						<option value="selectStationFrom" method="post" needDataType="N" <c:if test="${param.reqCode=='selectStationFrom'}">selected</c:if> >1.7 考官【选择】【编辑】或【查看】站点考核表单 (selectStationFrom)post</option>
						<%--<option value="editStationScore" method="post" needDataType="N" <c:if test="${param.reqCode=='editStationScore'}">selected</c:if> >1.8 考官查看已考核站点表单成绩 (editStationScore)post</option>--%>
						<option value="noFromSaveScore" method="post" needDataType="N" <c:if test="${param.reqCode=='noFromSaveScore'}">selected</c:if> >1.5 考官保存无表单的考核成绩 (noFromSaveScore)post</option>
						<option value="fromSaveScore" method="post" needDataType="N" <c:if test="${param.reqCode=='fromSaveScore'}">selected</c:if> >1.6 考官保存有表单的考核成绩 (fromSaveScore)post</option>
						<option value="scoreSubmit" method="post" needDataType="N" <c:if test="${param.reqCode=='scoreSubmit'}">selected</c:if> >1.9 考官提交已考核站点表单成绩 (scoreSubmit)post</option>
						<option value="scanStudent" method="post" needDataType="N" <c:if test="${param.reqCode=='scanStudent'}">selected</c:if> >2.0 考官已扫码学员列表 (scanStudent)post</option>
						<option value="notExamStudent" method="post" needDataType="N" <c:if test="${param.reqCode=='notExamStudent'}">selected</c:if> >2.0 考官未考考生列表 (notExamStudent)post</option>
						<option value="scoreBatchSumit" method="post" needDataType="N" <c:if test="${param.reqCode=='scoreBatchSumit'}">selected</c:if> >2.1 已扫码学员成绩批量提交 (scoreBatchSumit)post</option>
						<option value="manualCode" method="post" needDataType="N" <c:if test="${param.reqCode=='manualCode'}">selected</c:if> >2.2 手动添加扫码学员 (manualCode)post</option>
						<option value="saveSiginImage" method="post" needDataType="N" <c:if test="${param.reqCode=='saveSiginImage'}">selected</c:if> >2.3 保存签名图片 (saveSiginImage)post</option>
						<option value="showStationFiles" method="post" needDataType="N" <c:if test="${param.reqCode=='showStationFiles'}">selected</c:if> >2.4 查看站点试卷列表 (showStationFiles)post</option>
						<option value="showClinicalStationFiles" method="post" needDataType="N" <c:if test="${param.reqCode=='showClinicalStationFiles'}">selected</c:if> >2.4 查看站点下屏显试卷列表 (showClinicalStationFiles)post</option>
						<option value="showClinicalStationRooms" method="post" needDataType="N" <c:if test="${param.reqCode=='showClinicalStationRooms'}">selected</c:if> >2.4 查看站点下考场列表 (showClinicalStationRooms)post</option>
						<option value="setClinicalRoomFile" method="post" needDataType="N" <c:if test="${param.reqCode=='setClinicalRoomFile'}">selected</c:if> >2.4 设置屏显试卷推送到哪个考场 (setClinicalRoomFile)post</option>
						<option value="checkFile" method="post" isForm="N" needDataType="N" <c:if test="${param.reqCode=='checkFile'}">selected</c:if> >1.0.5 校验试卷文件file(checkFile)post</option>
						<option value="downFile" method="get" isForm="Y" needDataType="N" <c:if test="${param.reqCode=='downFile'}">selected</c:if> >1.0.6 下载试卷文件file(downFile)get</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
					<input type="button" value="测试2" class="search" onclick="test2();">
		</div>
		<div style="margin-left: 100px">
			记住参数：<br>
			userFlow:<input name="userFlow" type="text" value="${userFlow}">
			doctorFlow:<input name="doctorFlow" type="text" value="${doctorFlow}">
			<br>
			stationFlow:<input name="stationFlow" type="text" value="${stationFlow}">
			clinicalFlow:<input name="clinicalFlow" type="text" value="${clinicalFlow}">
			<br>
			roomRecordFlow:<input name="roomRecordFlow" type="text" value="${roomRecordFlow}">
			scoreFlow:<input name="scoreFlow" type="text" value="${scoreFlow}">
			<br>
			fromFlow:<input name="fromFlow" type="text" value="${fromFlow}">
			codeInfo:<input name="codeInfo" type="text" value="${codeInfo}">
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
		<textarea style="width: 350px; height: 400px" readonly="readonly">userCode=test&userPasswd=123456&isPad=N</textarea>
	</div>
	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&codeInfo=${codeInfo}</textarea>
	</div>
	<div id="manualCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&tickNumber=${tickNumber}</textarea>
	</div>
	<div id="exam" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&roomRecordFlow=${roomRecordFlow}</textarea>
	</div>
	<div id="assessRefresh" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&doctorFlow=${doctorFlow}&clinicalFlow=${clinicalFlow}&recordFlow=${recordFlow}</textarea>
	</div>
	<div id="noFromSaveScore" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&roomRecordFlow=${roomRecordFlow}&scoreFlow=${scoreFlow}&examScore=${examScore}</textarea>
	</div>
	<div id="saveSiginImage" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&siginImage=</textarea>
	</div>
	<div id="showStationFiles" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&stationFlow=</textarea>
	</div>
	<div id="showClinicalStationFiles" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&clinicalFlow=${clinicalFlow}&stationFlow=</textarea>
	</div>
	<div id="showClinicalStationRooms" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&clinicalFlow=${clinicalFlow}&stationFlow=</textarea>
	</div>
	<div id="setClinicalRoomFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roomFlow=${roomFlow}&fileFlow=</textarea>
	</div>
	<div id="fromSaveScore" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&roomRecordFlow=${roomRecordFlow}&scoreFlow=${scoreFlow}&scoreSum=${scoreSum}&fromFlow=${fromFlow}&isRequired=&jsonData=${jsonData}</textarea>
	</div>
	<div id="selectStationFrom" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&roomRecordFlow=${roomRecordFlow}&fromFlow=${fromFlow}&scoreFlow=${scoreFlow}&isRequired=</textarea>
	</div>
	<div id="editStationScore" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}&scoreFlow=${scoreFlow}&fromFlow=${fromFlow}</textarea>
	</div>
	<div id="scoreSubmit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlow=${doctorFlow}</textarea>
	</div>
	<div id="scanStudent" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&userName=${userName}&statusId=submit&pageIndex=1&pageSize=10&selectDate=2018-02-09</textarea>
	</div>
	<div id="notExamStudent" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="scoreBatchSumit" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&doctorFlows=doctorFlows为多个学员流水号以,分隔</textarea>
	</div>
	<div id="checkFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=</textarea>
	</div>
	<div id="downFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&fileFlow=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>