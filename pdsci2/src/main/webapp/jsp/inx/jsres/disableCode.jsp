<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>发送邮件</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="complete" value="true"/>
</jsp:include>
<script type="text/javascript">
	var backTime = 5;
	$(function(){
		$("#sec").text(backTime);
		setInterval(function(){
			if(backTime<1){
				goEmailActive();
			}else{
				$("#sec").text(--backTime);
			}
		},1000);
	});
	
	function goEmailActive(){
		window.location.href = "<s:url value='inx/jsres/sendEmail'/>?userEmail=${userEmail}";
	}
</script>
</head>

<body>

<div class="yw">

  <div class="top" onclick="window.location.href='<s:url value='/inx/jsres'/>'" style="cursor: pointer;">江苏省住院医师规范化培训管理平台</div>
  
  <div class="content">
  
  	<div class="mail">
   	  <h3>邮箱验证</h3>
		<img src="<s:url value='/css/skin/tanhao.png'/>" width="120" height="120"/>
      <h4>邮箱链接过期</h4>
      <span>你的邮箱&nbsp;&nbsp;验证失败</span>
      <p><b><font color="#FF0000" id="sec"></font></b>秒后自动重新发送邮件并跳转到邮箱激活页面</p>
      <input class="button  button-blue" type="button" value="返回邮箱激活" onclick="goEmailActive();">
    </div>
    
  </div>

</div>

<div class="footer">主管单位：江苏省卫生和计划生育委员会</div>

</body>
</html>