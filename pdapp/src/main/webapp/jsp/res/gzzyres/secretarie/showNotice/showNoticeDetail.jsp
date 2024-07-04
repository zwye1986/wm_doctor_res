<%@ page import="com.pinde.core.util.StringUtil" %>
<%@ page import="com.pinde.sci.model.mo.ResTarinNotice" %>
<%@ page import="com.pinde.res.biz.jswjw.IResLiveTrainingBiz" %>
<%@ page import="com.pinde.res.biz.jswjw.impl.ResLiveTrainingBizImpl" %>
<%@ page import="com.pinde.sci.dao.base.ResTarinNoticeMapper" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery.SuperSlide.2.1.1.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<%
		String readSecDocumentId=request.getParameter("readSecDocumentId");
	%>
	<script>
		$(document).ready(function(){
			var readSecDocumentId="<%=readSecDocumentId%>";
			var url ="<s:url value='/res/gzzyres/secretarie/showZPdetail?readSecDocumentId='/>"+readSecDocumentId;
			$.ajax({
				type : "get",
				url : url,
				cache : false,
				success : function(resp) {
					var obj=eval("("+resp+")");
					$("#content").html(obj.DocumentContent);
					$("#title").html(obj.Title);
				},
				error : function() {
					$("#content").html("无入科教育信息！");
					$("#title").html("入科教育详情");
				}
			});

		});
	</script>
</head>
<body>

<div width="100%" height="100%" align="center" style="">
	<div height="10%" width="100%" align="center">
		<h2 id="title">
			<%--住培指南详情<%=title%>--%>
		</h2>
	</div>
	<div height="auto" width="auto"id="content" align="left" style="font-size: 30px">
		<%--<%=content%>--%>
	</div>
</div>
</body>
</html>