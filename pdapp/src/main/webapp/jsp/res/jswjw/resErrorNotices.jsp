<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${notices}" var="notice" varStatus="s">
	     	{
				"recordFlow":"${notice.recordFlow}",
				"userFlow":"${notice.userFlow}",
				"userName":"${notice.userName}",
				"statusId":"${notice.statusId}",
				"statusName":"${notice.statusName}",
				"noticeTime":"${notice.noticeTime}",
				"noticeContent":"${notice.noticeContent}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
