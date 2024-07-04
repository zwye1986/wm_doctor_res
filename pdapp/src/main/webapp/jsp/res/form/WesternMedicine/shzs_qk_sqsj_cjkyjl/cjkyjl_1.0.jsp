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
			"inputId": "social_time",
			"label": "时间",
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
			"inputId": "social_projectLeader",
			"label": "课题负责人",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_joinRole",
			"label": "参与角色",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_finishStatus",
			"label": "完成情况",
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
			"inputId":"social_projectLeader",
			"value":${pdfn:toJsonString(formDataMap.social_projectLeader)}
		},
		{
			"inputId":"social_time",
			"value":${pdfn:toJsonString(formDataMap.social_time)}
		},
		{
			"inputId":"social_subject",
			"value":${pdfn:toJsonString(formDataMap.social_subject)}
		},
		{
			"inputId":"social_joinRole",
			"value":${pdfn:toJsonString(formDataMap.social_joinRole)}
		},
		{
			"inputId":"social_finishStatus",
			"value":${pdfn:toJsonString(formDataMap.social_finishStatus)}
		}
	]