<c:set var="rotationFlow" value="${doctor.rotationFlow}"/>
<c:if test="${empty rotationFlow}">
	<c:set var="rotationFlow" value="${result.rotationFlow}"/>
</c:if>
<c:set var="orgFlow" value="${doctor.orgFlow}"/>
<c:if test="${empty orgFlow}">
	<c:set var="orgFlow" value="${schDept.orgFlow}"/>
	<c:if test="${empty orgFlow}">
		<c:set var="orgFlow" value="${result.orgFlow}"/>
		<c:if test="${empty orgFlow}">
			<c:set var="orgFlow" value="${currRegProcess.orgFlow}"/>
		</c:if>
	</c:if>
</c:if>
<c:set var="schDeptFlow" value="${schDept.schDeptFlow}"/>
<c:if test="${empty schDeptFlow}">
	<c:set var="schDeptFlow" value="${result.schDeptFlow}"/>
	<c:if test="${empty schDeptFlow}">
		<c:set var="schDeptFlow" value="${currRegProcess.schDeptFlow}"/>
	</c:if>
</c:if>
<c:set var="deptFormId" value="default"/>
<c:if test="${!empty rotationFlow && !empty orgFlow && !empty schDeptFlow}">
	<c:set var="deptFormId" value="form_dept_cfg_${rotationFlow}${orgFlow}${schDeptFlow}"/>
	<c:set var="deptFormId" value="${sysCfgMap[deptFormId]}"/>
	<c:if test="${empty deptFormId}">
		<c:set var="deptFormId" value="default"/>
	</c:if>
</c:if>

<jsp:include page="/jsp/${jspPath}_${deptFormId}.jsp" flush="true"></jsp:include>