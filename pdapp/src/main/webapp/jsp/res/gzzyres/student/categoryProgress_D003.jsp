<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"catagories":[
			<c:forEach items="${operateSkillCatas}" var="catagoryData" varStatus="status">
	        {
	            "cataFlow": ${pdfn:toJsonString(catagoryData['cataFlow'])},
	            "title": ${pdfn:toJsonString(catagoryData['OpClass'])},
	            "neededNum": ${catagoryData['neededNum']},
	            "finishedNum": ${catagoryData['finishedNum']},
	            "auditedNum": ${catagoryData['auditedNum']},
	            "labelsNum": 3,
	            "progress":${catagoryData['progress']}
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
	]