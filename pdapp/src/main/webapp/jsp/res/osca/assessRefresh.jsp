<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
    "showSubmit":"${showSubmit}",
    "doctorInfo":{
        "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
        "doctorName":${pdfn:toJsonString(docUser.userName)},
        <c:set var="headImg" value="${uploadBaseUrl}/${empty docUser.userHeadImg?'default.png':docUser.userHeadImg}"/>
        "headImg": ${pdfn:toJsonString(headImg)},
        "trainingSpeName":${pdfn:toJsonString(doctor.trainingSpeName)},
        "sessionNumber":${pdfn:toJsonString(doctor.sessionNumber)},
        "ticketNumber":${pdfn:toJsonString(doctorAssessment.ticketNumber)}
        },
    "paramMap":{
        "clinicalFlow":${pdfn:toJsonString(paramMap.clinicalFlow)},
        "recordFlow":${pdfn:toJsonString(paramMap.recordFlow)},
        "roleId":${pdfn:toJsonString(paramMap.roleId)},
        "doctorFlow":${pdfn:toJsonString(paramMap.doctorFlow)}
        },
    "stations":
        [
        <c:forEach items="${stations}" var="station" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(station.stationFlow)},
                "stationName": ${pdfn:toJsonString(station.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "examinedContent": ${pdfn:toJsonString(station.examinedContent)},
                "stationScore": ${pdfn:toJsonString(station.stationScore)},
                "order": ${status.index+1},
                <c:set value="${stationMap[station.stationFlow]}" var="bean"/>
                "roomRecordFlow":${pdfn:toJsonString(bean.roomRecordFlow)},
                "roomFlow":${pdfn:toJsonString(bean.roomFlow)},
                "roomName":${pdfn:toJsonString(bean.roomName)},
                "stationExamStatusId":${pdfn:toJsonString(bean.examStatusId)},
                "stationExamStatusName":${pdfn:toJsonString(bean.examStatusName)},
                "examScoreStatusId":${pdfn:toJsonString(bean.examScoreStatusId)},
                <c:set value="${bean.scores}" var="scores"/>
                "examScores":[
                    <c:forEach items="${bean.scores}" var="score" varStatus="s1">
                        {
                            "examScore":${pdfn:toJsonString(score.examScore)},
                            "examScoreStatusId":${pdfn:toJsonString(score.statusId)},
                            "examScoreStatusName":${pdfn:toJsonString(score.statusName)},
                            "examScoreFlow":${pdfn:toJsonString(score.scoreFlow)},
                            "examScoreFromFlow":${pdfn:toJsonString(score.fromFlow)},
                            "examScoreFromName":${pdfn:toJsonString(score.fromName)},
                            "examScoreFromType":${pdfn:toJsonString(score.fromTypeId)},
                            "isHaveFrom":${pdfn:toJsonString(score.isHaveFrom)},
                            "isRequired":${pdfn:toJsonString(score.isRequired)}
                        }
                        <c:if test="${not s1.last }">
                            ,
                        </c:if>
                    </c:forEach>
                ],
                <c:set value="${station.stationFlow}ExamFiles" var="key"/>
                <c:set value="${stationMap[key]}" var="haveFile"/>
                "haveFile":${pdfn:toJsonString(haveFile)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
