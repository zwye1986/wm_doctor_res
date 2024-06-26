
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jssrm/css/style.css'/>"></link>
	<script type="text/javascript">

	</script>
</head>
<body>
	<div class="yw">
    <div class="top" onclick="window.location.href='<s:url value='/inx/jssrm'/>'" style="cursor: pointer;">${sysCfgMap['sys_title_name']}</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">用户激活</h1>
	     <div style="margin-left: 50px;margin-top: 20px;">
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
					激活成功！<a href="<s:url value='/inx/jssrm'/>">点击登录</a> 
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>

<div class="footer">主管单位：江苏省卫生和计划生育委员会</div>
</body>
</html>