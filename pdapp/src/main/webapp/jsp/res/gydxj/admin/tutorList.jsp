<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"tutorList": [
			<c:forEach items="${tutorList}" var="edu" varStatus="i">
				{
				"doctorFlow":${pdfn:toJsonString(edu.doctorFlow)},
				"doctorName":${pdfn:toJsonString(edu.doctorName)},
				"doctorTypeId":${pdfn:toJsonString(edu.doctorTypeId)},
				"doctorTypeName":${pdfn:toJsonString(edu.doctorTypeName)},
				"pydwOrgFlow":${pdfn:toJsonString(edu.pydwOrgFlow)},
				"pydwOrgName":${pdfn:toJsonString(edu.pydwOrgName)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}