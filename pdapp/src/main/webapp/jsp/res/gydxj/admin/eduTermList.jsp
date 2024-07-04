<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"termList": [
			<c:forEach items="${termList}" var="edu" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(edu.recordFlow)},
				"sessionNumber":${pdfn:toJsonString(edu.sessionNumber)},
				"gradeTermId":${pdfn:toJsonString(edu.gradeTermId)},
				"gradeTermName":${pdfn:toJsonString(edu.gradeTermName)},
				"classId":${pdfn:toJsonString(edu.classId)},
				"className":${pdfn:toJsonString(edu.className)},
				"gradationId":${pdfn:toJsonString(edu.gradationId)},
				"gradationName":${pdfn:toJsonString(edu.gradationName)},
				"termStartTime":${pdfn:toJsonString(edu.termStartTime)},
				"termEndTime":${pdfn:toJsonString(edu.termEndTime)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}