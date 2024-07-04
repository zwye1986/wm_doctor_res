<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"orgFlow":"${orgFlow}",
"orgName": "${orgName}",
"cityName": "${cityName}",
"speSkillName": "${speSkillName}",
"teacherName": "${teacherName}",
"scoreTable": "${scoreTable}",
"subjectFlow": "${subjectFlow}",
"speId": "${speId}",
"speName": "${speName}",
"deptName": "${deptName}",
"speSignUrl": "${speSignUrl}",
"peopleNum": "${peopleNum}",
"activityMinute": "${activityMinute}",
"activityUserDept": "${activityUserDept}",
"activityUserName": "${activityUserName}",
"activityStartTime": "${activityStartTime}",
"activityEndTime": "${activityEndTime}",
"activityDept": "${activityDept}",
"speAndDept": "${speAndDept}",
"leaderScore":"${leaderScore}",
"scheduleDate":"${scheduleDate}",
"scoreList": [
<c:forEach items="${scoreList}" var="r" varStatus="status">
    {
    "scheduleFlow": "${r.scheduleFlow}",
    "itemId": "${r.itemId}",
    "itemName": "${r.itemName}",
    "score": "${r.score}",
    "itemDetailed": "${r.itemDetailed}"
    }
    <c:if test="${!status.last}">
        ,
    </c:if>
</c:forEach>
]
}
