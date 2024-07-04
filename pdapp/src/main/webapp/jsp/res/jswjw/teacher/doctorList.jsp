<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": 1,
    "pageSize": 10,
    "dataCount": ${dataCount},
    "doctorList": [
    	<c:forEach items="${doctorMapList}" var="doctorMap" varStatus="status">
    		<c:set var="headImg" value="${uploadBaseUrl}/${empty doctorMap.doctorImg?'default.png':doctorMap.doctorImg}"/>
    		<c:set var="statusId" value=""/>
    		<c:set var="statusName" value=""/>
    		<c:if test="${doctorMap.isCurrentFlag eq 'Y'}">
    			<c:set var="statusId" value="Entering"/>
    			<c:set var="statusName" value="轮转中"/>
    		</c:if>
    		<c:if test="${doctorMap.schFlag eq 'Y'}">
    			<c:set var="statusId" value="Exited"/>
    			<c:set var="statusName" value="已出科"/>
    		</c:if>
	        {
	            "doctorFlow": ${pdfn:toJsonString(doctorMap.doctorFlow)},
	            "doctorName": ${pdfn:toJsonString(doctorMap.doctorName)},
	            "doctorImg": ${pdfn:toJsonString(headImg)},
	            "subDeptFlow": ${pdfn:toJsonString(doctorMap.resultFlow)},
	            "subDeptName": ${pdfn:toJsonString(doctorMap.schDeptName)},
	            "schStartDate": ${pdfn:toJsonString(doctorMap.schStartDate)},
	            "schEndDate": ${pdfn:toJsonString(doctorMap.schEndDate)},
	            "statusId": ${pdfn:toJsonString(statusId)},
	            "statusDesc": ${pdfn:toJsonString(statusName)},
	            "startDate": ${pdfn:toJsonString(doctorMap.startDate)},
	            "endDate": ${pdfn:toJsonString(doctorMap.endDate)},
				"score": ${pdfn:toJsonString(doctorMap.score)}
	        }
        <c:if test="${!status.last}">
	        ,
        </c:if>
    	</c:forEach>
    ]
    </c:if>
}
