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
			"inputId": "social_awardName",
			"label": "获奖名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_level",
			"label": "级别",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
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
			"inputId": "social_remark",
			"label": "备注",
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
			"inputId":"social_time",
			"value":${pdfn:toJsonString(formDataMap.social_time)}
		},
		{
			"inputId":"social_awardName",
			"value":${pdfn:toJsonString(formDataMap.social_awardName)}
		},
		{
			"inputId":"social_level",
			"value":${pdfn:toJsonString(formDataMap.social_level)}
		},
		{
			"inputId":"social_remark",
			"value":${pdfn:toJsonString(formDataMap.social_remark)}
		}
	]