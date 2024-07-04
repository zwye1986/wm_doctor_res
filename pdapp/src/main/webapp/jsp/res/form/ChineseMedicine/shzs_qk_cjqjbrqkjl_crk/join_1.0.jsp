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
			"inputId": "join_date",
			"label": "日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "join_patientName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "join_anamnesisNumbers",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "join_diseaseName",
			"label": "疾病名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "join_outcome",
			"label": "转归情况",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "join_directorPhysician",
			"label": "主管医师",
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
			"inputId":"join_date",
			"value":${pdfn:toJsonString(formDataMap.join_date)}
		},
		{
			"inputId":"join_diseaseName",
			"value":${pdfn:toJsonString(formDataMap.join_diseaseName)}
		},
		{
			"inputId":"join_patientName",
			"value":${pdfn:toJsonString(formDataMap.join_patientName)}
		},
		{
			"inputId":"join_anamnesisNumbers",
			"value":${pdfn:toJsonString(formDataMap.join_anamnesisNumbers)}
		},
		{
			"inputId":"join_outcome",
			"value":${pdfn:toJsonString(formDataMap.join_outcome)}
		},
		{
			"inputId":"join_directorPhysician",
			"value":${pdfn:toJsonString(formDataMap.join_directorPhysician)}
		}
	]