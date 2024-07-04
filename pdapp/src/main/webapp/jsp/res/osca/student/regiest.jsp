<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
	,
    "types": [

		<c:forEach items="${types}" var="data" varStatus="s">
	     	{
				"optionId": ${pdfn:toJsonString(data.id)},
				"optionDesc": ${pdfn:toJsonString(data.name)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     	</c:forEach>
	]
	</c:if>
}
