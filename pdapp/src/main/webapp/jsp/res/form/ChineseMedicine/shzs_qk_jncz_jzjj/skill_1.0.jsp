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
			"inputId": "skill_diseaseNiagnose",
			"label": "疾病诊断",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_yourselfOrHelper",
			"label": "自己完成/助手",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "自己完成",
					"optionDesc": "自己完成"
				},
				{
					"optionId": "助手",
					"optionDesc": "助手"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_directorPhysician",
			"label": "主治医师",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "skill_succeedOrFail",
			"label": "成功/失败",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "成功",
					"optionDesc": "成功"
				},
				{
					"optionId": "失败",
					"optionDesc": "失败"
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
			"inputId":"skill_diseaseNiagnose",
			"value":${pdfn:toJsonString(formDataMap.skill_diseaseNiagnose)}
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
			"inputId":"skill_yourselfOrHelper",
			"value":${pdfn:toJsonString(formDataMap.skill_yourselfOrHelper)}
		},
		{
			"inputId":"skill_directorPhysician",
			"value":${pdfn:toJsonString(formDataMap.skill_directorPhysician)}
		},
		{
			"inputId":"skill_succeedOrFail",
			"value":${pdfn:toJsonString(formDataMap.skill_succeedOrFail)}
		}
	]