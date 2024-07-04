<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		<c:if test="${sSwitch && !isAudit}">
			"save":"保存", 
			"del":"删除"
		</c:if>
		<c:if test="${sSwitch && isAudit && notPass}">
            "save":"保存",
            "del":"删除"
		</c:if>
		<c:if test="${tSwitch && !isAudit}">
			"save":"审核"
		</c:if>
	},
	"inputs":[
		{
			"inputId": "title",
			"label": "题目",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "articleTitle",
			"label": "文章题目",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "论文",
					"optionDesc": "论文"
				},
				{
					"optionId": "综述",
					"optionDesc": "综述"
				},
				{
					"optionId": "报告",
					"optionDesc": "报告"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "teaching",
			"label": "教学",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "实习",
					"optionDesc": "实习"
				},
				{
					"optionId": "专科讲座",
					"optionDesc": "专科讲座"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "lectureTitle",
			"label": "讲座题目",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "assessment",
			"label": "考核者",
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
			"inputId":"title",
			"value":${pdfn:toJsonString(formDataMap.title)}
		},
		{
			"inputId":"articleTitle",
			"value":${pdfn:toJsonString(formDataMap.articleTitle)}
		},
		{
			"inputId":"teaching",
			"value":${pdfn:toJsonString(formDataMap.teaching)}
		},
		{
			"inputId":"lectureTitle",
			"value":${pdfn:toJsonString(formDataMap.lectureTitle)}
		},
		{
			"inputId":"assessment",
			"value":${pdfn:toJsonString(formDataMap.assessment)}
		}
	]