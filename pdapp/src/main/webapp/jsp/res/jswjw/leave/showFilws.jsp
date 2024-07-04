<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "files": [
        <c:forEach items="${files}" var="b" varStatus="s">
            {
            "fileFlow":"${b.fileFlow}",
            "fileName":"${b.fileName}",
            <c:set var="filePath2" value="${uploadBaseUrl}/${b.filePath}"/>
            <c:set var="filePath" value="${empty b.filePath?'':filePath2}"/>
            "filePath":${pdfn:toJsonString(filePath)}
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
    ]
</c:if>
}