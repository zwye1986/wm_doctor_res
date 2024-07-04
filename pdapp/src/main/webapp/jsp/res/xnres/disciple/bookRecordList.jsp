<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "datas":[
        <c:forEach items="${list}" var="bean" varStatus="status">
            {
                "recordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "studyStartTime":${pdfn:toJsonString(bean.studyStartTime)},
                "studyEndTime":${pdfn:toJsonString(bean.studyEndTime)},
                "studyActionId":${pdfn:toJsonString(bean.studyActionId)},
                "studyActionName":${pdfn:toJsonString(bean.studyActionName)},
                "action":[
                ]
            }
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
        ]
</c:if>
}