<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"GraduationPlan":${pdfn:toJsonString(typeInfo['N']+0)},
	"HospitalPlan":${pdfn:toJsonString(typeInfo['Y']+0)},
    "datas": [
		<c:forEach items="${list}" var="data" varStatus="s">
	     	{
				"id": ${pdfn:toJsonString(data.DATE_MONTH)},
				"num": ${pdfn:toJsonString(data.QTY)},
				"type": ${pdfn:toJsonString(data.IS_LOCAL)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}