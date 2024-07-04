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
			"inputId": "skill_operDate",
			"label": "操作日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_pName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_mrNo",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_operName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "操作名称",
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
			"inputId": "skill_result",
			"label": "成功",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "是"
				},
				{
					"optionId": "0",
					"optionDesc": "否"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "fail_reason",
			"label": "失败原因",
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
			"inputId":"skill_operDate",
			"value":${pdfn:toJsonString(formDataMap.skill_operDate)}
		},
		{
			"inputId":"skill_pName",
			"value":${pdfn:toJsonString(formDataMap.skill_pName)}
		},
		{
			"inputId":"skill_mrNo",
			"value":${pdfn:toJsonString(formDataMap.skill_mrNo)}
		},
		{
			"inputId":"skill_operName",
			"value":${pdfn:toJsonString(formDataMap.skill_operName)}
		},
		{
			"inputId":"skill_result",
			"value":${pdfn:toJsonString(formDataMap.skill_result_id)}
		},
		{
			"inputId":"fail_reason",
			"value":${pdfn:toJsonString(formDataMap.fail_reason)}
		}
	]