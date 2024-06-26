
<%@include file="/jsp/common/doctype.jsp" %>
<!-- 合计 -->
<c:set var="courseCount" value="0"/>
<c:set var="coursePeriodSum" value="0"/>
<c:set var="courseCreditSum" value="0"/>
<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
<table class="xllist table" width="100%">
	<tr style="height: 60px;">
		<c:if test="${gzFlag}">
			<th style="width:20%;">课程类别</th>
			<th style="width:35%;">课程列表</th>
			<th style="width:15%;">学时</th>
			<th style="width:15%;">学分</th>
			<th style="width:15%;">学位课程</th>
		</c:if>
	<c:if test="${!gzFlag}">
		<th style="width:25%;">课程类别</th>
		<th style="width:45%;">课程列表</th>
		<th style="width:15%;">学时</th>
		<th style="width:15%;">学分</th>
	</c:if>
	</tr>

	<!-- 循环课程类别 -->
	<c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
		<c:set var="isFirst" value="true"/> <!-- 首列 （false无Td）-->
		<!-- 循环该课程类别下   已选所有课程 -->
		<c:forEach items="${studentCourseMap[courseType.id]}" var="studentCourse" varStatus="status">
			<!-- 课程 -->
			<c:if test="${not empty studentCourseMap[courseType.id]}">
				<tr>
					<c:set var="rowSapn" value="${studentCourseMap[courseType.id].size()}"/>
					<c:if test="${isFirst}">
						<td style="width:20%;" rowspan="${rowSapn}">${courseType.name}</td> 
					</c:if>
					<td>
						<font color="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y?'red':''}">[${studentCourse.courseCode}]${studentCourse.courseName}</font>
					</td>
					<td>${studentCourse.coursePeriod}</td>
					<td>${studentCourse.courseCredit}</td>
					<c:if test="${gzFlag}"><td>${studentCourse.degreeCourseFlag eq 'Y'?'是':'否'}</td></c:if>
					<c:set var="courseCount" value="${courseCount + 1}"/>
					<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
					<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
				</tr>
				<c:set var="isFirst" value="false"/>
			</c:if>
		</c:forEach>
		<c:if test="${empty studentCourseMap[courseType.id]}">
		<tr>
			<td style="width:20%;">${courseType.name}</td> 
			<td>--</td>
			<td>--</td>
			<td>--</td>
			<c:if test="${gzFlag}"><td>--</td></c:if>
		</tr>
		</c:if>
	</c:forEach>
	
	<c:if test="${not empty studentCourseList}">
		<tr>
			<td>合计</td>
			<td>已选&nbsp;${courseCount}&nbsp;门课程</td>
			<td><fmt:formatNumber type="number" value="${coursePeriodSum}" maxFractionDigits="2"/></td>
			<td><fmt:formatNumber type="number" value="${courseCreditSum}" maxFractionDigits="2"/></td>
			<c:if test="${gzFlag}"><td></td></c:if>
		</tr>
	</c:if>
</table>
