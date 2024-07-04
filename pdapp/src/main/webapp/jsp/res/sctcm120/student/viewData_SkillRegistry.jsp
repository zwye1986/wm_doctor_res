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
			"inputId":"skill_operName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label":"操作名称",
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
			"inputId":"skill_pName",
			"inputType":"text",
			"label":"病人姓名",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"skill_operDate",
			"inputType":"date",
			"label":"操作日期",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"skill_mrNo",
			"inputType":"text",
			"label":"病历号/检查号",
			"required":false
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
		,
		{
			"inputId":"skill_result",
			"inputType":"select",
			"label":"是否成功",
			"options": [
	                {
	                    "optionId": "1",
	                    "optionDesc": "是"
	                },
	                {
	                    "optionId": "0",
	                    "optionDesc": "否"
	                }
	            ],
			"required":false
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"fail_diagnosticResults",
			"inputType":"textarea",
			"label":"诊断结果",
			"required":false
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
		,
		{
			"inputId":"fail_reason",
			"inputType":"textarea",
			"label":"失败原因",
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"skill_operName",
			"value":${pdfn:toJsonString(formDataMap.skill_operName)}
		},
		{
			"inputId":"skill_pName",
			"value":${pdfn:toJsonString(formDataMap.skill_pName)}
		},
		{
			"inputId":"skill_operDate",
			"value":${pdfn:toJsonString(formDataMap.skill_operDate)}
		},
		{
			"inputId":"skill_mrNo",
			"value": ${pdfn:toJsonString(formDataMap.skill_mrNo)}
		}
		,
		{
			"inputId":"skill_result",
			"value": ${pdfn:toJsonString(formDataMap.skill_result_id)}
		}
		,
		{
			"inputId":"fail_diagnosticResults",
			"value": ${pdfn:toJsonString(formDataMap.fail_diagnosticResults)}
		}
		,
		{
			"inputId":"fail_reason",
			"value": ${pdfn:toJsonString(formDataMap.fail_reason)}
		}
	]