<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "doctor":{
        "signupYear":"${currYear}",
        "sessionNumber":"${doctor.sessionNumber}",
        "trainingYears":"<c:if test="${'OneYear' eq doctor.trainingYears}">一年</c:if><c:if test="${'TwoYear' eq doctor.trainingYears}">两年</c:if><c:if test="${'ThreeYear' eq doctor.trainingYears}">三年</c:if>",
        "orgName":"${doctor.orgName}",
        "orgFlow":"${doctor.orgFlow}",
        "trainingTypeId":"${doctor.trainingTypeId}",
        "trainingTypeName":"${doctor.trainingTypeName}",
        "trainingSpeId":"${doctor.trainingSpeId}",
        "trainingSpeName":"${doctor.trainingSpeName}",
        "trainingSpeId":"${doctor.trainingSpeId}",
        "trainingSpeName":"${doctor.trainingSpeName}",
        <c:if test="${not empty needChange && needChange eq 'N'}">
            "changeSpeId":"${doctor.trainingSpeId}",
            "changeSpeName":"${doctor.trainingSpeName}",
        </c:if>
        "userName":"${user.userName}",
        "cretTypeName":"${user.cretTypeName}",
        "idNo":"${user.idNo}"
        },
        "needChange":"${needChange}",

        "spes":[
        <c:forEach items="${spes}" var="spe" varStatus="speStatus">
            {
            "changeSpeId":"${spe.speId}",
            "changeSpeName":"${spe.speName}"
            }
            <c:if test="${not speStatus.last}">
                ,
            </c:if>
        </c:forEach>
        ]


    </c:if>
}
