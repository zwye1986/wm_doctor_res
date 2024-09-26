<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
function submitInfo(){
	jboxConfirm("提交后不可修改！请确认修改的信息是否已保存，否则提交的仍是保存前的信息，确认提交?",function(){
		jboxPost("<s:url value='/jsres/base/submitBaseInfo'/>" , $("#baseForm").serialize() , function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
				setTimeout(function(){
					$("#submitBtn").hide();
					$(".tab_select").children().click();
				},1000);
			}
		} , null , true);
	});
}

function getInfo(baseInfoName,baseFlow){
	var resBase = $("#baseStatusId").val();
	if(!resBase){
		loadInfo(baseInfoName,baseFlow);
	}else{
		editInfo(baseInfoName,baseFlow);
	}
}

function editInfo(baseInfoName,deptFlow){
    var url="<s:url value='/jsres/kzr/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&editFlag=${GlobalConstant.FLAG_Y}&baseInfoName="+baseInfoName+"&deptFlow="+deptFlow+"&orgFlow=${orgFlow}&isJoin=${isJoin}"+"&speFlow=${speFlow}&isglobal=${isglobal}";
    jboxLoad("hosContent", url, false);

}

function loadInfo(baseInfoName,deptFlow){
    if(!deptFlow){
        deptFlow = $("#deptFlow").find("option:selected").val();
    }
	var url="<s:url value='/jsres/kzr/findAllBaseInfo'/>?onlyRead=${onlyRead}&viewFlag=${viewFlag}&baseInfoName="+baseInfoName
        +"&deptFlow="+deptFlow+"&orgFlow=${orgFlow}&isJoin=${isJoin}"+"&speFlow=${speFlow}&isglobal=${isglobal}";
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

// 科室切换事件
function showTab(deptFlow){
    var baseInfoName = $(".tab_select").attr("baseInfoName");
    loadInfo(baseInfoName, deptFlow);
}
</script>
<div class="main_hd">
	<input type="hidden" id="baseStatusId" name="baseStatusId" value="${resBase.baseStatusId}"/>
	<form id="baseForm"style="position:relative;">
    <input type="hidden" name="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
    <input type="hidden" id="resBase" value="${resBase.orgFlow}"/>
	</form>
<%--	<c:if test="${GlobalConstant.FLAG_Y != viewFlag}">--%>
<%--	    <h2 style="line-height: 30px;padding: 5px 10px;color: #000000; font-size: 15px; font-family: Microsoft Yahei; font-weight: 500;">--%>
<%--			科室信息维护--%>
<%--	    </h2>--%>
<%--    </c:if>--%>
    <div class="title_tab" id="toptab" style="margin-top: 5px;">
        <ul>
            <li id="tab" class="tab_select" baseInfoName="${GlobalConstant.DEPT_BASIC_INFO}" cusTrigger="loadInfo('${GlobalConstant.DEPT_BASIC_INFO}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">科室基本信息</a>
            </li>
            <li class="tab" baseInfoName="${GlobalConstant.DIAG_DISEASE}" cusTrigger="loadInfo('${GlobalConstant.DIAG_DISEASE}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">诊疗疾病范围</a>
            </li>
            <li class="tab" baseInfoName="${GlobalConstant.EQUIPMENT_INSTRUMENTS}" cusTrigger="loadInfo('${GlobalConstant.EQUIPMENT_INSTRUMENTS}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">医疗设备仪器</a>
            </li>
            <li class="tab" baseInfoName="${GlobalConstant.GUIDING_PHYSICIAN}" cusTrigger="loadInfo('${GlobalConstant.GUIDING_PHYSICIAN}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">指导医师情况</a>
            </li>
            <li class="tab" baseInfoName="${GlobalConstant.DEPARTMENT_HEAD}" cusTrigger="loadInfo('${GlobalConstant.DEPARTMENT_HEAD}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">科室负责人信息</a>
            </li>
            <li class="tab" baseInfoName="${GlobalConstant.TRAINING}" cusTrigger="loadInfo('${GlobalConstant.TRAINING}','${param.deptFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">培训情况</a>
            </li>
        </ul>
        <div style="margin-right: 5px; float: right;<c:if test="${empty userDeptList or userDeptList.size() <= 1}">display: none;</c:if>">
            <span style="color: #000000;font: 14px 'Microsoft Yahei';font-weight: 400;">切换科室：</span>
            <select id="deptFlow" class="select" style="color: #000000;font: 14px 'Microsoft Yahei';font-weight: 400;" onchange="showTab(this.value);">
                <c:forEach var="dept" items="${userDeptList}">
                    <option style="text-align: center;" value="${dept.deptFlow}" ${deptFlow == dept.deptFlow ? 'selected' : ''}>${dept.deptName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="height: 600px;overflow: auto;"</c:if>>
    </div>
</div>
