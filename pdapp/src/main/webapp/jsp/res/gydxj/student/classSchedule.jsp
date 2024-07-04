<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"eduUserList": [
			<c:forEach items="${classList}" var="edu" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(edu.RECORD_FLOW)},
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"classCourseName":${pdfn:toJsonString(edu.CLASS_COURSE_NAME)},
				"courseCredit":${pdfn:toJsonString(edu.COURSE_CREDIT)},
				"classPeriod":${pdfn:toJsonString(edu.CLASS_PERIOD)},
				"classTeacherName":${pdfn:toJsonString(edu.CLASS_TEACHER_NAME)},
				"classroomName":${pdfn:toJsonString(edu.CLASSROOM_NAME)},
				"classTime":${pdfn:toJsonString(empty timeMap[edu.CLASS_ORDER]?edu.CLASS_ORDER:timeMap[edu.CLASS_ORDER])}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}