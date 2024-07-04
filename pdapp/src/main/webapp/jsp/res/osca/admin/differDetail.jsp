<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "teaList":[
        <c:forEach items="${skillDocTeaScores}" var="data" varStatus="s">
            {
                "partnerFlow": ${pdfn:toJsonString(data.partnerFlow)},
                "partnerName": ${pdfn:toJsonString(data.partnerName)},
                "examScore": ${pdfn:toJsonString(data.examScore)},
                "scores":[
                    <c:forEach items="${socreMap[data.partnerFlow]}" var="score" varStatus="s">
                        {
                            <c:set var="fromFlow" value="${score.fromFlow}"></c:set>
                            <c:set var="fromName" value="${score.fromName}"></c:set>
                            <c:if test="${empty score.fromFlow}">
                                <c:set var="fromFlow" value="NotFromFlow"></c:set>
                                <c:set var="fromName" value="考官打分"></c:set>
                            </c:if>
                            "scoreFlow": ${pdfn:toJsonString(score.scoreFlow)},
                            "fromFlow": ${pdfn:toJsonString(fromFlow)},
                            "fromName": ${pdfn:toJsonString(fromName)},
                            "examScore": ${pdfn:toJsonString(score.examScore)}
                        }
                        <c:if test="${not s.last }">
                            ,
                        </c:if>
                    </c:forEach>
                     ]
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
