<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        <c:if test="${empty file.filePath}">
            <c:set var="filePath" value=""/>
        </c:if>
        <c:if test="${not empty file.filePath}">
            <c:set var="filePath" value="${uploadBaseUrl}/${file.filePath}"/>
        </c:if>
        "fileUrl": ${pdfn:toJsonString(filePath)}
    </c:if>
}
