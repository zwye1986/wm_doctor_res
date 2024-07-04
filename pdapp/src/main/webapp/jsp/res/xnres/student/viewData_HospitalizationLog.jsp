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
			"inputId": "inHosDate",
			"label": "入院时间",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}

	],
	"values":[
		{
			"inputId":"diseaseName",
			"value":${pdfn:toJsonString(formDataMap["diseaseName"])}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap["patientName"])}
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
			"inputId":"inHosDate",
			"value":${pdfn:toJsonString(formDataMap.inHosDate)}
		}
	]