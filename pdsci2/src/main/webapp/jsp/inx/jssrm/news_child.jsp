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
	$("#navUl li").removeClass("active");
	$("#nav_${param.columnId}").addClass("active");
	$("img").attr("onerror","this.src='<s:url value="/jsp/inx/jssrm/images/load.png"/>'");
	loadInfo("${param.infoFlow}");
});

function loadInfo(infoFlow){
	//load
	var url= "<s:url value='/inx/jssrm/loadInfo?infoFlow='/>"+infoFlow;
	jboxLoad("news-icontent", url, true);
	//nextInfo
	var $nextInfo = $("#"+infoFlow).next();
	var nextInfoFlow = $nextInfo.val();
	if(nextInfoFlow!=""){
		var nextInfoTitle = $nextInfo.attr("infoTitle");
		$("#nextInfo").show();
		$a = $("#nextInfo").find("a");
		$a.text(nextInfoTitle);
		$a.attr("onclick","loadInfo('"+nextInfoFlow+"')");
	}else{
		$("#nextInfo").hide();
	}
}
</script>
<style>
	body,html {height:100%;padding:0px;margin:0px;}
	#b_container {width:100%;min-height:100%;position:relative;}
	.fenxiang{padding-bottom:170px;}
	#b_footer{height:140px;width:100%;position:absolute;bottom:0;}
</style>
</head>
<body>
<div id="b_container">
	<%@include file="header.jsp"%>
	<c:forEach items="${infoList}" var="info">
		<input type="hidden" id="${info.infoFlow}" value="${info.infoFlow}" infoTitle="${pdfn:cutString(info.infoTitle,32,true,6)}"/>
	</c:forEach>

	<div class="newChildren">
		<div class="news-icontent" id="news-icontent">

		</div>

		<div class="fenxiang">
			<div class="fright fr" id="nextInfo">
				下一条：<a href="javascript:void(0)"></a>
			</div>
		</div>
	</div>
	<div id="b_footer">
		<%@include file="footer.jsp" %>
	</div>
</div>
</body>
</html>
