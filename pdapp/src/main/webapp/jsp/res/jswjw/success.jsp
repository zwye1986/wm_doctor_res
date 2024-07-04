<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${not empty resultPath}">
        ,"resultPath": "${resultPath}"
    </c:if>
    <c:if test="${not empty showPath}">
    ,"showPath": "${showPath}"
    </c:if>
    <c:if test="${not empty pageIndex}">
        ,"pageIndex": ${pageIndex}
    </c:if>
    <c:if test="${not empty isPass}">
        ,"isPass": "${isPass}"
    </c:if>
    <c:if test="${not empty intervalDays}">
        ,"intervalDays": "${intervalDays}"
    </c:if>
}
