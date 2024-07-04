<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"dataCount": "${dataCount}",
		"doctorList": [
		<c:forEach items="${list}" var="doctorMap" varStatus="status">
			<c:set var="headImg" value="${uploadBaseUrl}/${empty doctorMap.userHeadImg?'default.png':doctorMap.userHeadImg}"/>
			{
				"doctorFlow": ${pdfn:toJsonString(doctorMap.userFlow)},
				"doctorName": ${pdfn:toJsonString(doctorMap.userName)},
				"doctorImg": ${pdfn:toJsonString(headImg)},
				"sessionNumber": ${pdfn:toJsonString(doctorMap.sessionNumber)},
				"trainingSpeName": ${pdfn:toJsonString(doctorMap.speName)},
				"qty": ${pdfn:toJsonString(doctorMap.qty+0)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}