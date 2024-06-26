<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
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

function getDoctorRecruit(recruitFlow, doctorFlow, backFlag){
	var url = "<s:url value='/jsres/recruitDoctorInfo/getDoctorRecruit?openType=open&recruitFlow='/>"+recruitFlow+"&doctorFlow="+doctorFlow;
	jboxLoad("doctorContent", url, false);
}
function doctorInfo(doctorFlow,recruitFlow){
	var url = "<s:url value='/jsres/recruitDoctorInfo/doctorInfo'/>?sessionNumber=${param.sessionNumber}&userFlow=" + doctorFlow+"&recruitFlow="+recruitFlow;
	jboxLoad("doctorContent", url, true);
	jboxEndLoading();
}
</script>
</head>
<body id="indexBody" style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
<div class="main_hd" >
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li><a onclick="doctorInfo('${param.doctorFlow}','${param.recruitFlow}');">基本信息</a></li>
             <c:forEach items="${recruitList}" var="recruit" varStatus="status">
				 <li <c:if test="${param.recruitFlow ==recruit.recruitFlow }">class="tab_select"</c:if>id="${recruit.recruitFlow}" onclick="getDoctorRecruit('${recruit.recruitFlow}','${param.doctorFlow}','${status.last}');"><a>${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
   
    </div>
</div>
</body>
</html>
