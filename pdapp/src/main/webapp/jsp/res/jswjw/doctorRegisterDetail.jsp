<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"userName":${pdfn:toJsonString(currUser.userName)},
	<c:set var="key" value="${recruit.recruitFlow}${recruit.doctorFlow}"></c:set>
	"rankNum":${pdfn:toJsonString(rankNumMap[key])},
	"orgName":${pdfn:toJsonString(recruit.orgName)},
	"speName":${pdfn:toJsonString(recruit.speName)},
	"examResult":${pdfn:toJsonString(recruit.examResult)},
	"auditionResult":${pdfn:toJsonString(recruit.auditionResult)},
	"operResult":${pdfn:toJsonString(recruit.operResult)},
	"totleResult":${pdfn:toJsonString(recruit.totleResult)},
		<c:if test="${recruit.confirmFlag eq 'Y'}">
			"confirmFlag":"是"
		</c:if>
		<c:if test="${recruit.confirmFlag ne 'Y'}">
			"confirmFlag":"否"
		</c:if>
		<c:if test="${recruit.recruitFlag eq 'Y'}">
			,"recruitFlag":"是"
		</c:if>
		<c:if test="${recruit.recruitFlag ne 'Y'}">
			,"recruitFlag":"否"
		</c:if>
		<c:if test="${recruit.recruitFlag eq 'Y' and recruit.confirmFlag ne 'Y'}">
			,"button":"Y"
		</c:if>
		<c:if test="${recruit.recruitFlag ne 'Y' or recruit.confirmFlag eq 'Y'}">
			,"button":"N"
		</c:if>


    </c:if>
}
