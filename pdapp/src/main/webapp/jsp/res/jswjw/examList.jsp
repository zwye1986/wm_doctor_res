<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,"datas": [
        <c:forEach items="${examArrangements}" var="examArrangement" varStatus="s">
            {
                "assessmentYear":"${examArrangement.assessmentYear}",
                "examScore":
                    <c:if test="${empty examLogMaps[examArrangement.arrangeFlow]['maxScore']}">
                        "-"
                    </c:if>
                    <c:if test="${not empty examLogMaps[examArrangement.arrangeFlow]['maxScore']}">
                        <c:set var="key" value="${examArrangement.arrangeFlow}"></c:set>
                        "${daMap[key].examScore ne '-20' ? daMap[key].examScore : '**'}分"
                    </c:if>

                ,
                "canExam":"${examLogMaps[examArrangement.arrangeFlow]['canExam']}",
                "isEnd":"${examLogMaps[examArrangement.arrangeFlow]['isEnd']}",
                "isNoStart":"${examLogMaps[examArrangement.arrangeFlow]['isNoStart']}",
                "arrangeFlow":"${examArrangement.arrangeFlow}"
            }
            <c:if test="${not s.last}">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
