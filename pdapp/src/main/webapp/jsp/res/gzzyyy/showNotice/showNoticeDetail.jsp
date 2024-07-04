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
		String recordFlow=request.getParameter("recordFlow");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		if(StringUtil.isBlank(title)){
			title="";
		}
		if(StringUtil.isBlank(content)){
			content="无住培指南信息！";
		}
//	if(StringUtil.isNotBlank(recordFlow)){
//		IResLiveTrainingBiz resLiveTrainingBiz=new ResLiveTrainingBizImpl();
//		ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
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
			var recordFlow="<%=recordFlow%>";
			var url ="<s:url value='/res/jswjw/showZPdetail?recordFlow='/>"+recordFlow;
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
					$("#content").html("无住培指南信息！");
					$("#title").html("住培指南详情");
				}
			});

		});
	</script>
	<style>
		#content span{
			font-size:45px !important;
			font-family: Microsoft YaHei !important;
		}
	</style>
</head>
<body>

<div width="100%" height="100%" align="center" style="">
	<div height="10%" width="100%" align="center" style="font-size:30px;">
		<h2 id="title">
			<%--住培指南详情<%=title%>--%>
		</h2>
	</div>
	<div height="90%" width="100%"id="content" align="left" style="font-size:45px">
		<%--<%=content%>--%>
	</div>
</div>
</body>
</html>