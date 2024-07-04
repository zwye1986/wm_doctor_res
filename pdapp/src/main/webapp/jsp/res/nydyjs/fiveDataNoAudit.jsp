<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"CaseRegistryNoAudit":"${CaseRegistryNoAudit}",
	"DiseaseRegistryNoAudit":"${DiseaseRegistryNoAudit}",
	"OperateSkillNoAudit":"${OperateSkillNoAudit}",
	"PossSkillNoAudit":"${PossSkillNoAudit}",
	"ActivityNoAudit":"${ActivityNoAudit}"
    </c:if>
}