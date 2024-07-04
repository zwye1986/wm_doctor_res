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
			"inputId": "disease_diagName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "病种名称",
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
			"inputId": "disease_patientName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_anamnesisNumbers",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_emergency",
			"label": "突发事件",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_director",
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
			"inputId": "disease_superiorPhysicianName",
			"label": "上级医师姓名",
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
			"inputId":"disease_patientName",
			"value":${pdfn:toJsonString(formDataMap.disease_patientName)}
		},
		{
			"inputId":"disease_anamnesisNumbers",
			"value":${pdfn:toJsonString(formDataMap.disease_anamnesisNumbers)}
		},
		{
			"inputId":"disease_diagName",
			"value":${pdfn:toJsonString(formDataMap.disease_diagName)}
		},
		{
			"inputId":"disease_emergency",
			"value":${pdfn:toJsonString(formDataMap.disease_emergency)}
		},
		{
			"inputId":"disease_director",
			"value":${pdfn:toJsonString(formDataMap.disease_director)}
		},
		{
			"inputId":"disease_superiorPhysicianName",
			"value":${pdfn:toJsonString(formDataMap.disease_superiorPhysicianName)}
		}
	]