<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
        "userFlow": "${user.userFlow}",
        "userName": "${user.userName}",
        "userSex": "${user.userSex}",
        "roleId":"${user.roleId}",
        "roleName":"${user.roleName}",
        "hosQC":"${user.hosQC}",
        "hosID":"${user.hosID}"
    },
    "roles":[
        <c:forEach items="${roles}" var="role" varStatus="s">
        {
            "roleFlow":"${role.roleFlow}",
            "roleId":"${role.roleId}",
            "roleName":"${role.roleName}"
        }
        <c:if test="${not s.last }">
    ,
        </c:if>
        </c:forEach>
    ]
    </c:if>
}
