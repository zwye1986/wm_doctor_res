<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    <c:choose>
    	<c:when test='${param.funcFlow eq "D002"}'>
    		<jsp:include page="categoryProgress_D002.jsp"></jsp:include>
    	</c:when>
    	<c:when test='${param.funcFlow eq "D003"}'>
    		<jsp:include page="categoryProgress_D003.jsp"></jsp:include>
    	</c:when>
    	<c:when test='${param.funcFlow eq "D004"}'>
    		<jsp:include page="categoryProgress_D004.jsp"></jsp:include>
    	</c:when>
    	<c:otherwise>
   		"catagories":[
			{
	 		"cataFlow":"0",
	     	"title":"title",
	     	"neededNum": 0,
			"finishedNum": 0,
	 		"auditedNum": 0,        	
	     	"progress":0,        	
	     	"labelsNum":0
	 		}
		]
    	</c:otherwise>
    </c:choose>
    </c:if>
}
