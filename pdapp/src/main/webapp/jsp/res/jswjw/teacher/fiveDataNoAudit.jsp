<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	<c:set value="${processFlow}CaseRegistry" var="preTrainMapKey"/>
	<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
	"mrNotAudit":"${resRecCountMap[notAuditKey]}",
	<c:set value="${processFlow}DiseaseRegistry" var="preTrainMapKey"/>
	<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
	"diseaseNotAudit":"${resRecCountMap[notAuditKey]}",
	<c:set value="${processFlow}SkillRegistry" var="preTrainMapKey"/>
	<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
	"skillNotAudit":"${resRecCountMap[notAuditKey]}",
	<c:set value="${processFlow}OperationRegistry" var="preTrainMapKey"/>
	<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
	"operationNotAudit":"${resRecCountMap[notAuditKey]}",
	<c:set value="${processFlow}CampaignRegistry" var="preTrainMapKey"/>
	<c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
	"activityNotAudit":"${resRecCountMap[notAuditKey]}"
    </c:if>
}