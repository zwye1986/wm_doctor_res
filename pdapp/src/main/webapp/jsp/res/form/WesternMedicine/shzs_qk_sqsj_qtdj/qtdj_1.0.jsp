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
			"inputId": "social_teachObject",
			"label": "带教对象",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_peopleNumber",
			"label": "人数",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "social_teachContent",
			"label": "教学内容",
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
			"inputId":"social_teachObject",
			"value":${pdfn:toJsonString(formDataMap.social_teachObject)}
		},
		{
			"inputId":"social_peopleNumber",
			"value":${pdfn:toJsonString(formDataMap.social_peopleNumber)}
		},
		{
			"inputId":"social_teachContent",
			"value":${pdfn:toJsonString(formDataMap.social_teachContent)}
		}
	]