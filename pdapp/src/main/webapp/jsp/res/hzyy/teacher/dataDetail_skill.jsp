<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
    <c:if test="${data.CheckStatus eq '0'}">
	"action":{
        "audit":"审核"
    },
    </c:if>
    "datas": [
         {
             "headId":"Col263",
             "label":"病人姓名",
             "value":"${data.Col263}"
         },
    	 {
            "headId": "Col262",
            "label": "门诊号",
             "value":"${data.Col262}"
        },
    	 {
            "headId": "Col261",
            "label": "操作名称",
             "value":"${data.Col261 eq '其他'?data.otherSkill:data.Col261}"
        },
    	 {
            "headId": "Col265",
            "label": "入院日期",
             "value":"${data.Col265}"
        },
    	 {
            "headId": "Col266",
            "label": "操作人身份",
             "value":"${data.Col266}"
        },
    	 {
            "headId": "Col267",
            "label": "主要诊断",
             "value":"${data.Col267}"
        },
        {
            "headId": "RecDate",
            "label": "填写日期",
             "value":"${data.RecDate}"
        }
	]