<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"hasNotReadInfo": ${pdfn:toJsonString(hasNotReadInfo)},
	    <c:if test="${isDoctor}">
		    "userInfo": {
		        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		        "userSex": ${pdfn:toJsonString(userinfo.sexName)},
		        "userName": ${pdfn:toJsonString(userinfo.userName)},
				"roleId": ${pdfn:toJsonString(roleId)},
				"roleName": ${pdfn:toJsonString(roleName)},
				"sessionNumber": ${pdfn:toJsonString(doctor.sessionNumber)},
				"trainingYears": ${pdfn:toJsonString(doctor.trainingYears)},
				"rotationFlow": ${pdfn:toJsonString(doctor.rotationFlow)},
				"rotationName": ${pdfn:toJsonString(doctor.rotationName)},
				"orgFlow": ${pdfn:toJsonString(doctor.orgFlow)},
				"orgName": ${pdfn:toJsonString(doctor.orgName)},
				"trainingSpeId": ${pdfn:toJsonString(doctor.trainingSpeId)},
				"trainingSpeName": ${pdfn:toJsonString(doctor.trainingSpeName)},
				"secondSpeId": ${pdfn:toJsonString(doctor.secondSpeId)},
				"secondSpeName": ${pdfn:toJsonString(doctor.secondSpeName)},
				"secondRotationFlow": ${pdfn:toJsonString(doctor.secondRotationFlow)},
				"secondRotationName": ${pdfn:toJsonString(doctor.secondRotationName)},
				"startDate": ${pdfn:toJsonString(minDate)},
				"endDate": ${pdfn:toJsonString(maxDate)},
				"schDays": ${pdfn:toJsonString(schDays)},
				"schProcess":${pdfn:toJsonString(schProcess)},
				"manualRotationFlag":${pdfn:toJsonString(manualRotationFlag)},
				"isInBySelfFlag":${pdfn:toJsonString(isInBySelfFlag)},
				"isCkk": ${isCkk}
		    }
	    </c:if>
	    <c:if test="${!isDoctor}">
		    "userInfo": {
		        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		        "userSex": ${pdfn:toJsonString(userinfo.sexName)},
		        "userName": ${pdfn:toJsonString(userinfo.userName)},
				"roleId": ${pdfn:toJsonString(roleId)},
				"roleName": ${pdfn:toJsonString(roleName)}
		    }
	    </c:if>
    </c:if>
}
