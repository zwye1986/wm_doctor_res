<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"isSch":"${studentNum}",
	"isCurrent":"${isCurrent}",
	"noAuditNumber":"${noAuditNumber}",
	"noAppealNumber":"${noAppealNumber}",
	"noAfterSummNumber":"${noAfterSummNumber}",
	"noAfterEvaNumber":"${noAfterEvaNumber}"
  </c:if>
}