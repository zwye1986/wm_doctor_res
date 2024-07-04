<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
    "files":
        [
        <c:forEach items="${files}" var="file" varStatus="status">
            {
                "fileFlow": ${pdfn:toJsonString(file.fileFlow)},
                "fileName": ${pdfn:toJsonString(file.fileName)}
            }
            <c:if test="${not status.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
