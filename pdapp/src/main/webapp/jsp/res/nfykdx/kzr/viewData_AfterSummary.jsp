<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"提交"
	},
	"inputs":[
		{
			"inputId":"summaryDocName",
			"inputType":"text",
			"label":"姓名",
			"readonly":true
		},
		{
			"inputId":"personalSummary",
			"inputType":"textarea",
			"label":"个人小结",
			"required":true,
			"readonly":false
		}
		,
		{
			"inputId":"deptAppraise",
			"inputType":"textarea",
			"label":"带教评价",
			"required":true,
			"readonly":true
		}
		,
		{
			"inputId":"deptHeadAutograth",
			"inputType":"textarea",
			"label":"科室评价",
			"required":true,
			<c:if test="${isAudit}">"readonly":true</c:if>
			<c:if test="${!isAudit}">"readonly":false</c:if>
		}
		,
		{
			"inputId":"isAgree",
			"inputType":"select",
			"label":"同意出科",
			"required":true,
			"readonly":false,
			"options": [
				{
					"optionId": "Y",
					"optionDesc": "是"
				},
				{
					"optionId": "N",
					"optionDesc": "否"
				}
			]
		}
		,
		{
			"inputId":"head",
			"inputType":"text",
			"label":"主任",
			"required":true,
			"readonly":true
		}
		,
		{
			"inputId":"date",
			"inputType":"date",
			"label":"日期",
			"required":true,
			<c:if test="${isAudit}">"readonly":true</c:if>
			<c:if test="${!isAudit}">"readonly":false</c:if>
		}
	]
	,
	"values":[
		{
			"inputId":"summaryDocName",
			"value":${pdfn:toJsonString(empty formDataMap.name?result.doctorName:formDataMap.name)}
		},
		{
			"inputId":"personalSummary",
			"value":${pdfn:toJsonString(formDataMap.personalSummary)}
		}
		,
		{
			"inputId":"deptAppraise",
			"value":${pdfn:toJsonString(formDataMap.deptAppraise)}
		}
		,
		{
			"inputId":"deptHeadAutograth",
			"value":${pdfn:toJsonString(formDataMap.deptHeadAutograth)}
		}
		,
		{
			"inputId":"isAgree",
			"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
		}
		,
		{
			"inputId":"head",
			"value":${pdfn:toJsonString(empty formDataMap.head?tea.userName:formDataMap.head)}
		}
		,
		{
			"inputId":"date",
			"value":${pdfn:toJsonString(formDataMap.date)}
		}
	]