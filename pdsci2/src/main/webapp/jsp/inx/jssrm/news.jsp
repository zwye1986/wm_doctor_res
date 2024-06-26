<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:set var="min" value=".min"/>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jbox" value="true" />
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/jssrm/css/font.css'/>" />

<script type="text/javascript">	
$(document).ready(function(){
	columnTitle("${param.columnId}");
	loadInfoList("${param.columnId}");
});

function queryByColumnId(columnId){
	location.href="<s:url value='/inx/jssrm/queryByColumnId?columnId='/>"+columnId;
}
	
function columnTitle(columnId){
	var title = "新闻中心";
	if(columnId=="LM02"){
		title = "下载中心"; 
	}else if(columnId=="LM03"){
		title = "通知公告"; 
	}else if(columnId=="LM04"){
		title = "政策法规"; 
	}
	$("#navUl li").removeClass("active");
	$("#nav_"+columnId).addClass("active");
	$("#new_title").text(title);
}

function loadInfoList(columnId){
	var currentPage = $("#currentPage").val();
	var url = "<s:url value='/inx/jssrm/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId=${param.columnId}&currentPage='/>" + currentPage;
	jboxLoad("content", url, true);
}

function toPage(page){
	$("#currentPage").val(page);
	loadInfoList("${param.columnId}");
}
</script>
</head>

<body>
<%@include file="header.jsp"%>

<div id="banner_sec">
	<div class="banner_sec"></div>
</div>
<div class="newChild">
	<input type="hidden" id="currentPage" name="currentPage" />
	<span class="new_title" id="new_title">新闻中心</span>
	<div id="content">
		<!-- 新闻列表 -->
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
