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
         }
         <c:if test='${empty activity.score}'>
         ,
         {
            "inputId":"join",
            "label":"是否参加",
            "inputType": "radio",
            "required": true,
            "options": [
                {
                    "optionId": "cj",
                    "optionDesc": "报名"
                }
            ]
            
         }
         </c:if>
         <c:if test='${activity.score eq 0}'>
         ,
         {
            "inputId":"mdmq",
            "label":"教学目的明确,准备充分",
            "inputType": "select",
            "required": true,
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
         }
         ,
         {
            "inputId":"gfsl",
            "label":"活动开展规范，顺利",
            "inputType": "select",
            "required": true,
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
         }
         ,
         {
            "inputId":"jxdw",
            "label":"教师指导正确,解析到位",
            "inputType": "select",
            "required": true,
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
         }
         ,
         {
            "inputId":"xgh",
            "label":"活动开展质量高,效果好",
            "inputType": "select",
            "required": true,
            "options": [
                {
                    "optionId": "1",
                    "optionDesc": "1"
                },
                {
                    "optionId": "2",
                    "optionDesc": "2"
                },
                {
                    "optionId": "3",
                    "optionDesc": "3"
                },
                {
                    "optionId": "4",
                    "optionDesc": "4"
                },
                {
                    "optionId": "5",
                    "optionDesc": "5"
                }
            ]
         }
         </c:if>
         
         <c:if test='${activity.score > 0}'>
          ,
         {
            "inputId":"mdmq",
            "label":"教学目的明确,准备充分",
            "inputType": "text",
            "readonly":true
         }
         ,
         {
            "inputId":"gfsl",
            "label":"活动开展规范，顺利",
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
         </c:if>
     
     ]
     <c:if test='${not empty activity}'>
     ,
     "values": [
        {
            "inputId": "time",
            "value": "${fn:substring(activity.time , 0 , 16)}"
        },
        {
            "inputId": "endTime",
            "value": "${fn:substring(activity.endTime , 0 , 16)}"
        },
        {
            "inputId": "mainSpeaker",
            "value": "${activity.mainSpeaker}"
        },
        {
            "inputId": "address",
            "value": "${activity.address}"
        },
        {
            "inputId": "item",
            "value": "${activity.item}"
        },
        {
            "inputId": "name",
            "value": "${activity.name}"
        },
        {
            "inputId":"content",
            "value":${pdfn:toJsonString(activity.content)}
        }
        <c:if test='${not empty activity.score}'>
        ,
         {
            "inputId":"mdmq",
            "value":"${activity.mdmq}"
         }
         ,
         {
            "inputId":"gfsl",
            "value":"${activity.gfsl}"
         }
         ,
         {
            "inputId":"jxdw",
            "value":"${activity.jxdw}"
         }
         ,
         {
            "inputId":"xgh",
            "value":"${activity.xgh}"
         }
        </c:if>
    ]
    </c:if>