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
			"inputId":"name",
			"inputType":"text",
			"label":"姓名",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"caseNo",
			"inputType":"text",
			"label":"病历号",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"time",
			"inputType":"date",
			"label":"操作时间",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"explain",
			"inputType":"textarea",
			"label":"诊断说明",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"name",
			"value":${pdfn:toJsonString(formDataMap.name)}
		},
		{
			"inputId":"caseNo",
			"value":${pdfn:toJsonString(formDataMap.caseNo)}
		},
		{
			"inputId":"time",
			"value":${pdfn:toJsonString(formDataMap.time)}
		},
		{
			"inputId":"explain",
			"value":${pdfn:toJsonString(formDataMap.explain)}
		}
	]