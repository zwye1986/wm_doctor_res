<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="min" value=".min"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jbox" value="true" />
</jsp:include>

<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/wxwsj/css/style.css'/>" />

<script type="text/javascript">
$(".next a").mouseover(function(){
	$(this).css({color:"red",textDecoration:"underline"});
}).mouseout(function(){
	$(this).css({color:"#215eb1",textDecoration:"none"});	
});
	

$(document).ready(function(){
	columnTitle("${param.columnId}");
	loadInfo("${param.infoFlow}");
});

function columnTitle(columnId){
	if(columnId.length > 4){
		columnId = columnId.substring(0,4);
	}
	if(columnId=="LM02"){
		$("#news_top").text("下载中心"); 
	}else if(columnId=="LM03"){
		$("#news_top").text("通知公告"); 
	}else if(columnId=="LM04"){
		$("#news_top").text("政策法规"); 
	}else if(columnId=="LM05"){
		$("#news_top").text("名医"); 
	}
}


function loadInfo(infoFlow){
	//load
	var url= "<s:url value='/inx/wxwsj/loadInfo?infoFlow='/>"+infoFlow;
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

</head>

<body style="background:url(<s:url value='/'/>jsp/inx/wxwsj/images/bg.png) repeat-x;margin:0;">

<c:forEach items="${infoList}" var="info">
	<input type="hidden" id="${info.infoFlow}" value="${info.infoFlow}" infoTitle="${pdfn:cutString(info.infoTitle,32,true,6)}"/>
</c:forEach>
    <div class="cn_ch_box">
		<div class="changtiao"><img src="<s:url value='/'/>jsp/inx/wxwsj/images/bg_new.png"></div>
		<s:url value='/'/>jsp/inx/wxwsj/images
		<div class="c">
    		&nbsp;&nbsp;<a id="news_top">新闻中心</a>
            </div>
    </div>
     
     <div class="news_text_box" id="news-icontent">
		
     </div>
     
     <div class="news_text_box_bottom">
     	<%-- <div class="share fl"><a>分享：</a>
        <a href=""><img src="<s:url value='/'/>jsp/inx/wxwsj/images/icon_01.png"></a>
        <a href=""><img src="<s:url value='/'/>jsp/inx/wxwsj/images/icon_02.png"></a>
        <a href=""><img src="<s:url value='/'/>jsp/inx/wxwsj/images/icon_03.png"></a>
        <a href=""><img src="<s:url value='/'/>jsp/inx/wxwsj/images/icon_04.png"></a>
        </div> --%>
        <div class="fr next" id="nextInfo">
        	<span style="color:#000;font-size:12px;">下一篇：</span>
        	<a href="javascript:void(0)" style="color:#215eb1;font-size:12px;"></a>
        </div>
     </div>
<%@include file="footer.jsp" %>
</body>
</html>
