<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)} 
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "heads": [
      <c:forEach items="${headList}" var="head" varStatus="status">
        {
            "headFlow": ${pdfn:toJsonString(head.userFlow)},
            "headName": ${pdfn:toJsonString(head.userName)}
        }
        <c:if test="${not status.last }">
				,
		</c:if>
	 </c:forEach>
    ]
    </c:if>
}
