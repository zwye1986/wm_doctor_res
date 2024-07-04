<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${resultMapList}" var="b" varStatus="s">
	     	{
				"assignYear": ${pdfn:toJsonString(b.ASSIGN_YEAR)},
				"orgName": ${pdfn:toJsonString(b.ORG_NAME)},
				"orgType": ${pdfn:toJsonString(b.ORG_TYPE)},
				"assignPlanSum": ${pdfn:toJsonString(b.ASSIGN_PLAN_SUM)},
				"orgFlow": ${pdfn:toJsonString(b.ORG_FLOW)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
