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
		String infoFlow=request.getParameter("infoFlow");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		if(StringUtil.isBlank(title)){
			title="";
		}
		if(StringUtil.isBlank(content)){
			content="无住培指南信息！";
		}
//	if(StringUtil.isNotBlank(infoFlow)){
//		IResLiveTrainingBiz resLiveTrainingBiz=new ResLiveTrainingBizImpl();
//		ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(infoFlow);
//		ResTarinNoticeMapper resTarinNoticeMapper=ResTarinNoticeMapper
//		if(tarinNotices!=null)
//		{
//			title="["+tarinNotices.getResNoticeTitle()+"]";
//			content=tarinNotices.getResNoticeContent();
//		}else{
//			content="无住培指南信息！";
//		}
//	}else{
//		content="无住培指南信息！";
//	}
	%>
	<script>
		$(document).ready(function(){
			var infoFlow="<%=infoFlow%>";
			var url ="<s:url value='/res/osca/student/showNoticeDetail?infoFlow='/>"+infoFlow;
			$.ajax({
				type : "get",
				url : url,
				cache : false,
				success : function(resp) {
					var obj=eval("("+resp+")");
					$("#content").html(obj.content);
					$("#title").html(obj.title);
					var a=$("a");
					$.each(a , function(i , n){
						var fileName=$(this).html();
						$(this).after("<font color='blue' style=' font-size: 23px;line-height: 60px;'>"+fileName+"(附件请至电脑端下载查看！)</font>");
						$(this).remove();
					});

				},
				error : function() {
					$("#content").html("无公告信息！");
					$("#title").html("公告详情");
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
	<div height="90%" width="100%"id="content" align="left">
		<%--<%=content%>--%>
	</div>
</div>
</body>
</html>