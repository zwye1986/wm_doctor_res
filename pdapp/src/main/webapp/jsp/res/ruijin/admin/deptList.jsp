<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "depts": [
        <c:forEach items="${depts}" var="dept" varStatus="status">
            <c:set var="monthCurrent" value="${dept.deptFlow}monthCurrent"></c:set>
            <c:set var="monthSch" value="${dept.deptFlow}monthSch"></c:set>
            <c:set var="waitSch" value="${dept.deptFlow}waitSch"></c:set>
	        {
	            "deptFlow": ${pdfn:toJsonString(dept.deptFlow)},
	            "deptName": ${pdfn:toJsonString(dept.deptName)},
	            "monthCurrent": ${pdfn:toJsonString(empty deptInfo[monthCurrent]?0:deptInfo[monthCurrent])},
	            "monthSch": ${pdfn:toJsonString(empty deptInfo[monthSch]?0:deptInfo[monthSch])},
	            "waitSch": ${pdfn:toJsonString(empty deptInfo[waitSch]?0:deptInfo[waitSch])},
	            "order": ${status.index+1}
	        }
	        <c:if test="${not status.last }">
	        ,
	        </c:if>
        </c:forEach>
    ]
    </c:if>
}
