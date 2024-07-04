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
		<c:if test="${!empty formDataMap.personalSummary}">
			,
			{
				"inputId":"deptAppraise",
				"inputType":"textarea",
				"label":"带教评价",
				"required":true
				<c:if test="${isAudit}">
					,
					"readonly":true
				</c:if>
			}
			,
			{
				"inputId":"isAgree",
				"inputType":"select",
				"label":"同意出科",
				"required":true
				<c:if test="${isAudit}">
					,
					"readonly":true
				</c:if>,
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
		<c:if test="${!empty formDataMap.personalSummary}">
			,
			{
				"inputId":"deptAppraise",
				"value":${pdfn:toJsonString(formDataMap.deptAppraise)}
			}

			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
			}
			,
			{
				"inputId":"head",
				"value":${pdfn:toJsonString(empty formDataMap.head ? currRegProcess.headUserName:  formDataMap.head)}
			}
			,
			<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
			{
				"inputId":"date",
				"value":${pdfn:toJsonString(empty formDataMap.date ? currDate:  formDataMap.date )}
			}
		</c:if>
	]
