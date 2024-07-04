<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "images": [
          <c:forEach items="${dataList}" var="dataMap" varStatus="status">
	     	{
	     		"imageFlow": ${pdfn:toJsonString(dataMap.imageFlow)},
				"imageUrl": ${pdfn:toJsonString(dataMap.imageUrl)},
				"thumbUrl":${pdfn:toJsonString(dataMap.thumbUrl)}
			}
			<c:if test="${not status.last }">
				,
			</c:if>
    	 </c:forEach>
    ]
    </c:if>
}
