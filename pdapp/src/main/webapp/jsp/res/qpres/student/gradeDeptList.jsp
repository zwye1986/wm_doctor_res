<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "isChargeOrg":true,
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
				<c:set var="key" value="${resultMap.processFlow}_TeacherGrade"/>
				"teacherScore":${pdfn:toJsonString(!empty  gradeMap[key]? gradeMap[key]:'未评价')},
				<c:set var="key" value="${resultMap.processFlow}_DeptGrade"/>
				"deptScore":${pdfn:toJsonString(!empty gradeMap[key]?gradeMap[key]:'未评价')},
				"rotationStatus":${pdfn:toJsonString(resultMap.rotationStatus)}
	        }
	        <c:if test="${!status.last}">
	        	,
	        </c:if>
	    </c:forEach>
    ]
    </c:if>
}
