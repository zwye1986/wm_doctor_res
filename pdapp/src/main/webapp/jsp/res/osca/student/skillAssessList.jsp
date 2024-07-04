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
				"auditStatusId":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusId)},
				"auditStatusName":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusName)},
				"times":[
					<c:forEach items="${times}" var="bean" varStatus="s1">
						{
							"examStartTime":"${bean.examStartTime}",
							"examEndTime":"${bean.examEndTime}"
						}
						<c:if test="${not s1.last }">
							,
						</c:if>
					</c:forEach>
				],
				<c:choose>

					<c:when test="${data.isLocal eq 'N' and data.clinicalYear >= graduationYear}">
						<c:choose>
							<c:when test="${empty oscaDoctorAssessment or oscaDoctorAssessment.auditStatusId eq 'UnPassed' }">
								"actionId":"NotAppoint",
								"actionName":"未预约"
							</c:when>
							<c:when test="${ oscaDoctorAssessment.auditStatusId eq 'Passed'}">
								"actionId":"AppointEd",
								"actionName":"已预约"
							</c:when>
							<c:when test="${ oscaDoctorAssessment.auditStatusId eq 'Passing'}">
								"actionId":"Appointing",
								"actionName":"预约中"
							</c:when>
							<c:otherwise>
								"actionId":"",
								"actionName":""
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${data.isLocal eq 'N' and data.clinicalYear < graduationYear}">
						"actionId":"",
						"actionName":""
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${empty oscaDoctorAssessment or oscaDoctorAssessment.auditStatusId eq 'UnPassed' }">
								"actionId":"NotAppoint",
								"actionName":"未预约"
							</c:when>
							<c:when test="${ oscaDoctorAssessment.auditStatusId eq 'Passed'}">
								"actionId":"AppointEd",
								"actionName":"已预约"
							</c:when>
							<c:when test="${ oscaDoctorAssessment.auditStatusId eq 'Passing'}">
								"actionId":"Appointing",
								"actionName":"预约中"
							</c:when>
							<c:otherwise>
								"actionId":"",
								"actionName":""
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}