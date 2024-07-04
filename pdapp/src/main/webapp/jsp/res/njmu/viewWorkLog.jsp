<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' and not empty worklog}">
    ,
	"data": {
    	"workDate": "${pdfn:formatDate(worklog.workTime,'yyyy-MM-dd')}",
		"workTypeId": ${pdfn:toJsonString(worklog.workType)},
		"workContent": ${pdfn:toJsonString(worklog.workContent)}
    }
    </c:if>
}
