<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"eduUserList": [
			<c:forEach items="${applyList}" var="app" varStatus="i">
				{
				"recordFlow":${pdfn:toJsonString(app.RECORD_FLOW)},
				"userFlow":${pdfn:toJsonString(app.USER_FLOW)},
				"applyTypeId":${pdfn:toJsonString(app.APPLY_TYPE_ID)},
				"applyTypeName":${pdfn:toJsonString(app.APPLY_TYPE_NAME)},
				"applyTime":${pdfn:toJsonString(app.APPLY_TIME)},
				"statusId":${pdfn:toJsonString(app.STATUS_ID)},
				"statusName":${pdfn:toJsonString(app.STATUS_NAME)},
				"auditPerson":${pdfn:toJsonString(app.AUDIT_PERSON)},
				"auditDate":${pdfn:toJsonString(app.AUDIT_DATE)}
				}
				<c:if test="${!i.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}
    </c:if>
}