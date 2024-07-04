<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "depts": [
        <c:forEach items="${resultList}" var="result" varStatus="status">
	        {
	            "resultFlow": ${pdfn:toJsonString(result.resultFlow)},
	            "schDeptFlow": ${pdfn:toJsonString(result.deptFlow)},
	            "schStartDate": ${pdfn:toJsonString(result.schStartDate)},
	            "schEndDate": ${pdfn:toJsonString(result.schEndDate)},
	            "order": ${status.index+1},
				<c:set var="cutDeptName" value="${pdfn:cutString(result.schDeptName,8,true,3)}"/>
	            "schDeptName": ${pdfn:toJsonString(cutDeptName)},
	            "processFlow": ${pdfn:toJsonString(processMap[result.resultFlow].processFlow)},
				<c:if test="${ (not empty processMap[result.resultFlow].processFlow)
				and (not empty countMap[result.orgFlow] and countMap[processMap[result.resultFlow].processFlow]+0<countMap[result.orgFlow]+0 or empty countMap[result.orgFlow])}">
					"canJoinExam":"Y",
				</c:if>
				<c:if test="${!( (not empty processMap[result.resultFlow].processFlow)
				and (not empty countMap[result.orgFlow] and countMap[processMap[result.resultFlow].processFlow]+0<countMap[result.orgFlow]+0 or empty countMap[result.orgFlow]))}">
					"canJoinExam":"N",
				</c:if>
				<c:if test="${not empty scoreMap[result.resultFlow].theoryScore}">
					"schScore": ${pdfn:toJsonString(scoreMap[result.resultFlow].theoryScore)}
				</c:if>
				<c:if test="${ empty scoreMap[result.resultFlow].theoryScore}">
					"schScore": "--"
				</c:if>
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
