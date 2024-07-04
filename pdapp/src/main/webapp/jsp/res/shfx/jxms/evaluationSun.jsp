<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    		<jsp:include page="/jsp/res/shfx/teacher/inputAfter.jsp"></jsp:include>
    ],
    "isExam":     ${pdfn:toJsonString(f)},
    "values": [
        {
            "inputId": "formFileName",
            "value": ${pdfn:toJsonString(formFileName)}
        },
        {
            "inputId": "deptFlow",
            "value": "${param.deptFlow}"
        },
         {
            "inputId": "operUserFlow",
            "value": "${param.docFlow}"
        },
         {
            "inputId": "roleFlag",
            "value": "${roleFlag}"
        },
         {
            "inputId": "recFlow",
			"value": "${rec.recFlow}"
        },
         {
            "inputId": "processFlow",
            "value": "${processFlow}"
        },
         {
            "inputId": "recordFlow",
            "value": "${param.recordFlow}"
        },
         {
            "inputId": "name",
            "value": <c:if test="${empty formDataMap['name']}">
						"${doctor.doctorName}"
					</c:if>
					<c:if test="${not empty formDataMap['name']}">
						"${formDataMap['name']}"
					</c:if>
        },
         {
            "inputId": "sessional",
            "value": <c:if test="${empty formDataMap['sessional']}">
						"${doctor.sessionNumber}"
					</c:if>
					<c:if test="${not empty formDataMap['sessional']}">
						"${formDataMap['sessional']}"
					</c:if>
        },
         {
            "inputId": "trainMajor",
            "value": <c:if test="${empty formDataMap['trainMajor']}">
						"${doctor.trainingSpeName}"
					</c:if>
					<c:if test="${not empty formDataMap['trainMajor']}">
						"${formDataMap['trainMajor']}"
					</c:if>
        },
         {
            "inputId": "deptName",
            "value": <c:if test="${empty formDataMap['deptName']}">
						"${resDoctorSchProcess.schDeptName}"
					</c:if>
					<c:if test="${not empty formDataMap['deptName']}">
						"${formDataMap['deptName']}"
					</c:if>
        },
         {
            "inputId": "cycleTimeQ",
            "value": <c:if test="${empty formDataMap['cycleTimeQ']}">
						"${resDoctorSchProcess.schStartDate}"
					</c:if>
					<c:if test="${not empty formDataMap['cycleTimeQ']}">
						"${formDataMap['cycleTimeQ']}"
					</c:if>
        },
         {
            "inputId": "cycleTimeH",
            "value":<c:if test="${empty formDataMap['cycleTimeH']}">
						"${resDoctorSchProcess.schEndDate}"
					</c:if>
					<c:if test="${not empty formDataMap['cycleTimeH']}">
						"${formDataMap['cycleTimeH']}"
					</c:if>
        },
         {
            "inputId": "bssx",
            "value": "${formDataMap['bssx']}"
        },
         {
            "inputId": "bssx_Label",
            "value": "${formDataMap['bssx_Label']}"
        },
         {
            "inputId": "ydyf",
            "value": "${formDataMap['ydyf']}"
        },
         {
            "inputId": "ydyf_Label",
            "value": "${formDataMap['ydyf_Label']}"
        },
         {
            "inputId": "cqqk",
            "value": "${formDataMap['cqqk']}"
        },
         {
            "inputId": "cqqk_Label",
            "value": "${dataMap['cqqk_Label']}"
        },
         {
            "inputId": "lzjh",
            "value": "${dataMap['lzjh']}"
        },
         {
            "inputId": "lzjh_Label",
            "value": "${dataMap['lzjh_Label']}"
        },
         {
            "inputId": "theoreResult",
            "value": "${dataMap['theoreResult']}"
        },
         {
            "inputId": "theoreResult_Label",
            "value": "${dataMap['theoreResult_Label']}"
        },
         {
            "inputId": "MedicalHistoryScore",
            "value": "${dataMap['MedicalHistoryScore']}"
        },
         {
            "inputId": "MedicalHistoryScore_Label",
            "value": "${dataMap['MedicalHistoryScore_Label']}"
        },
         {
            "inputId": "PhysiqueScore",
            "value": "${dataMap['PhysiqueScore']}"
        },
         {
            "inputId": "PhysiqueScore_Label",
            "value": "${dataMap['PhysiqueScore_Label']}"
        },
         {
            "inputId": "CaseAnalysisScore",
            "value": "${dataMap['CaseAnalysisScore']}"
        },
         {
            "inputId": "CaseAnalysisScore_Label",
            "value": "${dataMap['CaseAnalysisScore_Label']}"
        },
         {
            "inputId": "ClinicalScore",
            "value": "${dataMap['ClinicalScore']}"
        },
         {
            "inputId": "ClinicalScore_Label",
            "value": "${formDataMap['ClinicalScore_Label']}"
        },
         {
            "inputId": "ckkhScore",
            "value": "${formDataMap['ckkhScore']}"
        },
         {
            "inputId": "teacherName",
            "value":"${formDataMap['teacherName']}"
        },
         {
            "inputId": "teacherDate",
            "value": "${formDataMap['teacherDate']}"
        },
         {
            "inputId": "directorName",
            "value":"${formDataMap['directorName']}"
        },
         {
            "inputId": "directorDate",
            "value": "${formDataMap['directorDate']}"
        },
         {
            "inputId": "professionalBase",
            "value":"${formDataMap['professionalBase']}"
        },
         {
            "inputId": "baseDate",
            "value": "${formDataMap['baseDate']}"
        }
    ]
    </c:if>
}
