<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
	,
	"eduUserList": [
	<c:forEach items="${eduUserList}" var="edu" varStatus="i">
		{
		"userFlow":${pdfn:toJsonString(edu.USER_FLOW)},
		"userName":${pdfn:toJsonString(edu.USER_NAME)},
		"sid":${pdfn:toJsonString(edu.SID)},
		"majorId":${pdfn:toJsonString(edu.MAJOR_ID)},
		"majorName":${pdfn:toJsonString(edu.MAJOR_NAME)},
		"period":${pdfn:toJsonString(edu.PERIOD)}
		}
		<c:if test="${!i.last}">
			,
		</c:if>
	</c:forEach>
	],
	"dataCount": ${dataCount}
</c:if>
}