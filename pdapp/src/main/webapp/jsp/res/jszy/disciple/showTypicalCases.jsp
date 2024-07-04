<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}

    <c:if test="${resultId eq '200' }">
        ,
        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "noteTypeId":${pdfn:toJsonString(param.noteTypeId)},
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'Apply'"]+compMap["'PendingAudit'"]+0)},
            "Saved":${pdfn:toJsonString(compMap["'Apply'"]+0)},
            "NotAudit":${pdfn:toJsonString(compMap["'PendingAudit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        },
        "datas":[
        <c:forEach items="${results}" var="bean" varStatus="status">
            {
                "recordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "peopleName":${pdfn:toJsonString(bean.peopleName)},
                "sexName":${pdfn:toJsonString(bean.sexName)},
                "visitDate":${pdfn:toJsonString(bean.visitDate)},
                "auditStatusId":${pdfn:toJsonString(bean.auditStatusId)},
                "auditStatusName":${pdfn:toJsonString(bean.auditStatusName)},
                "action":[
                    <c:if test="${bean.auditStatusId eq 'Apply'or bean.auditStatusId eq 'UnQualified'}">
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