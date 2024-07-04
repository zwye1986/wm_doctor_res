<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		<c:if test="${sSwitch && !isAudit}">
			"save":"保存", 
			"del":"删除"
		</c:if>
		<c:if test="${sSwitch && isAudit && notPass}">
            "save":"保存",
            "del":"删除"
		</c:if>
		<c:if test="${tSwitch && !isAudit}">
			"save":"审核"
		</c:if>
	},
	"inputs":[
		{
			"inputId": "diseaseName",
			"label": "疾病名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "diseasePersonName",
			"label": "病人姓名",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "hospitalNumbers",
			"label": "住院号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "type",
			"label": "类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "一般",
					"optionDesc": "一般"
				},
				{
					"optionId": "危重",
					"optionDesc": "危重"
				},
				{
					"optionId": "抢救",
					"optionDesc": "抢救"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "result",
			"label": "结果",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "痊愈",
					"optionDesc": "痊愈"
				},
				{
					"optionId": "好转",
					"optionDesc": "好转"
				},
				{
					"optionId": "死亡",
					"optionDesc": "死亡"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "treatmentWay",
			"label": "治疗方法",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "中医",
					"optionDesc": "中医"
				},
				{
					"optionId": "西医",
					"optionDesc": "西医"
				},
				{
					"optionId": "中西医",
					"optionDesc": "中西医"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "comprehensiveWay",
			"label": "综合方法",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "针灸",
					"optionDesc": "针灸"
				},
				{
					"optionId": "推拿",
					"optionDesc": "推拿"
				},
				{
					"optionId": "火罐",
					"optionDesc": "火罐"
				},
				{
					"optionId": "刮痧",
					"optionDesc": "刮痧"
				},
				{
					"optionId": "其他",
					"optionDesc": "其他"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"diseaseName",
			"value":${pdfn:toJsonString(formDataMap.diseaseName)}
		},
		{
			"inputId":"diseasePersonName",
			"value":${pdfn:toJsonString(formDataMap.diseasePersonName)}
		},
		{
			"inputId":"hospitalNumbers",
			"value":${pdfn:toJsonString(formDataMap.hospitalNumbers)}
		},
		{
			"inputId":"type",
			"value":${pdfn:toJsonString(formDataMap.type)}
		},
		{
			"inputId":"result",
			"value":${pdfn:toJsonString(formDataMap.result)}
		},
		{
			"inputId":"treatmentWay",
			"value":${pdfn:toJsonString(formDataMap.treatmentWay)}
		},
		{
			"inputId":"comprehensiveWay",
			"value":${pdfn:toJsonString(formDataMap.comprehensiveWay)}
		}
	]