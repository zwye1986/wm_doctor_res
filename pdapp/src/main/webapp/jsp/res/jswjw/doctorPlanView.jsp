<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"orgName":${pdfn:toJsonString(sysOrg.orgName)},
	"assignYear":${pdfn:toJsonString(assignYear)},
	"signupMsg":${pdfn:toJsonString(signupMsg)},
    "datas": [
		<c:forEach items="${orgSpeList}" var="orgSpe" varStatus="s">
	     	{
				"speName": ${pdfn:toJsonString(orgSpe.SPE_NAME)},
				"speId": ${pdfn:toJsonString(orgSpe.SPE_ID)},
				"recordFlow": ${pdfn:toJsonString(orgSpe.RECORD_FLOW)},
			<c:set var="assignPlan" value="${empty orgSpe.ASSIGN_PLAN ? 0 : orgSpe.ASSIGN_PLAN}"></c:set>
			    "assignPlan": ${pdfn:toJsonString(assignPlan)},
			<c:set var="assignReal" value="${empty orgSpe.ASSIGN_REAL ? 0 : orgSpe.ASSIGN_REAL}"></c:set>
			    "assignReal": ${pdfn:toJsonString(assignReal)},
				"graduateSpe": ${pdfn:toJsonString(orgSpe.GRADUATE_SPE)},
				"education": ${pdfn:toJsonString(orgSpe.EDUCATION)},
			<c:if test="${signupBtnFlag eq 'Y' and assignPlan > 0 and assignPlan > assignReal}">
				<c:if test="${(speFlag eq 'Y' and orgSpe.SPE_NAME eq '全科') or (speFlag eq 'N')}">
					,"button":"Y"
				</c:if>
			</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
