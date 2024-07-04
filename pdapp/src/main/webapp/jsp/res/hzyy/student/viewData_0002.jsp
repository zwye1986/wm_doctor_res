<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
         {
             "inputId": "no",
             "label":"门诊号"
             "inputType": "text",
             "placeholder":"门诊号"
         },
         {
             "inputId": "name",
             "label": "名称",
             "required": true,
             "inputType": "text",
             "placeholder":"名称"
         },
         {
            "inputId": "study",
            "label": "病种与操作",
            "required": true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "见过",
                    "optionDesc": "见过"
                },
                {
                    "optionId": "参与",
                    "optionDesc": "参与"
                },
                {
                    "optionId": "实操",
                    "optionDesc": "实操"
                }
            ]
        },
        {
            "inputId": "master",
            "label": "掌握情况",
            "required": true,
            "inputType": "radio",
            "options": [
                {
                    "optionId": "了解",
                    "optionDesc": "了解"
                },
                {
                    "optionId": "熟悉",
                    "optionDesc": "熟悉"
                },
                {
                    "optionId": "掌握",
                    "optionDesc": "掌握"
                }
            ]
        },
        {
            "inputId": "dateTime",
            "label": "填写日期",
            "required": true,
            "inputType": "date",
            "placeholder":"填写日期"
         }
     
     ]
     <c:if test='${not empty studyInfo}'>
     ,
     "values": [
        {
            "inputId": "no",
            "value": "${studyInfo.no}"
        },
        {
            "inputId": "name",
            "value": "${studyInfo.name}"
        },
        {
            "inputId": "study",
            "value": "${studyInfo.study}"
        },
        {
            "inputId": "master",
            "value": "${studyInfo.master}"
        },
        {
            "inputId": "dateTime",
            "value": "${fn:substring(studyInfo.dateTime , 0 , 10)}"
        }
    ]
    </c:if>