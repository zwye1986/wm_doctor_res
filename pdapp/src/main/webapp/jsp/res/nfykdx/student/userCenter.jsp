<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
        "userSex": ${pdfn:toJsonString(userinfo.sexName)},
        "userName": ${pdfn:toJsonString(userinfo.userName)},
        "sessionNumber": ${pdfn:toJsonString(doctor.sessionNumber)},
        "trainingYears": ${pdfn:toJsonString(doctor.trainingYears)},
        "rotationFlow": ${pdfn:toJsonString(doctor.rotationFlow)},
        "rotationName": ${pdfn:toJsonString(doctor.rotationName)},
        "orgFlow": ${pdfn:toJsonString(doctor.orgFlow)},
        "orgName": ${pdfn:toJsonString(doctor.orgName)},
        "trainingSpeId": ${pdfn:toJsonString(doctor.trainingSpeId)},
        "trainingSpeName": ${pdfn:toJsonString(doctor.trainingSpeName)},
        "startDate": ${pdfn:toJsonString(minDate)},
        "endDate": ${pdfn:toJsonString(maxDate)},
        "schDays": ${pdfn:toJsonString(empty schDays?'0':schDays)},
        "trainingDays": ${pdfn:toJsonString(empty trainingDays?'0':trainingDays)},
        "schProcess": ${pdfn:toJsonString(empty schProcess?'0':schProcess)},
        "manualRotationFlag":${pdfn:toJsonString(manualRotationFlag)},
        "isCkk": ${isCkk}
    }
    </c:if>
}