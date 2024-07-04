<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        <c:set var="isRead" value="${!(info.auditStatusId eq 'PendingAudit'or 'UnQualified' eq info.auditStatusId)}"></c:set>
        <c:if test="${info.auditStatusId eq 'PendingAudit'or 'UnQualified' eq info.auditStatusId}">
            "action":{
                "save":"保存"
            },
        </c:if>
        "datas":[

            {
                "inputId":"recordFlow",
                "label":"流水号",
                "inputType":"hidden",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(info.recordFlow)}
            },
            {
                "inputId":"auditStatusId",
                "label":"状态",
                "inputType":"hidden",
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(info.auditStatusId)}
            },
            {
                "inputId":"discipleDate",
                "label":"出诊日期：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.discipleDate)}
            },
            {
                "inputId":"startTime",
                "label":"开始时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "HH:mm",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.startTime)}
            },
            {
                "inputId":"endTime",
                "label":"结束时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "HH:mm",
                    "type": "date"
                },
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.endTime)}
            },
            {
                "inputId":"address",
                "label":"地点：",
                "inputType":"text",
                "readonly":${isRead},
                "required":true,
                "value":${pdfn:toJsonString(info.address)}
            }
        ]
    </c:if>
}