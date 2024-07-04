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
			"inputId": "activity_content",
			"label": "内容",
			"required":true,
			"inputType": "textarea"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "activity_way",
			"label": "活动形式",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "教学查房"
				},
				{
					"optionId": "2",
					"optionDesc": "疑难病例讨论"
				},
				{
					"optionId": "3",
					"optionDesc": "危重病例讨论"
				},
				{
					"optionId": "4",
					"optionDesc": "学术讲座"
				},
				{
					"optionId": "5",
					"optionDesc": "死亡病例讨论"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "activity_period",
			"label": "学时",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "activity_speaker",
			"label": "主讲人",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"activity_content",
			"value":${pdfn:toJsonString(formDataMap.activity_content)}
		},
		{
			"inputId":"activity_way",
			"value":${pdfn:toJsonString(formDataMap.activity_way_id)}
		},
		{
			"inputId":"activity_period",
			"value":${pdfn:toJsonString(formDataMap.activity_period_id)}
		},
		{
			"inputId":"activity_speaker",
			"value":${pdfn:toJsonString(formDataMap.activity_speaker)}
		}
	]