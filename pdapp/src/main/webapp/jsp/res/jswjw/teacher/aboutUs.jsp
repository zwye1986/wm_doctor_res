<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
        "userName": ${pdfn:toJsonString(userinfo.userName)},
        "userSex": "${userinfo.sexName}"
    },
    "imgurl":${pdfn:toJsonString(imgurl)},
    "androidimgurl":${pdfn:toJsonString(androidimgurl)}
    </c:if>
}
