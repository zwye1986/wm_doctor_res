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
			
			"inputId":"dept",
			"inputType":"select",
			"label":"科室",
			 "options":[{
				"optionId":${pdfn:toJsonString(result.schDeptName)},
				"optionDesc":${pdfn:toJsonString(result.schDeptName)}
			}],
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"time",
			"inputType":"date",
			"label":"时间",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"caseName",
			"inputType":"text",
			"label":"病名",
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
		}
	],
	"values":[
		{
			"inputId":"dept",
			"value":${pdfn:toJsonString(formDataMap.dept)}
		},
		{
			"inputId":"time",
			"value":${pdfn:toJsonString(formDataMap.time)}
		},
		{
			"inputId":"caseName",
			"value":${pdfn:toJsonString(formDataMap.caseName)}
		},
		{
			"inputId":"caseNo",
			"value":${pdfn:toJsonString(formDataMap.caseNo)}
		}
	]