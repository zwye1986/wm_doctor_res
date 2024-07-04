<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"recForm": ${pdfn:toJsonString(recForm)},
	"resultFlow": ${pdfn:toJsonString(resultFlow)},
	"doctorFlow": ${pdfn:toJsonString(doctorFlow)},
    "datas": [
		<c:forEach items="${enums}" var="bean" varStatus="s">
	     	{
				"recTypeId":"${bean.id}",
				"recTypeName":"${bean.name}",
				"action":{
					"addEvl":"新增评价"
				}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}