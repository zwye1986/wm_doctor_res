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
	    <c:forEach items="${results}" var="resultMap" varStatus="status">
			<c:set var="deptName" value="[${resultMap.standardDeptName}]${resultMap.schDeptName}"/>
	        {
				"stageName": ${pdfn:toJsonString(resultMap.stageName)},
	            "deptFlow": ${pdfn:toJsonString(resultMap.resultFlow)},
	            "deptName": ${pdfn:toJsonString(deptName)},
	            "teacherFlow": ${pdfn:toJsonString(resultMap.teacherUserFlow)},
	            "teacherName": ${pdfn:toJsonString(resultMap.teacherUserName)},
	            "deptHeadFlow": ${pdfn:toJsonString(resultMap.headUserFlow)},
	            "deptHeadName": ${pdfn:toJsonString(resultMap.headUserName)},
	            "schStartDate": ${pdfn:toJsonString(resultMap.schStartDate)},
	            "schEndDate": ${pdfn:toJsonString(resultMap.schEndDate)},
	            "startDate": ${pdfn:toJsonString(resultMap.schStartDate)},
	            "endDate": ${pdfn:toJsonString(resultMap.schEndDate)},
				"schMonth": ${pdfn:toJsonString(resultMap.schMonth)},
	            "realMonth": ${pdfn:toJsonString(resultMap.realMonth)},
	            "schScore": ${pdfn:toJsonString(resultMap.schScore)},
	            "schDeptFlow": ${pdfn:toJsonString(resultMap.schDeptFlow)},
				"standardDeptFlow": ${pdfn:toJsonString(resultMap.rotationDeptFlow)},
				"schDeptName":${pdfn:toJsonString(resultMap.schDeptName)},
				"standardDeptName":${pdfn:toJsonString(resultMap.standardDeptName)},
				"processFlow": ${pdfn:toJsonString(resultMap.processFlow)},
				<c:if test="${ (not empty resultMap.processFlow)
				and (not empty countMap[resultMap.orgFlow] and countMap[resultMap.processFlow]<countMap[resultMap.orgFlow] or  empty countMap[resultMap.orgFlow])}">
					"canJoinExam":"Y",
				</c:if>
				<c:if test="${!( (not empty resultMap.processFlow)
				and (not empty countMap[resultMap.orgFlow] and countMap[resultMap.processFlow]<countMap[resultMap.orgFlow] or  empty countMap[resultMap.orgFlow]))}">
					"canJoinExam":"N",
				</c:if>
				<c:if test="${not empty scoreMap[resultMap.resultFlow].theoryScore}">
					"schScore": ${pdfn:toJsonString(scoreMap[resultMap.resultFlow].theoryScore)}
				</c:if>
				<c:if test="${ empty scoreMap[resultMap.resultFlow].theoryScore}">
					"schScore": "--"
				</c:if>
	        }
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
    ]
    </c:if>
}
