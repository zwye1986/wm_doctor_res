<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "compelete":"${compelete}"
    <c:if test="${resultId eq '200' }">
        ,
        "userInfo": {
            "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
            "userSex": ${pdfn:toJsonString(userinfo.sexName)},
            "userName": ${pdfn:toJsonString(userinfo.userName)},
            "roleId": ${pdfn:toJsonString(roleId)},
            "roleName": ${pdfn:toJsonString(roleName)}
            }
    </c:if>
}