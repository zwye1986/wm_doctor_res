<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'PendingAudit'"])},
            "NotAudit":${pdfn:toJsonString(compMap["'PendingAudit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        },
        "datas":[
        <c:forEach items="${results}" var="bean" varStatus="status">
            {
                "recordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "discipleDate":${pdfn:toJsonString(bean.discipleDate)},
                "startTime":${pdfn:toJsonString(bean.startTime)},
                "endTime":${pdfn:toJsonString(bean.endTime)},
                "address":${pdfn:toJsonString(bean.address)},
                "auditStatusId":${pdfn:toJsonString(bean.auditStatusId)},
                "auditStatusName":${pdfn:toJsonString(bean.auditStatusName)},
                "action":[
                    <c:if test="${bean.auditStatusId eq 'PendingAudit'or 'UnQualified' eq bean.auditStatusId}">
                        {
                            "id":"edit",
                            "name":"编辑"
                        },
                        {
                            "id":"del",
                            "name":"删除"
                        }
                    </c:if>
                ]
            }
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}