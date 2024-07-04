<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	<c:if test="${param.userCode eq 'student' }">
	    "userInfo": {
	        "userFlow": "student",
	        "userSex": "男",
	        "userName": "张三",
			"roleId": "Student",
			"roleName": "医师",
			"sessionNumber": "2015",
			"trainingYears": "三年",
			"rotationFlow": "123456",
			"rotationName": "2014国家西医内科轮转方案",
			"orgFlow": "123456",
			"orgName": "江苏省人民医院",
			"trainingSpeId": "21",
			"trainingSpeName": "内科",
			"startDate": "2014-09-15",
			"endDate": "2014-10-15",
			"schDays": "30",
			"schProcess":20
	    }
	    </c:if>
	    <c:if test="${param.userCode eq 'teacher' }">
	    "userInfo": {
	        "userFlow": "teacher",
	        "userSex": "男",
	        "userName": "张老师",
			"roleId": "Teacher",
			"roleName": "老师"
	    }
	    </c:if>
	    <%-- <c:if test="${isDoctor}">
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
				"startDate": "",
				"endDate": "",
				"schDays": "",
				"schProcess":""
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
	    </c:if> --%>
    </c:if>
}
