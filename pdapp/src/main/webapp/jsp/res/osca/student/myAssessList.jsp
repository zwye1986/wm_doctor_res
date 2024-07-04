<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${skillsAssessmentList}" var="data" varStatus="s">
			<c:set var="times" value="${timesMap[data.clinicalFlow]}"></c:set>
			<c:set var="oscaDoctorAssessment" value="${odaMap[data.clinicalFlow]}"></c:set>
	     	{
				"clinicalFlow": ${pdfn:toJsonString(data.clinicalFlow)},
				"clinicalName": ${pdfn:toJsonString(data.clinicalName)},
				"clinicalYear": ${pdfn:toJsonString(data.clinicalYear)},
				"isLocal": ${pdfn:toJsonString(data.isLocal)},
				"speName": ${pdfn:toJsonString(data.speName)},
				"appointStartTime": ${pdfn:toJsonString(data.appointStartTime)},
				"appointEndTime": ${pdfn:toJsonString(data.appointEndTime)},
				"appointNum": ${pdfn:toJsonString(data.appointNum)},
				"overplus": ${pdfn:toJsonString(data.overplus)},
				"orgName": ${pdfn:toJsonString(data.orgName)},
				"remarks":${pdfn:toJsonString(data.remarks)},
				"examAddress":${pdfn:toJsonString(data.examAddress)},
				"recordFlow":${pdfn:toJsonString(oscaDoctorAssessment.recordFlow)},
				"doctorFlow":${pdfn:toJsonString(oscaDoctorAssessment.doctorFlow)},
				"appointTime":${pdfn:toJsonString(oscaDoctorAssessment.appointTime)},
				"examStartTime":${pdfn:toJsonString(oscaDoctorAssessment.examStartTime)},
				"examEndTime":${pdfn:toJsonString(oscaDoctorAssessment.examEndTime)},
				"appointTime":${pdfn:toJsonString(oscaDoctorAssessment.appointTime)},
				"auditStatusId":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusId)},
				"auditStatusName":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusName)},
				"reason":${pdfn:toJsonString(oscaDoctorAssessment.reason)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}