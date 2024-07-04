<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
     <c:if test="${resultId eq '200' }">
    ,
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
    </c:if>
}
