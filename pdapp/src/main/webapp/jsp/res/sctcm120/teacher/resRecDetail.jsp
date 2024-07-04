<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataType":${pdfn:toJsonString(param.recType)},
    "auditId": <c:if test="${not empty resultData['auditId']}">"isAudit"</c:if><c:if test="${empty resultData['auditId']}">"notAudit"</c:if>,
    "dataFlow": "${param.recFlow}",
    "values": {
    	<c:if test="${param.recType eq 'CaseRegistry' and not empty resultData}">
            "mr_pName": ${pdfn:toJsonString(resultData.mr_pName)},
            "mr_no": ${pdfn:toJsonString(resultData.mr_no)},
            "disease_pName": ${pdfn:toJsonString(resultData.disease_pName)},
            "mr_diagType":${pdfn:toJsonString(resultData.mr_diagType)}
    	</c:if>
    	<c:if test="${param.recType eq 'DiseaseRegistry' and not empty resultData}">
            "disease_pDate": ${pdfn:toJsonString(resultData.disease_pDate)},
            "disease_pName": ${pdfn:toJsonString(resultData.disease_pName)},
            "disease_mrNo": ${pdfn:toJsonString(resultData.disease_mrNo)},
            "disease_diagName": ${pdfn:toJsonString(empty resultData.disease_diagName ? resultData.disease_diagName : resultData.disease_diagName)},
            "disease_diagType": ${pdfn:toJsonString(resultData.disease_diagType )},
            "disease_isCharge": ${pdfn:toJsonString(resultData.disease_isCharge )},
            "disease_isRescue": ${pdfn:toJsonString(resultData.disease_isRescue )},
            "disease_treatStep": ${pdfn:toJsonString(resultData.disease_treatStep)}
    	</c:if>
    	<c:if test="${param.recType eq 'SkillRegistry' and not empty resultData}">
            "skill_operDate": ${pdfn:toJsonString(resultData.skill_operDate)},
            "skill_pName": ${pdfn:toJsonString(resultData.skill_pName)},
            "skill_mrNo": ${pdfn:toJsonString(resultData.skill_mrNo)},
            "skill_operName": ${pdfn:toJsonString(empty resultData.skill_operName  ? resultData.skill_operName : resultData.skill_operName )},
            "skill_result": ${pdfn:toJsonString(resultData.skill_result )},
            "fail_reason": ${pdfn:toJsonString(resultData.fail_reason)}
    	</c:if>
    	<c:if test="${param.recType eq 'OperationRegistry' and not empty resultData}">
            "operation_operDate": ${pdfn:toJsonString(resultData.operation_operDate)},
            "operation_pName": ${pdfn:toJsonString(resultData.operation_pName)},
            "operation_mrNo": ${pdfn:toJsonString(resultData.operation_mrNo)},
            "operation_operName": ${pdfn:toJsonString(empty resultData.operation_operName  ? resultData.operation_operName : resultData.operation_operName )},
            "operation_operRole": ${pdfn:toJsonString(resultData.operation_operRole )},
            "operation_memo": ${pdfn:toJsonString(resultData.operation_memo)}
    	</c:if>
    	<c:if test="${param.recType eq 'CampaignRegistry' and not empty resultData}">

            "activity_date": ${pdfn:toJsonString(resultData.activity_date)},
            "activity_content": ${pdfn:toJsonString(resultData.activity_content)},
            "activity_way": ${pdfn:toJsonString(resultData.activity_way )},
            "activity_period": ${pdfn:toJsonString(resultData.activity_period )},
            "activity_speaker": ${pdfn:toJsonString(resultData.activity_speaker)}
    	</c:if>
    	<c:if test="${param.recType eq 'Grave' and not empty resultData}">

            "name": ${pdfn:toJsonString(resultData.name)},
            "caseNo": ${pdfn:toJsonString(resultData.caseNo)},
            "time": ${pdfn:toJsonString(resultData.time )},
            "explain": ${pdfn:toJsonString(resultData.explain )}
    	</c:if>
    	<c:if test="${param.recType eq 'CaseRecord' and not empty resultData}">

            "caseType": ${pdfn:toJsonString(resultData['case'])},
            "diseaseName": ${pdfn:toJsonString(resultData.diseaseName)},
            "caseNumber": ${pdfn:toJsonString(resultData.caseNumber )},
            "date": ${pdfn:toJsonString(resultData.date )},
            "teacherSignature": ${pdfn:toJsonString(resultData.teacherSignature)},
            "summary": ${pdfn:toJsonString(resultData.summary)}
    	</c:if>
    	<c:if test="${param.recType eq 'AfterSummary' and not empty resultData}">
            "summary_content": ${pdfn:toJsonString(resultData.summary_content)}
    	</c:if>
    }
    </c:if>
}
