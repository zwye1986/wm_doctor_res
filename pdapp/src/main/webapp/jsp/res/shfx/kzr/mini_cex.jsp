<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    		<jsp:include page="/jsp/res/shfx/teacher/inputMini.jsp"></jsp:include>
    ],
    "values": [
              {
                    "inputId": "formFileName",
                    "value": ${pdfn:toJsonString(formFileName)}
                },
                {
                    "inputId": "schDeptFlow",
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
                    "value": "${param.processFlow}"
                },
              {
                    "inputId": "ZongHe",
                    "value": "${formDataMap['ZongHe']}"
              },
              {
                    "inputId": "studentName",
                    "value": "${doctor.doctorName}"
              },
              {
                    "inputId": "stuLevel",
                    "value": "${formDataMap['stuLevel']}"
              },
              {
                    "inputId": "professional",
                    "value": "${doctor.trainingSpeName}"
              },
              {
                    "inputId": "stuSid",
                    "value": "${formDataMap['stuSid']}"
              },
              {
                    "inputId": "studentType",
                    "value": "${formDataMap['studentType']}"
              },
              {
                    "inputId": "teacherType",
                    "value": "${formDataMap['teacherType']}"
              },
              {
                    "inputId": "assessmentDate",
                    "value": "${formDataMap['assessmentDate']}"
              },
              {
                    "inputId": "assessmentPlace",
                    "value": "${formDataMap['assessmentPlace']}"
              },
              {
                    "inputId": "patientName",
                    "value": "${formDataMap['patientName']}"
              },
              {
                    "inputId": "sex",
                    "value": "${formDataMap['sex']}"
              },
              {
                    "inputId": "age",
                    "value": "${formDataMap['age']}"
              },
              {
                    "inputId": "deptName",
                    "value": "${formDataMap['deptName']}"
              },
              {
                    "inputId": "qutpatientNum",
                    "value": "${formDataMap['qutpatientNum']}"
              },
              {
                    "inputId": "diagnosis",
                    "value": "${formDataMap['diagnosis']}"
              },
              {
                    "inputId": "patientConsent",
                    "value": "${formDataMap['patientConsent']}"
              },
              {
                    "inputId": "severity",
                    "value": "${formDataMap['severity']}"
              },
              {
                    "inputId": "diagnosisKeynote",
                    "value": "${formDataMap['diagnosisKeynote']}"
              },
              {
                    "inputId": "observationTime",
                    "value": "${formDataMap['observationTime']}"
              },
              {
                    "inputId": "feedbackTime",
                    "value": "${formDataMap['feedbackTime']}"
              },
              {
                    "inputId": "medicalInterview",
                    "value": "${formDataMap['medicalInterview']}"
              },
              {
                    "inputId": "physicalExamination",
                    "value": "${formDataMap['physicalExamination']}"
              },
              {
                    "inputId": "humanisticCare",
                    "value": "${formDataMap['humanisticCare']}"
              },
              {
                    "inputId": "communicationSkills",
                    "value": "${formDataMap['communicationSkills']}"
              },
              {
                    "inputId": "clinicalJudgment",
                    "value": "${formDataMap['clinicalJudgment']}"
              },
              {
                    "inputId": "organization",
                    "value": "${formDataMap['organization']}"
              },
              {
                    "inputId": "holisticCare",
                    "value": "${formDataMap['holisticCare']}"
              },
              {
                    "inputId": "assessmentTea",
                    "value": "${formDataMap['assessmentTea']}"
              },
              {
                    "inputId": "assessmentDate",
                    "value": "${formDataMap['assessmentDate']}"
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
                    "inputId": "teacherSign",
                    "value":"${formDataMap['teacherSign']}"
              },
              {
                    "inputId": "doctorComment",
                    "value": "${formDataMap['doctorComment']}"
              },
              {
                    "inputId": "studentSign",
                    "value": "${formDataMap['studentSign']}"
              },
              {
                    "inputId": "studentDegreeSatisfaction",
                    "value": "${formDataMap['studentDegreeSatisfaction']}"
              }

    ]
    </c:if>
}
