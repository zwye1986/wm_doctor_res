<%@ page import="com.pinde.core.util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery.SuperSlide.2.1.1.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<%
		String recordFlow=request.getParameter("recordFlow");
	%>
	<script>
		$(document).ready(function(){
			var recordFlow="<%=recordFlow%>";
			var url ="<s:url value='/res/zsey/showNew?infoFlow='/>"+recordFlow;
			$.ajax({
				type : "get",
				url : url,
				cache : false,
				success : function(resp) {
					var obj=eval("("+resp+")");
					var info=obj.info;
					if(info)
					{

						$("#content").html(info.infoContent);
						$("#title").html(info.infoTitle);
					}else{
						$("#content").html("无住培动态信息！");
						$("#title").html("住培动态详情");
					}
					var a=$("a");
					$.each(a , function(i , n){
						var fileName=$(this).html();
						$(this).after("<font color='blue' style=' font-size: 23px;line-height: 60px;'>"+fileName+"(附件请至电脑端下载查看！)</font>");
						$(this).remove();
					});

				},
				error : function() {
					$("#content").html("无住培动态信息！");
					$("#title").html("住培动态详情");
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