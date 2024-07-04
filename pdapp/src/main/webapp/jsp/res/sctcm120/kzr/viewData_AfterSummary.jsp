<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"审核" 
		},
	</c:if>
	"inputs":[
		{
			"inputId":"name",
            "label":"姓名",
            "inputType":"text",
			"readonly":true
		},
		{
			"inputId":"personalSummary",
			"inputType":"textarea",
			"label":"个人小结",
			"readonly":true,
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
			,
			{
				"inputId":"isAgree",
				"inputType":"select",
				"label":"同意出科",
				"required":true,
				"readonly":${isAudit},
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
				"inputType":"text",
				"label":"日期",
				"required":true,
				"readonly":true
			}
	]
	,
	"values":[
		{
			"inputId":"name",
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
				"value":${pdfn:toJsonString(!empty formDataMap.head?formDataMap.head:tea.userName)}
			}
			,
			{
				"inputId":"date",
				"value":${pdfn:toJsonString(!empty formDataMap.date?formDataMap.date:(pdfn:getCurrDate()))}
			}
	]
