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
        <c:forEach items="${resultList}" var="result" varStatus="status">
	        {
	            "subDeptFlow": ${pdfn:toJsonString(result.resultFlow)},
	            "deptFlow": ${pdfn:toJsonString(deptMap[result.resultFlow].recordFlow)},
	            "order": ${status.index+1},
				<c:set var="cutDeptName" value="${pdfn:cutString(result.schDeptName,8,true,3)}"/>
	            "subDeptName": ${pdfn:toJsonString(cutDeptName)},
	            "startDate": ${pdfn:toJsonString(result.schStartDate)},
	            "endDate": ${pdfn:toJsonString(result.schEndDate)},
	            "sysDeptFlow":${pdfn:toJsonString(result.deptFlow)},
	            "teacherFlow":${pdfn:toJsonString(processMap[result.resultFlow].teacherUserFlow)},
		        "teacherName": ${pdfn:toJsonString(processMap[result.resultFlow].teacherUserName)},
		        "headFlow": ${pdfn:toJsonString(processMap[result.resultFlow].headUserFlow)},
		        "headName": ${pdfn:toJsonString(processMap[result.resultFlow].headUserName)},
		        <c:set var="key" value="${processMap[result.resultFlow].processFlow }_TeacherGrade"/>
		        "teacherScore":${pdfn:toJsonString(!empty  gradeMap[key]? gradeMap[key]:'未评价')},
		        <c:set var="key" value="${processMap[result.resultFlow].processFlow }_DeptGrade"/>
		        "deptScore":${pdfn:toJsonString(!empty gradeMap[key]?gradeMap[key]:'未评价')},
	            "progress": 100
	           
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
