<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
		{
		     "inputId": "name",
		     "label": "名称",
		     "required": true,
		     "inputType": "text"
		},
		{
		     "inputId": "inDateTime",
		     "label": "入院时间",
		     "required": true,
		     "inputType": "text",
		     "placeholder":"入院时间"
		},
		{
		    "inputId": "no",
		     "label": "住院号/id号",
		     "inputType": "text",
		     "placeholder":"住院号/id号"
		},
		{
		    "inputId": "patient",
		    "label": "患者名称",
		    "required": true,
		    "inputType": "text",
		    "placeholder":"患者名称"
		},
		{
		    "inputId": "diagnosis",
		    "label": "诊断",
		    "required": true,
		    "inputType": "text",
		    "placeholder":"诊断"
		},
		{
		    "inputId": "tecName",
		    "label": "经治医生/带教老师",
		    "required": true,
		    "inputType": "text",
		    "placeholder":"经治医生/带教老师"
		}
    ]
    <c:if test='${!empty medicalInfo}'>
    ,
	"values": [
	     {
	         "inputId": "name",
	         "value": "${medicalInfo.name}"
	     },
	     {
	         "inputId": "inDateTime",
	         "value": "${fn:substring(medicalInfo.inDateTime , 0 , 10)}"
	     },
	     {
	         "inputId": "no",
	         "value": "${medicalInfo.no}"
	     },
	     {
	         "inputId": "patient",
	         "value": "${medicalInfo.patient}"
	     },
	     {
	         "inputId": "diagnosis",
	         "value": "${medicalInfo.diagnosis}"
	     },
	     {
	         "inputId":"tecName",
	         "value":"${medicalInfo.tecName}"
	     }
    ]
    </c:if>