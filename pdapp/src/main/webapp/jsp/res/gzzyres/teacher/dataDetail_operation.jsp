<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
    <c:if test="${data.CheckStatus eq '0'}">
	"action":{
        "audit":"审核"
    },
    </c:if>
    "datas": [
         {
             "headId":"Col273",
             "label":"病人姓名",
             "value":"${data.Col273}"
         },
    	 {
            "headId": "Col272",
            "label": "门诊号",
             "value":"${data.Col272}"
        },
    	 {
            "headId": "Col274",
            "label": "住院号",
             "value":"${data.Col274}"
        },
    	 {
            "headId": "Col271",
            "label": "手术名称",
             "value":"${data.Col271 eq '其他'?data.otherPOSSkill:data.Col271}"
        },
    	 {
            "headId": "Col275",
            "label": "入院日期",
             "value":"${data.Col275}"
        },
    	 {
            "headId": "Col276",
            "label": "操作人身份",
             "value":"${data.Col276}"
        },
    	 {
            "headId": "Col277",
            "label": "主要诊断",
             "value":"${data.Col277}"
        },
        {
            "headId": "RecDate",
            "label": "填写日期",
             "value":"${data.RecDate}"
        }
	]