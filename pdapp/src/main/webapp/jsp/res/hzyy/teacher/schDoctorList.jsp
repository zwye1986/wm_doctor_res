<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    "doctorList": [
	    <c:forEach items="${doctotList}" var="doctor" varStatus="status">
				{
					"studentFlow":"${doctor.doctorFlow}",
					"doctorFlow":"${doctor.UCSID}",
					"doctorName":"${doctor.TrueName}",
					"doctorImg":"<s:url value='/images/xx.png'/>",
					"cySecId":"${doctor.cySecId}",
					"schStartDate":"${doctor.RStartTime}",
	                "schEndDate":"${doctor.REndTime}",
	                "schStatusId":"${doctor.schStatusId}",
	                "schStatusDesc":"${doctor.schStatusDesc}",
	                "startDate":"${doctor.RStartTime}",
                	"endDate":"${doctor.REndTime}"
				}
				<c:if test="${not status.last}">
					,
				</c:if>
	     </c:forEach>
    ]
    </c:if>
}
