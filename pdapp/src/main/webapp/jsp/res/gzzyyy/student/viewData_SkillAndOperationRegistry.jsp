<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:set var="inputType" value="select"/>
	<c:if test="${isOther}">
		<c:set var="inputType" value="text"/>
	</c:if>
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
			"inputId": "skillName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "技术操作及手术名称",
			"required":true,
			<c:if test="${!isOther}">
				,
				"options": [
					{
						"optionId": ${pdfn:toJsonString(req.itemName)},
						"optionDesc": ${pdfn:toJsonString(req.itemName)}
					}
				]
			</c:if>
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
			"inputId": "date",
			"label": "日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "assistant",
			"label": "助手",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "第一助手",
					"optionDesc": "第一助手"
				},
				{
					"optionId": "第二助手",
					"optionDesc": "第二助手"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "status",
			"label": "住院号/检查号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "assessment",
			"label": "考核者",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"skillName",
			"value":${pdfn:toJsonString(formDataMap.skillName)}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap.patientName)}
		},
		{
			"inputId":"date",
			"value":${pdfn:toJsonString(formDataMap.date)}
		},
		{
			"inputId":"assistant",
			"value":${pdfn:toJsonString(formDataMap.assistant)}
		},
		{
			"inputId":"status",
			"value":${pdfn:toJsonString(formDataMap.status)}
		},
		{
			"inputId":"assessment",
			"value":${pdfn:toJsonString(formDataMap.assessment)}
		},
		{
			"inputId":"operation_memo",
			"value":${pdfn:toJsonString(formDataMap.operation_memo)}
		}
	]