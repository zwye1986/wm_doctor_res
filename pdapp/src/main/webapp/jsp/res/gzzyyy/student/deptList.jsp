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
	    	<c:if test="${empty resultMap.processFlow || (!(resultMap.isCurrentFlag eq 'Y') && !(resultMap.schFlag eq 'Y'))}">
	    		<c:set var="statusId" value="NotEntered"/>
	    		<c:set var="statusName" value="未入科"/>
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
	    	</c:if>
			<c:set var="deptName" value="[${resultMap.standardDeptName}]${resultMap.schDeptName}"/>
	        {
				"stageName": ${pdfn:toJsonString(resultMap.stageName)},
	            "deptFlow": ${pdfn:toJsonString(resultMap.resultFlow)},
	            "deptName": ${pdfn:toJsonString(deptName)},
	            "statusId": ${pdfn:toJsonString(statusId)},
	            "statusDesc": ${pdfn:toJsonString(statusName)},
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
	            "progress": ${deptPerMap[resultMap.resultFlow]+0},
	            "schDeptFlow": ${pdfn:toJsonString(resultMap.schDeptFlow)},
				"standardDeptFlow": ${pdfn:toJsonString(resultMap.rotationDeptFlow)},
				"schDeptName":${pdfn:toJsonString(resultMap.schDeptName)},
				"standardDeptName":${pdfn:toJsonString(resultMap.standardDeptName)},
				"rotationStatus":${pdfn:toJsonString(resultMap.rotationStatus)}
	        }
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
	    ]
    </c:if>
}
