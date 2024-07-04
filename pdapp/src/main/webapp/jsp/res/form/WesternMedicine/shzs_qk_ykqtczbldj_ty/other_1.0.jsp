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
			"inputId": "other_patientName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "other_anamnesisNumbers",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "other_diseaseName",
			"label": "疾病名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "other_attendingPhysician",
			"label": "主治医师",
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
			"inputId":"other_diseaseName",
			"value":${pdfn:toJsonString(formDataMap.other_diseaseName)}
		},
		{
			"inputId":"other_patientName",
			"value":${pdfn:toJsonString(formDataMap.other_patientName)}
		},
		{
			"inputId":"other_anamnesisNumbers",
			"value":${pdfn:toJsonString(formDataMap.other_anamnesisNumbers)}
		},
		{
			"inputId":"other_attendingPhysician",
			"value":${pdfn:toJsonString(formDataMap.other_attendingPhysician)}
		}
	]