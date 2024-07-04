<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	,
	"addActivity": ${pdfn:toJsonString(addActivity)},
    "progresses": [
        <c:forEach items="${globalDataList}" var="globalData" varStatus="status">
	        {
	            "title": ${pdfn:toJsonString(globalData['title'])},
	            "order": ${globalData['order']},
	            "dataType": ${pdfn:toJsonString(globalData['dataType'])},
	            "label": ${pdfn:toJsonString(globalData['label'])},
	            "canAdd": ${pdfn:toJsonString(globalDataList.get(0)['canAdd'])},
	            "progress": "${globalData['progress']}"
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
