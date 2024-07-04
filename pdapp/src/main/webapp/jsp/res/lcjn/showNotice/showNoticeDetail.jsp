<%@ page import="com.pinde.core.util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery.SuperSlide.2.1.1.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<%
		String infoFlow=request.getParameter("infoFlow");
	%>
	<script>
		$(document).ready(function(){
			var infoFlow="<%=infoFlow%>";
			var url ="<s:url value='/res/lcjn/showNoticeDetail?infoFlow='/>"+infoFlow;
			$.ajax({
				type : "get",
				url : url,
				cache : false,
				success : function(resp) {
					var obj=eval("("+resp+")");
					$("#content").html(obj.content);
					$("#title").html(obj.title);

				},
				error : function() {
					$("#content").html("无通知公告信息！");
					$("#title").html("通知公告详情");
				}
			});

		});
	</script>
</head>
<body>

<div width="100%" height="100%" align="center" style="">
	<div height="10%" width="100%" align="center">
		<h2 id="title">
		</h2>
	</div>
	<div height="90%" width="100%"id="content" align="left">
	</div>
</div>
</body>
</html>