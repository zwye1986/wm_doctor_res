<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataType": ${pdfn:toJsonString(param.dataType)},
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
	    <c:if test="${param.dataType eq 'mr'}">
	    "showType":"cata1DataN",
	    </c:if>
	    <c:if test="${param.dataType eq 'disease'}">
	    "showType":"cataNDataN",
	    </c:if>
	    <c:if test="${param.dataType eq 'skill'}">
	    "showType":"cataNDataN",
	    </c:if>
	    <c:if test="${param.dataType eq 'operation'}">
	    "showType":"cataNDataN",
	    </c:if>
	    <c:if test="${param.dataType eq 'activity'}">
	    "showType":"cata1DataN",
	    </c:if>
	    <c:if test="${param.dataType eq 'summary'}">
	    "showType":"cata1Data1",
	    </c:if>
	    "catagories": [
	    <c:forEach items="${catagoryDataList}" var="catagoryData" varStatus="status">
	        {
	            "cataFlow": ${pdfn:toJsonString(catagoryData['cataFlow'])},
	            "title": ${pdfn:toJsonString(catagoryData['title'])},
	            "neededNum": ${catagoryData['neededNum']},
	            "finishedNum": ${catagoryData['finishedNum']},
	            "auditedNum": ${catagoryData['auditedNum']},
	            "progress": ${catagoryData['progress']},
	            "labelsNum": 3
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
	    ]
    </c:if>
}
