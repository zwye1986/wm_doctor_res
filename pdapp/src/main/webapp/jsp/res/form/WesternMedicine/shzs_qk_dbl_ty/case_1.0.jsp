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
			"inputId": "mr_pName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
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
			"inputId": "disease_mainDiagnosis",
			"label": "主要诊断",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_superiorPhysicianName",
			"label": "上级医师姓名",
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
			"inputId":"mr_pName",
			"value":${pdfn:toJsonString(formDataMap.mr_pName)}
		},
		{
			"inputId":"mr_no",
			"value":${pdfn:toJsonString(formDataMap.mr_no)}
		},
		{
			"inputId":"disease_mainDiagnosis",
			"value":${pdfn:toJsonString(formDataMap.disease_mainDiagnosis)}
		},
		{
			"inputId":"disease_superiorPhysicianName",
			"value":${pdfn:toJsonString(formDataMap.disease_superiorPhysicianName)}
		}
	]