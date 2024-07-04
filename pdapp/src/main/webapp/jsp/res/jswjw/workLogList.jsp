<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "datas": [
    <c:forEach items="${worklogList}" var="worklog" varStatus="status">
    	{
			"workDate": "${pdfn:formatDate(worklog.workTime,'yyyy-MM-dd')}"
        }
     	<c:if test="${not status.last }">
			,
		</c:if>
    </c:forEach>
    ]
    </c:if>
}
