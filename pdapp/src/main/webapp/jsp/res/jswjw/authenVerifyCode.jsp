<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
        "userFlow": ${pdfn:toJsonString(userInfo.userFlow)},
        "userName": ${pdfn:toJsonString(userInfo.userName)},
        "isVerify": ${pdfn:toJsonString(userInfo.isVerify)}
    }
    </c:if>
}