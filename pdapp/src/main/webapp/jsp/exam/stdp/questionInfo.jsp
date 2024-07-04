<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
    	"questionFlow": "${param.questionFlow}",
    	"questionTypeId": ${pdfn:toJsonString(questionTypeId)},
    	"questionTypeName": ${pdfn:toJsonString(questionInfo.questionTypeName)},
		"curOrder": ${curOrder},
		"allCount": ${allCount},
		"lastFlow": "${lastFlow}",
		"nextFlow": "${nextFlow}",
		"stem": ${pdfn:toJsonString(questionInfo.content)},
		"value":[

		],
		answer":[
		]
		
    </c:if>
}
