<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
         {
             "inputId": "no",
             "label": "住院号/id号",
             "inputType": "text",
             "placeholder":"住院号/id号"
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
            "inputType": "text"
        },
        {
            "inputId": "master",
            "label": "掌握情况",
            "required": true,
            "inputType": "text"
        },
        {
            "inputId": "dateTime",
            "label": "填写日期",
            "required": true,
            "inputType": "text",
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