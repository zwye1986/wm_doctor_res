<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"datas":[
			<c:forEach items="${targets}" var="bean" varStatus="s">
				{
					"targetName":"${bean.targetName}",
					"evalScore":"${bean.evalScore+0}",
					"isText":"${bean.isText}"
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}