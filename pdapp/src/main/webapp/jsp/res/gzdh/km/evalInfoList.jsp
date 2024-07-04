<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				<c:set var="scorekey" value="${bean.recFlow}Score"/>
				<c:set var="name" value="${bean.recFlow}EvalName"/>
				"recFlow":"${bean.recFlow}",
				"processFlow":"${bean.processFlow}",
				"typeId":"${bean.recTypeId}",
				"evalName":"${scoreMap[name]}",
				"totalScore":"${scoreMap[scorekey]}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}