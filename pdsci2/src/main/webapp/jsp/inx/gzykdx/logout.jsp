<%@ page import="com.pinde.sci.common.InitConfig" %>
<%@ page import="com.pinde.core.util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
</head>
<body>

<c:choose>
	<c:when test="${empty applicationScope.sysCfgMap['sys_logout_url']}">
		<jsp:forward page="/index.jsp"></jsp:forward>
	</c:when>
	<c:otherwise>
		<script>
			window.location.href='${applicationScope.sysCfgMap["sys_logout_url"]}';
		</script>
	</c:otherwise>

</c:choose>
</body>
</html>