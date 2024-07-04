<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "data": {
        "recordFlow":"${kq.recordFlow}",
        "subName":"${fn:substring(kq.doctorName , fn:length(kq.doctorName)-2 , fn:length(kq.doctorName))}",
        "doctorName":"${kq.doctorName}",
        "kqTypeId":"${kq.kqTypeId}",
        "kqTypeName":"${kq.kqTypeName}",
        "typeId":"${kq.typeId}",
        "typeName":"${kq.typeName}",
        "startDate":"${kq.startDate}",
        "endDate":"${kq.endDate}",
        "intervalDays":"${kq.intervalDays}",
        "doctorRemarks":"${kq.doctorRemarks}",
        "schDeptName":"${kq.schDeptName}",
        "processFlow":"${kq.processFlow}",
        "submitTime":"${pdfn:transDateTime(kq.createTime)}",
        "auditStatusId":"${kq.auditStatusId}",
        "auditStatusName":"${kq.auditStatusName}"
    },
    "files": [
        <c:forEach items="${files}" var="b" varStatus="s">
            {
            "fileFlow":"${b.fileFlow}",
            "fileName":"${b.fileName}",
            <c:set var="filePath2" value="${uploadBaseUrl}/${b.filePath}"/>
            <c:set var="filePath" value="${empty b.filePath?'':filePath2}"/>
            "filePath":${pdfn:toJsonString(filePath)}
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
    ],
    "leaveList": [
    <c:forEach items="${leaveMapList}" var="leave" varStatus="s">
        {
        "auditInfo":"${leave.auditInfo}",
        "auditTime":"${leave.auditTime}",
        "roleId":"${leave.roleId}",
        "roleName":"${leave.roleName}",
        "userName":"${leave.userName}",
        "auditName":${pdfn:toJsonString(leave.auditName)}
        }
        <c:if test="${not s.last }">
            ,
        </c:if>
    </c:forEach>
    ]
</c:if>
}
