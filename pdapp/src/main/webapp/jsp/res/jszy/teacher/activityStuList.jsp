<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${results}" var="b" varStatus="s">
	     	{
				"userName":"${b.userName}",
				"sessionNumber":"${b.sessionNumber}",
				"speName":"${b.doctorCategoryName}",
				"regiestTime":"${b.regiestTime1}",
				"siginTime":"${b.siginTime}",
				"siginTime2":"${b.siginTime2}",
				"resultFlow":"${b.resultFlow}",
				"evalScore":"${b.evalScore}",
				"isEffective":"${b.isEffective}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
