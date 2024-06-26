<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
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

function basicGeneralDoc(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/basicGeneralDoc.jsp'/>?type=${param.type}",false);
}

function nationGeneralDoc(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/province/statistics/nationGeneralDoc.jsp'/>?type=${param.type}",false);
}

</script>
</head>
<body>
<div class="main_hd">
	<h2>全科在培医师统计</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select"><a href="javascript:void(0);" onclick="basicGeneralDoc();">省级全科在培医师统计</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="nationGeneralDoc();">国家全科在培医师统计</a></li>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div id="hosContent">
    </div>
</div>
</body>
</html>
