<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "datas": {
				"doctorFlow":"${doctor.doctorFlow}",
				"currYear":"${currYear}",
				"inTime":"${inTime}",
				"isAllowApply":"${isAllowApply}",
		"signups":[
		<c:forEach items="${signups}" var="b" varStatus="s">
			{
			"order":"${s.index+1}",
			"signupFlow":"${b.signupFlow}",
			"testId":"${b.testId}",
			<c:if test="${b.signupTypeId eq 'Skill'}">
				"trainingTypeName":"技能补考",
			</c:if>
			<c:if test="${b.signupTypeId eq 'Theory'}">
				"trainingTypeName":"理论补考",
			</c:if>
			"createTime":"${b.createTime}",
			<c:set var="k" value="${b.testId}_make_up"/>
			<c:if test="${ sysCfgMap[k] eq 'Y'}">
				"auditStatusName":"${b.auditStatusName}",
				"localReason":"${b.localReason}",
				"cityReason":"${b.cityReason}",
				"globalReason":"${b.globalReason}",
			</c:if>
			<c:if test="${ sysCfgMap[k] eq 'N' or empty sysCfgMap[k] }">
				<c:choose>
					<c:when test="${not empty b.globalAuditStatusId}">
						"auditStatusName":"省厅审核中",
					</c:when>
					<c:otherwise>
						"auditStatusName":"${b.auditStatusName}",
					</c:otherwise>
				</c:choose>
				"localReason":"${b.localReason}",
				"cityReason":"${b.cityReason}",
				"globalReason":""
			</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
		]
			}
    </c:if>
}
