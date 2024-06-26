<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_qrcode" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="true" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
<script>
	function toPage(page) {
		if (page) {
			$("#currentPage").val(page);
		}
		search();
	}

	function search(){
		$("#searchForm").submit();
	}
</script>
<div>

	<form id="searchForm" action="<s:url value='/res/qingpuKq/signinList'/>">
	<input type="hidden" name="currentPage" id="currentPage">
	<div class="" style="width: 100%;">
		<div class="inputDiv">
			<label class="qlable">签到时间：</label>
			<input type="text" class="qtext" name="signinDate" value="${param.signinDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</div>
			<div class="qcheckboxDiv" style="text-align: left;margin-left: 0px;margin-top: 6px;">
				<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
			</div>
		</div>
		</form>
</div>
<table class="xllist" style="margin-top: 10px;">
	<tr>
	<th style="width: 100px;">姓名</th>
	<th style="width: 200px;">签到时间</th>
	<th style="width: 200px;">轮转科室</th>
	<th style="width: 250px;">轮转时间</th>
	<th style="width: 100px;">二维码提供人</th>
		<c:forEach items="${list}" var="bean" varStatus="status">
			<tr>
				<td>${bean.doctorName}</td>
				<td>${bean.signinDate}</td>
				<td>${bean.schDeptName}</td>
				<td>${bean.schStartDate}~${bean.schEndDate}</td>
				<td>${bean.teacherUserName}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr><td colspan="99">暂无签到信息</td></tr>
		</c:if>
</table>
<p>
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</p>
		