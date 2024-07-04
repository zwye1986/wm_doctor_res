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
		<c:if test="${tSwitch && !isAudit}">
			"save":"审核"
		</c:if>
	},
	"inputs":[
		{
			"inputId": "operation_mrNo",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_operName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "手术名称",
			"required":true
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
			"inputId": "operation_operRole",
			"label": "术中职务",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "0",
					"optionDesc": "术者"
				},
				{
					"optionId": "1",
					"optionDesc": "一助"
				},
				{
					"optionId": "2",
					"optionDesc": "二助"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_memo",
			"label": "备注",
			"required":false,
			"inputType": "textarea"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"operation_mrNo",
			"value":${pdfn:toJsonString(formDataMap.operation_mrNo)}
		},
		{
			"inputId":"operation_operName",
			"value":${pdfn:toJsonString(formDataMap.operation_operName)}
		},
		{
			"inputId":"operation_operRole",
			"value":${pdfn:toJsonString(formDataMap.operation_operRole_id)}
		},
		{
			"inputId":"operation_memo",
			"value":${pdfn:toJsonString(formDataMap.operation_memo)}
		}
	]