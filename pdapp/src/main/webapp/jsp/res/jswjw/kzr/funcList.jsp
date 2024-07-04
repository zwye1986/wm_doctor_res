<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        <c:set value="${param.processFlow}DOPS" var="dopsKey"/>
        <c:set value="${param.processFlow}Mini_CEX" var="miniKey"/>
        <c:set value="${param.processFlow}AfterEvaluation" var="afterKey"/>
    "doctorFlow":"${param.doctorFlow}",
    "processFlow":"${param.processFlow}",
    "recordFlow":"${schRotationDept.recordFlow}",
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
					"typeName":"临床操作技能评估量化表(DOPS)",
					"recType":"DOPS",
                    "reqType":"DOPS",
                    "recFlow":"${resRecMap[dopsKey].recFlow}",
                    "statusId":"<c:if test="${empty resRecMap[dopsKey]}">notAudit</c:if><c:if test="${not empty resRecMap[dopsKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[dopsKey]}">未审核</c:if><c:if test="${not empty resRecMap[dopsKey]}">已审核</c:if>"
                 },
				{
					"a":"7",
					"typeName":"迷你临床演练评估量化表(Mini_CEX)",
					"recType":"MINI-CEX",
                    "reqType":"mini_cex",
                    "recFlow":"${resRecMap[miniKey].recFlow}",
                    "statusId":"<c:if test="${empty resRecMap[miniKey]}">notAudit</c:if><c:if test="${not empty resRecMap[miniKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[miniKey]}">未审核</c:if><c:if test="${not empty resRecMap[miniKey]}">已审核</c:if>"

				},
				{
					"a":"8",
					"typeName":"出科考核表",
					"recType":"AfterEvaluation",
                    "reqType":"afterEvaluation",
                    "recFlow":"${resRecMap[afterKey].recFlow}",
                    "statusId":"<c:if test="${empty resRecMap[afterKey]}">notAudit</c:if><c:if test="${not empty resRecMap[afterKey]}">isAudit</c:if>",
                    "statusName":"<c:if test="${empty resRecMap[afterKey]}">带教未审核</c:if><c:if test="${not empty resRecMap[afterKey]}">已审核</c:if>"
				},
				{
					"a":"9",
					"typeName":"出科考核表附件",
					"recType":"viewImage",
                    "reqType":"viewImage",
                    "recFlow":"",,
                    "statusId":"<c:if test="${!(canViewImage eq 'Y')}">notAudit</c:if><c:if test="${canViewImage eq 'Y'}">isAudit</c:if>",
                    "statusName":"<c:if test="${!(canViewImage eq 'Y')}">未上传</c:if><c:if test="${canViewImage eq 'Y'}">已上传</c:if>"
				}
		],
    </c:if>
}
