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
</head>
<script type="application/javascript">
	function courseOutline(courseFlow,courseName){
		var url = "<s:url value ='/gyxjgl/course/manage/searchOutline'/>?reqFlag=outline&courseFlow="+courseFlow;
		jboxOpen(url,courseName+"课程大纲查看",800,500);
	}
	function courseTeaching(courseFlow,courseName){
		var url = "<s:url value ='/gyxjgl/course/manage/searchOutline'/>?reqFlag=teaching&courseFlow="+courseFlow;
		jboxOpen(url,courseName+"参考教材查看",800,500);
	}
	function toPage(page){
		var form=$("#myForm");
		$("#currentPage").val(page);
		jboxStartLoading();
		form.submit();
	}
</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm" action="<s:url value='/gyxjgl/course/manage/searchAllCourse'/>" method="post">
			<input type="hidden" name="year" value="${param.year}"/>
			<input type="hidden" name="currentPage" id="currentPage" value="1"/>
		</form>
		<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
		<c:if test="${!gzFlag}">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr><td style="text-align:center;"><font color="red" size="4">需跨专业、跨层次选课的同学，请于${param.year}年9月8日12：00前至培养科登记</font></td></tr>
			</table>
		</c:if>
		<table class="xllist" width="100%">
			<tr>
				<th>代码</th>
				<th>中文名称</th>
				<th>授课层次</th>
				<th>课程类别</th>
				<th>容纳人数</th>
				<th>承担单位</th>
				<th>学分</th>
				<th>总学时</th>
				<th>操作</th>
			</tr>

			<c:forEach items="${coursesList}" var="course">
				<tr>
					<td>${course.courseCode}</td>
					<td>${course.courseName}</td>
					<td>${course.gradationName}</td>
					<td>${course.courseTypeName}</td>
					<td>${course.courseUserCount}</td>
					<td>${course.assumeOrgName}</td>
					<td>${course.courseCredit}</td>
					<td>${course.coursePeriod}</td>
					<td><a style="cursor: pointer; color: blue;" onclick="courseOutline('${course.courseFlow}','${course.courseName}');">课程大纲</a>
						<a style="cursor: pointer; color: blue;" onclick="courseTeaching('${course.courseFlow}','${course.courseName}');">推荐教材</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty coursesList}">
				<tr>
					<td colspan="9" >无记录！</td>
				</tr>
			</c:if>
		</table>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(coursesList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>