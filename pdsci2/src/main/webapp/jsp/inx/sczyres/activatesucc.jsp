
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="yw">
    <div class="top" onclick="window.location.href='<s:url value='/inx/sczyres'/>'" style="cursor: pointer;">${sysCfgMap['sys_title_name']}</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 880px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv" style="margin-bottom: 50px;">
		<h1 class="reg_title">用户激活</h1>
	     <div style="margin-left: 50px;margin-top: 20px;">
               <c:set var="mailArray" value="${fn:split(userEmail,'@') }"/>
               <div class="buts" style="display: none;"><a href="http://mail.${mailArray[1]}" target="_blank" class="button button-blue">登录邮箱</a></div>
					激活成功！<a href="<s:url value='/inx/sczyres'/>">点击登录</a> 
	     </div>
	     </div>
	</div>
  </div>
    </div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
<div class="footer">主管单位：四川省卫生厅科教处 | 协管单位：四川省毕业后医学继续教育委员会</div>
</body>
</html>