<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex": 1,
    "pageSize": 10,
    "dataCount": ${dataCount},
    "noticeList": [
        <c:forEach items="${infos}" var="bean" varStatus="s">
            {
                "infoFlow":"${bean.infoFlow}",
                "infoTitle":"${bean.infoTitle}",
                "infoTime":"${bean.infoTime}",
                "infoContent":${pdfn:toJsonString(bean.infoContent)},
                "info":${pdfn:toJsonString(pdfn:cutString(bean.infoContent,18,true,1))}
            }
            <c:if test="${not s.last }">
                ,
            </c:if>
        </c:forEach>
    ]
}