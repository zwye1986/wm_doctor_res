<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/hbzy/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
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
		$(".tab_select").click();
	}
});

function getDoctorRecruit(recruitFlow, doctorFlow){
	var url = "<s:url value='/hbzy/doctor/getDoctorRecruit?change=${param.change}&openType=open&recruitFlow='/>"+recruitFlow +"&doctorFlow=" + doctorFlow;
	jboxLoad("contentDiv", url, true);
}

function doctorInfo(doctorFlow){
	var url = "<s:url value='/hbzy/doctor/doctorInfo'/>?openType=${param.openType}&viewFlag=${GlobalConstant.FLAG_Y}&userFlow=" + doctorFlow+"&hideApprove=${GlobalConstant.FLAG_Y}";
	jboxLoad("contentDiv", url, true);
}
function backTrain(userFlow){
	var url = "<s:url value='/hbzy/doctor/backTrainInfo?seeFlag=Y&userFlow='/>"+userFlow;
	jboxLoad("contentDiv", url, true);
}
function delay(userFlow,jointOrg){
	var url = "<s:url value='/hbzy/doctor/delay?seeFlag=Y&userFlow='/>"+userFlow+"&jointOrg="+jointOrg;
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
            <c:if test="${not empty recList }">
         	   <li class="tab"  onclick="backTrain('${param.doctorFlow}');"><a>退培信息</a></li>
            </c:if>
             <c:if test="${not empty delayList }">
         	   <li class="tab"  onclick="delay('${param.doctorFlow}','${param.jointOrg}');"><a>延期信息</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="contentDiv">
   
    </div>
</div>
</body>
</html>
