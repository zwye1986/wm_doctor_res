<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"userFlow": "${userFlow}",
    "datas": [
		<c:forEach items="${results}" var="b" varStatus="s">
	     	{
				"order":"${s.index+1}",
				"orgName":"${b.ORG_NAME}",
				"activityTypeName":"${b.ACTIVITY_TYPE_NAME}",
				"num":"${b.NUM}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     	</c:forEach>
    ]
    </c:if>
}
