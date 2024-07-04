<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		,
		"evalYear": ${evalYear},
		"data":[
			<c:forEach var="org" items="${orgs}" varStatus="status">
				{
					"orgFlow":${pdfn:toJsonString(org.orgFlow)},
					"orgName":${pdfn:toJsonString(org.orgName)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}