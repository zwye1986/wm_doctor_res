<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "title":${pdfn:toJsonString(title)},
    "content":${pdfn:toJsonString(content)},
    "iosDetailUrl":${pdfn:toJsonString(iosDetailUrl)},
    "androidDetailUrl":${pdfn:toJsonString(androidDetailUrl)}
    </c:if>
}
