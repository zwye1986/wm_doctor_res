<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs": [
    	{
            "inputId":"0",
            "label":"确认信息无误，点击保存进行确认入科！",
            "tip":"确认信息无误，点击保存进行确认入科！",
            "inputType":"title"
        },
        {
            "inputId": "1",
            "label": "姓名",
            "inputType": "text"
        },
         {
            "inputId": "2",
            "label": "科室",
            "inputType": "text"
         },
         {
            "inputId": "3",
            "label": "专业",
            "inputType": "text"
         },
         {
            "inputId": "4",
            "label": "计划开始时间",
            "inputType": "text"
         },
         {
            "inputId": "5",
            "label": "计划结束时间",
            "inputType": "text"
         }
     ],
     "values":[
         {
             "inputId":"1",
             "value":${pdfn:toJsonString(doctorInfo.TrueName)}
         },
         {
             "inputId":"2",
             "value":${pdfn:toJsonString(doctorInfo.HosSecName)}
         },
         {
             "inputId":"3",
             "value":${pdfn:toJsonString(doctorInfo.SpName)}
         },
         {
             "inputId":"4",
             "value":${pdfn:toJsonString(doctorInfo.StartTime)}
         },
         {
             "inputId":"5",
             "value":${pdfn:toJsonString(doctorInfo.EndTime)}
         }
     ]