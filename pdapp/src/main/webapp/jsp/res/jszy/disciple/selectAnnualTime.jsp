<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
		,
		"action":{
			"save":"保存"
		},
	"inputs":[

                {
                    "inputId":"annualYear",
                    "inputType":"date",
                    "label":"考核年度",
                    "readonly":false,
                    "required":true,
                    "verify": {
                        "dateFmt": "yyyy",
                        "type": "date"
                    },
                    "value":""
                },
                {
                    "inputId":"startTime",
                    "inputType":"date",
                    "label":"跟师开始日期",
                    "readonly":false,
                    "required":true,
                    "verify": {
                        "dateFmt": "yyyy-MM-dd",
                        "type": "date"
                    },
                    "value":""
                },
                {
                    "inputId":"endTime",
                    "inputType":"date",
                    "label":"跟师结束日期",
                    "readonly":false,
                    "required":true,
                    "verify": {
                        "dateFmt": "yyyy-MM-dd",
                        "type": "date"
                    },
                    "value":""
                }
	]
	,
	"values":[
		{
			"inputId": "annualYear",
			"value":""
		},
		{
			"inputId": "startTime",
			"value":""
		},
		{
			"inputId": "endTime",
			"value":""
		}
	]
	</c:if>
}