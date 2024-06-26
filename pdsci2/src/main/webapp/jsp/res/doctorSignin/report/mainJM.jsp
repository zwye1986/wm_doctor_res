<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_qrcode" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="true" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
	</jsp:include>
<script type="text/javascript">
	var token=getuuid();
$(function(){
		initCode();
		setInterval("initCode()",5000);
		getReportData(1);
		setInterval("getReportData(1)",180000);
});
function getReportData(pageIndex){
	jboxLoad("signinData","<s:url value='/res/doctorSignin/signinDataJM'/>?roleFlag=${roleFlag}",false);
}
function getuuid() {
	var s = [];
	var hexDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
	s[8] = s[13] = s[18] = s[23] = "-";

	var uuid = s.join("");
	return uuid;
}
function guid() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		return v.toString(16);
	});
}
function initCode(){
	$("#code").empty();
	var url = "${signUrl}"+ "&signTime="+moment().format('YYYYMMDDHHmmss');
	$("#code").qrcode({
		render: "canvas",
		width: 250,
		height:250,
		correctLevel:0,//纠错等级 
		text: url
	});
}
function longPolling()
{
	$.ajax({
		url: "<s:url value='/res/doctorSignin/codeIsUse'/>",
		data: {"timed": new Date().getTime(),"token": token},
		dataType: "text",
		timeout: 5000,
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			longPolling();
		},
		success: function (data, textStatus) {
			$("#state").append("[state: " + textStatus + ", data: { " + data + "} ]<br/>");
			if (data == "Y") { // 请求成功
				token=getuuid();
				initCode();
			}
			longPolling();
		}
	});
}
</script>
</head> 
<body>
<div class="mainright">

	<%--<c:if test="${not empty deptFlowsStr}">--%>
		<div class="content" >
			<div id="signinData" style="width: 70%;float: left;padding-top: 10px;">
			</div>
			<div style="width: 30%;padding-top: 10px;float: right;text-align: center">
				<div id="code"></div>
				<%--<br/><br/>--%>
				<%--点击<font style="color: blue;cursor: pointer;" onclick="initCode();">此处</font>刷新二维码--%>
			</div>
		</div>
</div>
</body>
</html>