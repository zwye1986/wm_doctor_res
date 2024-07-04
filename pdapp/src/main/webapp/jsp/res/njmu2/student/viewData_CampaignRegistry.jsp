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
		<c:if test="${isOther}">
			{
				"inputId":"activeName",
				"inputType":"text",
				"label":"活动名称",
				"required":true
				<c:if test="${cannotOperSwitch}">
					,
					"readonly":true
				</c:if>
			},
		</c:if>
		{
			"inputId":"activeDate",
			"inputType":"date",
			"label":"活动日期",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"activeTitle",
			"inputType":"text",
			"label":"讲座题目",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		<c:if test="${isOther}">
			{
				"inputId":"activeName",
				"value":${pdfn:toJsonString(formDataMap.activeName)}
			},
		</c:if>
		{
			"inputId":"activeDate",
			"value":${pdfn:toJsonString(formDataMap.activeDate)}
		},
		{
			"inputId":"activeTitle",
			"value":${pdfn:toJsonString(formDataMap.activeTitle)}
		}
	]