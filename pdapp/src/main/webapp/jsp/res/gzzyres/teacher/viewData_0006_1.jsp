<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
             {
                 "inputId":"truename",
                 "label":"学员姓名",
                 "inputType":"text",
                 "required":true,
                 "readonly":true
             },
             {
                 "inputId":"username",
                 "label":"工号",
                 "inputType":"text",
                 "required":true,
                 "readonly":true
             },
             {
                 "inputId":"HosSecName",
                 "label":"轮转科室",
                 "inputType":"text",
                 "required":true,
                 "readonly":true
             },
             {
                 "inputId":"StartTime",
                 "label":"开始时间",
                 "inputType":"text",
                 "required":true,
                 "readonly":true
             },
             {
                 "inputId":"EndTime",
                 "label":"结束时间",
                 "inputType":"text",
                 "required":true,
                 "readonly":true
             },
             {
                 "inputId":"Mini_Item",
                 "label":"考核内容",
                 "inputType":"textarea",
                 "required": true,
                 "placeholder":""
             },
             {
                 "inputId":"Mini_Content",
                 "label":"点评",
                 "inputType":"textarea",
                 "required": true,
                 "placeholder":""
             },
             {
                 "inputId":"Mini_Score",
                 "label":"得分",
                 "inputType":"text",
                 "required":true,
                 "verify": {
					"max": "100",
					"type": "int"
				 }
             },
            {
                "inputId":"Mini_TecName",
                "label":"考官签名",
                "inputType":"text",
                "required":true,
                "readonly":true
            },
            {
                "inputId":"Mini_Time",
                "label":"考核时间",
                "inputType":"text",
                "required":true,
                "readonly":true
            },
            {
                "inputId":"Mini_ProfessorName",
                "label":"科主任审批签名",
                "inputType":"text",
                "required":true,
                "readonly":false
            }
         ]
         ,
         "values":[
         	 {
                 "inputId":"truename",
                 "value":${pdfn:toJsonString(doctorInfo.TrueName)}
             },
         	 {
                 "inputId":"username",
                 "value":${pdfn:toJsonString(doctorInfo.username)}
             },
         	 {
                 "inputId":"HosSecName",
                 "value":${pdfn:toJsonString(doctorInfo.HosSecName)}
             },
         	 {
                 "inputId":"StartTime",
                 "value":${pdfn:toJsonString(doctorInfo.StartTime)}
             },
         	 {
                 "inputId":"EndTime",
                 "value":${pdfn:toJsonString(doctorInfo.EndTime)}
             },
             {
                 "inputId":"Mini_Item",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Item)}
             },
             {
                 "inputId":"Mini_Content",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Content)}
             },
             {
                 "inputId":"Mini_Score",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Score)}
             },
             {
                 "inputId":"Mini_TecName",
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_TecName ? doctorInfo.TecName:outMiniCex.Mini_TecName)}
             },
            <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
             {
                 "inputId":"Mini_Time",
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_Time ? (currDate):outMiniCex.Mini_Time)}
             },
             {
                 "inputId":"Mini_ProfessorName",
                 "value":${pdfn:toJsonString(empty outMiniCex.Mini_ProfessorName ? doctorInfo.ManagerName:outMiniCex.Mini_ProfessorName)}
             }
         ]