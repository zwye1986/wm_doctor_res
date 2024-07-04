<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        <c:set value="${param.processFlow}DOPS" var="dopsKey"/>
        <c:set value="${param.processFlow}Mini_CEX" var="miniKey"/>
        <c:set value="${param.processFlow}AfterEvaluation" var="afterKey"/>
        <c:set value="${param.processFlow}AfterSummary" var="summaryKey"/>
    "doctorFlow":"${param.doctorFlow}",
    "processFlow":"${param.processFlow}",
	"funcLists":[
				{
					"a":"1",
					"typeName":"大病历",
					"recType":"mr",
                    "reqType":"resRecList",
                    "recFlow":"",
                    "statusId":"",
                    "statusName":""
				},
				{
					"a":"2",
					"typeName":"病种",
					"recType":"disease",
                    "reqType":"resRecList",
                    "recFlow":"",
                    "statusId":"",
                    "statusName":""
				},
				{
					"a":"3",
					"typeName":"操作技能",
					"recType":"skill",
                    "reqType":"resRecList",
                    "recFlow":"",
                    "statusId":"",
                    "statusName":""
				},
				{
					"a":"4",
					"typeName":"手术",
					"recType":"operation",
                    "reqType":"resRecList",
                    "recFlow":"",
                    "statusId":"",
                    "statusName":""
				},
				{
					"a":"5",
					"typeName":"参加活动",
					"recType":"activity",
                    "reqType":"resRecList",
                    "recFlow":"",
                    "statusId":"",
                    "statusName":""
				}
                ,
				{
					"a":"6",
					"typeName":"临床操作技能评估量化表",
                    "recType":"dops",
                    "recTypeId":"DOPS",
                    "recFlow":"",
                    "resultFlow":"${process.schResultFlow}",
                    "processFlow":"${process.processFlow}",
                    "statusId":"<c:if test="${empty resRecMap[dopsKey]}">notAudit</c:if><c:if test="${not empty resRecMap[dopsKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[dopsKey]}">未审核</c:if><c:if test="${not empty resRecMap[dopsKey]}">已审核</c:if>"
                 },
				{
					"a":"7",
					"typeName":"迷你临床演练评估量化表",
					"recType":"mini_cex",
                    "recTypeId":"Mini_CEX",
                    "recFlow":"",
                    "resultFlow":"${process.schResultFlow}",
                    "processFlow":"${process.processFlow}",
                    "statusId":"<c:if test="${empty resRecMap[miniKey]}">notAudit</c:if><c:if test="${not empty resRecMap[miniKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[miniKey]}">未审核</c:if><c:if test="${not empty resRecMap[miniKey]}">已审核</c:if>"
				},
				{
					"a":"8",
					"typeName":"出科考核表",
                    "recType":"afterEvaluation",
                    "recTypeId":"AfterEvaluation",
                    "recFlow":"${resRecMap[afterKey].recFlow}",
                    "rotationFlow":"${resDoctor.rotationFlow}",
                    "schDeptFlow":"${process.schDeptFlow}",
                    "operUserFlow":"${process.userFlow}",
                    "resultFlow":"${process.schResultFlow}",
                    "processFlow":"${process.processFlow}",
                    "headAuditStatusId":"${!empty resRecMap[afterKey]?resRecMap[afterKey].headAuditStatusId:''}",
                    "statusId":"<c:if test="${empty resRecMap[afterKey]}">notAudit</c:if><c:if test="${not empty resRecMap[afterKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[afterKey]}">未审核</c:if><c:if test="${not empty resRecMap[afterKey]}">已审核</c:if>"
				},
                {
                    "a":"9",
                    "typeName":"出科小结",
                    "recType":"afterSummary",
                    "recTypeId":"AfterSummary",
                    "recFlow":"${resRecMap[summaryKey].recFlow}",
                    "rotationFlow":"${resDoctor.rotationFlow}",
                    "schDeptFlow":"${process.schDeptFlow}",
                    "operUserFlow":"${process.userFlow}",
                    "resultFlow":"${process.schResultFlow}",
                    "processFlow":"${process.processFlow}",
                    "headAuditStatusId":"${!empty resRecMap[summaryKey]?resRecMap[summaryKey].headAuditStatusId:''}",
                    "statusId":"<c:if test="${empty resRecMap[summaryKey].auditStatusId}">notAudit</c:if><c:if test="${not empty resRecMap[summaryKey].auditStatusId}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[summaryKey].auditStatusId}">未审核</c:if><c:if test="${not empty resRecMap[summaryKey].auditStatusId}">已审核</c:if>"
                }
		],
    </c:if>
}