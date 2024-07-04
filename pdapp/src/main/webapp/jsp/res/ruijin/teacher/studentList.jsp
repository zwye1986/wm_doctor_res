<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"recTypeList":[
			<c:forEach items="${enumList}" var="bean" varStatus="status">
				{
					"id":"${bean.id}",
					"name":"${bean.name}"
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": "${dataCount}",
		"doctorList": [
		<c:forEach items="${list}" var="doctorMap" varStatus="status">
			<c:set var="headImg" value="${uploadBaseUrl}/${empty doctorMap.doctorImg?'default.png':doctorMap.doctorImg}"/>
			<c:set var="statusId" value=""/>
			<c:set var="statusName" value=""/>
			<c:if test="${empty doctorMap.processFlow || (!(doctorMap.isCurrentFlag eq 'Y') && !(doctorMap.schFlag eq 'Y'))}">
				<c:set var="statusId" value="NotEntered"/>
				<c:set var="statusName" value="未入科"/>
			</c:if>
			<c:if test="${doctorMap.isCurrentFlag eq 'Y'}">
				<c:set var="statusId" value="Entering"/>
				<c:set var="statusName" value="轮转中"/>
			</c:if>
			<c:if test="${doctorMap.schFlag eq 'Y'}">
				<c:set var="statusId" value="Exited"/>
				<c:set var="statusName" value="已出科"/>
			</c:if>
			{
				"doctorFlow": ${pdfn:toJsonString(doctorMap.doctorFlow)},
				"doctorName": ${pdfn:toJsonString(doctorMap.doctorName)},
				"doctorImg": ${pdfn:toJsonString(headImg)},
				"deptName": ${pdfn:toJsonString(doctorMap.schDeptName)},
				"schStartDate": ${pdfn:toJsonString(doctorMap.schStartDate)},
				"schEndDate": ${pdfn:toJsonString(doctorMap.schEndDate)},
				"statusId": ${pdfn:toJsonString(statusId)},
				"statusDesc": ${pdfn:toJsonString(statusName)},
				"startDate": ${pdfn:toJsonString(doctorMap.startDate)},
				"endDate": ${pdfn:toJsonString(doctorMap.endDate)},
				"score": ${pdfn:toJsonString(doctorMap.score)},
				"processFlow": ${pdfn:toJsonString(doctorMap.processFlow)},
				"resultFlow": ${pdfn:toJsonString(doctorMap.resultFlow)},
				<c:set value="${doctorMap.processFlow}DOPS" var="dopsKey"/>
				<c:set value="${doctorMap.processFlow}Mini_CEX" var="miniKey"/>
				<c:set value="${doctorMap.processFlow}AfterEvaluation" var="afterEvaKey"/>
				<c:set value="${doctorMap.processFlow}AfterSummary" var="afterSumKey"/>
				"afterEvaRecFlow":"${resRecMap[afterEvaKey].recFlow}",
				"afterEvaStatusId":"<c:if test="${empty resRecMap[afterEvaKey]}">notAudit</c:if><c:if test="${not empty resRecMap[afterEvaKey]}">isAudit</c:if>",
				"afterEvaStatusName":"<c:if test="${empty resRecMap[afterEvaKey]}">未审核</c:if><c:if test="${not empty resRecMap[afterEvaKey]}">已审核</c:if>",
				"afterSumRecFlow":"${resRecMap[afterSumKey].recFlow}",
				"afterSumStatusId":"<c:if test="${empty resRecMap[afterSumKey]}">notSumbit</c:if><c:if test="${(not empty resRecMap[afterSumKey]) and (empty resRecMap[afterSumKey].auditStatusId)}">notAudit</c:if><c:if test="${not empty resRecMap[afterSumKey].auditStatusId}">isAudit</c:if>",
				"afterSumStatusName":"<c:if test="${empty resRecMap[afterSumKey]}">未提交</c:if><c:if test="${ (not empty resRecMap[afterSumKey]) and (empty resRecMap[afterSumKey].auditStatusId)}">未审核</c:if><c:if test="${not empty resRecMap[afterSumKey].auditStatusId}">已审核</c:if>",
				"dopsRecFlow":"${resRecMap[dopsKey].recFlow}",
				"dopsStatusId":"<c:if test="${empty resRecMap[dopsKey]}">notAudit</c:if><c:if test="${not empty resRecMap[dopsKey]}">isAudit</c:if>",
				"dopsStatusName":"<c:if test="${empty resRecMap[dopsKey]}">未审核</c:if><c:if test="${not empty resRecMap[dopsKey]}">已审核</c:if>",
				"miniCexRecFlow":"${resRecMap[miniKey].recFlow}",
				"miniCexStatusId":"<c:if test="${empty resRecMap[miniKey]}">notAudit</c:if><c:if test="${not empty resRecMap[miniKey]}">isAudit</c:if>",
				"miniCexStatusName":"<c:if test="${empty resRecMap[miniKey]}">未审核</c:if><c:if test="${not empty resRecMap[miniKey]}">已审核</c:if>"
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}