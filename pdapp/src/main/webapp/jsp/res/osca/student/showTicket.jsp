<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "data":{

        <c:if test="${empty user.userHeadImg}">
            <c:set var="userHeadImg" value=""/>
        </c:if>
        <c:if test="${not empty user.userHeadImg}">
            <c:set var="userHeadImg" value="${uploadBaseUrl}/${user.userHeadImg}"/>
        </c:if>
        "headImg":${pdfn:toJsonString(userHeadImg)},
        "userName":${pdfn:toJsonString(user.userName)},
        "examAddress":${pdfn:toJsonString(skillsAssessment.examAddress)},
        "remarks":${pdfn:toJsonString(skillsAssessment.remarks)},
        "speName":${pdfn:toJsonString(skillsAssessment.speName)},
        "speId":${pdfn:toJsonString(skillsAssessment.speId)},
        "ticketNumber":${pdfn:toJsonString(oda.ticketNumber)},
        "examStartTime":${pdfn:toJsonString(oda.examStartTime)},
        "examEndTime":${pdfn:toJsonString(oda.examEndTime)},
        "idNo":${pdfn:toJsonString(user.idNo)},
        "codeInfo":${pdfn:toJsonString(oda.codeInfo)}
     }
    </c:if>
}
