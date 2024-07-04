<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": 1,
    "pageSize": 10,
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
                "reason":"${bean.reason}",
                "examScore":"${bean.examScore}",
                "addNum":"${bean.addNum}",
                "lastNum":"${bean.lastNum}",
                <c:set var="appointId" value="NoThing"></c:set>
                <c:set var="appointName" value="无操作"></c:set>
                <c:set var="auditStatusId" value="NoThing"></c:set>
                <c:set var="auditStatusName" value="无状态"></c:set>
                    <c:if test="${(bean.auditStatusId eq 'Passed')}">
                        <c:set var="auditStatusId" value="AppointSuccess"></c:set>
                        <c:set var="auditStatusName" value="预约成功"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'UnPassed')}">
                        <c:set var="auditStatusId" value="AppointFail"></c:set>
                        <c:set var="auditStatusName" value="预约失败"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Invalid')}">
                        <c:set var="auditStatusId" value="Invalid"></c:set>
                        <c:set var="auditStatusName" value="已失效"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passed') and (startDateMap[bean.courseFlow]<=nowTime ) and ( evalMap[bean.courseFlow] eq 'false')}">
                        <c:set var="auditStatusId" value="AppointSuccess"></c:set>
                        <c:set var="auditStatusName" value="预约成功"></c:set>
                        <c:set var="appointId" value="Evaluate"></c:set>
                        <c:set var="appointName" value="评价"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passed') and (evalMap[bean.courseFlow] eq 'true' )}">
                        <c:set var="auditStatusId" value="AppointSuccess"></c:set>
                        <c:set var="auditStatusName" value="预约成功"></c:set>
                        <c:set var="appointId" value="ShowEvaluate"></c:set>
                        <c:set var="appointName" value="查看"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passing')}">
                        <c:set var="auditStatusId" value="AduitIng"></c:set>
                        <c:set var="auditStatusName" value="审核中"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passing') and (bean.appointStartTime <= nowTime) and (bean.appointEndTime >= nowTime)}">
                        <c:set var="appointId" value="Cancell"></c:set>
                        <c:set var="appointName" value="取消预约"></c:set>
                        <c:set var="auditStatusId" value="AduitIng"></c:set>
                        <c:set var="auditStatusName" value="审核中"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passing') and !((bean.appointStartTime <= nowTime) and (bean.appointEndTime >= nowTime))}">
                        <c:set var="appointId" value="NotCancell"></c:set>
                        <c:set var="appointName" value="无法取消预约"></c:set>
                        <c:set var="auditStatusId" value="AduitIng"></c:set>
                        <c:set var="auditStatusName" value="审核中"></c:set>
                    </c:if>
                    <c:if test="${(bean.auditStatusId eq 'Passing') and (startDateMap[bean.courseFlow]<nowTime ) }">
                        <c:set var="auditStatusId" value="Invalid"></c:set>
                        <c:set var="auditStatusName" value="已失效"></c:set>
                    </c:if>
                "auditStatusId":"${auditStatusId}",
                "auditStatusName":"${auditStatusName}",
                "actionId":"${appointId}",
                "actionName":"${appointName}",
                <c:set var="times" value="${timeMap[bean.courseFlow]}"></c:set>
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
                ]
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
    ]
    </c:if>
}