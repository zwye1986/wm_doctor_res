<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	"answerResult": true,
    	"analysis": "题目答案解析内容",
    	"answer": [
    		"B","C"
    	]
    </c:if>
}
