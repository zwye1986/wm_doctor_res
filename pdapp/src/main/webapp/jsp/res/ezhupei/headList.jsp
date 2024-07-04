<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		,
		"pageIndex": ${param.pageIndex},
		"pageSize": ${param.pageSize},
		"dataCount":${dataCount},
		"heads":[
			<c:forEach var="user" items="${userList}" varStatus="status">
				{
					"headFlow":${pdfn:toJsonString(user.userFlow)},
					"headName":${pdfn:toJsonString(user.userName)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}