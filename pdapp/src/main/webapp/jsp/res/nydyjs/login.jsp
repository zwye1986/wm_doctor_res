<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
,
    "userInfo": {
        "userFlow": ${pdfn:toJsonString(userinfo.userid)},
        "userName": ${pdfn:toJsonString(userinfo.truename)},
        "userSex": "${userinfo.sex ? '男':'女'}",
        "roleId":"6"
    }
    </c:if>
}
