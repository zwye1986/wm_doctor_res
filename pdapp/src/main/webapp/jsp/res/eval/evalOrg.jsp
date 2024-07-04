<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	,
	"data":
		{
			<c:import url="readCfg.jsp">

			</c:import>
		}
    </c:if>
}
