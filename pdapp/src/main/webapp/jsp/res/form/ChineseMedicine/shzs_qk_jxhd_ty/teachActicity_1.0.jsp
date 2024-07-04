<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:set var="inputType" value="select"/>
	<c:if test="${isOther}">
		<c:set var="inputType" value="text"/>
	</c:if>
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
			"inputId": "teach_date",
			"label": "日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teach_content",
			"label": "内容",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teach_activityForm",
			"label": "活动形式",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teach_period",
			"label": "学时",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teach_keynoteSpeaker",
			"label": "主讲人",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teach_remark",
			"label": "备注",
			"required":false,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"teach_date",
			"value":${pdfn:toJsonString(formDataMap.teach_date)}
		},
		{
			"inputId":"teach_period",
			"value":${pdfn:toJsonString(formDataMap.teach_period)}
		},
		{
			"inputId":"teach_content",
			"value":${pdfn:toJsonString(formDataMap.teach_content)}
		},
		{
			"inputId":"teach_activityForm",
			"value":${pdfn:toJsonString(formDataMap.teach_activityForm)}
		},
		{
			"inputId":"teach_keynoteSpeaker",
			"value":${pdfn:toJsonString(formDataMap.teach_keynoteSpeaker)}
		},
		{
			"inputId":"teach_remark",
			"value":${pdfn:toJsonString(formDataMap.teach_remark)}
		}
	]