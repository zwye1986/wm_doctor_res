<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "userInfo": {
    "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
    "userSex": ${pdfn:toJsonString(userinfo.sexName)},
    "userName": ${pdfn:toJsonString(userinfo.userName)},
    "roleId": ${pdfn:toJsonString(roleId)},
    "roleName": ${pdfn:toJsonString(roleName)},
    "doctorStatusName": ${pdfn:toJsonString(doctor.doctorStatusName)},
    "graduatedName": ${pdfn:toJsonString(doctor.graduatedName)},
    "sessionNumber": ${pdfn:toJsonString(doctor.sessionNumber)},
    "trainingYears": ${pdfn:toJsonString(doctor.trainingYears)},
    "rotationFlow": ${pdfn:toJsonString(doctor.rotationFlow)},
    "rotationName": ${pdfn:toJsonString(doctor.rotationName)},
    "orgFlow": ${pdfn:toJsonString(doctor.orgFlow)},
    "orgName": ${pdfn:toJsonString(doctor.orgName)},
    "trainingSpeId": ${pdfn:toJsonString(doctor.trainingSpeId)},
    "trainingSpeName": ${pdfn:toJsonString(doctor.trainingSpeName)},
    "startDate": ${pdfn:toJsonString(minDate)},
    "endDate": ${pdfn:toJsonString(maxDate)},
    "schDays": ${schDays+0}
    },
    "data":[
    <c:forEach items="${resultList}" var="result">
        <c:set value="${processMap[result.resultFlow]}" var="process"/>
        {
        "schStatus":
        <c:choose>
            <c:when test="${empty process }">
                "未入科"
            </c:when>
            <c:when test="${'Y' eq process.schFlag }">
                "已出科"
            </c:when>
            <c:when test="${process.isCurrentFlag eq 'Y' }">
                "轮转中"
            </c:when>
            <c:otherwise>""</c:otherwise>
        </c:choose>,
        "schDeptAndScore":{
        "deptAndScore":
        <c:if test="${not empty process}">
            "${process.schDeptName}(<c:out value="${process.schScore}" default="暂无"/>)"
        </c:if>
        <c:if test="${empty process}">
            "${result.schDeptName}"
        </c:if>,
        "isExternal":"${schDeptMap[result.schDeptFlow].isExternal}"
        },
        "schTime":
        <c:if test="${not empty process}">
            "${process.startDate} ~ ${process.endDate}",
        </c:if>
        <c:if test="${empty process}">
            "${result.schStartDate} ~ ${result.schEndDate}",
        </c:if>
        "processPer":"${perMap[process.schResultFlow]+0}%",
        <c:set var="teacherName" value="-"></c:set>
        <c:if test="${not empty process.teacherUserName}">
            <c:set var="teacherName" value="${process.teacherUserName}"></c:set>
        </c:if>
        "teacherName":"${teacherName}",
        <c:set var="headName" value="-"></c:set>
        <c:if test="${not empty process.headUserName}">
            <c:set var="headName" value="${process.headUserName}"></c:set>
        </c:if>
        "headName":"${headName}"
        }
        <c:if test="${!status.last}">
            ,
        </c:if>
    </c:forEach>
    ]
</c:if>
}