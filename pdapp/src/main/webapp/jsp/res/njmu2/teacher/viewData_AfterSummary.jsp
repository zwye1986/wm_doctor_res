<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${(!isAudit)and (not empty formDataMap)}">
		"action":{
			"save":"提交" 
		},
	</c:if>
	"inputs":[
		{
			"inputId":"name",
			"inputType":"text",
			"label":"姓名",
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
			"inputId":"internshipEvaluation",
			"inputType":"select",
			"label":"实习表现评价",
			"placeholder":"实习表现评价",
			 "options":[{
				"optionId":"1",
				"optionDesc":"优"
			},
			{
				"optionId":"2",
				"optionDesc":"良"
			}
			,
			{
				"optionId":"3",
				"optionDesc":"中"
			}
			,
			{
				"optionId":"4",
				"optionDesc":"合格"
			},
			{
				"optionId":"5",
				"optionDesc":"不合格"
			}]
			,
			"required":true
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"theoryTest",
			"inputType":"text",
			"label":"理论考试（20分）",
			"placeholder":"根据实际分数折算",
			"required":true,
			"verify": {
                "max": 20,
                "type": "int"
           	}
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
			
		}
		,
		{
			"inputId":"skillTest",
			"inputType":"text",
			"label":"技能考核（80分）",
			"placeholder":"根据实际分数折算",
			"required":true,
			"verify": {
                "max": 80,
                "type": "int"
           	}
			<c:if test="${isAudit}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"totalScore",
			"inputType":"text",
			"label":"总分",
			"placeholder":"满分100分",
			"readonly":true
		}
	]
	,
	"values":[
		{
			"inputId":"name",
			"value":${pdfn:toJsonString(formDataMap.name)}
		},
		{
			"inputId":"personalSummary",
			"value":${pdfn:toJsonString(formDataMap.personalSummary)}
		}
		,
		{
			"inputId":"internshipEvaluation",
			"value":${pdfn:toJsonString(formDataMap.internshipEvaluation_id)}
		}
		,
		{
			"inputId":"theoryTest",
			"value":${pdfn:toJsonString(formDataMap.theoryTest)}
		}
		,
		{
			"inputId":"skillTest",
			"value":${pdfn:toJsonString(formDataMap.skillTest)}
		}
		,
		{
			"inputId":"totalScore",
			"value":${pdfn:toJsonString(formDataMap.totalScore)}
		}
	]
