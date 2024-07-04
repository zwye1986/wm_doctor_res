<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"stuCourseList": [
			<c:forEach items="${stuCourseList}" var="edu" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(edu.RECORD_FLOW)},
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.COURSE_NAME)},
				"sid":${pdfn:toJsonString(edu.SID)},
				"userFlow":${pdfn:toJsonString(edu.USER_FLOW)},
				"userName":${pdfn:toJsonString(edu.USER_NAME)},
				"courseTypeId":${pdfn:toJsonString(edu.COURSE_TYPE_ID)},
				"courseTypeName":${pdfn:toJsonString(edu.COURSE_TYPE_NAME)},
				"courseGrade":${pdfn:toJsonString(edu.COURSE_GRADE)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}