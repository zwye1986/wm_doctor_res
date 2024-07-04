<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData1",
	        "label": "病种",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病种",
	        "readonly":true
	    },
	    <c:if test="${caseClassCataName eq '其他' or caseClassCataName eq '其它' }">
	    {
	        "inputId": "OtherCaseClass",
	        "label": "具体名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"具体名称"
	    },
		</c:if>
	    {
	        "inputId": "RecData2",
	        "label": "门诊号",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"门诊号"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData4",
	        "label": "住院号",
	        "inputType": "text",
	        "placeholder":"住院号"
	    },
        {
            "inputId": "RecData5",
            "label": "入院日期",
            "required": true,
            "inputType": "date",
            "placeholder":"入院日期"
        },
        {
            "inputId": "RecData6",
            "label": "主要诊断",
            "required": true,
            "inputType": "textarea",
            "placeholder":"主要诊断"
        }
     
     ]
     <c:if test='${empty caseClass}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${caseClassCataName}"
        }
    ]
     </c:if>
     <c:if test='${!empty caseClass}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${caseClass.RecData1}"
        },
        {
            "inputId": "RecData2",
            "value": "${caseClass.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${caseClass.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${caseClass.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${caseClass.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${caseClass.RecData6}"
        },
        {
            "inputId":"OtherCaseClass",
            "value":"${caseClass.OtherCaseClass}"
        }
    ]
     </c:if>