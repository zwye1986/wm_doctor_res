<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	"answerFlow": ${pdfn:toJsonString(answerInfo.answerFlow)},
    	"questionFlows": [
			${allQuestionFlows}
        ],
        "questionTypes": [
        	<c:forEach var="detail" items="${examDetails}" varStatus="status">
			{
				"questionTypeId" : ${pdfn:toJsonString(detail.questionTypeId)},
				"questionTypeName" : ${pdfn:toJsonString(detail.questionTypeName)},
				"questionFlows": [
					${detail.questionFlows}
		        ]
	        }
        	<c:if test='${not status.last}'>
	        ,
	        </c:if>
	        </c:forEach>
        ]
    </c:if>
}
