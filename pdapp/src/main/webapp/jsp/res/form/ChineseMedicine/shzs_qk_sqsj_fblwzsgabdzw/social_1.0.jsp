<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:set var="inputType" value="select"/>
	<c:if test="${issocial}">
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
			"inputId": "social_date",
			"label": "日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_subject",
			"label": "题目",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_category",
			"label": "类别1~4",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_reportJournal",
			"label": "发表刊物",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_fewAuthor",
			"label": "第几作者",
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
			"inputId":"social_category",
			"value":${pdfn:toJsonString(formDataMap.social_category)}
		},
		{
			"inputId":"social_date",
			"value":${pdfn:toJsonString(formDataMap.social_date)}
		},
		{
			"inputId":"social_subject",
			"value":${pdfn:toJsonString(formDataMap.social_subject)}
		},
		{
			"inputId":"social_reportJournal",
			"value":${pdfn:toJsonString(formDataMap.social_reportJournal)}
		},
		{
			"inputId":"social_fewAuthor",
			"value":${pdfn:toJsonString(formDataMap.social_fewAuthor)}
		}
	]