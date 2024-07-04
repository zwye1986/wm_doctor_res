<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        <c:if test="${empty file.filePath}">
            <c:set var="filePath" value=""/>
        </c:if>
        <c:if test="${not empty file.filePath}">
            <c:set var="filePath" value="${uploadBaseUrl}/${file.filePath}"/>
        </c:if>
        "userInfo": {
            "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
            "userSex": ${pdfn:toJsonString(userinfo.sexName)},
            "userName": ${pdfn:toJsonString(userinfo.userName)},
            "orgName": ${pdfn:toJsonString(userinfo.orgName)},
            "siginImage": ${pdfn:toJsonString(filePath)},
            "codeInfo": ${pdfn:toJsonString(codeInfo)},
            "roleId":${pdfn:toJsonString(roleFlow)},
            "roleName": ${pdfn:toJsonString(roleName)},
            "isNew": ${pdfn:toJsonString(isNew)}
            }
    </c:if>
}