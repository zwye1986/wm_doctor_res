<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "nowDate": "${nowDate}"
    <c:if test="${not empty recordFlow}">
        ,"recordFlow":"${recordFlow}"
    </c:if>
</c:if>
}