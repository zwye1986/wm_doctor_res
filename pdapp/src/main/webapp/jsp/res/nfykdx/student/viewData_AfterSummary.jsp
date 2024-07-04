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
			"required":true
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		}
		<c:if test="${!empty formDataMap.deptAppraise}">
			,
			{
				"inputId":"deptAppraise",
				"inputType":"textarea",
				"label":"带教评价",
				"required":true,
				"readonly":true
			}
		</c:if>
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
			,
			{
			"inputId":"deptHeadAutograth",
			"inputType":"textarea",
			"label":"科室评价",
			"required":true,
			"readonly":true
			}
		</c:if>
		<c:if test="${!empty formDataMap.isAgree}">
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
		<c:if test="${!empty formDataMap.head}">
			,
			{
				"inputId":"head",
				"inputType":"text",
				"label":"主任",
				"required":true,
				"readonly":true
			}
		</c:if>
		<c:if test="${!empty formDataMap.date}">
			,
			{
				"inputId":"date",
				"inputType":"text",
				"label":"日期",
				"required":true,
				"readonly":true
			}
		</c:if>
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
		<c:if test="${!empty formDataMap.deptAppraise}">
			,
			{
				"inputId":"deptAppraise",
				"value":${pdfn:toJsonString(formDataMap.deptAppraise)}
			}
		</c:if>
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
			,
			{
				"inputId":"deptHeadAutograth",
				"value":${pdfn:toJsonString(formDataMap.deptHeadAutograth)}
			}
		</c:if>
		<c:if test="${!empty formDataMap.isAgree_id}">
			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
			}
		</c:if>
		<c:if test="${!empty formDataMap.head}">
			,
			{
				"inputId":"head",
				"value":${pdfn:toJsonString(formDataMap.head)}
			}
		</c:if>
		<c:if test="${!empty formDataMap.date}">
			,
			{
				"inputId":"date",
				"value":${pdfn:toJsonString(formDataMap.date)}
			}
		</c:if>
	]
