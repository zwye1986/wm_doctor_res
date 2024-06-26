<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
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
	if ("${param.liId}" != "") {
		$("#${param.liId}").addClass("tab_select");
	} else {
		$('li').first().addClass("tab_select");
	}
    console.log($(".tab_select:first a"));
	$("li:first a").click();
});

function getDoctorRecruit(recruitFlow, doctorFlow, backFlag){
    <c:if test="${GlobalConstant.FLAG_Y == zlFlag }">
        var url = "<s:url value='/hbzy/doctor/getDoctorRecruitInfo?info=${param.info}&openType=open&recruitFlow='/>"+recruitFlow+"&doctorFlow="+doctorFlow+"&backFlag="+backFlag+"&zlFlag=${zlFlag}";
    </c:if>
    <c:if test="${GlobalConstant.FLAG_Y != zlFlag }">
        var url = "<s:url value='/hbzy/doctor/getDoctorRecruit?info=${param.info}&openType=open&recruitFlow='/>"+recruitFlow+"&doctorFlow="+doctorFlow+"&backFlag="+backFlag;
    </c:if>
	jboxLoad("doctorContent", url, false);
}
function doctorInfo(doctorFlow){
	var url = "<s:url value='/hbzy/doctor/doctorInfo'/>?viewFlag=${GlobalConstant.FLAG_Y}&openType=open&userFlow=" + doctorFlow+"&hideApprove=${param.hideApprove}";
	jboxLoad("doctorContent", url, true);
	jboxEndLoading();
}
function trainInfo(){
	jboxLoad("doctorContent","<s:url value='/jsp/hbzy/province/doctor/trainInfo.jsp'/>?type=${param.type}",false);
}
function getInfo(recruitFlow){
	var url = "<s:url value='/hbzy/doctor/getDocRecruitForReduction'/>?studyFlag=${GlobalConstant.FLAG_Y}&openType=open&recruitFlow="+recruitFlow;
	jboxLoad("doctorContent", url, false);
}
function backTrain(userFlow){
	var url = "<s:url value='/hbzy/doctor/backTrainInfo?seeFlag=Y&userFlow='/>"+userFlow;
	jboxLoad("doctorContent", url, false);
}
function delay(userFlow){
	var url = "<s:url value='/hbzy/doctor/delay?seeFlag=Y&userFlow='/>"+userFlow;
	jboxLoad("doctorContent", url, false);
}
</script>
</head>
<body id="indexBody" style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
<div class="main_hd" >
    <div class="title_tab" style="margin-top: 0;">
        <ul>
        <c:if test="${GlobalConstant.FLAG_Y != studyFlag }">
            <li><a onclick="doctorInfo('${param.doctorFlow}');">基本信息</a></li>
             <c:forEach items="${recruitList}" var="recruit" varStatus="status">
             	<c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq recruit.auditStatusId }">
	           		 <li <c:if test="${param.recruitFlow ==recruit.recruitFlow }">class="tab_select"</c:if>id="${recruit.recruitFlow}" onclick="getDoctorRecruit('${recruit.recruitFlow}','${param.doctorFlow}','${status.last}');"><a>${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
            	</c:if>
            </c:forEach>
            <c:if test="${ (not empty recList) and  GlobalConstant.FLAG_Y != zlFlag }">
         	   <li class="tab"  onclick="backTrain('${param.doctorFlow}');"><a>退培信息</a></li>
       	    </c:if>
       	     <c:if test="${(not empty delayList)  and  GlobalConstant.FLAG_Y != zlFlag  }">
         	   <li class="tab"  onclick="delay('${param.doctorFlow}');"><a>延期信息</a></li>
            </c:if>
         </c:if>
         <c:if test="${GlobalConstant.FLAG_Y eq studyFlag }">
			<li <c:if test="${param.recruitFlow ==recruit.recruitFlow }">class="tab_select"</c:if> id="${recruit.recruitFlow}"><a onclick="getInfo('${recruit.recruitFlow}');">${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
         </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
   
    </div>
</div>
</body>
</html>
