<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"userFlow": "${userFlow}",
    "datas": [
		<c:forEach items="${orgList}" var="b" varStatus="s">
	     	{
				"orgFlow":"${b.orgFlow}",
				"orgName":"${b.orgName}",
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     	</c:forEach>
    ]
    </c:if>
}
