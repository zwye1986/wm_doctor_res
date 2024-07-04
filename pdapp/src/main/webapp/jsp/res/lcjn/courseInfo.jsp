<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "course": {
                "courseFlow":"${info.courseFlow}",
                "courseName":"${info.courseName}",
                "coursePeopleNum":"${info.coursePeopleNum}",
                "courseAddress":"${info.courseAddress}",
                "courseDemo":"${info.courseDemo}",
                "appointStartTime":"${info.appointStartTime}",
                "appointEndTime":"${info.appointEndTime}",
                "isReleased":"${info.isReleased}",
                "isScoreReleased":"${info.isScoreReleased}",
                "codeInfo":"${info.codeInfo}",
                "recordFlow":"${doctorCourse.recordFlow}",
                "doctorFlow":"${doctorCourse.doctorFlow}",
                "doctorName":"${doctorCourse.doctorName}",
                "appointTime":"${doctorCourse.appointTime}",
                "auditStatusId":"${doctorCourse.auditStatusId}",
                "auditStatusName":"${doctorCourse.auditStatusName}",
                "reason":"${doctorCourse.reason}",
                "examScore":"${doctorCourse.examScore}",
                "lastNum":"${lastNum}",
                <c:set var="appointId" value="Appoint"></c:set>
                <c:set var="appointName" value="预约"></c:set>
                <c:choose>
                    <c:when test="${info.appointStartTime > nowTime}">
                        <c:set var="appointId" value="NoStart"></c:set>
                        <c:set var="appointName" value="未开始"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${(doctorCourse.auditStatusId eq 'Passed') }">
                                <c:set var="appointId" value="AppointEd"></c:set>
                                <c:set var="appointName" value="已预约"></c:set>
                            </c:when>
                            <c:when test="${doctorCourse.auditStatusId eq 'Passing'}">
                                <c:set var="appointId" value="Passing"></c:set>
                                <c:set var="appointName" value="审核中"></c:set>
                            </c:when>
                            <c:when test="${lastNum <= 0}">
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
            },
    "times":[
        <c:forEach items="${times}" var="bean" varStatus="s">
        {
            "recordFlow":"${bean.recordFlow}",
            "trainStartDate":"${bean.trainStartDate}",
            "trainEndDate":"${bean.trainStartDate}",
            "startTime":"${bean.startTime}",
            "endTime":"${bean.endTime}"
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ],
    "teas":[
        <c:forEach items="${teas}" var="bean" varStatus="s">
        {
            "recordFlow":"${bean.recordFlow}",
            "userFlow":"${bean.userFlow}",
            "userName":"${bean.userName}",
            "teachingCost":"${bean.teachingCost}"
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ],
    "skills":[
        <c:forEach items="${skills}" var="bean" varStatus="s">
        {
            "recordFlow":"${bean.recordFlow}",
            "skillFlow":"${bean.skillFlow}",
            "skillName":"${bean.skillName}"
        }
        <c:if test="${not s.last }">
         ,
        </c:if>
        </c:forEach>
    ]
    </c:if>
}