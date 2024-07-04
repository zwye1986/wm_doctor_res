<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "startDate":	${pdfn:toJsonString(startDate)},
    "endDate":	${pdfn:toJsonString(endDate)},
    "kqTypeId":	${pdfn:toJsonString(param.kqTypeId)},
    "recordFlow": ${pdfn:toJsonString(recordFlow)},
    "intervalDays": ${pdfn:toJsonString(intervalDays)},
    "intervalDays2": ${pdfn:toJsonString(intervalDays2)},
    "less":[
        {
        "isAudit":"${less.teacherFlag}",
        "userName":"${less.teacherName}"
        },
        {
        "isAudit":"${less.headFlag}",
        "userName":"${less.headName}"
        },
        {
        "isAudit":"${less.managerFlag}",
        "userName":"${less.managerName}"
        }
    ],
    "greater":[
        {
        "isAudit":"${greater.teacherFlag}",
        "userName":"${greater.teacherName}"
        },
        {
        "isAudit":"${greater.headFlag}",
        "userName":"${greater.headName}"
        },
        {
        "isAudit":"${greater.managerFlag}",
        "userName":"${greater.managerName}"
        }
    ],
    "midd":[
        {
        "isAudit":"${midd.teacherFlag}",
        "userName":"${midd.teacherName}"
        },
        {
        "isAudit":"${midd.headFlag}",
        "userName":"${midd.headName}"
        },
        {
        "isAudit":"${midd.managerFlag}",
        "userName":"${midd.managerName}"
        }
    ]
</c:if>
}
