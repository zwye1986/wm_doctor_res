<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    <%--{--%>
	        <%--"inputId": "RecData1",--%>
	        <%--"label": "ID号",--%>
	        <%--"required": true,--%>
	        <%--"inputType": "text",--%>
	        <%--"placeholder":"ID号"--%>
	    <%--},--%>
	    {
	        "inputId": "RecData2",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "住院号",
	        "inputType": "text",
	        "placeholder":"住院号"
	    },
        {
            "inputId": "RecData4",
            "label": "入院日期",
            "required": true,
            "inputType": "date",
            "placeholder":"入院日期"
        },
        {
            "inputId": "RecData5",
            "label": "名称",
            "required": true,
            "inputType": "select",
            "options": [
               {
                   "optionId": "完整管理",
                   "optionDesc": "完整管理"
               },
               {
                   "optionId": "参与管理",
                   "optionDesc": "参与管理"
               }
           ]
        },
        {
            "inputId": "RecData6",
            "label": "主要诊断",
            "required": true,
            "inputType": "textarea",
            "placeholder":"主要诊断"
        }
     
     ]
     <c:if test='${!empty bigHistory}'>
     ,
      "values": [
        <%--{--%>
            <%--"inputId": "RecData1",--%>
            <%--"value": "${bigHistory.RecData1}"--%>
        <%--},--%>
        {
            "inputId": "RecData2",
            "value": "${bigHistory.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${bigHistory.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${bigHistory.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${bigHistory.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${bigHistory.RecData6}"
        }
    ]
     </c:if>