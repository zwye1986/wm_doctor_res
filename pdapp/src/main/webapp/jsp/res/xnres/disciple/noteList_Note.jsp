<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "noteTypeId":${pdfn:toJsonString(param.noteTypeId)},
        "compMap":{
            "All":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+compMap["'Apply'"]+compMap["'Submit'"])},
            "Saved":${pdfn:toJsonString(compMap["'Apply'"]+0)},
            "NotAudit":${pdfn:toJsonString(compMap["'Submit'"]+0)},
            "Audited":${pdfn:toJsonString(compMap["'Qualified'"]+compMap["'UnQualified'"]+0)}
        },
        "datas":[
        <c:forEach items="${results}" var="bean" varStatus="status">
            {
                "recordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "studyStartDate":${pdfn:toJsonString(bean.studyStartDate)},
                "studyTimeId":${pdfn:toJsonString(bean.studyTimeId)},
                "studyTimeName":${pdfn:toJsonString(bean.studyTimeNmae)},
                "auditStatusId":${pdfn:toJsonString(bean.auditStatusId)},
                "auditStatusName":${pdfn:toJsonString(bean.auditStatusName)},
                "action":[
                    <c:if test="${bean.auditStatusId eq 'Apply' or bean.auditStatusId eq 'UnQualified'}">
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