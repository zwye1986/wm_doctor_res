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
	    	<c:set var="statusId" value=""/>
	    	<c:set var="statusName" value=""/>
	    	<c:if test="${empty resultMap.processFlow}">
	    		<c:set var="statusId" value="NotEntered"/>
	    		<c:set var="statusName" value="未入科"/>
	    		<c:set var="startDate" value="${resultMap.schStartDate}"/>
	    		<c:set var="endDate" value="${resultMap.schEndDate}"/>
	    	</c:if>
	    	<c:if test="${!empty resultMap.processFlow}">
	    		<c:if test="${resultMap.isCurrentFlag eq 'Y'}">
		    		<c:set var="statusId" value="Entering"/>
		    		<c:set var="statusName" value="轮转中"/>
	    		</c:if>
	    		<c:if test="${resultMap.schFlag eq 'Y'}">
		    		<c:set var="statusId" value="Exited"/>
		    		<c:set var="statusName" value="已出科"/>
	    		</c:if>
	    		<c:set var="startDate" value="${resultMap.schStartDate}"/>
	    		<c:set var="endDate" value="${resultMap.schEndDate}"/>
	    	</c:if>
	        {
				"stageName": ${pdfn:toJsonString(resultMap.stageName)},
	            "deptFlow": ${pdfn:toJsonString(resultMap.resultFlow)},
	            "deptName": ${pdfn:toJsonString(resultMap.schDeptName)},
	            "statusId": ${pdfn:toJsonString(statusId)},
	            "statusDesc": ${pdfn:toJsonString(statusName)},
	            "teacherFlow": ${pdfn:toJsonString(resultMap.teacherUserFlow)},
	            "teacherName": ${pdfn:toJsonString(resultMap.teacherUserName)},
	            "deptHeadFlow": ${pdfn:toJsonString(resultMap.headUserFlow)},
	            "deptHeadName": ${pdfn:toJsonString(resultMap.headUserName)},
	            "startDate": ${pdfn:toJsonString(startDate)},
	            "endDate": ${pdfn:toJsonString(endDate)},
				"schMonth": ${pdfn:toJsonString(resultMap.schMonth)},
	            "realMonth": ${pdfn:toJsonString(resultMap.realMonth)},
	            "schScore": ${pdfn:toJsonString(resultMap.schScore)},
	            "progress": ${deptPerMap[resultMap.resultFlow]+0}
	        }
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
	    ]
    </c:if>
}
