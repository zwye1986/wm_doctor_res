<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveInProcess",
        <c:if test="${!isProcessing}">
            "save":"入    科"
        </c:if>
        <c:if test="${isProcessing}">
            "save":"保    存"
        </c:if>
    },
    "datas": [
        {
            "inputId":"RUserID",
            "label":"学员ID：",
            "inputType":"text",
            "required":true,
            "readonly":true,
            "hidden":true,
            "value":${pdfn:toJsonString(data.UserID)}
        },
        {
            "inputId":"RCySecID",
            "label":"标准轮转ID：",
            "inputType":"text",
            "required":true,
            "readonly":true,
            "hidden":true,
            "value":${pdfn:toJsonString(data.CySecID)}
        },
        {
            "inputId":"RHosCySecID",
            "label":"医院轮转ID：",
            "inputType":"text",
            "required":true,
            "readonly":true,
            "hidden":true,
            "value":${pdfn:toJsonString(data.HosCySecID)}
        },
        {
            "inputId":"HosSecID",
            "label":"医院科室ID：",
            "inputType":"text",
            "required":true,
            "readonly":true,
            "hidden":true,
            "value":${pdfn:toJsonString(data.HosSecID)}
        },
        {
            "inputId":"UCSID",
            "label":"轮转流水ID：",
            "inputType":"text",
            "required":true,
            "readonly":true,
            "hidden":true,
            "value":${pdfn:toJsonString(data.UCSID)}
        },
        {
            "inputId":"HosQC",
            "label":"医院名称：",
            "inputType":"text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.HosQC)}
        },
        {
            "inputId": "TrueName",
            "label": "学员姓名：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.TrueName)}
        },
        {
            "inputId": "SpName",
            "label": "专业：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.SpName)}
        },
        {
            "inputId": "StartYear",
            "label": "年级：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.StartYear)}
        },
        {
            "inputId": "StartTime",
            "label": "计划开始时间：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.StartTime)}
        },
        {
            "inputId": "EndTime",
            "label": "计划结束时间：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.EndTime)}
        },
        {
            "inputId": "HosSecName",
            "label": "科室名称：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.HosSecName)}
        },
        {
            "inputId": "CySecTime",
            "label": "轮转时间：",
            "inputType": "text",
            "required":false,
            "readonly":true,
            "value":${pdfn:toJsonString(data.CySecTime)}
        },
        {
            "inputId": "RStartTime",
            "label": "实际开始时间：",
            "inputType": "date",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(data.RStartTime)}
        },
        {
            "inputId": "REndTime",
            "label": "实际结束时间：",
            "inputType": "date",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(data.REndTime)}
        },
        {
            "inputId": "UserTecID",
            "label": "带教老师：",
            "inputType": "pullDown",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(data.UserTecID)},
            "options":[
                <c:forEach items="${teas}" var="tea" varStatus="s">
                {
                    "optionId": ${pdfn:toJsonString(tea.UserID)},
                    "optionDesc": ${pdfn:toJsonString(tea.TrueName)}
                }
                <c:if test='${not s.last}'>
                 ,
                </c:if>
                </c:forEach>
            ]
        },
        {
            "inputId": "SectionManagerID",
            "label": "科主任：",
            "inputType": "pullDown",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(data.SectionManagerID)},
            "options":[
                <c:forEach items="${heads}" var="tea" varStatus="s">
                {
                    "optionId": ${pdfn:toJsonString(tea.UserID)},
                    "optionDesc": ${pdfn:toJsonString(tea.TrueName)}
                }
                <c:if test='${not s.last}'>
                ,
                </c:if>
                </c:forEach>
            ]
        }
    ]
	</c:if>
}
