<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,

		"datas":[
			<c:forEach items="${orgList}" var="org" varStatus="status">
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