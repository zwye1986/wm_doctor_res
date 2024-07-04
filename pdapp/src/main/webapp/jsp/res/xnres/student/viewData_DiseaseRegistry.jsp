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
		<c:if test="${sSwitch && isAudit && notPass}">
            "save":"保存",
            "del":"删除"
		</c:if>
		<c:if test="${tSwitch && !isAudit}">
			"save":"审核"
		</c:if>
	},
	"inputs":[

		{
			"inputId": "diseaseName",
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
			"inputId": "patientName",
			"label": "病人姓名",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "caseNo",
			"label": "病历号/检查号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "treatMeasure",
			"label": "治疗措施",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "recType",
			"label": "类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "门诊",
					"optionDesc": "门诊"
				},
				{
					"optionId": "急诊",
					"optionDesc": "急诊"
				},
				{
					"optionId": "病房",
					"optionDesc": "病房"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "inHosDate",
			"label": "入院日期",
			"required":false,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "recStatus",
			"label": "状态",
			"required":false,
			"inputType": "checkbox",
			"options": [
				{
					"optionId": "主管",
					"optionDesc": "主管"
				},
				{
					"optionId": "抢救",
					"optionDesc": "抢救"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "caseType",
			"label": "病历类型",
			"required":false,
			"inputType": "checkbox",
			"options": [
				{
					"optionId": "新收病人",
					"optionDesc": "新收病人"
				},
				{
					"optionId": "书写规范住院大病历",
					"optionDesc": "书写规范住院大病历"
				},
				{
					"optionId": "学习病历",
					"optionDesc": "学习病历"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "diagnosisType",
			"label": "诊断类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "主要诊断",
					"optionDesc": "主要诊断"
				},
				{
					"optionId": "次要诊断",
					"optionDesc": "次要诊断"
				},
				{
					"optionId": "并行诊断",
					"optionDesc": "并行诊断"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "remark",
			"label": "备注",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "record",
			"label": "记录",
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
			"inputId":"diseaseName",
			"value":${pdfn:toJsonString(formDataMap.diseaseName)}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap.patientName)}
		},
		{
			"inputId":"caseNo",
			"value":${pdfn:toJsonString(formDataMap.caseNo)}
		},
		{
			"inputId":"treatMeasure",
			"value":${pdfn:toJsonString(formDataMap.treatMeasure)}
		},
		{
			"inputId":"recType",
			"value":${pdfn:toJsonString(formDataMap.recType)}
		},
		{
			"inputId":"inHosDate",
			"value":${pdfn:toJsonString(formDataMap.inHosDate)}
		},
		{
			"inputId":"recStatus",
			"values":[${pdfn:parseMultipleVal(formDataMap.recStatus,',')}]
		},
		{
			"inputId":"caseType",
			"values":[${pdfn:parseMultipleVal(formDataMap.caseType,',')}]
		},
		{
			"inputId":"diagnosisType",
			"value":${pdfn:toJsonString(formDataMap.diagnosisType)}
		},
		{
			"inputId":"remark",
			"value":${pdfn:toJsonString(formDataMap.remark)}
		},
		{
			"inputId":"record",
			"value":${pdfn:toJsonString(formDataMap.record)}
		}
	]