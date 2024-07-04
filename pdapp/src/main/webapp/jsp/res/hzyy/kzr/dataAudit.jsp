<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "datas": [
        <c:forEach var="d" items="${datas}" varStatus="status">
        {
            "dataTypeId":"${d.dataTypeId}",
            "dataTypeName":"${d.dataTypeName}"
        }
        <c:if test='${not status.last}'>
        ,
        </c:if>
        </c:forEach>
    ]
    </c:if>
}
