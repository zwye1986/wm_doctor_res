<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
	"dataCount":2
    <c:if test="${resultId eq '200' }"> 
    	,
    	"exams": [
			{
				"examFlow": "60000001",
				"examName": "各科三基模拟考试",	
	            "order": "1",
	            "questionCount": 50,
	            "totalScore": 100,
	            "passScore": 60,
	            "note": "试卷信息试卷简介试卷信息试卷试卷信息试卷简介试卷信息试卷",
	            "score": 50,
	            "examTime": "2015-10-11 12:15:07",
	            "timeCount": "11分30秒"
	        }
		]
    </c:if>
}
