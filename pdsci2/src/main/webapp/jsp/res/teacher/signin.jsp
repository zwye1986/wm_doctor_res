<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_qrcode" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

<script type="text/javascript">
$(function(){
	initCode();
	setInterval("initCode()",60000);
	//
	getSignData();
	setInterval("getSignData()",3000);
});
function getSignData(){
	jboxLoad("signData","<s:url value='/res/teacher/signinData'/>",false);
}
function initCode(){
	$("#code").empty();
	var url = "${signUrl}"+ "&signTime="+moment().format('YYYYMMDDHHmmss');
	$("#code").qrcode({
		render: "canvas",
		width: 300,
		height:300,
		correctLevel:0,//纠错等级 
		text: url
	});
	//$("#url").html(url);
}
function errorImg(img){
	img.src = '<s:url value='/css/skin/up-pic.jpg'/>';
}
</script>
</head> 
<body>
<div class="mainright">
	<div class="content" >
		<div id="signData" style="width: 50%;float: left;padding-top: 10px;">
		</div>
		<div style="width: 40%;margin-left: 50px;padding-top: 10px;float: right;">
			<div id="code"></div>
			<br/><br/>
			<font style="color: red;margin-left: 70px;">Tip:签到二维码每分钟刷新一次</font>
		</div>
		<div>
		<span id="url"></span>
		</div>
</div>
</div>
</body>
</html>