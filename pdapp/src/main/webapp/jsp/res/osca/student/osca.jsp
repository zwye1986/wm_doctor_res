<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "main": 
            {
                "subjectFlow":${pdfn:toJsonString(main.subjectFlow)},
                "subjectName":${pdfn:toJsonString(main.subjectName)},
                "trainingSpeId":${pdfn:toJsonString(main.trainingSpeId)},
                "trainingSpeName":${pdfn:toJsonString(main.trainingSpeName)},
                "actionTypeId":${pdfn:toJsonString(main.actionTypeId)},
                "actionTypeName":${pdfn:toJsonString(main.actionTypeName)},
                "stationNum":${pdfn:toJsonString(main.stationNum)},
                "isReleased":${pdfn:toJsonString(main.isReleased)},
                <c:set var="name" value="未发布"></c:set>
                <c:if test="${main.isReleased eq 'Y'}">
                    <c:set var="name" value="已发布"></c:set>
                </c:if>
                "isReleasedName":${pdfn:toJsonString(name)}
            },
        "oscaSkillsAssessment":
            {
                "clinicalFlow":${pdfn:toJsonString(oscaSkillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(oscaSkillsAssessment.clinicalName)},
                "clinicalYear":${pdfn:toJsonString(oscaSkillsAssessment.clinicalYear)},
                "speId":${pdfn:toJsonString(oscaSkillsAssessment.speId)},
                "speName":${pdfn:toJsonString(oscaSkillsAssessment.speName)},
                "actionTypeId":${pdfn:toJsonString(oscaSkillsAssessment.actionTypeId)},
                "actionTypeName":${pdfn:toJsonString(oscaSkillsAssessment.actionTypeName)},
                "subjectFlow":${pdfn:toJsonString(oscaSkillsAssessment.subjectFlow)},
                "subjectName":${pdfn:toJsonString(oscaSkillsAssessment.subjectName)},
                "appointStartTime":${pdfn:toJsonString(oscaSkillsAssessment.appointStartTime)},
                "appointEndTime":${pdfn:toJsonString(oscaSkillsAssessment.appointEndTime)},
                "appointNum":${pdfn:toJsonString(oscaSkillsAssessment.appointNum)},
                "examStartTime":${pdfn:toJsonString(oscaDoctorAssessment.examStartTime)},
                "examEndTime":${pdfn:toJsonString(oscaDoctorAssessment.examEndTime)},
                "examAddress":${pdfn:toJsonString(oscaSkillsAssessment.examAddress)},
                "isLocal":${pdfn:toJsonString(oscaSkillsAssessment.isLocal)},
                <c:set var="isLocalName" value="非本院考核信息"></c:set>
                <c:if test="${oscaSkillsAssessment.isLocal eq 'Y'}">
                    <c:set var="isLocalName" value="本院考核信息"></c:set>
                </c:if>
                "isLocalName":${pdfn:toJsonString(isLocalName)},
                "orgFlow":${pdfn:toJsonString(oscaSkillsAssessment.orgFlow)},
                "orgName":${pdfn:toJsonString(oscaSkillsAssessment.orgName)},
                "isShow":${pdfn:toJsonString(oscaSkillsAssessment.isShow)},
                <c:set var="isShowName" value="学员不可查看成绩"></c:set>
                <c:if test="${oscaSkillsAssessment.isLocal eq 'Y'}">
                    <c:set var="isShowName" value="学员可查看成绩"></c:set>
                </c:if>
                "isShowName":${pdfn:toJsonString(isShowName)},
                "isReleased":${pdfn:toJsonString(oscaSkillsAssessment.isReleased)},
                <c:set var="name" value="未发布"></c:set>
                <c:if test="${oscaSkillsAssessment.isReleased eq 'Y'}">
                    <c:set var="name" value="已发布"></c:set>
                </c:if>
                "isReleasedName":${pdfn:toJsonString(name)},
                "codeInfo":${pdfn:toJsonString(oscaSkillsAssessment.codeInfo)}
            },
        "oscaDoctorAssessment":
            {
                "recordFlow":${pdfn:toJsonString(oscaDoctorAssessment.recordFlow)},
                "clinicalFlow":${pdfn:toJsonString(oscaDoctorAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(oscaDoctorAssessment.clinicalName)},
                "clinicalYear":${pdfn:toJsonString(oscaDoctorAssessment.clinicalYear)},
                "doctorFlow":${pdfn:toJsonString(oscaDoctorAssessment.doctorFlow)},
                "doctorName":${pdfn:toJsonString(oscaDoctorAssessment.doctorName)},
                "appointTime":${pdfn:toJsonString(oscaDoctorAssessment.appointTime)},
                "auditStatusId":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusId)},
                "auditStatusName":${pdfn:toJsonString(oscaDoctorAssessment.auditStatusName)},
                "reason":${pdfn:toJsonString(oscaDoctorAssessment.reason)},
                "siginTime":${pdfn:toJsonString(oscaDoctorAssessment.siginTime)},
                "siginStatusId":${pdfn:toJsonString(oscaDoctorAssessment.siginStatusId)},
                "siginStatusName":${pdfn:toJsonString(oscaDoctorAssessment.siginStatusName)},
                "ticketNumber":${pdfn:toJsonString(oscaDoctorAssessment.ticketNumber)},
                "isPass":${pdfn:toJsonString(oscaDoctorAssessment.isPass)},
                "codeInfo":${pdfn:toJsonString(oscaDoctorAssessment.codeInfo)}
            },
        "nowDay": ${pdfn:toJsonString(nowDay)},
        "stations":
        [
        <c:forEach items="${stations}" var="station" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(station.stationFlow)},
                "stationName": ${pdfn:toJsonString(station.stationName)},
                "clinicalFlow":${pdfn:toJsonString(oscaDoctorAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(oscaDoctorAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "examinedContent": ${pdfn:toJsonString(station.examinedContent)},
                "stationScore": ${pdfn:toJsonString(station.stationScore)},
                "order": ${status.index+1},
                <c:set var="roomMap" value="${stationRoomMap[station.stationFlow]}"></c:set>
                "roomSize":${pdfn:toJsonString(roomMap.roomSize)},
                <c:set var="roomExt" value="${roomMap.roomExt}"></c:set>
                "roomRecordFlow":${pdfn:toJsonString(roomExt.recordFlow)},
                "roomFlow":${pdfn:toJsonString(roomExt.roomFlow)},
                "roomName":${pdfn:toJsonString(roomExt.roomName)},
                "peopleCount":${pdfn:toJsonString(roomExt.peopleCount)},
                "waitingCount":${pdfn:toJsonString(roomExt.waitingCount)},
                "examStatusId":${pdfn:toJsonString(roomExt.examStatusId)},
                "examStatusName":${pdfn:toJsonString(roomExt.examStatusName)},
                "examScore":${pdfn:toJsonString(roomExt.examScore)},
                "showBtn":"N",
                <c:set var="isCan" value="${canClick}"></c:set>
                <c:if test="${(canClick eq 'N') and (roomExt.examStatusId eq 'Waiting')}">
                    <c:set var="isCan" value="Y"></c:set>
                </c:if>
                <c:if test="${(roomExt.examStatusId eq 'Assessment')}">
                    <c:set var="isCan" value="N"></c:set>
                </c:if>
                "canClick":${pdfn:toJsonString(isCan)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
