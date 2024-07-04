<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
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
				"startDate": ${pdfn:toJsonString(minDate)},
				"endDate": ${pdfn:toJsonString(maxDate)},
				"schDays":${pdfn:toJsonString(empty schDays?'0':schDays)},
				"schProcess":${pdfn:toJsonString(empty schProcess?'0':schProcess)},
				"manualRotationFlag":${pdfn:toJsonString(manualRotationFlag)},
				"isInBySelfFlag":${pdfn:toJsonString(isInBySelfFlag)},
				"isCkk": ${isCkk}
		    }
	    </c:if>
	    <c:if test="${!isDoctor && !isSuper and !isHospitalLeader}">
		    "userInfo": {
		        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		        "userSex": ${pdfn:toJsonString(userinfo.sexName)},
		        "userName": ${pdfn:toJsonString(userinfo.userName)},
				"roleId": ${pdfn:toJsonString(roleId)},
				"roleName": ${pdfn:toJsonString(roleName)}
		    }
	    </c:if>
		<c:if test="${isSuper}">
			"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
			"userCode": ${pdfn:toJsonString(userinfo.userCode)},
			"userName": ${pdfn:toJsonString(userinfo.userName)},
			"userPhone": ${pdfn:toJsonString(userinfo.userPhone)},
			"userLevelId": ${pdfn:toJsonString(userinfo.userLevelId)},
			"userLevelName": ${pdfn:toJsonString(userinfo.userLevelName)},
			"speId": ${pdfn:toJsonString(userinfo.speId)},
			"speName": ${pdfn:toJsonString(userinfo.speName)},
			"userSignUrl": ${pdfn:toJsonString(userinfo.userSignUrl)}
			}
		</c:if>
		<c:if test="${isHospitalLeader}">
			"subjectNum":${pdfn:toJsonString(subjectNum)},
			"userInfo":{
			"userFlow": ${pdfn:toJsonString(sysUser.userFlow)},
			"userCode": ${pdfn:toJsonString(sysUser.userCode)},
			"userName": ${pdfn:toJsonString(sysUser.userName)},
			"userPhone": ${pdfn:toJsonString(sysUser.userPhone)},
			"userLevelId": ${pdfn:toJsonString(sysUser.userLevelId)},
			"userLevelName": ${pdfn:toJsonString(sysUser.userLevelName)},
			"speId": ${pdfn:toJsonString(sysUser.resTrainingSpeId)},
			"speName": ${pdfn:toJsonString(sysUser.resTrainingSpeName)},
			"showPath": ${pdfn:toJsonString(showPath)}
			}
		</c:if>
    </c:if>
}
