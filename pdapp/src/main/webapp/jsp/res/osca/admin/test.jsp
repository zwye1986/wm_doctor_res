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
			var url = "<s:url value='/res/osca/admin'/>/" + action ;
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
			var url = "<s:url value='/res/osca/admin'/>/" + action + "?_method=" + method;
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
	<form name="appForm" action="${ctxPath}/res/osca/admin/remember" method="get">
	<div style="width: 60%; float: left">
		<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 100px">
			动作：	<select id="reqCode" name="reqCode" onchange="change(this.value);">
						<option value="" method="">请选择</option>
						<option value="examInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='examInfo'}">selected</c:if> >1.0 考核概况(examInfo)post</option>
						<option value="singinInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='singinInfo'}">selected</c:if> >1.1 考生签到(singinInfo)post</option>
						<option value="noSiginList" method="post" needDataType="N" <c:if test="${param.reqCode=='noSiginList'}">selected</c:if> >1.2 未签到学员列表(noSiginList)post</option>
						<option value="teaInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='teaInfo'}">selected</c:if> >1.3 考官信息(teaInfo)post</option>
						<option value="roomInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='roomInfo'}">selected</c:if> >1.4 考场动态(roomInfo)post</option>
						<option value="scoreDetailList" method="post" needDataType="N" <c:if test="${param.reqCode=='scoreDetailList'}">selected</c:if> >1.5.1 考核成绩-成绩详情(scoreDetailList)post</option>
						<option value="differDetail" method="post" needDataType="N" <c:if test="${param.reqCode=='differDetail'}">selected</c:if> >1.5.1.1 考核成绩-成绩详情-差异详情(differDetail)post</option>
						<option value="showStationFromScore" method="post" needDataType="N" <c:if test="${param.reqCode=='showStationFromScore'}">selected</c:if> >1.5.1.2 考核成绩-成绩详情-差异详情-表单详情(showStationFromScore)post</option>
						<option value="stationDetailList" method="post" needDataType="N" <c:if test="${param.reqCode=='stationDetailList'}">selected</c:if> >1.5.2 考核成绩-考站详情(stationDetailList)post</option>
						<option value="perInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='perInfo'}">selected</c:if> >1.5.3 通过率(perInfo)post</option>
						<option value="calendarInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='calendarInfo'}">selected</c:if> >1.5.4 日历信息(calendarInfo)post</option>
						<option value="monthInfo" method="post" needDataType="N" <c:if test="${param.reqCode=='monthInfo'}">selected</c:if> >1.5.4 月历信息(monthInfo)post</option>
						<option value="rooms" method="post" needDataType="N" <c:if test="${param.reqCode=='rooms'}">selected</c:if> >1.5.4 考场信息(rooms)post</option>
						<option value="getRoomFile" method="post" needDataType="N" <c:if test="${param.reqCode=='getRoomFile'}">selected</c:if> >1.5.4 获取考场内的试卷信息(getRoomFile)post</option>
					</select>
					<input type="button" value="测试" class="search" onclick="test();">
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
			selectDate:<input name="selectDate" type="text" value="${selectDate}">
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
	<div id="examInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}</textarea>
	</div>
	<div id="singinInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&speId=</textarea>
	</div>
	<div id="noSiginList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&clinicalFlow=&recordFlow=&selectDate=${selectDate}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="teaInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="scoreDetailList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&searchStr=&speId=&pageIndex=1&pageSize=10&isPass=Y</textarea>
	</div>
	<div id="stationDetailList" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&searchStr=&speId=&pageIndex=1&pageSize=10&isPass=Y</textarea>
	</div>
	<div id="perInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&speId=</textarea>
	</div>
	<div id="roomInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&selectDate=${selectDate}&searchStr=&pageIndex=1&pageSize=10</textarea>
	</div>
	<div id="differDetail" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&clinicalFlow=&doctorFlow=&stationFlow=</textarea>
	</div>
	<div id="showStationFromScore" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&scoreFlow=&doctorFlow=&partnerFlow=</textarea>
	</div>
	<div id="calendarInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&month=</textarea>
	</div>
	<div id="monthInfo" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&year=</textarea>
	</div>
	<div id="rooms" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}</textarea>
	</div>
	<div id="getRoomFile" class="reqParam" style="display: none">
		<textarea style="width: 350px; height: 400px" readonly="readonly">userFlow=${userFlow}&roomFlow=</textarea>
	</div>
</body>
</html>
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	hljs.initHighlightingOnLoad();
});
</script>