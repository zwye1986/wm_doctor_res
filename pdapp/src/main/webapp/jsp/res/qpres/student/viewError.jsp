<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,"datas": [
        <c:forEach items="${results}" var="result" varStatus="s">
            {
            "soluName":"${result.soluName}",
            "examTime":${pdfn:toJsonString(result.examTime)},
            "submitTime":${pdfn:toJsonString(result.submitTime)},
            "theoryScore":"${result.theoryScore}",
            "resultsId":"${result.resultsId}"
            }
            <c:if test="${not s.last}">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
