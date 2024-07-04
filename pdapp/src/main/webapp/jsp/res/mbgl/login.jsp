<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
            "userFlow": ${pdfn:toJsonString(userinfo.yhm)},
            "userSex": ${pdfn:toJsonString(userSex)},
            "userName": ${pdfn:toJsonString(userinfo.zsxm)},
            "ringId": ${pdfn:toJsonString(userinfo.ringId)},
            "ringPass": ${pdfn:toJsonString(userinfo.ringPass)},
            "roleId": ${pdfn:toJsonString(userinfo.type)},
            "roleName": ${pdfn:toJsonString(roleName)}
        },
    "users":[
        <c:forEach items="${users}" var="u" varStatus="status">
            {
                "userFlow":${pdfn:toJsonString(u.yhm)},
                "userName": ${pdfn:toJsonString(u.zsxm)},
                "ringId": ${pdfn:toJsonString(u.ringId)}
            }
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}