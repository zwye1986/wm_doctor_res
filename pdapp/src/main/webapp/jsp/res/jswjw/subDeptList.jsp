<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${pageIndex},
    "pageSize": ${pageSize},
    "dataCount": ${dataCount},
    "isChargeOrg":${pdfn:toJsonString(isChargeOrg)},
    "depts": [
		<c:set var="schMonth" value="0"></c:set>
        <c:forEach items="${resultList}" var="result" varStatus="status">
	        {
				<c:set var="schMonth" value="${schMonth + result.schMonth}"></c:set>
	            "subDeptFlow": ${pdfn:toJsonString(result.resultFlow)},
	            "order": ${status.index+1},
				<c:set var="cutDeptName" value="${pdfn:cutString(result.schDeptName,8,true,3)}"/>
	            "subDeptName": ${pdfn:toJsonString(cutDeptName)},
	            "startDate": ${pdfn:toJsonString(result.schStartDate)},
	            "endDate": ${pdfn:toJsonString(result.schEndDate)},
				"currMonth": ${pdfn:toJsonString(result.schMonth)},
	            <c:if test="${isChargeOrg}">
	            	 "sysDeptFlow":${pdfn:toJsonString(result.deptFlow)},
	            	 "teacherFlow":${pdfn:toJsonString(processMap[result.resultFlow].teacherUserFlow)},
	            	 "processFlow":${pdfn:toJsonString(processMap[result.resultFlow].processFlow)},
	            	 "temporaryAuditStatusId":${pdfn:toJsonString(processMap[result.resultFlow].temporaryAuditStatusId)},
	            	 "ckxz":${pdfn:toJsonString(ckxz)},
					<c:if test="${ckxz ne 'Y'}">
						"temporaryOut":"Y",
					</c:if>
					<c:if test="${ckxz eq 'Y'}">
						<c:if test="${processMap[result.resultFlow].temporaryOut eq 'Y' and processMap[result.resultFlow].temporaryAuditStatusId ne 'NotPassed'}">
							"temporaryOut":"Y",
						</c:if>
						<c:if test="${(processMap[result.resultFlow].temporaryOut ne 'Y' or processMap[result.resultFlow].temporaryAuditStatusId eq 'NotPassed')  and processMap[result.resultFlow].recordStatus ne 'Y'}">
							"temporaryOut":"N",
						</c:if>
					</c:if>
					"xuyck":"${processMap[result.resultFlow].recordStatus}",
					<c:if test="${processMap[result.resultFlow].temporaryOut eq 'Y' and processMap[result.resultFlow].temporaryAuditStatusId eq 'Passed'}">
						"isTemporaryOut":"Y",
					</c:if>
					<c:if test="${processMap[result.resultFlow].temporaryOut ne'Y' or processMap[result.resultFlow].temporaryAuditStatusId ne 'Passed'}">
						"isTemporaryOut":"N",
					</c:if>
		             "teacherName": ${pdfn:toJsonString(processMap[result.resultFlow].teacherUserName)},
		             "headFlow": ${pdfn:toJsonString(processMap[result.resultFlow].headUserFlow)},
		             "headName": ${pdfn:toJsonString(processMap[result.resultFlow].headUserName)},
		             <c:set var="key" value="${processMap[result.resultFlow].processFlow }_TeacherGrade"/>
		             "teacherScore":${pdfn:toJsonString(!empty  gradeMap[key]? gradeMap[key]:'未评价')},
		              <c:set var="key" value="${processMap[result.resultFlow].processFlow }_DeptGrade"/>
		             "deptScore":${pdfn:toJsonString(!empty gradeMap[key]?gradeMap[key]:'未评价')},
	            </c:if>
	            "progress": 100
	           
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ],
		"schMonth":${schMonth},
		"schMaxMonth":${empty rotationDept.schMaxMonth ? "0" : rotationDept.schMaxMonth}
    </c:if>
}
