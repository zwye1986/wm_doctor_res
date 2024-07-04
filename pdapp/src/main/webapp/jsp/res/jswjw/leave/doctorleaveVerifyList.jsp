<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "dataCount": ${dataCount},
    "datas": [
    <c:forEach items="${kqList}" var="b" varStatus="s">
        {
        "recordFlow":"${b.recordFlow}",
        "subName":"${fn:substring(b.doctorName , fn:length(b.doctorName)-2 , fn:length(b.doctorName))}",
        "doctorName":"${b.doctorName}",
        "kqTypeId":"${b.kqTypeId}",
        "kqTypeName":"${b.kqTypeName}",
        "typeId":"${b.typeId}",
        "typeName":"${b.typeName}",
        "startDate":"${b.startDate}",
        "endDate":"${b.endDate}",
        "intervalDays":"${b.intervalDays}",
        "doctorRemarks":"${b.doctorRemarks}",
        "schDeptName":"${b.schDeptName}",
        "processFlow":"${b.processFlow}",
        "submitTime":"${pdfn:transDateTime(b.createTime)}",
        "auditStatusId":"${b.auditStatusId}",
        "auditStatusName":"${b.auditStatusName}",
        "teacherFlow":"${b.teacherFlow}",
        "teacherName":"${b.teacherName}",
        "headFlow":"${b.headFlow}",
        "headName":"${b.headName}",
        "managerFlow":"${b.managerFlow}",
        "managerName":"${b.managerName}",
        "fileFlows":"${b.fileFlows}",
        "action":[
        <c:if test="${b.auditStatusId ne 'ManagerPass' and b.auditStatusId ne 'ManagerUnPass' and b.auditStatusId ne 'RevokeManagerPass'
                        and b.auditStatusId ne 'BackLeave' or b.auditStatusId eq 'ManagerPass'}">
            {
            "id":"${b.operId}",
            "name":"${b.operName}"
            }
        </c:if>
        ]
        }
        <c:if test="${not s.last }">
            ,
        </c:if>
    </c:forEach>
    ]
</c:if>
}