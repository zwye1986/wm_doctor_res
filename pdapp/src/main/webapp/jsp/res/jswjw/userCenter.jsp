<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
        "userFlow": ${pdfn:toJsonString(doctorInfoMap.doctor.doctorFlow)},
        "userName": ${pdfn:toJsonString(doctorInfoMap.doctor.doctorName)},
        "isVerify": ${pdfn:toJsonString(userinfo.isVerify)},
        "userPhone": ${pdfn:toJsonString(userinfo.userPhone)},
        "sessionNumber": ${pdfn:toJsonString(doctorInfoMap.doctor.sessionNumber)},
        "trainingYears": ${pdfn:toJsonString(doctorInfoMap.doctor.trainingYears)},
        "schDays": ${pdfn:toJsonString(empty doctorInfoMap.schDays?'0':doctorInfoMap.schDays)},
        "trainingDays": ${pdfn:toJsonString(empty doctorInfoMap.trainingDays?'0':doctorInfoMap.trainingDays)},
        "schProcess": ${pdfn:toJsonString(empty doctorInfoMap.schProcess?'0':doctorInfoMap.schProcess)},
        "startDate": ${pdfn:toJsonString(doctorInfoMap.startDate)},
        "endDate": ${pdfn:toJsonString(doctorInfoMap.endDate)},
        <%--"orgName": ${pdfn:toJsonString(doctorInfoMap.doctor.orgName)},--%>
        "orgFlow": ${pdfn:toJsonString(empty doctorInfoMap.resDoctorRecruit.jointOrgFlow?doctorInfoMap.resDoctorRecruit.orgFlow:doctorInfoMap.resDoctorRecruit.jointOrgFlow)},
        "orgName": ${pdfn:toJsonString(empty doctorInfoMap.resDoctorRecruit.jointOrgFlow?doctorInfoMap.resDoctorRecruit.orgName:doctorInfoMap.resDoctorRecruit.jointOrgName)},
        "trainingSpeName": ${pdfn:toJsonString(doctorInfoMap.doctor.trainingSpeName)},
        "tipFlag": ${pdfn:toJsonString(tipFlag)},
        "tipContent":${pdfn:toJsonString(tipContent)},
        "isCkk": ${isCkk},
        "isPxsc": ${isPxsc},
        "isNdks": ${pdfn:toJsonString(isNdks)},
        "isActivity": ${isActivity},
        "isAttendance": ${isAttendance},
        "appMenu": ${appMenu}
    }
    </c:if>
}
