<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
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
			"inputId": "mr_no",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_pName",
			"label": "疾病名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "mr_diagType",
			"label": "诊断类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "主要诊断"
				},
				{
					"optionId": "2",
					"optionDesc": "次要诊断"
				},
				{
					"optionId": "3",
					"optionDesc": "并行诊断"
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
			"inputId":"mr_no",
			"value":${pdfn:toJsonString(formDataMap.mr_no)}
		},
		{
			"inputId":"disease_pName",
			"value":${pdfn:toJsonString(formDataMap.disease_pName)}
		},
		{
			"inputId":"mr_diagType",
			"value":${pdfn:toJsonString(formDataMap.mr_diagType_id)}
		}
	]