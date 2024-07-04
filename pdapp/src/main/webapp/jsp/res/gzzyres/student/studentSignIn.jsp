<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "count": ${pdfn:toJsonString(count)},
        <%--"orgName": ${pdfn:toJsonString(resdoctor.orgName)},--%>
        "orgFlow": ${pdfn:toJsonString(currUser.hosID)},
        "nowDay": ${pdfn:toJsonString(nowDay)},
        "signList":
        [
        <c:forEach items="${list}" var="result" varStatus="status">
            {
            "attendTime": ${pdfn:toJsonString(result.Attend_Time)},
            "order": ${status.index+1},
            "attendLocal": ${pdfn:toJsonString(result.Attend_Local)},
            "selfRemarks": ${pdfn:toJsonString(result.Self_Remarks)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}