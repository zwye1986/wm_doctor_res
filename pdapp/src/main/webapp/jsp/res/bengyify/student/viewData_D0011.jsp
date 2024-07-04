<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData2",
	        "label": "姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "门诊日期",
            "required": true,
	        "inputType": "date",
	        "placeholder":"门诊日期"
	    },
        {
            "inputId": "RecData4",
            "label": "诊断",
            "required": true,
            "inputType": "textarea",
            "placeholder":"诊断"
        }

     ]
     <c:if test='${!empty outPatient}'>
     ,
      "values": [
        {
            "inputId": "RecData2",
            "value": "${outPatient.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${outPatient.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${outPatient.RecData4}"
        }
    ]
     </c:if>