<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"courseList": [
			<c:forEach items="${selectedList}" var="edu" varStatus="i">
				{
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.COURSE_NAME)},
				"courseCredit":${pdfn:toJsonString(edu.COURSE_CREDIT)},
				"coursePeriod":${pdfn:toJsonString(edu.COURSE_PERIOD)},
				"courseUserCount":${pdfn:toJsonString(edu.COURSE_USER_COUNT)},
				"selectCount":${pdfn:toJsonString(edu.SELECTCOUNT)},
				"selectFlag":${pdfn:toJsonString(courseMap[edu.COURSE_FLOW])}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
			<c:forEach items="${noSelectList}" var="edu" varStatus="i">
				{
				"courseFlow":${pdfn:toJsonString(edu.COURSE_FLOW)},
				"courseCode":${pdfn:toJsonString(edu.COURSE_CODE)},
				"courseName":${pdfn:toJsonString(edu.COURSE_NAME)},
				"courseCredit":${pdfn:toJsonString(edu.COURSE_CREDIT)},
				"coursePeriod":${pdfn:toJsonString(edu.COURSE_PERIOD)},
				"courseUserCount":${pdfn:toJsonString(edu.COURSE_USER_COUNT)},
				"selectCount":${pdfn:toJsonString(edu.SELECTCOUNT)},
				"selectFlag":${pdfn:toJsonString(courseMap[edu.COURSE_FLOW])}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}