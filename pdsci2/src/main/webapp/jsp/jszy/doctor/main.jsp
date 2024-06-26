<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jszy/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
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
		$("li:first").click();
	}
	
	//无记录 or 有未审核通过的
	<%--<c:if test="${empty recruitList or empty unPassed}"> 20160714本次修改无记录时才可以新增--%>

	<c:if test="${empty recruitList or recruitList.get(recruitList.size()-1).doctorStatusId eq '21'}">
		$("#addRecruitBtn").show();
	</c:if>
});

function doctorInfo(userFlow){
	var url = "<s:url value='/jszy/doctor/doctorInfo?userFlow='/>" + userFlow;
	jboxLoad("doctorContent", url, false);
}

function editDoctorRecruit(recruitFlow){
	var doctorFlow = $("#doctorFlow").val();
	if(doctorFlow == ""){
		jboxTip("请先完善基本信息！");
		return false;
	}
	var url = "<s:url value='/jszy/doctor/editDoctorRecruit'/>?openType=open&recruitFlow="+recruitFlow;
	var title = "新增";
	if(recruitFlow){
		title = "修改";
	}
	jboxOpen(url, title + "培训记录", 900, 500);
}

function getDoctorRecruit(recruitFlow, doctorFlow){
	var url = "<s:url value='/jszy/doctor/getDoctorRecruit?recruitFlow='/>"+recruitFlow + "&doctorFlow=" + doctorFlow;
	jboxLoad("doctorContent", url, false);
}
function backTrain(userFlow){
	var url = "<s:url value='/jszy/doctor/backTrainInfo?currentPage=1&seeFlag=Y&userFlow='/>"+userFlow;
	jboxLoad("doctorContent", url, false);
}
function delay(userFlow){
	var url = "<s:url value='/jszy/doctor/delay?seeFlag=Y&userFlow='/>"+userFlow;
	jboxLoad("doctorContent", url, false);
}
</script>
<div class="main_hd">
	<input id="doctorFlow" style="display: none;" value="${doctorFlow}"/>
    <h2>培训信息&#12288;&#12288;
     <c:if test="${empty sysCfgMap['jsres_is_train'] or sysCfgMap['jsres_is_train'] eq 'Y' }">
		 <c:if test="${empty recruitList or recruitList.get(recruitList.size()-1).doctorStatusId eq '21'}">
       			<input class="btn_brown" type="button" id="addRecruitBtn" onclick="editDoctorRecruit('');" value="添加培训记录" style="display: none;">
		 </c:if>
     </c:if></h2>
    <div class="title_tab" id="toptab">
        <ul>
            <%--<li class="tab_select"  onclick="doctorInfo('${sessionScope.currUser.userFlow}');"><a>基本信息</a></li>--%>
            <c:forEach items="${recruitList}" var="recruit">
            	<input type="hidden" class="${recruit.catSpeId}" value="${recruit.speId}"/>
	            <li class="tab" id="li_${recruit.recruitFlow}" onclick="getDoctorRecruit('${recruit.recruitFlow}','${sessionScope.currUser.userFlow}');"><a>${recruit.catSpeName}（${empty recruit.speName?'--':recruit.speName}）</a></li>
            </c:forEach>
            <c:if test="${not empty resRecList }">
         	   <li class="tab"  onclick="backTrain('${sessionScope.currUser.userFlow}');"><a>退培信息</a></li>
            </c:if>
            <c:if test="${not empty delayList }">
         	   <li class="tab"  onclick="delay('${sessionScope.currUser.userFlow}');"><a>延期信息</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent" >
    </div>
</div>
