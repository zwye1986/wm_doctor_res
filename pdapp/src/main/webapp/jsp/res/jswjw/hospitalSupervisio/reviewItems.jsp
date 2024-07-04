<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "num": "${num}",
    "reviewedNum": "${reviewedNum}",
    "reviewList": [
        <c:forEach items="${list}" var="r" varStatus="status">
            {
                "subjectFlow": "${r.subjectFlow}",
                "subjectName": "${r.subjectName}",
                "speId": "${r.speId}",
                "speName": "${r.speName}",
                "scoreTable": "${r.scoreTable}",
                "activityName": "${r.activityName}",
                "activityStartTime": "${r.activityStartTime}",
                "activityEndTime": "${r.activityEndTime}",
                "score": "${userFlow== r.leaderOneFlow ? r.leaderOneScore:r.leaderTwoScore}",
                "leaderSubNum":"${empty r.leaderSubNum ? 0 :r.leaderSubNum}",
                "toEdit":"${r.modifyTime}",
                "toDel":"${r.modifyUserFlow}",
                "orgFlow":"${orgFlow}",
                "orgName":"${orgName}"
            }
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
    ],
    "deptList": [
    <c:if test="${empty activityTypeEnumList}">
        {
        "inspectionType": "",
        "inspectionTypeName": "请选择"
        }
    </c:if>
    <c:if test="${not empty activityTypeEnumList}">
        {
        "inspectionType": "",
        "inspectionTypeName": "请选择"
        },
    </c:if>
    <c:forEach items="${activityTypeEnumList}" var="r" varStatus="status">
        {
        "inspectionType": "${r.id}",
        "inspectionTypeName": "${r.name}"
        }
        <c:if test="${!status.last}">
            ,
        </c:if>
    </c:forEach>
    ]
}
