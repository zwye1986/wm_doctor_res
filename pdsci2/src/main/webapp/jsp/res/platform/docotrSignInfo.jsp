<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<script type="text/javascript">
$(document).ready(function(){
});
function search(){
	$("#searchForm").submit();
}
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	search();
}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/platform/docotrSignInfo"/>" method="post">
				<input id="currentPage" name="currentPage" type="hidden" value="">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">机&#12288;&#12288;构：</label>
						<input type="text" class="qtext" name="orgName" value="${param.orgName}"/>
					</div>
					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
				</div>
			</form>
		<c:set value="sessionNumber" var="sessionNumber"></c:set>
		<c:set value="orgName" var="orgName"></c:set>
		<c:set value="userName" var="userName"></c:set>
		<c:set value="speName" var="speName"></c:set>
		<c:set value="maxDate" var="maxDate"></c:set>
		<c:set value="sigininCount" var="sigininCount"></c:set>
		<div class="resultDiv">
			<table class="basic" width="100%">
				<colgroup>
					<col width="20%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="20%"/>
					<col width="15%"/>
				</colgroup>
				<tr style="font-weight: bold;">
					<th style="text-align: center;padding: 0px;">机构</th>
					<th style="text-align: center;padding: 0px;">姓名</th>
					<th style="text-align: center;padding: 0px;">年级</th>
					<th style="text-align: center;padding: 0px;">专业</th>
					<th style="text-align: center;padding: 0px;">最近签到时间</th>
					<th style="text-align: center;padding: 0px;">签到次数</th>
				</tr>
		<c:forEach items="${signInfoMaps}" var="map">
			<tr>
				<td style="text-align: center;padding: 0px;">${map[orgName]}</td>
				<td style="text-align: center;padding: 0px;">${map[userName]}</td>
				<td style="text-align: center;padding: 0px;">${map[sessionNumber]}</td>
				<td style="text-align: center;padding: 0px;">${map[speName]}</td>
				<td style="text-align: center;padding: 0px;">${map[maxDate]}</td>
				<td style="text-align: center;padding: 0px;">${map[sigininCount]}</td>
			</tr>		
		</c:forEach>
		<c:if test="${empty signInfoMaps }"> 
			<tr>
				<td style="text-align: center;" colspan="99">无记录</td>
			</tr>
		
		</c:if>
		</table>
			</div>
		<div>
	       	<c:set var="pageView" value="${pdfn:getPageView(signInfoMaps)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>	 
	    </div>
	</div>
	</div>
</div>
</body>
</html>