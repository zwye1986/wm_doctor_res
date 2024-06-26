<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:set var="min" value=".min"/>
<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<jsp:include page="/jsp/common/htmlhead.jsp">
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

<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/wxwsj/css/style.css'/>" />
<style type="text/css">
	.news_t_b  a{width: 262px;}
	.checkColumnId{background: #D2E8FF}
</style>
<script type="text/javascript">	
$(document).ready(function(){
	columnTitle("${param.columnId}");
	mouseFun();
	//loadInfoList("${param.columnId}");
});
	
function mouseFun(){
	$(".news_t_b a").mouseover(function(){
		$(this).css({color:"#0659b6"});
	}).mouseout(function(){
		$(this).css({color:"#333",});	
	})
}

function queryByColumnId(columnId){
	location.href="<s:url value='/inx/wxwsj/queryByColumnId?columnId='/>"+columnId;
}
	
function columnTitle(columnId){
	if(columnId=="LM02"){
		$(".news_t_top").text("下载分类"); 
	}else if(columnId=="LM03"){
		$(".news_t_top").text("通知公告分类"); 
	}else if(columnId=="LM04"){
		$(".news_t_top").text("政策法规分类"); 
	}else if(columnId=="LM05"){
		$(".news_t_top").text("名医分类"); 
	}
}

function loadInfoList(columnId, obj){
	$(".news_t_b a").each(function(){
		$(this).removeClass("checkColumnId");
	});
	$(obj).addClass("checkColumnId");
	var url = "<s:url value='/inx/wxwsj/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId='/>" + columnId;
	jboxLoad("content", url, true);
}

function toPage(page){
	var url = "<s:url value='/inx/wxwsj/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId="+$("#columnId").val()+"&currentPage='/>" + page;
	jboxLoad("content", url, true);
}

</script>
</head>

<body style="background:url(<s:url value='/'/>jsp/inx/wxwsj/images/bg.png) repeat-x;margin:0">
<%@include file="header.jsp" %>

<div class="banner">
	<div class="tip"><img src="<s:url value='/'/>jsp/inx/wxwsj/images/banner_news.png"></div>
    <div class="projection"></div>
</div>

<div class="com_news_box">
	<div class="news_t fl">
		<div class="news_t_top">新闻分类</div>
        <div class="news_t_b">
            <c:set value="${param.columnId}" var="defaultColumnId"/>
        	<c:forEach items="${classifyList}" var="parent" varStatus="status">
        	    <c:if test="${status.index==0}">
        	        <c:set value="${parent.columnId}" var="defaultColumnId"/>
        	    </c:if>
       		    <a href="javascript:void(0);" id="columnId_${status.index}" onclick="loadInfoList('${parent.columnId}', this);">&gt;${parent.columnName}</a><br/><br/>
        	</c:forEach>
        	<c:if test="${empty classifyList}">
        		<a>&nbsp;&nbsp;暂无分类</a><br/><br/>
        	</c:if>
        	<script>
	        	$(document).ready(function(){
	    			loadInfoList("${defaultColumnId}", $("#columnId_0"));
	    		});
        	</script>
        </div>
	</div>
	
	<div id="content">
		
	</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
