<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
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
		function search(){
			$("#searchForm").submit();
		}
		function toPage(page){
			if(page) {
				$("#currentPage").val(page);
				search();
			}
		}
	</script>
	<style>
		.table1{border: 0}
		.table1 td{border: 0}
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class=" clearfix">
			<div class="queryDiv">
			<form id="searchForm" action="<s:url value='/res/ProfessionalBase/annualExamScore'/>" method="post">
				<div class="inputDiv">
					培训类别：
					<select name="doctorCategoryId" class="qselect">
						<option value="all">全部</option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
							<option value="${category.id}"
								${(doctorCategoryId eq category.id)?'selected':''}>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					年&#12288;&#12288;级：
					<select name="sessionNumber" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
							<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					成绩年份：
					<input class="qtext" type="text" name="assessmentYear" value="${(empty param.assessmentYear)?pdfn:getCurrYear():param.assessmentYear}"  onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
				</div>
				<div class="inputDiv" style="max-width: 275px;min-width: 275px;">
					结业考核年份：
					<input class="qtext" type="text" name="graduationYear" value="${param.graduationYear}"  onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：
					<input type="text" name="userName" class="qtext" value="${param.userName}"/>
				</div>
				<div class="doctorTypeDiv">
					<div class="doctorTypeLabel">学员类型：</div>
					<div class="doctorTypeContent">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input type="checkbox" name="doctorTypeList" id="${type.dictId}"
										  <c:if test="${empty param.doctorTypeList}">checked</c:if>
										  value="${type.dictId}">${type.dictName}</label>
						</c:forEach>
					</div>
				</div>
				<div class="lastDiv" >
					&#12288;<input class="search" type="button" value="查&#12288;询" onclick="search();"/>
				</div>
			</form>
			</div>
			<table class="basic table2" width="100%">
			<tr>
				<th style="text-align: center;padding: 0px">姓名</th>
				<th style="text-align: center;padding: 0px">性别</th>
				<th style="text-align: center;padding: 0px">培训类别</th>
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
					<td style="text-align: center;padding: 0px">${b.doctorCategoryName}</td>
					<td style="text-align: center;padding: 0px">${b.trainingSpeName}</td>
					<td style="text-align: center;padding: 0px">${b.sessionNumber}</td>
					<td style="text-align: center;padding: 0px">${b.trainingYears}</td>
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
			<pd:pagination toPage="toPage"/>
		</div>
		</div>
	</div>
</div>
</body>
</html>
