<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "recordFlow":${pdfn:toJsonString(scanRegist.recordFlow)}
    <c:if test="${resultId eq '200' }">
    ,
        "datas": [
        <c:forEach items="${lectureTargetList}" var="t" varStatus="s">
            {
            <c:set var="key" value="${scanRegist.recordFlow}${t.targetFlow}"></c:set>
            "scanRegistFlow":${pdfn:toJsonString(scanRegist.recordFlow)},
            "lectureFlow":${pdfn:toJsonString(scanRegist.lectureFlow)},
            "targetFlow":"${t.targetFlow}",
            "ordinal":"${t.ordinal}",
            "targetName":"${t.targetName}",
            "evaScore":"${evalDetailMap[key]}"
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}
