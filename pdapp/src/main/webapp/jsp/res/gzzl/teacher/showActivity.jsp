<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
        ,
        "action":{
             "upload":"上传"
        },
        "inputs":[
            {
                "inputId":"userName",
                "label":"主讲人",
                "inputType":"text",
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(activity.userName)}
            },
            {
                "inputId":"deptName",
                "label":"所在科室",
                "inputType":"text",
                "readonly":true,
                "required":false,
                "value":${pdfn:toJsonString(activity.deptName)}
            },
            {
                "inputId":"activityTypeName",
                "label":"活动形式",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.activityTypeName)}
            },
            {
                "inputId":"activityName",
                "label":"活动名称",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.activityName)}
            },
            {
                "inputId":"activityAddress",
                "label":"活动地点",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.activityAddress)}
            },
            {
                "inputId":"speakerPhone",
                "label":"联系方式",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.speakerPhone)}
            },
            {
                "inputId":"startTime",
                "label":"开始时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd HH:mm",
                    "type": "date"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.startTime)}
            },
            {
                "inputId":"endTime",
                "label":"结束时间：",
                "inputType":"date",
                "verify": {
                    "dateFmt": "yyyy-MM-dd HH:mm",
                    "type": "date"
                },
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.endTime)}
            },
            {
                "inputId":"fileName",
                "label":"附        件",
                "inputType":"text",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(empty activity.fileName?"无":activity.fileName)}
            },
            {
                "inputId":"imageList",
                "label":"现场照片",
                "inputType":"imageList",
                "readonly":false,
                "required":true,
                "value":${pdfn:toJsonString(imgCount)}
            },
            {
                "inputId":"activityRemark",
                "label":"活动简介",
                "inputType":"textarea",
                "readonly":true,
                "required":true,
                "value":${pdfn:toJsonString(activity.activityRemark)}
            }

        ]
    </c:if>
}