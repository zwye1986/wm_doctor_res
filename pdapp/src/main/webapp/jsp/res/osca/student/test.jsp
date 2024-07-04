<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>临床技能考核学员APP测试程序<%-- --http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%></title>
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
			var url = "<s:url value='/res/osca/student'/>/" + action ;
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
			var url = "<s:url value='/res/osca/student'/>/" + action + "?_method=" + method;
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
	<form name="appForm" action="${ctxPath}/res/osca/student/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="version" method="get" needDataType="N" <c:if test="${param.reqCode=='version'}">selected</c:if> >1.0 查询最新版本号(version)get</option>
						<option value="regiest" method="post" needDataType="N" <c:if test="${param.reqCode=='regiest'}">selected</c:if> >1.1 用户注册页面(regiest)post</option>
						<option value="saveRegister" method="post" needDataType="N" <c:if test="${param.reqCode=='saveRegister'}">selected</c:if> >1.2 用户注册保存(saveRegister)post</option>
						<option value="completeInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='completeInfo'}">selected</c:if> >1.3.1 完善个人信息页面(completeInfo)post</option>
						<option value="saveCompleteInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='saveCompleteInfo'}">selected</c:if> >1.3.2 保存个人信息页面(saveCompleteInfo)post</option>
						<option value="qrCode" method="post" needDataType="N" <c:if test="${param.reqCode=='qrCode'}">selected</c:if> >1.3 扫码签到(qrCode)post</option>
						<option value="osca" method="post" needDataType="N" <c:if test="${param.reqCode=='osca'}">selected</c:if> >1.4.1 我的考核[考核信息](osca)post</option>
						<option value="myScores" method="post" needDataType="N" <c:if test="${param.reqCode=='myScores'}">selected</c:if> >1.4.2 我的考核[我的成绩](myScores)post</option>
						<option value="scoreDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='scoreDetail'}">selected</c:if> >1.4.3 我的考核[我的成绩-成绩详情](scoreDetail)post</option>
						<option value="lineUp" method="post" needDataType="N" <c:if test="${param.reqCode=='lineUp'}">selected</c:if> >1.5 排队或取消排队(lineUp)post</option>
						<option value="stationRooms" method="post" needDataType="N" <c:if test="${param.reqCode=='stationRooms'}">selected</c:if> >1.6 站点所有房间(stationRooms)post</option>
						<option value="skillAssessList" method="post" needDataType="N" <c:if test="${param.reqCode=='skillAssessList'}">selected</c:if> >1.7 预约考核列表(skillAssessList)post</option>
						<option value="toOrdered" method="post" needDataType="N" <c:if test="${param.reqCode=='toOrdered'}">selected</c:if> >1.8 预约考核(toOrdered)post</option>
						<option value="changeOrdered" method="post" needDataType="N" <c:if test="${param.reqCode=='changeOrdered'}">selected</c:if> >1.9 取消预约(changeOrdered)post</option>
						<option value="myAssessList" method="post" needDataType="N" <c:if test="${param.reqCode=='myAssessList'}">selected</c:if> >1.10 我的预约列表(myAssessList)post</option>
						<option value="showTicket" method="post" needDataType="N" <c:if test="${param.reqCode=='showTicket'}">selected</c:if> >1.11 准考证信息(showTicket)post</option>
						<option value="noticeList" method="post" needDataType="N" <c:if test="${param.reqCode=='noticeList'}">selected</c:if> >1.12 公告信息列表(noticeList)post</option>
						<option value="noticeDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='noticeDetail'}">selected</c:if> >1.13 公告详情(noticeDetail)post</option>
						<option value="ownInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='ownInfo'}">selected</c:if> >1.14 个人中心(ownInfo)post</option>
						<option value="changePassword" method="post" needDataType="N" <c:if test="${param.reqCode=='changePassword'}">selected</c:if> >1.15 保存修改密码(changePassword)post</option>
						<option value="checkPassword" method="post" needDataType="N" <c:if test="${param.reqCode=='checkPassword'}">selected</c:if> >1.16 修改手机号前校验密码(checkPassword)post</option>
						<option value="changePhone" method="post" needDataType="N" <c:if test="${param.reqCode=='changePhone'}">selected</c:if> >1.17 保存手机号(changePhone)post</option>

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
			roleId:<input name="roleId" type="text" value="${roleId}">
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
	<div id="regiest" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly"></textarea>
	</div>
	<div id="saveRegister" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userEmail=&userPhone=&userPasswd=&reUserPasswd=&cretTypeId=&idNo=</textarea>
	</div>
	<div id="completeInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>

	<div id="qrCode" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&funcTypeId=${funcTypeId}&funcFlow=${funcFlow}&codeInfo=${codeInfos}</textarea>
	</div>
	<div id="osca" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="lineUp" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&roomRecordFlow=${roomRecordFlow}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}&waitingFlag=${waitingFlag}</textarea>
	</div>
	<div id="stationRooms" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&clinicalFlow=${clinicalFlow}&stationFlow=${stationFlow}</textarea>
	</div>
	<div id="skillAssessList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="myAssessList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="myScores" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="noticeList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="noticeDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&infoFlow=${infoFlow}</textarea>
	</div>
	<div id="toOrdered" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&clinicalFlow=</textarea>
	</div>
	<div id="changeOrdered" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&recordFlow=</textarea>
	</div>
	<div id="showTicket" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&clinicalFlow=</textarea>
	</div>
	<div id="scoreDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}&clinicalFlow=</textarea>
	</div>
	<div id="changePassword" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&oldPass=&newPass=&reNewPass=</textarea>
	</div>
	<div id="checkPassword" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&oldPass=</textarea>
	</div>
	<div id="changePhone" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&oldPass=&userPhone=</textarea>
	</div>
	<div id="ownInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roleId=${roleId}</textarea>
	</div>
	<div id="saveCompleteInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&isNew=Y&userImg=&userName=&sexId=&cretTypeId=&idNo=&userEmail=&userPhone=&trainingTypeId=&trainingSpeId=&sessionNumber=&trainingYears=&graduationYear=&workOrgName=&orgFlow=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>