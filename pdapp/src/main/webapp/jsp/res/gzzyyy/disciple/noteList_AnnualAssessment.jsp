<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>

        "pageIndex":1,
        "pageSize":10,
        "dataCount":${dataCount},
        "noteTypeId":${pdfn:toJsonString(param.noteTypeId)},
        "datas":[
        <c:forEach items="${results}" var="bean" varStatus="status">
            {
                "recordFlow":${pdfn:toJsonString(bean.recordFlow)},
                "recordYear":${pdfn:toJsonString(bean.recordYear)},
                "doctorName":${pdfn:toJsonString(bean.doctorName)},
                "teacherName":${pdfn:toJsonString(bean.teacherName)},
                "auditStatusId":${pdfn:toJsonString(bean.auditStatusId)},
                "auditStatusName":${pdfn:toJsonString(bean.auditStatusName)},
                "action":[
                    <c:if test="${bean.auditStatusId eq 'Apply'or 'DiscipleBack' eq bean.auditStatusId
                                            or 'AdminBack' eq bean.auditStatusId}">
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