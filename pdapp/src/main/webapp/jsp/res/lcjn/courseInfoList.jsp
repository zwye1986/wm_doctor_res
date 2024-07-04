<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": 1,
    "pageSize": 10,
    "noticesCount": "${noticesCount}",
    "dataCount": ${dataCount},
    "datas": [
        <c:forEach items="${infos}" var="bean" varStatus="s">
            {
                "courseFlow":"${bean.courseFlow}",
                "courseName":"${bean.courseName}",
                "coursePeopleNum":"${bean.coursePeopleNum}",
                "courseAddress":"${bean.courseAddress}",
                "courseDemo":"${bean.courseDemo}",
                "appointStartTime":"${bean.appointStartTime}",
                "appointEndTime":"${bean.appointEndTime}",
                "isReleased":"${bean.isReleased}",
                "isScoreReleased":"${bean.isScoreReleased}",
                "codeInfo":"${bean.codeInfo}",
                "recordFlow":"${bean.recordFlow}",
                "doctorFlow":"${bean.doctorFlow}",
                "doctorName":"${bean.doctorName}",
                "appointTime":"${bean.appointTime}",
                "auditStatusId":"${bean.auditStatusId}",
                "auditStatusName":"${bean.auditStatusName}",
                "reason":"${bean.reason}",
                "examScore":"${bean.examScore}",
                "addNum":"${bean.addNum}",
                "lastNum":"${bean.lastNum}",
                <c:set var="appointId" value="Appoint"></c:set>
                <c:set var="appointName" value="预约"></c:set>
                <c:choose>
                    <c:when test="${bean.appointStartTime > nowTime}">
                        <c:set var="appointId" value="NoStart"></c:set>
                        <c:set var="appointName" value="未开始"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${(bean.auditStatusId eq 'Passed') }">
                                <c:set var="appointId" value="AppointEd"></c:set>
                                <c:set var="appointName" value="已预约"></c:set>
                            </c:when>
                            <c:when test="${bean.auditStatusId eq 'Passing'}">
                                <c:set var="appointId" value="Passing"></c:set>
                                <c:set var="appointName" value="审核中"></c:set>
                            </c:when>
                            <c:when test="${bean.lastNum <= 0}">
                                <c:set var="appointId" value="NotAppoint"></c:set>
                                <c:set var="appointName" value="不可预约"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="appointId" value="Appoint"></c:set>
                                <c:set var="appointName" value="预约"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
            </c:choose>
                "isCanAppointId":"${appointId}",
                "isCanAppointName":"${appointName}"
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
    ]
    </c:if>
}