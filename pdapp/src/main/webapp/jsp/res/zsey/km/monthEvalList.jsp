<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"evals":[
			<c:forEach items="${evals}" var="bean" varStatus="s">
				{
					"processFlow":"${bean.processFlow}",
					"doctorFlow":"${doctorFlow}",
					"evalMonth":"${bean.evalMonth}",
					"startTime":"${bean.startTime}",
					"endTime":"${bean.endTime}",
					"recordFlow":"${bean.recordFlow}",
					<c:if test="${empty bean.recordFlow}">
						"statusId":"NotAudit",
						"statusName":"待考评"
					</c:if>
					<c:if test="${not empty bean.recordFlow}">
						"statusId":"Audited",
						"statusName":"已考评"
					</c:if>
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}