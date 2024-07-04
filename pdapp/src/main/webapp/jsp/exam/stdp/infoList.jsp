<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
	    "pageIndex": ${param.pageIndex},
		"pageSize": ${param.pageSize},
		"dataCount":${dataCount},
    	"infos": [
    		<c:forEach var="info" items="${infoList}" varStatus="status">
			{
				"infoFlow": ${pdfn:toJsonString(info.infoFlow)},
				"title": ${pdfn:toJsonString(info.title)},	
				"content": ${pdfn:toJsonString(info.content)},
				"date":${pdfn:toJsonString(info.date)}
        	}
        	<c:if test='${not status.last}'>
	        ,
	        </c:if>
	        </c:forEach>
		]
    </c:if>
}
