<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
    "rooms":
        [
        <c:forEach items="${rooms}" var="room" varStatus="status">
            {
                "roomFlow": ${pdfn:toJsonString(room.dictId)},
                "orgFlow": ${pdfn:toJsonString(room.orgFlow)},
                "roomName": ${pdfn:toJsonString(room.dictName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
