<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	"timeCount": ${pdfn:toJsonString(answerInfo.timeCount)},
    	"score": ${pdfn:toJsonString(answerInfo.score)}	,
    	"wrongQuestionFlows": [
			${wrongQuestionFlows}
        ]
    </c:if>
}
