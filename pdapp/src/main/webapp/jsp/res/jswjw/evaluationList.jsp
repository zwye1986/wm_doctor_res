<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8" %>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
    <c:if test="${roleFlag eq 'Teacher'}">
        "datas": [
        <c:forEach items="${processList}" var="process" varStatus="s">
            <c:set var="user" value="${userMap[process.userFlow]}"/>
            <c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
            <c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
            <c:set var="mapKey1" value="${process.processFlow}TeacherDoctorAssess"/>
            <c:set var="mapKey2" value="${process.processFlow}TeacherDoctorAssessTwo"/>
            <c:set var="rec" value="${requestScope[mapKey1]}"/>
            <c:set var="rec2" value="${requestScope[mapKey2]}"/>
            <c:set var="mapKey" value="TeacherDoctorAssess${process.processFlow}"/>
            <c:set var="mapKey2" value="TeacherDoctorAssessTwo${process.processFlow}"/>
            <c:set var="dataMap" value="${requestScope[mapKey]}"/>
            <c:set var="dataMap2" value="${requestScope[mapKey2]}"/>
            {
            "userName":${pdfn:toJsonString(user.userName)},
            "sessionNumber":${pdfn:toJsonString(doctor.sessionNumber)},
            "trainingSpeName":${pdfn:toJsonString(doctor.trainingSpeName)},
            "doctorTypeName":${pdfn:toJsonString(doctor.doctorTypeName)},
            "schDeptName":${pdfn:toJsonString(process.schDeptName)},
            "schStartDate":${pdfn:toJsonString(process.schStartDate)},
            "schEndDate":${pdfn:toJsonString(process.schEndDate)},
            "status":
            <c:if test="${empty rec and empty rec2}">
                "待评价",
            </c:if>
            <c:if test="${not empty rec or not empty rec2}">
                "已评价",
            </c:if>
            <c:if test="${empty rec and empty rec2}">
                "totalScore":"",
                "recTypeId":${pdfn:toJsonString(rec.recTypeId)},
                "schResultFlow":${pdfn:toJsonString(process.schResultFlow)},
                "rotationFlow":${pdfn:toJsonString(result.rotationFlow)},
                "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
                "recFlow":${pdfn:toJsonString(rec.recFlow)},
                "processFlow":${pdfn:toJsonString(process.processFlow)},
                "schDeptFlow":${pdfn:toJsonString(process.schDeptFlow)},
                "typeId":"",
                "deptFlow":${pdfn:toJsonString(process.deptFlow)}
            </c:if>
            <c:if test="${not empty rec and empty rec2}">
                "totalScore":${pdfn:toJsonString((dataMap.totalScore+0))},
                "recTypeId":${pdfn:toJsonString(rec.recTypeId)},
                "schResultFlow":${pdfn:toJsonString(process.schResultFlow)},
                "rotationFlow":${pdfn:toJsonString(result.rotationFlow)},
                "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
                "recFlow":${pdfn:toJsonString(rec.recFlow)},
                "processFlow":${pdfn:toJsonString(process.processFlow)},
                "schDeptFlow":${pdfn:toJsonString(process.schDeptFlow)},
                "typeId":${pdfn:toJsonString(rec.recTypeId)},
                "deptFlow":${pdfn:toJsonString(process.deptFlow)}
            </c:if>
            <c:if test="${not empty rec2 and empty rec}">
                "totalScore":${pdfn:toJsonString((dataMap.totalScore+0))},
                "recTypeId":${pdfn:toJsonString(rec2.recTypeId)},
                "schResultFlow":${pdfn:toJsonString(process.schResultFlow)},
                "rotationFlow":${pdfn:toJsonString(result.rotationFlow)},
                "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
                "recFlow":${pdfn:toJsonString(rec2.recFlow)},
                "processFlow":${pdfn:toJsonString(process.processFlow)},
                "schDeptFlow":${pdfn:toJsonString(process.schDeptFlow)},
                "typeId":${pdfn:toJsonString(rec2.recTypeId)},
                "deptFlow":${pdfn:toJsonString(process.deptFlow)}
            </c:if>
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
    <c:if test="${roleFlag eq 'Nurse'}">
        "datas": [
        <c:forEach items="${processList}" var="process" varStatus="s">
            <c:set var="user" value="${userMap[process.userFlow]}"/>
            <c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
            <c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
            <c:set var="mapKey1" value="${process.processFlow}NurseDoctorGrade"/>
            <c:set var="rec" value="${requestScope[mapKey1]}"/>
            <c:set var="mapKey" value="NurseDoctorGrade${process.processFlow}"/>
            <c:set var="dataMap" value="${requestScope[mapKey]}"/>
            {
            "userName":${pdfn:toJsonString(user.userName)},
            "sessionNumber":${pdfn:toJsonString(doctor.sessionNumber)},
            "trainingSpeName":${pdfn:toJsonString(doctor.trainingSpeName)},
            "doctorTypeName":${pdfn:toJsonString(doctor.doctorTypeName)},
            "schDeptName":${pdfn:toJsonString(process.schDeptName)},
            "schStartDate":${pdfn:toJsonString(process.schStartDate)},
            "schEndDate":${pdfn:toJsonString(process.schEndDate)},
            "status":
            <c:if test="${empty rec}">
                "待评价",
            </c:if>
            <c:if test="${not empty rec}">
                "已评价",
            </c:if>
            <c:if test="${empty rec}">
                "totalScore":"",
                "recTypeId":${pdfn:toJsonString(rec.recTypeId)},
                "schResultFlow":${pdfn:toJsonString(process.schResultFlow)},
                "rotationFlow":${pdfn:toJsonString(result.rotationFlow)},
                "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
                "recFlow":${pdfn:toJsonString(rec.recFlow)},
                "processFlow":${pdfn:toJsonString(process.processFlow)},
                "schDeptFlow":${pdfn:toJsonString(process.schDeptFlow)},
                "deptFlow":${pdfn:toJsonString(process.deptFlow)}
            </c:if>
            <c:if test="${not empty rec}">
                "totalScore":${pdfn:toJsonString((dataMap.totalScore+0))},
                "recTypeId":${pdfn:toJsonString(rec.recTypeId)},
                "schResultFlow":${pdfn:toJsonString(process.schResultFlow)},
                "rotationFlow":${pdfn:toJsonString(result.rotationFlow)},
                "doctorFlow":${pdfn:toJsonString(doctor.doctorFlow)},
                "recFlow":${pdfn:toJsonString(rec.recFlow)},
                "processFlow":${pdfn:toJsonString(process.processFlow)},
                "schDeptFlow":${pdfn:toJsonString(process.schDeptFlow)},
                "deptFlow":${pdfn:toJsonString(process.deptFlow)}
            </c:if>
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
</c:if>
}