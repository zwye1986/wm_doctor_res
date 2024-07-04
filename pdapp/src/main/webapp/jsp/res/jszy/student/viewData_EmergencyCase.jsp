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
			"inputId": "case",
			"label": "病例类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "一般病例",
					"optionDesc": "一般病例"
				},
				{
					"optionId": "抢救病例",
					"optionDesc": "抢救病例"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
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
			"inputId": "patientName",
			"label": "病人姓名",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "cases",
			"label": "例次",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "date",
			"label": "日期",
			"required":false,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}

	],
	"values":[
		{
			"inputId":"case",
			"value":${pdfn:toJsonString(formDataMap["case"])}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap.patientName)}
		},
		{
			"inputId":"diseaseName",
			"value":${pdfn:toJsonString(formDataMap.diseaseName)}
		},
		{
			"inputId":"cases",
			"value":${pdfn:toJsonString(formDataMap.cases)}
		},
		{
			"inputId":"date",
			"value":${pdfn:toJsonString(formDataMap.date)}
		}
	]