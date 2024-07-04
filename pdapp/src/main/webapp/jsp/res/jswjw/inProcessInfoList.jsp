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
	            "teacherFlow":${pdfn:toJsonString(processMap[result.resultFlow].teacherUserFlow)},
		        "teacherName": ${pdfn:toJsonString(processMap[result.resultFlow].teacherUserName)},
		        "headFlow": ${pdfn:toJsonString(processMap[result.resultFlow].headUserFlow)},
		        "headName": ${pdfn:toJsonString(processMap[result.resultFlow].headUserName)},
				<c:set var="cutDeptName" value="${pdfn:cutString(result.schDeptName,8,true,3)}"/>
	            "schDeptName": ${pdfn:toJsonString(cutDeptName)},
	            "processFlow": ${pdfn:toJsonString(processMap[result.resultFlow].processFlow)},
				<c:if test="${not empty inprocessInfoMap[result.resultFlow]}">
					"canShowInfo":"Y",
				</c:if>
				<c:if test="${empty inprocessInfoMap[result.resultFlow]}">
					"canShowInfo":"N",
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
