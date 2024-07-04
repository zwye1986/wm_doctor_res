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
                "roomFlow": ${pdfn:toJsonString(room.roomFlow)},
                "roomName": ${pdfn:toJsonString(room.roomName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
