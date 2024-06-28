<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>发送邮件</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
<jsp:param name="register" value="true"/>
<jsp:param name="font" value="true"/>
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
		window.location.href = "<s:url value='inx/hbres/sendEmail'/>?userEmail=${userEmail}";
	}
</script>
</head>

<body>

<div class="yw">

  <jsp:include page="/jsp/hbres/head.jsp" flush="true">
      <jsp:param value="/inx/hbres" name="indexUrl"/>
      <jsp:param value="true" name="notShowAccount"/>
  </jsp:include>
  
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
<jsp:include page="/jsp/hbres/foot.jsp"  flush="true"/>
</body>
</html>