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
			"inputId": "skill_mainDiagnosis",
			"label": "主要诊断",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_secondaryDiagnosis",
			"label": "次要诊断",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_director",
			"label": "是否主管",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "是",
					"optionDesc": "是"
				},
				{
					"optionId": "否",
					"optionDesc": "否"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_remark",
			"label": "备注",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
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
			"inputId":"skill_mainDiagnosis",
			"value":${pdfn:toJsonString(formDataMap.skill_mainDiagnosis)}
		},
		{
			"inputId":"skill_secondaryDiagnosis",
			"value":${pdfn:toJsonString(formDataMap.skill_secondaryDiagnosis)}
		},
		{
			"inputId":"skill_director",
			"value":${pdfn:toJsonString(formDataMap.skill_director)}
		},
		{
			"inputId":"skill_remark",
			"value":${pdfn:toJsonString(formDataMap.skill_remark)}
		}
	]