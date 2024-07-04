<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	"userInfo": {
	        "userFlow": ${pdfn:toJsonString(user.userFlow)},
	        "userCode": ${pdfn:toJsonString(user.userCode)},
	        "userName": ${pdfn:toJsonString(user.userName)},
	        "userSex": ${pdfn:toJsonString(user.userSex)},
	        "userMail": ${pdfn:toJsonString(user.userMail)},
	        "roleName": ${pdfn:toJsonString(user.roleName)}
	    }
    </c:if>
}
