<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "qrCode1":${pdfn:toJsonString(url)},
    "qrCode2":${pdfn:toJsonString(url2)}
    </c:if>
}
