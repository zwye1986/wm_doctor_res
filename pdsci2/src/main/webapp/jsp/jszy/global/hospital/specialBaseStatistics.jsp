<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jszy/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
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
function baseInfo(type){
	var url="<s:url value='/jszy/base/baseInfo'/>?type="+type;
	jboxLoad("hosContent",url,false);
}
</script>
</head>
<body>
<div class="main_hd">
	<h2>基地清单</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <%--<li class="tab_select" onclick="baseInfo();"style="cursor: pointer;"><a>全部</a></li>--%>
            <li class="tab" onclick="baseInfo('${orgLevelEnumCountryOrg.id}');"style="cursor: pointer;"><a >国家基地</a></li>
            <%--<li class="tab" onclick="baseInfo('${orgLevelEnumProvinceOrg.id}');"style="cursor: pointer;"><a>省级基地</a></li>--%>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div id="hosContent">
    </div>
</div>
</body>
</html>
