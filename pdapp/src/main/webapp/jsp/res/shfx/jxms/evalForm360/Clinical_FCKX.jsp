<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		{
			"inputId": "formFileName",
			"label": "表单名称",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": ${pdfn:toJsonString(formFileName)}
		},
		{
			"inputId": "schDeptFlow",
			"label": "轮转科室流水号",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": "${doctorSchProcess.schDeptFlow}"
		},
		{
			"inputId": "operUserFlow",
			"label": "学员流水号",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": "${param.docFlow}"
		},
		{
			"inputId": "roleFlag",
			"label": "角色ID",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": "${roleFlag}"
		},
		{
			"inputId": "recFlow",
			"label": " 评价表流水号",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": "${rec.recFlow}"
		},
		{
			"inputId": "processFlow",
			"label": "过程流水号",
			"required": true,
			"inputType": "text",
			"isHidden": true,
			"value": "${processFlow}"
		},
		{
			"inputId":"studentName",
			"inputType":"text",
			"label":"姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"date",
			"inputType":"date",
			"label":"考核日期",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.date?pdfn:getCurrDate():formDataMap.date)}
		},
		{
			"inputId":"studentSid",
			"inputType":"text",
			"label":"工号",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.studentSid)}
		},
		{
			"inputId":"speName",
			"inputType":"text",
			"label":"考核专业",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.speName)}
		},
		{
			"inputId":"titleLabel0",
			"inputType":"text",
			"label":"手术名称",
			"readonly":true,
			"value":"负压吸宫术"
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"术前准备：询问病史（15分）"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "15",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"术前准备：专科检查（15分）"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "15",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"t2",
			"inputType":"title",
			"label":"术前准备：手术、操作指征及麻醉方式的选择（5分）"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"术前准备：术前医嘱（5分）"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"t4",
			"inputType":"title",
			"label":"术中过程：无菌操作严密（6分）"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"t5",
			"inputType":"title",
			"label":"术中过程：基本操作规范（10分）"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"t6",
			"inputType":"title",
			"label":"术中过程：手术技巧娴熟（6分）"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"术中过程：解剖层面清晰（6分）"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"t8",
			"inputType":"title",
			"label":"术中过程：术野显露清楚（6分）"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"t9",
			"inputType":"title",
			"label":"术中过程：探查脏器有序（6分）"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"t10",
			"inputType":"title",
			"label":"术中过程：应变能力敏捷（5分）"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"术后处理：术后医嘱（5分）"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part11)}
		},
		{
			"inputId":"t12",
			"inputType":"title",
			"label":"术后处理：手术、操作记录（5分）"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},
		{
			"inputId":"t13",
			"inputType":"title",
			"label":"术后处理：常见并发症及处理（5分）"
		},
		{
			"inputId":"part13",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part13)}
		},
		{
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"总分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.skillScore?0:formDataMap.skillScore)}
		}
	]