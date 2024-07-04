<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		<c:set var="periodMap" value="${studentCourseMap[eduUser.USER_FLOW]}" />
		<c:forEach items="${periodMap}" var="period" varStatus="status">
			<c:set var="coursePeriodSum" value="0"/>
			<c:set var="courseCreditSum" value="0"/>
			<c:set var="degreeCourseCreditSum" value="0"/>
			<c:set var="courseTypeMap" value="${period.value}"/>
			<c:set var="pulicEscList" value="${courseTypeMap[xjglCourseTypeEnumPublic.id] }"/>
			"publicList":[
				<c:forEach items="${pulicEscList}" var="studentCourse" varStatus="i">
					{
						"courseCode":${pdfn:toJsonString(studentCourse.courseCode)},
						"courseName":${pdfn:toJsonString(studentCourse.courseName)}
					}
					<c:if test="${!i.last}">
						,
					</c:if>
					<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
					<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
					<c:if test="${studentCourse.degreeCourseFlag eq GlobalConstant.FLAG_Y}">
						<c:set var="degreeCourseCreditSum" value="${degreeCourseCreditSum + studentCourse.courseCredit}"/>
					</c:if>
				</c:forEach>
			],
			<c:set var="optionalNeedList" value="${courseTypeMap[xjglCourseTypeEnumOptionalNeed.id] }"/>
			"optionalNeedList":[
				<c:forEach items="${optionalNeedList}" var="studentCourse" varStatus="i">
					{
						"courseCode":${pdfn:toJsonString(studentCourse.courseCode)},
						"courseName":${pdfn:toJsonString(studentCourse.courseName)}
					}
					<c:if test="${!i.last}">
						,
					</c:if>
					<c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
					<c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
					<c:if test="${studentCourse.degreeCourseFlag eq GlobalConstant.FLAG_Y}">
						<c:set var="degreeCourseCreditSum" value="${degreeCourseCreditSum + studentCourse.courseCredit}"/>
					</c:if>
				</c:forEach>
			],
		</c:forEach>
		"baseInfo":
		{
			"userFlow":${pdfn:toJsonString(eduUser.USER_FLOW)},
			"userName":${pdfn:toJsonString(eduUser.USER_NAME)},
			"sid":${pdfn:toJsonString(eduUser.SID)},
			"majorId":${pdfn:toJsonString(eduUser.MAJOR_ID)},
			"majorName":${pdfn:toJsonString(eduUser.MAJOR_NAME)},
			"period":${pdfn:toJsonString(eduUser.PERIOD)},
			"coursePeriodSum":${pdfn:toJsonString(coursePeriodSum)},
			"courseCreditSum":${pdfn:toJsonString(courseCreditSum)},
			"degreeCourseCreditSum":${pdfn:toJsonString(degreeCourseCreditSum)}
		}
    </c:if>
}