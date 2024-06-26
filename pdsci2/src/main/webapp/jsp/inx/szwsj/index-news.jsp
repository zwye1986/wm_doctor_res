<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/basic.css'/>" /> 
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/szwsj/css/font.css'/>" /> 
<style type="text/css">
body,html{
	overflow:auto;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		columnTitle("${param.columnId}");
		loadInfoList("${param.columnId}");
	});
	
	function columnTitle(columnId){
		if(columnId=="LM02"){
			//$(".banner").children().attr("src","<s:url value='/'/>jsp/inx/szwsj/images/portal/down.png");
			$(".banner").children().attr({src: "<s:url value='/'/>jsp/inx/szwsj/images/portal/down.png", width: ""});
			$(".news_classify h1").text("下载中心分类");
			$(".position").children().next().text("下载中心"); 
			$(".news_rank h1").text("下载排行");
		}
		if(columnId=="LM03"){
			$(".banner").children().attr({src: "<s:url value='/'/>jsp/inx/szwsj/images/portal/banner.jpg", width: ""});
			$(".news_classify h1").text("通知公告分类");
			$(".position").children().next().text("通知公告"); 
			$(".news_rank h1").text("通知公告排行");
		}
		if(columnId=="LM04"){
			$(".banner").children().attr({src: "<s:url value='/'/>jsp/inx/szwsj/images/portal/banner.jpg", width: ""});
			$(".news_classify h1").text("政策法规分类");
			$(".position").children().next().text("政策法规"); 
			$(".news_rank h1").text("政策法规排行");
		}
		if(columnId=="LM05"){
			$(".banner").children().attr({src: "<s:url value='/'/>jsp/inx/szwsj/images/portal/banner.jpg", width: ""});
			$(".news_classify h1").text("继续教育分类");
			$(".position").children().next().text("继续教育"); 
			$(".news_rank h1").text("继续教育排行");
		}
	}
	
	
	function queryByColumnId(columnId){
		location.href="<s:url value='/inx/szwsj/queryByColumnId?columnId='/>"+columnId;
	}
	
	function doSearch() {
		$("#searchForm").submit();
	}
	
	function toPage(page){
		var url = "<s:url value='/inx/szwsj/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId=${param.columnId}&currentPage='/>" + page;
		jboxLoad("inxInfoList", url, true);
	}
	
	function loadInfoList(columnId, obj){
		$(".news_ul li").each(function(){
			$(this).css("background","#e9f8ff");
		});
		$(obj).parent().parent().css("background","white");
		var url = "<s:url value='/inx/szwsj/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId='/>" + columnId;
		jboxLoad("inxInfoList", url, true);
	}
	
</script>
</head>
<body>
<div id="menhu">
	<%@include file="header.jsp" %>
	<div id="menhu_con">
		<div class="location">
		 <div class="position">您当前所在位置：<a href="<s:url value='/'/>">首页</a>&gt;<a href="#">新闻中心</a></div>
	    	 <form id="searchForm" action="<s:url value='/inx/szwsj/doSearch'/>" method="POST">
		    	 <div id="search">
		    	 	<input name="search" type="text" class="xltext" value="${param.search}"/>
					 <img src="<s:url value='/'/>jsp/inx/szwsj/images/portal/search.png" onclick="doSearch();"/>
		    	 </div>	
			 </form>
	    </div>
	    
   	    <div id="inxInfoList">
		
	    </div>
	    
   	    <div class="news_classify" style="margin-right: 0px;">
	        <h1>新闻分类</h1>
	        <ul class="news_ul">
	        	<c:forEach items="${classifyList}" var="parent">
	        		<li>
	        		<b>
	        		    <a href="javascript:void(0);" onclick="loadInfoList('${parent.columnId}', this);">&gt;&gt;${parent.columnName}</a><br/><br/>
	        		</b>
	        		</li>
	        	</c:forEach>
	        </ul>
	    </div>
	    
	    <div class="news_rank" style="clear:left;" style="margin-top: 0px;" >
	        <h1>新闻排行</h1>
	        <ul class="rank">
	            <c:forEach items="${infoList}" var="info" varStatus="status">
			 	 <li class="lev_${status.count}">
				 <a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow }'/>">
					 ${pdfn:cutString(info.infoTitle,12,true,6)}
				 </a>
			 	 </li>
	    		</c:forEach>
	        </ul>
	    </div>
	 </div>

	<%@include file="footer.jsp" %>
</div>
</body>
</html>