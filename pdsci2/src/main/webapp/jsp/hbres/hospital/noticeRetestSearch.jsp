<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>
<body>
	<div class="form_news">
		<h3>${docRecruit.orgName}通知</h3>
		<div>
			<c:if test="${param.flag eq 'fs'}">${docRecruit.retestNotice}</c:if>
			<c:if test="${param.flag eq 'lq'}">${docRecruit.admitNotice}</c:if>
		</div>
	</div>
</body>
</html>