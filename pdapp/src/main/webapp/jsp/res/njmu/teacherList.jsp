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
      <c:forEach items="${deptMapList}" var="dataMap" varStatus="status">
        {
            "teacherFlow": ${pdfn:toJsonString(dataMap.teacherFlow)},
            "teacherName": "${dataMap.teacherName}[${dataMap.deptName}]",
            "teacherSex": ${pdfn:toJsonString(dataMap.teacherSex)}
        }
        <c:if test="${not status.last }">
				,
		</c:if>
	 </c:forEach>
    ]
    </c:if>
}
