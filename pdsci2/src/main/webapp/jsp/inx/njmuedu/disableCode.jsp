<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>发送邮件</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="complete" value="true"/>
</jsp:include>
<script type="text/javascript">
$(function(){
	timer=setInterval("setSec()",1000);
});
function setSec(){
	sec=parseInt($("#sec").text());
	if(sec<=1){
		window.location.href="<s:url value='/inx/njmuedu'/>";
	}
	if(sec>=1){
		sec=sec-1;	
	}
	$("#sec").text(sec);
}
</script>
</head>
<body>
<div class="rbody">
	<div class="registerLogo"><img src="<s:url value='/jsp/njmuedu/css/images/registerLogo.png'/>" /></div>
    <div class="registerMassge">
      <h3><img src="<s:url value='/jsp/njmuedu/css/images/Email.png'/>"/>邮箱验证</h3>  
    </div>
    <div class="eGuoqi">
    	<img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/>
    	<h2>邮箱验证链接过期</h2>
        你的邮箱&nbsp;&nbsp;验证失败</br>
        <font color="#FF0000" id="sec">5</font>秒后自动跳转到网站首页</br>
        <input class="btn-back" type="button" value="返回首页" onclick='window.location.href="<s:url value='/inx/njmuedu'/>"'/>
    </div>
    <div class="regform-foot">
		Copyright © 2001- 2014 Character Technology, Inc. All rights reserved.
    </div>
</div>
</body>
</html>