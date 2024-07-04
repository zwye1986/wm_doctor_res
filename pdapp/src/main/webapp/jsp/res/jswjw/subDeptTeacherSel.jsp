<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)} 
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "teachers": [
      <c:forEach items="${teacherList}" var="teacher" varStatus="status">
        {
            "teacherFlow": ${pdfn:toJsonString(teacher.userFlow)},
            "teacherName": ${pdfn:toJsonString(teacher.userName)}
        }
        <c:if test="${not status.last }">
				,
		</c:if>
	 </c:forEach>
    ]
    </c:if>
}
