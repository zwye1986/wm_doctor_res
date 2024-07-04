<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "title":${pdfn:toJsonString(title)},
    "content":${pdfn:toJsonString(content)},
        <c:if test="${pdfn:contains(iosDetailUrl,'http:')}">
            "iosDetailUrl":${pdfn:replaceString(pdfn:toJsonString(iosDetailUrl),"http:","https:")},
            "androidDetailUrl":${pdfn:replaceString(pdfn:toJsonString(androidDetailUrl),"http:","https:")},
        </c:if>
        <c:if test="${pdfn:contains(iosDetailUrl,'https:')}">
            "iosDetailUrl":${pdfn:toJsonString(iosDetailUrl)},
            "androidDetailUrl":${pdfn:toJsonString(androidDetailUrl)}
        </c:if>

    </c:if>
}
