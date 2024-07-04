<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"monthCurrent":"${monthCurrent}",
	"monthSch":"${monthSch}",
	"waitSch":"${waitSch}"
  </c:if>
}