<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
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
		function search(){
			$("#serchForm").submit();
		}
		function toPage(page){
			if(page){
				$("#currentPage").val(page);
				search();
			}
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/recruit/secondaryUnitsRecruitInfo"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="year" value="${(empty param.year)?thisYear:param.year}" class="xltext"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"	>
							<input type="button" value="查&#12288;询" class="search" onclick="search()">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th style="width: 40%">机构名称</th>
				<th>学术学位</th>
				<th>专业学位</th>
				<th>合计</th>
			</tr>
			<c:forEach items="${secondaryUnitsRecruitInfoList}" var="info">
				<tr>
					<td>${info['ORG_NAME']}</td>
					<td>${info['ACADEMIC_NUM']}</td>
					<td>${info['SPECIALIZED_NUM']}</td>
					<td>${info['ALL_NUM']}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty secondaryUnitsRecruitInfoList}">
				<tr>
					<td colspan="4">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(secondaryUnitsRecruitInfoList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>
</body>
</html>