<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "isRecruit":"${isRecruit}",
        "roles":[
        <c:forEach items="${roles}" var="role" varStatus="s">
            {
            "roleId":"${role.roleId}",
            "roleName":"${role.roleName}"
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ],
        "hasNotReadInfo": ${pdfn:toJsonString(hasNotReadInfo)},
        "pkModulus":${pdfn:toJsonString(pkModulus)},
        "pkExponent":${pdfn:toJsonString(pkExponent)},
        "isStrongPasswd":${pdfn:toJsonString(isStrongPasswd)},
        <c:if test="${isDoctor}">
            "userInfo": {
            "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
            "userName": ${pdfn:toJsonString(userinfo.userName)},
            "userSex": "${userinfo.sexName}",
            "roleId": ${pdfn:toJsonString(roleId)},
            "roleName": ${pdfn:toJsonString(roleName)},
            "authFlag": "${authFlag}",
            "isZJ": "${isZJ}",
            "isChargeOrg": "${isChargeOrg}",
            "token": ${pdfn:toJsonString(token)}
            }
        </c:if>
        <c:if test="${isTeacher or isAdmin or isHead or isSeretary or isCharge or isNurse}">
            "userInfo": {
            "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
            "userSex": ${pdfn:toJsonString(userinfo.sexName)},
            "userName": ${pdfn:toJsonString(userinfo.userName)},
            "orgFlow": ${pdfn:toJsonString(userinfo.orgFlow)},
            "orgName": ${pdfn:toJsonString(userinfo.orgName)},
            "deptFlow": ${pdfn:toJsonString(userinfo.deptFlow)},
            "deptName": ${pdfn:toJsonString(userinfo.deptName)},
            "roleId": ${pdfn:toJsonString(roleId)},
            "roleName": ${pdfn:toJsonString(roleName)},
            "isChargeOrg":"${isChargeOrg}",
            "token": ${pdfn:toJsonString(token)}
            },
            "depts":[
                <c:forEach items="${depts}" var="dict" varStatus="s">
                    {
                        "deptFlow":"${dict.deptFlow}",
                        "deptName":"${dict.deptName}"
                    }
                    <c:if test="${not s.last }">
                        ,
                    </c:if>
                </c:forEach>
            ]
        </c:if>

    </c:if>
}