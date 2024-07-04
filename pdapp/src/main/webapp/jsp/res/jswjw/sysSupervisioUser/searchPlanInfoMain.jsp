<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
		,
	    "userList": [
	    <c:forEach items="${userList}" var="s" varStatus="status">
			{
				"userFlow":${pdfn:toJsonString(s.userFlow)},
				"userName":${pdfn:toJsonString(s.userName)},
				"evaluationDate":${pdfn:toJsonString(s.evaluationDate)},
			    "title":
			    <c:if test="${empty s.evaluationDate}">
					"评分（未提交）"
				</c:if>
		    	<c:if test="${not empty s.evaluationDate}">
		    		"评分"
		    	</c:if>
			}
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
	    ]
    </c:if>
}
