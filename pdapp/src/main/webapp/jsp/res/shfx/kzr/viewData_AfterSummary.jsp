<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
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
			"readonly":true
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
			"readonly":${isAudit}
		}
		<c:if test="${isAudit}">
			,
			{
				"inputId":"isAgree",
				"inputType":"select",
				"label":"同意出科",
				"required":true,
				"readonly":true,
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
		</c:if>
		,
		{
			"inputId":"head",
			"inputType":"text",
			"label":"主任",
			"required":true,
			"readonly":${isAudit}
		}
		,
		{
			"inputId":"date",
			"inputType":"date",
			"label":"日期",
			"required":true,
			"readonly":${isAudit}
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
		<c:if test="${isAudit}">
			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
			}
		</c:if>
		,
		{
			"inputId":"head",
			"value":${pdfn:toJsonString(empty formDataMap.head?tea.userName:formDataMap.head)}
		}
		,
		{
			"inputId":"date",
			"value":${pdfn:toJsonString(empty formDataMap.date? pdfn:getCurrDate(): formDataMap.date)}
		}
	]