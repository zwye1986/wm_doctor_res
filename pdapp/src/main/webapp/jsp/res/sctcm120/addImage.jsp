<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200'}">
        ,
        "fileFlow":"${pubFile.fileFlow}",
        <c:set value="${uploadBaseUrl}${pubFile.filePath}" var="filePath"></c:set>
        "filePath":${pdfn:toJsonString(filePath)}
    </c:if>
}
