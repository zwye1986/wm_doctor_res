<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
         {
             "inputId": "time",
             "label": "开始时间",
             "inputType": "text",
             "readonly":true
         },
         {
             "inputId": "endTime",
             "label": "结束时间",
             "inputType": "text",
             "readonly":true
         },
         {
             "inputId": "mainSpeaker",
             "label": "主讲人",
             "inputType": "text",
             "readonly":true
         },
         {
            "inputId": "address",
             "label": "地点",
             "inputType": "text",
             "readonly":true
        },
        {
            "inputId": "item",
            "label": "活动形式",
            "inputType": "text",
            "readonly":true
        },
        {
            "inputId": "name",
            "label": "活动名称",
            "inputType": "text",
            "readonly":true
         },
         {
            "inputId": "content",
            "label": "活动简介",
            "inputType": "textarea",
            "readonly":true
         },
         {
            "inputId":"mdmq",
            "label":"教学目的明确,准备充分",
            "inputType": "text",
            "readonly":true
         }
         ,
         {
            "inputId":"gfsl",
            "label":"活动开展规范",
            "inputType": "text",
            "readonly":true
         }
         ,
         {
            "inputId":"jxdw",
            "label":"教师指导正确,解析到位",
            "inputType": "text",
            "readonly":true
         }
         ,
         {
            "inputId":"xgh",
            "label":"活动开展质量高,效果好",
            "inputType": "text",
            "readonly":true
         }
     ]
     <c:if test='${not empty activity}'>
     ,
     "values": [
        {
            "inputId": "time",
            "value": "${fn:substring(activity.CaDisTime , 0 , 16)}"
        },
        {
            "inputId": "endTime",
            "value": "${fn:substring(activity.CaEndDisTime , 0 , 16)}"
        },
        {
            "inputId": "mainSpeaker",
            "value": "${activity.CaDisMainSpeaker}"
        },
        {
            "inputId": "address",
            "value": "${activity.CaAddress}"
        },
        {
            "inputId": "item",
            "value": "${activity.DicItem}"
        },
        {
            "inputId": "name",
            "value": "${activity.CaName}"
        },
        {
            "inputId":"content",
            "value":${pdfn:toJsonString(activity.CaDisContent)}
        },
         {
            "inputId":"mdmq",
            "value":"${activity.Ismdmq}"
         }
         ,
         {
            "inputId":"gfsl",
            "value":"${activity.Isgfsl}"
         }
         ,
         {
            "inputId":"jxdw",
            "value":"${activity.Isjxdw}"
         }
         ,
         {
            "inputId":"xgh",
            "value":"${activity.Isxgh}"
         }
    ]
    </c:if>