<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "recruits": [
     <c:forEach items="${recruitList}" var="recruit" varStatus="status">
	     	{
		 		"order": ${status.index+1},
	     		"doctorFlow": ${pdfn:toJsonString(recruit.doctorFlow)},
				"speId": ${pdfn:toJsonString(recruit.speId)},
				"speName":${pdfn:toJsonString(recruit.speName)},
				"rotationFlow": ${pdfn:toJsonString(recruit.rotationFlow)},
				"rotationName":  ${pdfn:toJsonString(recruit.rotationName)}
			}
			<c:if test="${not status.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
