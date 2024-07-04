<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"notice":{
			"infoFlow":${pdfn:toJsonString(notice.infoFlow)},
			"infoTitle":${pdfn:toJsonString(notice.infoTitle)},
			"infoTargetFlow":${pdfn:toJsonString(notice.infoTargetFlow)},
			"infoTypeId":${pdfn:toJsonString(notice.infoTypeId)},
			"infoTypeName":${pdfn:toJsonString(notice.infoTypeName)},
			"infoContent":${pdfn:toJsonString(notice.infoContent)},
			"jspUrl":${pdfn:toJsonString(jspUrl)}
		}
    </c:if>
}