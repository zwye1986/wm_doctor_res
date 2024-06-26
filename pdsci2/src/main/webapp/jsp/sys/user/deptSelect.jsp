<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
<select class="<c:if test="${sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='local'}">validate[required] </c:if>xlselect" id="deptFlow" name="deptFlow" onchange="mulChange();">
--%>
<c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
	<select class="xlselect" id="deptFlow" name="deptFlow" onchange="mulChange(this.value);">
		<option value="">请选择</option>
		<c:forEach items="${sysDeptList}" var="dept">
			<option value="${dept.deptFlow}" <c:if test="${dept.deptFlow==param.deptFlow}">selected</c:if> >${dept.deptName}</option>
		</c:forEach>
	</select>
</c:if>
<c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
	<select class="qselect" id="deptFlow" name="deptFlow" onchange="mulChange(this.value);">
		<option value="">请选择</option>
		<c:forEach items="${sysDeptList}" var="dept">
			<option value="${dept.deptFlow}" <c:if test="${dept.deptFlow==param.deptFlow}">selected</c:if> >${dept.deptName}</option>
		</c:forEach>
	</select>
</c:if>
