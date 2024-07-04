<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
,
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount":${dataCount},
	"isAudit":"${isAudit}",
    "datas": [
        <c:forEach items="${skillList}" var="d" varStatus="status">
            {
	        	"skillFlow":"${d.Skill_TFlow}",
	        	"skillName":"${d.Skill_Name}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]
	</c:if>
}