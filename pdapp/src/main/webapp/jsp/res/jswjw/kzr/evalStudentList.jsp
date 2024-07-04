<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
				"userName":"${bean.userName}",
				"schStartDate":"${bean.schStartDate}",
				"schEndDate":"${bean.schEndDate}",
				"userHeadImg":"${bean.userHeadImg}",
				"docFlow":"${bean.userFlow}",
				"schResultFlow":"${bean.schResultFlow}",
				"resultFlow":"${bean.resultFlow}",
				"sessionNumber":"${bean.sessionNumber}",
				"speName":"${bean.speName}",
				"teacherUserName":"${bean.teacherUserName}",
				"processFlow":"${bean.processFlow}",
				"orgFlow":"${bean.orgFlow}",
				"orgName":"${bean.orgName}",
				"deptFlow":"${bean.deptFlow}",
				"deptName":"${bean.deptName}",
				"schDeptFlow":"${bean.schDeptFlow}",
				"schDeptName":"${bean.schDeptName}",
				"recordFlow":"${schRotationDeptMap[bean.processFlow].recordFlow}",
				"currDate":"${currDate}",
				"schType":"${bean.statusId}",
				"schStatus":"${bean.statusName}",
				<c:set var="evals" value="${evalMap[bean.processFlow]}"></c:set>
				<c:set var="doctorFlow" value="${bean.userFlow}"></c:set>
				"evals":[
						<c:forEach items="${evals}" var="bean" varStatus="s1">
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
							<c:if test="${not s1.last }">
								,
							</c:if>
						</c:forEach>
				]

			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}