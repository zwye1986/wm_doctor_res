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
	    <c:forEach items="${resultMaps}" var="dept" varStatus="status">
	        {
	            "standardDeptFlow": ${pdfn:toJsonString(dept.recordFlow)},
	            "stageName": ${pdfn:toJsonString(dept.stageName)},
	            "groupFlow": ${pdfn:toJsonString(dept.groupFlow)},
	            "groupName": ${pdfn:toJsonString(dept.groupName)},
	            "standardDeptId": ${pdfn:toJsonString(dept.standardDeptId)},
	            "standardDeptName": ${pdfn:toJsonString(dept.standardDeptName)},
	            "schMonth": ${pdfn:toJsonString(dept.schMonth)},
	            "deptNote": ${pdfn:toJsonString(dept.deptNote)},
				"schdepts": [
					<c:set var="arrResultList" value="${resultMap[dept.recordFlow]}"/>
					<c:forEach items="${arrResultList}" var="result" varStatus="status">
						<c:set var="statusId" value=""/>
						<c:set var="statusName" value=""/>
						<c:if test="${empty result.processFlow || (!(result.isCurrentFlag eq 'Y') && !(result.schFlag eq 'Y'))}">
							<c:set var="statusId" value="NotEntered"/>
							<c:set var="statusName" value="未入科"/>
						</c:if>
						<c:if test="${!empty result.processFlow}">
							<c:if test="${result.isCurrentFlag eq 'Y'}">
								<c:set var="statusId" value="Entering"/>
								<c:set var="statusName" value="轮转中"/>
							</c:if>
							<c:if test="${result.schFlag eq 'Y'}">
								<c:set var="statusId" value="Exited"/>
								<c:set var="statusName" value="已出科"/>
							</c:if>
						</c:if>
						<c:set var="deptName" value="[${result.standardDeptName}]${result.schDeptName}"/>
						{
						"stageName": ${pdfn:toJsonString(result.stageName)},
						"deptFlow": ${pdfn:toJsonString(result.resultFlow)},
						"deptName": ${pdfn:toJsonString(deptName)},
						"statusId": ${pdfn:toJsonString(statusId)},
						"statusDesc": ${pdfn:toJsonString(statusName)},
						"teacherFlow": ${pdfn:toJsonString(result.teacherUserFlow)},
						"teacherName": ${pdfn:toJsonString(result.teacherUserName)},
						"deptHeadFlow": ${pdfn:toJsonString(result.headUserFlow)},
						"deptHeadName": ${pdfn:toJsonString(result.headUserName)},
						"schStartDate": ${pdfn:toJsonString(result.schStartDate)},
						"schEndDate": ${pdfn:toJsonString(result.schEndDate)},
						"startDate": ${pdfn:toJsonString(result.schStartDate)},
						"endDate": ${pdfn:toJsonString(result.schEndDate)},
						"schMonth": ${pdfn:toJsonString(result.schMonth)},
						"realMonth": ${pdfn:toJsonString(result.realMonth)},
						"schScore": ${pdfn:toJsonString(result.schScore)},
						"progress": ${deptPerMap[result.resultFlow]+0},
						"schDeptFlow": ${pdfn:toJsonString(result.schDeptFlow)},
						"standardDeptFlow": ${pdfn:toJsonString(result.rotationDeptFlow)},
						"schDeptName":${pdfn:toJsonString(result.schDeptName)},
						"standardDeptName":${pdfn:toJsonString(result.standardDeptName)},
						"rotationStatus":${pdfn:toJsonString(result.rotationStatus)},
						"isExternal":${pdfn:toJsonString(result.isExternal)}
						}
						<c:if test="${!status.last}">
							,
						</c:if>
				</c:forEach>
				]
	        }
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
	    ]
    </c:if>
}
