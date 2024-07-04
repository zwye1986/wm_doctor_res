<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		,
		"pageIndex": ${param.pageIndex},
		"pageSize": ${param.pageSize},
		"dataCount":${dataCount},
		"depts":[
			<c:forEach var="schDept" items="${schDeptList}" varStatus="status">
				{
					"schDeptFlow":${pdfn:toJsonString(schDept.schDeptFlow)},
					"schDeptName":${pdfn:toJsonString(schDept.schDeptName)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}