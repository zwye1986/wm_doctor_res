<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${doctorTypeIdList}" var="data">
			$("#"+"${data}").attr("checked","checked");
			</c:forEach>
		});

		function search() {
			jboxStartLoading();
			jboxPostLoad("content","<s:url value='/res/responsibleTeacher/annualExamScore'/>",$("#searchForm").serialize(),true);
		}
		function toPage(page){
			if(page) {
				$("#currentPage").val(page);
				search();
			}
		}
	</script>
	<style>
		.table1 td{border: 0}
	</style>
</head>
<div class="main_hd">
	<h2 class="underline">年度成绩查询</h2>
</div>
<body>
<div style="padding: 10px 40px;">
	<form id="searchForm" action="<s:url value='/res/responsibleTeacher/annualExamScore'/>" method="post">
		<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
		<table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">成绩年份：</td>
				<td>
					<input class="input" style="width: 118px;" type="text" name="assessmentYear" value="${(empty param.assessmentYear)?currentYear:param.assessmentYear}"  onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" class="select"  style="margin: 0 5px;width: 124px">
						<option  value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<select name="sessionNumber" class="select"  style="margin: 0 5px;width: 124px">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
							<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">结业考核年份：</td>
				<td>
					<input class="input"  style="width: 118px;" type="text" name="graduationYear" value="${param.graduationYear}"  onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" style="width: 118px;" name="userName" class="input" value="${param.userName}"/>
				</td>
				<td class="td_left">学员类型：</td>
				<td colspan="6">
					<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
						<label style="margin: 0 5px;"><input type="checkbox" name="doctorTypeIdList"
															 value="${type.dictId}" id="${type.dictId}"
															 <c:if test="${empty param.doctorTypeIdList}">checked</c:if>
						>${type.dictName}</label>
					</c:forEach>
					<input type="button" value="查&#12288;询" class="btn_green" onclick="search()"/>&nbsp;
				</td>
			</tr>
		</table>
	</form>
	<table class="grid" width="100%">
		<tr>
			<th style="text-align: center;padding: 0px">姓名</th>
			<th style="text-align: center;padding: 0px">性别</th>
			<th style="text-align: center;padding: 0px">培训专业</th>
			<th style="text-align: center;padding: 0px">年级</th>
			<th style="text-align: center;padding: 0px">培训年限</th>
			<c:forEach items="${years}" var="year">
				<th style="text-align: center;padding: 0px">${year}年度</th>
			</c:forEach>
		</tr>
		<c:forEach items="${list}" var="b">
			<tr>
				<td style="text-align: center;padding: 0px">${b.sysUser.userName}</td>
				<td style="text-align: center;padding: 0px">${b.sysUser.sexName}</td>
				<td style="text-align: center;padding: 0px">${b.trainingSpeName}</td>
				<td style="text-align: center;padding: 0px">${b.sessionNumber}</td>
				<td style="text-align: center;padding: 0px">
						${b.trainingYears eq 'OneYear'?'一年':''}
						${b.trainingYears eq 'TwoYear'?'两年':''}
						${b.trainingYears eq 'ThreeYear'?'三年':''}
				</td>
				<c:forEach items="${years}" var="year">
					<c:set var="key" value="${year}${b.doctorFlow}${b.sessionNumber}"></c:set>
					<c:set var="schExam" value="${daMap[key]}"></c:set>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty schExam.examScore }">
							<a href="javascript:downloadExamPaper('${schExam.recordFlow}');">${schExam.examScore }</a>
						</c:if>
						<c:if test="${empty schExam.examScore }">
							--
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr>
				<td colspan="20" style="text-align: center">无记录！</td>
			</tr>
		</c:if>
	</table>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>
</body>
</html>
