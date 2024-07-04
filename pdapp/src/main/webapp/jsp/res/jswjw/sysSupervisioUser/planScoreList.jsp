<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
		,
		"pageIndex": ${param.pageIndex},
		"pageSize": ${param.pageSize},
		"dataCount": ${dataCount},
		"subjectName":"${subjectName}",
		"orgFlow":"${orgFlow}",
		"speIdParam":"${speId}",
		"userFlow":"${userFlow}",
		"roleFlag":"${roleFlag}",
	    "supervisioSubject": [
	    <c:forEach items="${list}" var="s" varStatus="status">
			{
				"subjectName":${pdfn:toJsonString(s.subjectName)},
				"subjectFlow":${pdfn:toJsonString(s.subjectFlow)},
				"orgName":${pdfn:toJsonString(s.orgName)},
				"orgFlow":${pdfn:toJsonString(s.orgFlow)},
				"speName":${pdfn:toJsonString(s.speName)},
				"subjectYear":${pdfn:toJsonString(s.subjectYear)},
				"openTime":${pdfn:toJsonString(s.openTime)},
				"closedTime":${pdfn:toJsonString(s.closedTime)},
				"subjectActivitiFlows":${pdfn:toJsonString(s.subjectActivitiFlows)},
				"speId":${pdfn:toJsonString(s.speId)}
		    	<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
				,"speFeedback":${pdfn:toJsonString(s.speFeedback)}
				</c:if>
			    ,"scoreButton":
						<%--根据当前时间和项目评分的关闭时间、是否提交判断是评分还是查看评分--%>
					<c:if test="${roleFlag eq 'expertLeader'}">
						<c:set value="${s.subjectFlow}" var="key"></c:set>
						<c:if test="${s.closedTime > dateNow and expertEditMap[key] eq 'Y'}">
							"Y"
						</c:if>
						<c:if test="${s.closedTime < dateNow or expertEditMap[key] ne 'Y'}">
							"N"
						</c:if>
					</c:if>
			}
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
	    ]
    </c:if>
}
