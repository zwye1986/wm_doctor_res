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
				"sessionNumber":"${b.SESSION_NUMBER}",
				"speName":"全部",
				"num":"${b.NUM}",
				"detailNum":[
					<c:forEach items="${speMap[b.ORG_FLOW]}" var="spe" varStatus="sp">
						{
							"speName2":"${spe.SPE_NAME}",
							"num2":"${spe.NUM}"
						}
						<c:if test="${not sp.last}">
							,
						</c:if>
					</c:forEach>
				]
			}
			<c:if test="${not s.last}">
				,
			</c:if>
     	</c:forEach>
    ]
    </c:if>
}