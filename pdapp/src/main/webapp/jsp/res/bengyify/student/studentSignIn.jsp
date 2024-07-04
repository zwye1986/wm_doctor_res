<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "count": ${pdfn:toJsonString(count)},
        "nowDay": ${pdfn:toJsonString(nowDay)},
        "signList":
        [
        <c:forEach items="${list}" var="result" varStatus="status">
            {
                "attendTime": ${pdfn:toJsonString(result.signTime)},
                "order": ${status.index+1},
                "attendLocal": ${pdfn:toJsonString(result.attendLocal)},
                "selfRemarks": ${pdfn:toJsonString(result.selfRemarks)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}