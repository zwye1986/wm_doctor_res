<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "data":{
        "allScore":${pdfn:toJsonString(allScore)},
        "userName":${pdfn:toJsonString(user.userName)},
        "isLocal":${pdfn:toJsonString(skillsAssessment.isLocal)},
        "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
        "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
        "examAddress":${pdfn:toJsonString(skillsAssessment.examAddress)},
        "remarks":${pdfn:toJsonString(skillsAssessment.remarks)},
        "speName":${pdfn:toJsonString(skillsAssessment.speName)},
        "speId":${pdfn:toJsonString(skillsAssessment.speId)},
        "ticketNumber":${pdfn:toJsonString(oda.ticketNumber)},
        "examStartTime":${pdfn:toJsonString(oda.examStartTime)},
        "examEndTime":${pdfn:toJsonString(oda.examEndTime)},
        "idNo":${pdfn:toJsonString(user.idNo)},
        "isPass": ${pdfn:toJsonString(oda.isPass)},
        "isPassName": ${pdfn:toJsonString(oda.isPassName)},
        "stations":[
				<c:forEach items="${stations}" var="bean" varStatus="s1">
                    <c:set var="rd" value="${docRoomMap[bean.stationFlow]}"></c:set>
					{
						"stationFlow":"${bean.stationFlow}",
						"stationName":"${bean.stationName}",
						"stationScore":"${bean.stationScore}",
						"examScore":"${rd.examScore}"
					}
					<c:if test="${not s1.last }">
						,
					</c:if>
				</c:forEach>
        ]
     }
    </c:if>
}
