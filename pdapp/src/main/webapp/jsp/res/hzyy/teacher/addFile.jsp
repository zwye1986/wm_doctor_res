<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "fileFlow": ${pdfn:toJsonString(fileFlow)},
        <c:set var="fileUrl" value="${baseUrl}/${file.AttachPath}/${file.AttachFileName}"></c:set>
        "fileUrl":${pdfn:toJsonString(fileUrl)},
        "fileName": ${pdfn:toJsonString(fileName)}

    </c:if>
}
