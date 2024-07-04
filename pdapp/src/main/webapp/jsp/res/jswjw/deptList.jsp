<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}

<c:if test="${resultId eq '200' }">
	,
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount": ${dataCount},
	"edit": ${pdfn:toJsonString(edit)},
	"cfgValue": ${pdfn:toJsonString(cfgValue)},
	"depts": [
	<c:forEach items="${rotationDeptMap}" var="dataMap" varStatus="status">
		{
		<c:set var="stageName" value="${dataMap.stageName}"/>
		"stageName": ${pdfn:toJsonString(stageName)},
		"deptFlow": ${pdfn:toJsonString(dataMap.recordFlow)},
		"isRequired":${pdfn:toJsonString(dataMap.isRequired)},
		"order": ${status.index+1},
		"deptName": ${pdfn:toJsonString(dataMap.deptName)},
		"schMonth":  ${pdfn:toJsonString(dataMap.schMonth+0)},
		"realMonth": ${pdfn:toJsonString(dataMap.realMonth)},
		"deptNote" :${pdfn:toJsonString(dataMap.deptNote)},
		"isCurrent" :${pdfn:toJsonString(dataMap.isCurrent)},
		<c:set var="progress" value="${progressMap[dataMap.recordFlow]}"/>
		<c:if test="${empty progressMap[dataMap.recordFlow]}">
			<c:set var="progress" value="0"/>
		</c:if>
		"progress": ${pdfn:toJsonString(progress)}
		}
		<c:if test="${not status.last }">
			,
		</c:if>
	</c:forEach>
	]
</c:if>
}
