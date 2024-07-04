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
	            "resultFlow": ${pdfn:toJsonString(result.resultFlow)},
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
		        <c:set var="key1" value="${processMap[result.resultFlow].processFlow }_TeacherAssess"/>
		        <c:set var="key2" value="${processMap[result.resultFlow].processFlow }_TeacherAssessTwo"/>
		        <c:set var="key3" value="${processMap[result.resultFlow].processFlow }"/>
		        <c:set var="key4" value="${processMap[result.resultFlow].processFlow }TypeId"/>
		        "teacherScore":
				<c:if test="${not empty gradeMap[key1] and empty gradeMap[key2]}">
					${pdfn:toJsonString(gradeMap[key1])},
				</c:if>
				<c:if test="${not empty gradeMap[key2] and empty gradeMap[key1]}">
				${pdfn:toJsonString(gradeMap[key2])},
				</c:if>
				<c:if test="${empty gradeMap[key2] and empty gradeMap[key1]}">
				"未评价",
				</c:if>
		        "recFlow":${pdfn:toJsonString(!empty  gradeMap[key3]? gradeMap[key3]:'')},
		        "typeId":${pdfn:toJsonString(!empty  gradeMap[key4]? gradeMap[key4]:'')}
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
