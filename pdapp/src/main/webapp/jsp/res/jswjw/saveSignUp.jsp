<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "user":{
        "userName":"${user.userName}",
        "cretTypeName":"${user.cretTypeName}",
        "idNo":"${user.idNo}"
        },
    "doctor":{
        "sessionNumber":"${doctor.sessionNumber}",
        "trainingYears":"<c:if test="${'OneYear' eq doctor.trainingYears}">一年</c:if><c:if test="${'TwoYear' eq doctor.trainingYears}">两年</c:if><c:if test="${'ThreeYear' eq doctor.trainingYears}">三年</c:if>",
        "orgName":"${doctor.orgName}",
        "orgFlow":"${doctor.orgFlow}",
        "trainingTypeId":"${doctor.trainingTypeId}",
        "trainingTypeName":"${doctor.trainingTypeName}",
        "trainingSpeId":"${doctor.trainingSpeId}",
        "trainingSpeName":"${doctor.trainingSpeName}",
        "trainingSpeId":"${doctor.trainingSpeId}",
        "trainingSpeName":"${doctor.trainingSpeName}"
        },
	"userFlow": "${userFlow}",
        "needChange":"${needChange}",
        "needChange":"${needChange}",
        "spes":"${spes}"
    </c:if>
}
