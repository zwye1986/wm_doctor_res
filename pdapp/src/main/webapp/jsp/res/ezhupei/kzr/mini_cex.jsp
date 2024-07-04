<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    		<jsp:include page="/jsp/res/hbres/teacher/inputMini.jsp"></jsp:include>
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
                    "inputId": "grade",
                    "value": "${doctor.sessionNumber}"
              },
              {
                    "inputId": "professional",
                    "value": "${doctor.trainingSpeName}"
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
                    "inputId": "age",
                    "value": "${formDataMap['age']}"
              },
              {
                    "inputId": "sex",
                    "value": "${formDataMap['sex']}"
              },
              {
                    "inputId": "patientSource",
                    "value": "${formDataMap['patientSource']}"
              },
              {
                    "inputId": "patientSourceType",
                    "value": "${formDataMap['patientSourceType']}"
              },
              {
                    "inputId": "diagnosis",
                    "value": "${formDataMap['diagnosis']}"
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
                    "inputId": "clinicalJudgment",
                    "value": "${formDataMap['clinicalJudgment']}"
              },
              {
                    "inputId": "communicationSkills",
                    "value": "${formDataMap['communicationSkills']}"
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
                    "inputId": "observationTime",
                    "value": "${formDataMap['observationTime']}"
              },
              {
                    "inputId": "feedbackTime",
                    "value": "${formDataMap['feedbackTime']}"
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
                    "value": "${formDataMap['studentSign']}"
              },
              {
                    "inputId": "studentSign1",
                    "value": "${formDataMap['studentSign']}"
              }

    ]
    </c:if>
}
