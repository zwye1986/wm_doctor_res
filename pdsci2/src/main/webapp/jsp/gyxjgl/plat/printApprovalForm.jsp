<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="text-align:center;line-height:30px;">
			<p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课程教学审批表</p>
			${form.schoolYearDesc} 学年度 ${form.gradeDesc} 第 ${form.termDesc} 学期
		</div>
		<table class="basic" style="width:100%;border:0px;">
			<tr>
				<td style="width:40%;border:0px;padding:0px;">课程名称：${empty form?course.courseName:form.courseName}</td>
				<td style="width:60%;border:0px;padding:0px;">授课教研室：${empty form?course.assumeOrgName:form.teachingPlace}</td>
			</tr>
			<tr>
				<td style="border:0px;padding:0px;">英文名称：${empty form?course.courseNameEn:form.courseNameEn}</td>
				<td style="border:0px;padding:0px;"><div style="float:left;line-height:35px;height:100%;">理论课教师：</div><c:forEach items="${subList}" var="su" varStatus="i">${su.teacherName}<c:if test="${!i.last}">&ensp;</c:if></c:forEach></td>
			</tr>
			<tr>
				<td style="border:0px;padding:0px;">教材名称：${form.teachingMaterial}</td>
				<td style="border:0px;padding:0px;">
					作者：${form.authorName}&#12288;
					版次：${form.layoutNum}&#12288;
					出版社：${form.pressName}
				</td>
			</tr>
		</table>
		<table class="basic" style="width: 100%;">
			<tr>
				<th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
				<th style="width:15%;text-align:center;padding:0px;">职称</th>
				<th style="width:60%;text-align:center;padding:0px;">授课内容</th>
				<th style="width:10%;text-align:center;padding:0px;">学时</th>
			</tr>
			<c:forEach items="${subList}" var="su">
				<tr>
					<td style="text-align:center;padding:0px;">${su.teacherName}</td>
					<td style="text-align:center;padding:0px;">${su.teacherPost}</td>
					<td style="text-align:center;padding:0px;">${su.contentDesc}</td>
					<td style="text-align:center;padding:0px;">${su.schoolHours}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>