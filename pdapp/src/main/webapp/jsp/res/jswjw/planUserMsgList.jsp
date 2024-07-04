<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "doctorList": [
     <c:forEach items="${doctorList}" var="d" varStatus="status">
	     	{
		 		"order": ${status.index+1},
	     		"planContent": ${pdfn:toJsonString(d.planContent)},
				"planStartTime": ${pdfn:toJsonString(d.planStartTime)},
				"planEndTime": ${pdfn:toJsonString(d.planEndTime)},
				"certificateNo":${pdfn:toJsonString(d.certificateNo)},
				"sendCertificateTime": ${pdfn:toJsonString(d.sendCertificateTime)},
				"recordFlow":  ${pdfn:toJsonString(d.recordFlow)}
			}
			<c:if test="${not status.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
