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
			"inputId": "common_patientName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "common_anamnesisNumbers",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "common_diseaseNiagnose",
			"label": "疾病诊断",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "common_outcome",
			"label": "转归情况",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "common_superiorPhysician",
			"label": "上级医师",
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
			"inputId":"common_diseaseNiagnose",
			"value":${pdfn:toJsonString(formDataMap.common_diseaseNiagnose)}
		},
		{
			"inputId":"common_patientName",
			"value":${pdfn:toJsonString(formDataMap.common_patientName)}
		},
		{
			"inputId":"common_anamnesisNumbers",
			"value":${pdfn:toJsonString(formDataMap.common_anamnesisNumbers)}
		},
		{
			"inputId":"common_outcome",
			"value":${pdfn:toJsonString(formDataMap.common_outcome)}
		},
		{
			"inputId":"common_superiorPhysician",
			"value":${pdfn:toJsonString(formDataMap.common_superiorPhysician)}
		}
	]