<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
   <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "deptHeads": [
      <c:forEach items="${deptMapList}" var="dataMap" varStatus="status">
        {
            "deptHeadFlow": ${pdfn:toJsonString(dataMap.deptHeadFlow)},
            "deptHeadName": "${dataMap.deptHeadName}[${dataMap.deptName}]",
            "deptHeadSex": ${pdfn:toJsonString(dataMap.deptHeadSex)}
        }
        <c:if test="${not status.last }">
				,
		</c:if>
	 </c:forEach>
    ]
    </c:if>
}
