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
		eval($(this).attr("cusTrigger"));
	});
	if ("${param.liId}" != "") {
		$("#${param.liId}").addClass("tab_select");
	} else {
		$('li').first().addClass("tab_select");
	}
	$(".tab_select a").click();
	<c:if test="${!empty resBase}">
		<c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id }">
			$("#submitBtn").show();	
	    </c:if>
    </c:if>
});


function getInfo(baseInfoName,baseFlow){
	var resBase = $("#baseStatusId").val();
	if(!resBase){
		loadInfo(baseInfoName,baseFlow);
	}else{
		editInfo(baseInfoName,baseFlow);
	}
}

function editInfo(baseInfoName,speFlow){
    url="<s:url value='/jsres/speAdmin/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&editFlag=${GlobalConstant.FLAG_Y}&baseInfoName="+baseInfoName+"&speFlow="+speFlow+"&orgFlow=${orgFlow}";
    jboxLoad("hosContent", url, false);
}

function loadInfo(baseInfoName,speFlow){
	var url="<s:url value='/jsres/speAdmin/findAllBaseInfo'/>?onlyRead=${onlyRead}&viewFlag=${viewFlag}&baseInfoName="+baseInfoName
        +"&speFlow="+speFlow+"&orgFlow=${orgFlow}&isJoin=${isJoin}&editFlag=${editFlag}&sessionNumber=${sessionNumber}&ishos=${ishos}";
	jboxLoad("hosContent", url, false);
}

function trainSpeInfo(){
	var url="<s:url value='/jsres/base/trainSpeMain'/>?orgFlow=${param.baseFlow}";
	jboxLoad("hosContent", url, false);
}

function coopBaseInfo(){
	var url="<s:url value='/jsres/base/findCoopBase'/>";
	jboxLoad("hosContent", url, false);
}

function commuHospital(){
	jboxLoad("hosContent","<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>",false);
}
</script>
<div class="main_hd">
	<input type="hidden" id="baseStatusId" name="baseStatusId" value="${resBase.baseStatusId}"/>
	<form id="baseForm" style="position:relative;">
    <input type="hidden" name="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
    <input type="hidden" id="resBase" value="${resBase.orgFlow}"/>
	</form>
	<c:if test="${GlobalConstant.FLAG_Y != viewFlag}">
	    <h2 style="line-height: 30px;padding: 5px 10px;color: #000000; font-size: 15px; font-family: Microsoft Yahei; font-weight: 500;">专业信息维护</h2>
    </c:if>
    <div class="title_tab" id="toptab" style="margin-top: 5px;">
        <ul>
            <li id="tab"class="tab_select" cusTrigger="loadInfo('${GlobalConstant.DEPT_BASIC_INFO}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">基本信息</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.DEPARTMENT_HEAD}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">专业基地负责人情况</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.GUIDING_PHYSICIAN}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">指导医师情况</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.ROTATING_DEPARTMENTS}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">轮转科室</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.DIAG_DISEASE}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">诊疗疾病范围</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.EQUIPMENT_INSTRUMENTS}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">医疗设备仪器</a>
            </li>
            <li class="tab" cusTrigger="loadInfo('${GlobalConstant.TRAINING}','${param.speFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">培训情况</a>
            </li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="overflow: auto;"</c:if>>
    </div>
</div>
