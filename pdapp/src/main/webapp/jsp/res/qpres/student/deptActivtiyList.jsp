<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="b" varStatus="s">
	     	{
				"deptName":"${b.deptName}",
				"planDate":"${b.planDate}",
				"planTime":"${b.planTime}",
				"joinUserName":"${b.joinUserName}",
				"userCode":"${b.userCode}",
				"content":${pdfn:toJsonString(b.content)},
				"address":${pdfn:toJsonString(b.address)},
				"itemFlow":"${b.itemFlow}",
				"itemTypeId":"${b.itemTypeId}",
				"itemTypeName":"${b.itemTypeName}",
				"evalFlow":"${b.evalFlow}",
				"operId":
						<c:choose>
							<c:when test="${empty b.evalFlow}">
								"NotEval",
							</c:when>
							<c:otherwise>
								"Evaled",
							</c:otherwise>
						</c:choose>
				"operName":
					<c:choose>
						<c:when test="${empty b.evalFlow}">
							"评价",
						</c:when>
						<c:otherwise>
							"查看",
						</c:otherwise>
					</c:choose>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
