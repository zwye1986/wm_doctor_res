<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
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
	
	//加载基本信息或培训信息
	var recruitFlow = "${param.recruitFlow}";
	if(recruitFlow != ""){
		$("#li_"+ recruitFlow).click();
	}else{
		$(".tab_select a").click();
	}
});

function getDoctorRecruit(recruitFlow, doctorFlow){
	var url = "<s:url value='/jszy/doctor/getDoctorRecruit?openType=open&auditFlag=${GlobalConstant.FLAG_Y}&recruitFlow='/>"+recruitFlow + "&doctorFlow=" + doctorFlow;
	jboxLoad("contentDiv", url, true);
}

function doctorInfo(doctorFlow){
	var url = "<s:url value='/jszy/doctor/doctorInfo'/>?openType=${param.openType}&viewFlag=${GlobalConstant.FLAG_Y}&userFlow=" + doctorFlow+"&hideApprove=${GlobalConstant.FLAG_Y}";
	jboxLoad("contentDiv", url, true);
}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
<div class="main_hd">
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select" onclick="doctorInfo('${param.doctorFlow}');"><a>基本信息</a></li>
            <c:forEach items="${recruitsList}" var="recruit">
            	<li class="tab" id="li_${recruit.recruitFlow}" onclick="getDoctorRecruit('${recruit.recruitFlow}','${param.doctorFlow}');"><a>${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="contentDiv">
   
    </div>
</div>
</body>
</html>
