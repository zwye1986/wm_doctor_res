<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    		<jsp:include page="/jsp/res/shfx/teacher/inputDOPS.jsp"></jsp:include>
    ],
    "values":[
        {
        "inputId": "formFileName",
        "value":"${formFileName}"
        },
        {
        "inputId": "schDeptFlow",
        "value":"${param.deptFlow}"
        },
        {
        "inputId": "recFlow",
        "value":"${param.recFlow}"
        },
        {
        "inputId": "roleFlag",
        "value":"${roleFlag}"
        },
        {
        "inputId": "operUserFlow",
        "value":"${param.docFlow}"
        },
        {
        "inputId": "processFlow",
        "value":"${param.processFlow}"
        },
        {
        "inputId": "ZongHe",
        "value":"${formDataMap['ZongHe']}"
        },
        {
        "inputId": "studentName",
        "value":"${doctor.doctorName}"
        },
        {
        "inputId": "grade",
        "value":"${doctor.sessionNumber}"
        },
        {
        "inputId": "professional",
        "value":"${doctor.trainingSpeName}"
        },
        {
        "inputId": "studentType",
        "value":"${formDataMap['studentType']}"
        },
        {
        "inputId": "teacherType",
        "value":"${formDataMap['teacherType']}"
        },
        {
        "inputId": "assessmentDate",
        "value":"${formDataMap['assessmentDate']}"
        },
        {
        "inputId": "assessmentPlace",
        "value":"${formDataMap['assessmentPlace']}"
        },
        {
        "inputId": "patientName",
        "value":"${formDataMap['patientName']}"
        },
        {
        "inputId": "age",
        "value":"${formDataMap['age']}"
        },
        {
        "inputId": "sex",
        "value":"${formDataMap['sex']}"
        },
        {
        "inputId": "patientSource",
        "value":"${formDataMap['patientSource']}"
        },
        {
        "inputId": "patientSourceType",
        "value":"${formDataMap['patientSourceType']}"
        },
        {
        "inputId": "patientDiagnosis",
        "value":"${formDataMap['patientDiagnosis']}"
        },
        {
        "inputId": "operatingSkills",
        "value":"${formDataMap['operatingSkills']}"
        },
        {
        "inputId": "studentSkillNum",
        "value":"${formDataMap['studentSkillNum']}"
        },
        {
        "inputId": "skillComplexDegree",
        "value":"${formDataMap['skillComplexDegree']}"
        },
        {
        "inputId": "skillLevel",
        "value":"${formDataMap['skillLevel']}"
        },
        {
        "inputId": "consentForm",
        "value":"${formDataMap['consentForm']}"
        },
        {
        "inputId": "readyToWork",
        "value":"${formDataMap['readyToWork']}"
        },
        {
        "inputId": "painAndStabilization",
        "value":"${formDataMap['painAndStabilization']}"
        },
        {
        "inputId": "SkillAbility",
        "value":"${formDataMap['SkillAbility']}"
        },
        {
        "inputId": "asepticTechnique",
        "value":"${formDataMap['asepticTechnique']}"
        },
        {
        "inputId": "seekAssistance",
        "value":"${formDataMap['seekAssistance']}"
        },
        {
        "inputId": "relatedDisposal",
        "value":"${formDataMap['relatedDisposal']}"
        },
        {
        "inputId": "communicationSkills",
        "value":"${formDataMap['communicationSkills']}"
        },
        {
        "inputId": "feelProfessionalDegree",
        "value":"${formDataMap['feelProfessionalDegree']}"
        },
        {
        "inputId": "overallPerformance",
        "value":"${formDataMap['overallPerformance']}"
        },
        {
        "inputId": "observationTime",
        "value":"${formDataMap['observationTime']}"
        },
        {
        "inputId": "feedbackTime",
        "value":"${formDataMap['feedbackTime']}"
        },
        {
        "inputId": "degreeSatisfaction",
        "value": "${formDataMap['degreeSatisfaction']}"
        },
        {
        "inputId": "teacherComment",
        "value": "${formDataMap['teacherComment']}"
        },
        {
        "inputId": "studentDegreeSatisfaction",
        "value": "${formDataMap['studentDegreeSatisfaction']}"
        },
        {
        "inputId": "teacherSign",
        "value":"${formDataMap['teacherSign']}"
        },
        {
        "inputId": "studentSign",
        "value":"${formDataMap['studentSign']}"
        },
        {
        "inputId": "studentSign1",
        "value":"${formDataMap['studentSign']}"
        }


        ]
    </c:if>
}
