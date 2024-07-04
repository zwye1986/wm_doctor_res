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
			"inputId":"activity_way",
			"inputType":"${isOther?'text':'select'}",
			"label":"活动形式"
			<c:if test="${!isOther}">
			,
			"options": [
                {
                    "optionId": ${pdfn:toJsonString(req.itemName)},
                    "optionDesc": ${pdfn:toJsonString(req.itemName)}
                }
				]
            </c:if>
			,"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"activity_date",
			"inputType":"date",
			"label":"日期",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
		,
		{
			"inputId":"activity_period",
			"inputType":"select",
			"label":"学时(小时)",
			"options": [
				{
					"optionId": '1',
					"optionDesc": '1'
				},
				{
				"optionId": '2',
				"optionDesc": '2'
				},
				{
				"optionId": '3',
				"optionDesc": '3'
				},
				{
				"optionId": '4',
				"optionDesc": '4'
				},
				{
				"optionId": '5',
				"optionDesc": '5'
				},
			],
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
		,
		{
			"inputId":"activity_speaker",
			"inputType":"text",
			"label":"主讲人",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
		,
		{
			"inputId":"activity_content",
			"inputType":"textarea",
			"label":"内容",
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"activity_way",
			"value":${pdfn:toJsonString(formDataMap.activity_way)}
		},
		{
			"inputId":"activity_date",
			"value":${pdfn:toJsonString(formDataMap.activity_date)}
		}
		,
		{
			"inputId":"activity_period",
			"value":${pdfn:toJsonString(formDataMap.activity_period_id)}
		}
		,
		{
			"inputId":"activity_speaker",
			"value":${pdfn:toJsonString(formDataMap.activity_speaker)}
		}
		,
		{
			"inputId":"activity_content",
			"value":${pdfn:toJsonString(formDataMap.activity_content)}
		}
	]