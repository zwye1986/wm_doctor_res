<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,

        <c:if test="${empty user.userHeadImg}">
            <c:set var="userHeadImg" value=""/>
        </c:if>
        <c:if test="${not empty user.userHeadImg}">
            <c:set var="userHeadImg" value="${uploadBaseUrl}/${user.userHeadImg}"/>
        </c:if>
    "data":{
             "userFlow":${pdfn:toJsonString(user.userFlow)},
             "userName":${pdfn:toJsonString(user.userName)},
             "sexId":${pdfn:toJsonString(user.sexId)},
             "sexName":${pdfn:toJsonString(user.sexName)},
             "headImg":${pdfn:toJsonString(userHeadImg)},
             "cretTypeId":${pdfn:toJsonString(user.cretTypeId)},
             "cretTypeName":${pdfn:toJsonString(user.cretTypeName)},
             "idNo":${pdfn:toJsonString(user.idNo)},
             "userEmail":${pdfn:toJsonString(user.userEmail)},
             "userPhone":${pdfn:toJsonString(user.userPhone)},
             "trainingTypeId":${pdfn:toJsonString(doctor.trainingTypeId)},
             "trainingTypeName":${pdfn:toJsonString(doctor.trainingTypeName)},
             "trainingSpeId":${pdfn:toJsonString(doctor.trainingSpeId)},
             "trainingSpeName":${pdfn:toJsonString(doctor.trainingSpeName)},
             "sessionNumber":${pdfn:toJsonString(doctor.sessionNumber)},
             "trainingYears":${pdfn:toJsonString(trainingYears)},
             "graduationYear":${pdfn:toJsonString(doctor.graduationYear)},
             "workOrgName":${pdfn:toJsonString(doctor.workOrgName)},
             "orgFlow":${pdfn:toJsonString(doctor.orgFlow)},
             "orgName":${pdfn:toJsonString(doctor.orgName)}
        }
    </c:if>
}
