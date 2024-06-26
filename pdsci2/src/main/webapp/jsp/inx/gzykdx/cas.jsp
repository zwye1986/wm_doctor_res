<%@page import="com.neusoft.education.tp.sso.client.filter.CASFilter"%>
<%@page import="com.neusoft.education.tp.sso.client.CASReceipt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>CAS JSP测试主页</title>
</head>
<body>
<h1>欢迎登录CAS JSP测试主页</h1>
<%
	CASReceipt receipt = (CASReceipt)session.getAttribute(CASFilter.SSO_FILTER_RECEIPT);
	if(receipt == null){
		out.print("你尚未登录！");
	} else {
		out.print("用户名：" + receipt.getUserName());
		out.print("<br>");
		out.print("属性：" + receipt.getUserMap());
		out.print("<br>");
		out.print("机构：" + receipt.getOrganizationList());
		out.print("<br>");
		out.print("权限：" + receipt.getPrivilegeList());
	}
%>
</body>
</html>