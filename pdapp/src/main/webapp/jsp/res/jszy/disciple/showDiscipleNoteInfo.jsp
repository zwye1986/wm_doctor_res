<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}

    <c:if test="${resultId eq '200' }">
        ,
        <jsp:include page="noteList_${param.noteTypeId}.jsp"></jsp:include>
    </c:if>
}