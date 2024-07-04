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
            "diseaseName":${pdfn:toJsonString(resultData['diseaseName'])},
            "hospitalNumbers":${pdfn:toJsonString(resultData['hospitalNumbers'])},
            "diseasePersonName":${pdfn:toJsonString(resultData['diseasePersonName'])},
            "type":${pdfn:toJsonString(resultData['type'])},
            "result":${pdfn:toJsonString(resultData['result'])},
            "treatmentWay":${pdfn:toJsonString(resultData['treatmentWay'])},
            "comprehensiveWay":${pdfn:toJsonString(resultData['comprehensiveWay'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'EmergencyCase' and not empty resultData}">
            "case":${pdfn:toJsonString(resultData['case'])},
            "diseaseName":${pdfn:toJsonString(resultData['diseaseName'])},
            "patientName":${pdfn:toJsonString(resultData['patientName'])},
            "cases":${pdfn:toJsonString(resultData['cases'])},
            "date":${pdfn:toJsonString(resultData['date'])},
            "teacherSignature": ${pdfn:toJsonString(empty resultData['teacherSignature']?user.userName:resultData['teacherSignature'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'DiseaseRegistry' and not empty resultData}">
            "diseaseName": ${pdfn:toJsonString(resultData['diseaseName'])},
            "patientName": ${pdfn:toJsonString(resultData['patientName'])},
            "caseNo": ${pdfn:toJsonString(resultData['caseNo'])},
            "treatMeasure": ${pdfn:toJsonString(resultData['treatMeasure'])},
            "recType": ${pdfn:toJsonString(resultData['recType'])},
            "inHosDate": ${pdfn:toJsonString(resultData['inHosDate'])},
            "recStatus": ${pdfn:toJsonString(resultData['recStatus'])},
            "caseType": ${pdfn:toJsonString(resultData['caseType'])},
            "diagnosisType": ${pdfn:toJsonString(resultData['diagnosisType'])},
            "remark": ${pdfn:toJsonString(resultData['remark'])},
            "record": ${pdfn:toJsonString(resultData['record'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'SkillRegistry' and not empty resultData}">
            "skill_operDate": ${pdfn:toJsonString(resultData.skill_operDate)},
            "skill_pName": ${pdfn:toJsonString(resultData.skill_pName)},
            "skill_mrNo": ${pdfn:toJsonString(resultData.skill_mrNo)},
            "skill_operName": ${pdfn:toJsonString(empty resultData.skill_operName  ? resultData.skill_operName : resultData.skill_operName )},
            "skill_result": ${pdfn:toJsonString(resultData.skill_result )},
            "fail_reason": ${pdfn:toJsonString(resultData.fail_reason)},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'OperationRegistry' and not empty resultData}">
            "operation_operDate": ${pdfn:toJsonString(resultData.operation_operDate)},
            "operation_pName": ${pdfn:toJsonString(resultData.operation_pName)},
            "operation_mrNo": ${pdfn:toJsonString(resultData.operation_mrNo)},
            "operation_operName": ${pdfn:toJsonString(empty resultData.operation_operName  ? resultData.operation_operName : resultData.operation_operName )},
            "operation_operRole": ${pdfn:toJsonString(resultData.operation_operRole )},
            "operation_memo": ${pdfn:toJsonString(resultData.operation_memo)},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'SkillAndOperationRegistry' and not empty resultData}">
            "skillName": ${pdfn:toJsonString(resultData['skillName'])},
            "patientName": ${pdfn:toJsonString(resultData['patientName'])},
            "date": ${pdfn:toJsonString(resultData['date'])},
            "assistant": ${pdfn:toJsonString(resultData['assistant'])},
            "status": ${pdfn:toJsonString(resultData['status'])},
            "assessment": ${pdfn:toJsonString(resultData['assessment'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'HospitalizationLog' and not empty resultData}">
            "diseaseName": ${pdfn:toJsonString(resultData['diseaseName'])},
            "patientName": ${pdfn:toJsonString(resultData['patientName'])},
            "hospitalNumbers": ${pdfn:toJsonString(resultData['hospitalNumbers'])},
            "type": ${pdfn:toJsonString(resultData['type'])},
            "inHosDate": ${pdfn:toJsonString(resultData['inHosDate'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'CampaignNoItemRegistry' and not empty resultData}">
            "title": ${pdfn:toJsonString(resultData['title'])},
            "articleTitle": ${pdfn:toJsonString(resultData['articleTitle'])},
		    "teaching": ${pdfn:toJsonString(resultData['teaching'])},
		    "lectureTitle": ${pdfn:toJsonString(resultData['lectureTitle'])},
            "assessment": ${pdfn:toJsonString(resultData['assessment'])},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    	<c:if test="${param.recType eq 'AfterSummary' and not empty resultData}">
            "summary_content": ${pdfn:toJsonString(resultData.summary_content)},
            "auditAppraise": ${pdfn:toJsonString(resultData['auditAppraise'])}
    	</c:if>
    }
    </c:if>
}
