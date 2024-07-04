<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
	,
	"applyUserList": [
	<c:forEach items="${applyUserList}" var="app" varStatus="i">
		{
		"recordFlow":${pdfn:toJsonString(app.RECORD_FLOW)},
		"userFlow":${pdfn:toJsonString(app.USER_FLOW)},
		"userName":${pdfn:toJsonString(app.USER_NAME)},
		"sid":${pdfn:toJsonString(app.SID)},
		"applyTypeId":${pdfn:toJsonString(app.APPLY_TYPE_ID)},
		"applyTypeName":${pdfn:toJsonString(app.APPLY_TYPE_NAME)},
		"applyTime":${pdfn:toJsonString(app.APPLY_TIME)},
		"statusId":${pdfn:toJsonString(app.STATUS_ID)},
		"statusName":${pdfn:toJsonString(app.STATUS_NAME)},
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