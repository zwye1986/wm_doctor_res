<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<style type="text/css">
		.errorMsg{
			background: url(<c:url value="/jsp/res/doctor/image/beijing.png"/>) no-repeat ;
			width: 100%;
			height: 60%;
			background-position: center;
			text-align: center;
			vertical-align: middle;
		}
		.errorMsg2{
            margin-top: 200px;
            width: 100%;
            height: 60%;
            background-position: center;
            text-align: center;
            vertical-align: middle;
		}
		span{
			margin-top: 60px;
			font-size: 20px;
			color: #000;
		}
	</style>
</head>
<body>
<div class="mainright">
		<%--<div class="errorMsg">--%>
		<%--</div>--%>
		<div class="errorMsg2">
			<span>${result}</span>
		</div>
</div>
</body>
</html>