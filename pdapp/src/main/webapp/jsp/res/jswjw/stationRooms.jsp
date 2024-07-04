<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "nowDay": ${pdfn:toJsonString(nowDay)},
        "isSelect": ${pdfn:toJsonString(isSelect)},
        "stations":
        [
        <c:if test="${not empty roomExt}">
            {
                "stationFlow": ${pdfn:toJsonString(roomExt.stationFlow)},
                "stationName": ${pdfn:toJsonString(roomExt.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "examinedContent": ${pdfn:toJsonString(station.examinedContent)},
                "stationScore": ${pdfn:toJsonString(station.stationScore)},
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
            <c:if test="${not empty list}">
                ,
            </c:if>
        </c:if>
        <c:forEach items="${list}" var="bean" varStatus="status">
            {
                "stationFlow": ${pdfn:toJsonString(bean.stationFlow)},
                "stationName": ${pdfn:toJsonString(bean.stationName)},
                "clinicalFlow":${pdfn:toJsonString(skillsAssessment.clinicalFlow)},
                "clinicalName":${pdfn:toJsonString(skillsAssessment.clinicalName)},
                "subjectName": ${pdfn:toJsonString(station.subjectName)},
                "subjectFlow": ${pdfn:toJsonString(station.subjectFlow)},
                "examinedContent": ${pdfn:toJsonString(station.examinedContent)},
                "stationScore": ${pdfn:toJsonString(station.stationScore)},
                "roomRecordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "roomFlow":${pdfn:toJsonString(bean.roomFlow)},
                "roomName":${pdfn:toJsonString(bean.roomName)},
                "peopleCount":${pdfn:toJsonString(bean.peopleCount)},
                "waitingCount":${pdfn:toJsonString(bean.waitingCount)},
                "examStatusId":${pdfn:toJsonString(bean.examStatusId)},
                "examStatusName":${pdfn:toJsonString(bean.examStatusName)},
                "examScore":${pdfn:toJsonString(bean.examScore)},
                "showBtn":"N",
                <c:set var="isCan" value="${canClick}"></c:set>
                <c:if test="${(canClick eq 'N') and (bean.examStatusId eq 'Waiting')}">
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
