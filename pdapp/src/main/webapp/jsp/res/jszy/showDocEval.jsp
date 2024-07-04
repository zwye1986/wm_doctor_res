<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	<c:if test="${empty result.evalScore}">
		"action":{
			"save":"保存"
		},
	</c:if>
		"datas": [
		<c:forEach items="${targets}" var="t" varStatus="s">
	     	{
				<c:set var="key" value="${result.resultFlow}${t.targetFlow}"></c:set>
				"targetFlow":"${t.targetFlow}",
				"targetName":"${t.targetName}",
				"evalScore":"${evalDetailMap[key]}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
