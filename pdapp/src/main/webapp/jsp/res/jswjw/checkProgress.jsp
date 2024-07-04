<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	,
	"isOutTime":${pdfn:toJsonString(isOutTime)},
	"outDate":${pdfn:toJsonString(outDate)},
	"checkDay":${pdfn:toJsonString(checkDay)},
	"auditStatusId":${pdfn:toJsonString(resOutOfficeLockList.auditStatusId)},
	"auditStatusName":${pdfn:toJsonString(resOutOfficeLockList.auditStatusName)}
    </c:if>
}
