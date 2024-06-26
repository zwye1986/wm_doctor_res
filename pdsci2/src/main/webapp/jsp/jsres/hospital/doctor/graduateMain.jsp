<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
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

function doctorInfo(){
	jboxLoad("doctorContent","<s:url value='/jsp/jsres/province/doctor/doctorInfo.jsp'/>?type=${param.type}",false);
}

function trainInfo(){
	jboxLoad("doctorContent","<s:url value='/jsp/jsres/province/doctor/trainInfo.jsp'/>?type=${param.type}",false);
}

function onlineRegistInfo(){
	jboxLoad("doctorContent","<s:url value='/jsp/jsres/hospital/doctor/onlineRegistInfo.jsp'/>?type=${param.type}",false);
}

</script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select"><a href="javascript:void(0);" onclick="doctorInfo();">基本信息</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="trainInfo();">西医</a></li>
            <li class="tab"><a href="javascript:void(0);" onclick="onlineRegistInfo();">报名材料</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
    </div>
</div>
</body>
</html>
