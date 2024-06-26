<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<h1 style="margin: 5px;margin-left: 20px;">学员名册</h1>
<table class="xllist" >
	<tr>
		<th width="100">序号</th>
		<th width="200">学号</th>
		<th width="150">姓名</th>
		<th width="400">行政班级</th>
		<th width="230">联系方式</th>
		<th width="400">培养单位</th>
	</tr>
	<c:if test="${not empty currentPage }">
		<c:set var="prefix" value="${(currentPage-1)*pageSize}"/>
	</c:if>
	<c:forEach items="${studentCourseExt}" var="studentCourseExt" varStatus="status">
		<tr>
			<td>${prefix + status.count}</td>
			<td>${studentCourseExt.eduUser.sid}</td>
			<td>${studentCourseExt.sysUser.userName}</td>
			<td>${studentCourseExt.eduUser.className}</td>
			<td>${studentCourseExt.sysUser.userPhone}</td>
			<td>${studentCourseExt.resDoctor.orgName}</td>
		</tr>
	</c:forEach>
	<c:if test="${empty studentCourseExt }">
		<tr>
			<td colspan="10">无记录!</td>
		</tr>
	</c:if>
</table>

<div>
    <c:set var="pageView" value="${pdfn:getPageView(studentCourseExt)}" scope="request"></c:set>
	<pd:pagination toPage="toPage"/>	 
</div>