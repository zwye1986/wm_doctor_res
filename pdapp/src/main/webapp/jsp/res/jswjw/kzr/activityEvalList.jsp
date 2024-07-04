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
				"speName":"${b.speName}",
				"siginTime":"${b.siginTime}",
				"siginTime2":"${b.siginTime2}",
				"resultFlow":"${b.resultFlow}",
				"evalScore":"${b.evalScore}",
				"isEffective":"${b.isEffective}",
				"targets":[
					<c:forEach items="${targets}" var="bean" varStatus="s2">
						<c:set var="key" value="${b.resultFlow}${bean.targetFlow}"></c:set>
						{
							"targetName":"${bean.targetName}",
							"evalScore":"${evalDetailMap[key]}",
							"evalRemarks":"${evalRemarksMap[key]}",
							"isText":"${bean.isText}"
						}
						<c:if test="${not s2.last }">
							,
						</c:if>
					</c:forEach>
				],
				"action":[
						<c:if test="${activity.speakerFlow eq user.userFlow and activity.isEffective eq 'Y'}">
							{
								"id":"${b.isEffective eq 'Y'?'N':'Y'}",
								"name":"${b.isEffective eq 'Y'?'不认可':'认可'}"
							}
						</c:if>
				]

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
