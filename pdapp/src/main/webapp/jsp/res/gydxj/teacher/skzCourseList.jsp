<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"courseList": [
			<c:forEach items="${courseList}" var="edu" varStatus="i">
				{
				"courseFlow":${pdfn:toJsonString(edu.courseFlow)},
				"courseCode":${pdfn:toJsonString(edu.courseCode)},
				"courseName":${pdfn:toJsonString(edu.courseName)},
				"courseSession":${pdfn:toJsonString(edu.courseSession)},
				"courseCredit":${pdfn:toJsonString(edu.courseCredit)},
				"coursePeriod":${pdfn:toJsonString(edu.coursePeriod)},
				"courseUserCount":${pdfn:toJsonString(edu.courseUserCount)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}