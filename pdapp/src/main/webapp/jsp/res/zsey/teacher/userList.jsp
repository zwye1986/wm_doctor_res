<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"dataCount": "${dataCount}",
		"doctorList": [
		<c:forEach items="${list}" var="doctorMap" varStatus="status">
			<c:set var="headImg" value="${uploadBaseUrl}/${empty doctorMap.doctorImg?'default.png':doctorMap.doctorImg}"/>
			{
				"doctorFlow": ${pdfn:toJsonString(doctorMap.doctorFlow)},
				"doctorName": ${pdfn:toJsonString(doctorMap.doctorName)},
				"doctorImg": ${pdfn:toJsonString(headImg)},
				"sessionNumber": ${pdfn:toJsonString(doctorMap.sessionNumber)},
				"departMentFlow": ${pdfn:toJsonString(doctorMap.departMentFlow)},
				"departMentName": ${pdfn:toJsonString(doctorMap.departMentName)},
				"phone": ${pdfn:toJsonString(doctorMap.phone)},
				"trainingSpeName": ${pdfn:toJsonString(doctorMap.trainingSpeName)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}