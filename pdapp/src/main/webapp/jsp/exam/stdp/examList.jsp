<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }"> 
    	,
	    "pageIndex": ${param.pageIndex},
		"pageSize": ${param.pageSize},
		"dataCount":${dataCount},
    	"exams": [
    		<c:forEach var="exam" items="${examList}" varStatus="status">
			{
				"examFlow": ${pdfn:toJsonString(exam.examFlow)},
				"examName": ${pdfn:toJsonString(exam.examName)},	
	            "order": ${pdfn:toJsonString(exam.order)},
	            "questionCount": ${pdfn:toJsonString(exam.questionCount)},
	            "totalScore": ${pdfn:toJsonString(exam.totalScore)},
	            "passScore": ${pdfn:toJsonString(exam.passScore)},
	            "answerTime": "${exam.answerTime}分钟",
	            "startDate": ${pdfn:toJsonString(exam.startDate)},
	            "endDate": ${pdfn:toJsonString(exam.endDate)},
	        }
        	<c:if test='${not status.last}'>
	        ,
	        </c:if>
	        </c:forEach>
		]
    </c:if>
}
