<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "depts": [
        <c:forEach items="${depts}" var="dept" varStatus="status">
	        {
	            "deptFlow": ${pdfn:toJsonString(dept.deptFlow)},
	            "deptName": ${pdfn:toJsonString(dept.deptName)},
	            "order": ${status.index+1},
				<c:if test="${not empty inprocessInfoMap[dept.deptFlow]}">
					"canShowInfo":"Y"
				</c:if>
				<c:if test="${empty inprocessInfoMap[dept.deptFlow]}">
					"canShowInfo":"N"
				</c:if>
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
