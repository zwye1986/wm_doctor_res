<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="b" varStatus="s">
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
				"action":[
					<c:if test="${b.auditStatusId eq 'Auditing'}">
					 {
                         "id":"revoke",
                         "name":"撤销"
                     },
                     {
                         "id":"del",
                         "name":"删除"
                     }
					</c:if>
					<c:if test="${b.auditStatusId eq 'Revoke'}">
                     {
                         "id":"del",
                         "name":"删除"
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
