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
     <c:forEach items="${deptMapList}" var="dataMap" varStatus="status">
	     	{
				"deptFlow": ${pdfn:toJsonString(dataMap.deptFlow)},
				"deptOrder": ${dataMap.deptOrder},
				"deptName": ${pdfn:toJsonString(dataMap.deptName)},
				"startDate": ${pdfn:toJsonString(dataMap.startDate)},
				"endDate": ${pdfn:toJsonString(dataMap.endDate)},
				"teacherName": ${pdfn:toJsonString(dataMap.teacherName)},
				"deptHeadName": ${pdfn:toJsonString(dataMap.deptHeadName)},
				"statusId": ${pdfn:toJsonString(dataMap.statusId)},
				"statusDesc": ${pdfn:toJsonString(dataMap.statusDesc)},
				"score": ${pdfn:toJsonString(dataMap.score)},
				"progress": ${empty dataMap.progress ? 0 : dataMap.progress}
			}
			<c:if test="${not status.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
