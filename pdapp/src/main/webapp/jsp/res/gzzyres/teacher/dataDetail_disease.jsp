<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
    <c:if test="${data.CheckStatus eq '0'}">
	"action":{
        "audit":"审核"
    },
    </c:if>
    "datas": [
         {
             "headId":"Col253",
             "label":"病人姓名",
             "value":"${data.Col253}"
         },
    	 {
            "headId": "Col252",
            "label": "门诊号",
             "value":"${data.Col252}"
        },
    	 {
            "headId": "Col251",
            "label": "病种名称",
             "value":"${data.Col251 eq '其他'?data.otherCaseClass:data.Col251}"
        },
    	 {
            "headId": "Col255",
            "label": "入院日期",
             "value":"${data.Col255}"
        },
    	 {
            "headId": "Col256",
            "label": "主要诊断",
             "value":"${data.Col256}"
        },
    	 {
            "headId": "Col257",
            "label": "管理类型",
             "value":"${data.Col257}"
        },
        {
            "headId": "RecDate",
            "label": "填写日期",
             "value":"${data.RecDate}"
        }
	]