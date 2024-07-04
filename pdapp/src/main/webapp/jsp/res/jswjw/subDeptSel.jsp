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
        <c:forEach items="${sysDeptList}" var="dept" varStatus="status">
	        {
	            "sysDeptFlow": ${pdfn:toJsonString(dept.deptFlow)},
	            "sysDeptName": ${pdfn:toJsonString(dept.deptName)}
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
