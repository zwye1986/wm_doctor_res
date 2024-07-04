<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
    <c:if test="${data.CheckStatus eq '0'}">
	"action":{
        "audit":"审核"
    },
    </c:if>
    "datas": [

         {
             "headId":"Col232",
             "label":"病人姓名",
             "value":"${data.Col232}"
         },
    	 {
            "headId": "Col231",
            "label": "门诊号",
             "value":"${data.Col231}"
        },
    	 {
            "headId": "Col233",
            "label": "住院号",
             "value":"${data.Col233}"
        },
    	 {
            "headId": "Col234",
            "label": "入院日期",
             "value":"${data.Col234}"
        },
    	 {
            "headId": "Col236",
            "label": "主要诊断",
             "value":"${data.Col236}"
        },
    	 {
            "headId": "Col235",
            "label": "参与类型",
             "value":"${data.Col235}"
        },
        {
            "headId": "RecDate",
            "label": "填写日期",
             "value":"${data.RecDate}"
        }
	]