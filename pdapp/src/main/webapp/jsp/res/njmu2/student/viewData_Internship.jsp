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
			"inputId":"time",
			"inputType":"date",
			"label":"时间",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"content",
			"inputType":"textarea",
			"label":"实习主要内容",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"time",
			"value":${pdfn:toJsonString(formDataMap.time)}
		},
		{
			"inputId":"content",
			"value":${pdfn:toJsonString(formDataMap.content)}
		}
	]