<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>æ±èçä½é¢å»å¸è§èåå¹è®­ç®¡çå¹³å°</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
$(document).ready(function(){
	$("li").click(function(){
		$(".tab_select").addClass("tab");
		$(".tab_select").removeClass("tab_select");
		$(this).removeClass("tab");
		$(this).addClass("tab_select");
	});
	if ("${param.liId}" != "") {
		$("#${param.liId}").addClass("tab_select");
	} else {
		$('li').first().addClass("tab_select");
	}
	$(".tab_select a").click();
});

function basicInfo(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/basicInfo.jsp'/>?type=${param.type}",false);
}

function basicInfoChart(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/basicInfoChart.jsp'/>?type=${param.type}",false);
}

function basicAreaChart(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/basicAreaChart.jsp'/>?type=${param.type}",false);
}

function nationInfo(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/nationInfo.jsp'/>?type=${param.type}",false);
}

function nationInfoChart(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/nationInfoChart.jsp'/>?type=${param.type}",false);
}

function nationAreaChart(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/nationAreaChart.jsp'/>?type=${param.type}",false);
}

</script>
</head>
<body>
<div class="main_hd">
	<h2>ä¸ä¸åºå°ç»è®¡</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select"><a href="javascript:void(0);" onclick="basicInfo();">ççº§åºå°ä¿¡æ¯</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="basicInfoChart();">ççº§åºå°ä¿¡æ¯å¾è¡¨</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="basicAreaChart();">ççº§åºå°å°åºå¾è¡¨</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="nationInfo();">å½å®¶çº§åºå°ä¿¡æ¯</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="nationInfoChart();">å½å®¶çº§åºå°å¾è¡¨</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="nationAreaChart();">å½å®¶çº§å°åºå¾è¡¨</a></li>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div id="hosContent">
    </div>
</div>
</body>
</html>
