<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
     ,
     "imageFlow":   ${pdfn:toJsonString(imgUrlMap['imageFlow'])},
     "imageUrl":   ${pdfn:toJsonString(imgUrlMap['imageUrl'])},
     "thumbUrl":   ${pdfn:toJsonString(imgUrlMap['thumbUrl'])}
    </c:if>
}
