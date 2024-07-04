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
			"label":"考生姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"pxxk",
			"inputType":"text",
			"label":"培训学科",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.pxxk)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"考核科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.deptName?doctorSchProcess.deptName:formDataMap.deptName)}
		},
		{
			"inputId":"khsj_label",
			"inputType":"text",
			"label":"考核时间",
			"readonly":true,
			"value":"30分钟"
		},
		{
			"inputId":"mf_label",
			"inputType":"text",
			"label":"满分",
			"readonly":true,
			"value":"100分"
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"补充询问病史扣 10 分"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"reason00",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"补充询问体检结果扣 10 分"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"reason01",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason01)}
		},
		{
			"inputId":"t2",
			"inputType":"title",
			"label":"补充询问辅助检查结果及进一步检查项目 10 分"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"reason02",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"病史特点归纳 10 分"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"reason03",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason03)}
		},
		{
			"inputId":"t4",
			"inputType":"title",
			"label":"诊断及依据 10 分"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"reason04",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason04)}
		},
		{
			"inputId":"t5",
			"inputType":"title",
			"label":"鉴别诊断要点 10 分"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"reason05",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason05)}
		},
		{
			"inputId":"t6",
			"inputType":"title",
			"label":"治疗措施 10 分"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"reason06",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"专业提问 10 分"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"reason07",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason07)}
		},
		{
			"inputId":"t8",
			"inputType":"title",
			"label":"其他（人文关爱及沟通表达能力）（20分）"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"扣分",
			"verify": {
			"max": "20",
			"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"reason08",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason08)}
		},
		{
		"inputId":"skillScore",
		"inputType":"text",
		"label":"得分",
"isTotal":true,
		"readonly":true, 		"required":true,
		"value":${pdfn:toJsonString(empty formDataMap.skillScore?100:formDataMap.skillScore)}
		},
		{
		"inputId":"remarks",
		"inputType":"text",
		"label":"备注",
		"readonly":${isAudit}, 		"required":true,
		"value":${pdfn:toJsonString(formDataMap.remarks)}
		},
		{
		"inputId":"teacherName",
		"inputType":"text",
		"label":"考官姓名",
		"readonly":${isAudit}, 		"required":true,
		"value":${pdfn:toJsonString(formDataMap.teacherName)}
		},
		{
		"inputId":"date",
		"inputType":"date",
		"label":"日期",
		"readonly":${isAudit}, 		"required":true,
		"value":${pdfn:toJsonString(empty formDataMap.date?pdfn:getCurrDate():formDataMap.date)}
		}
	]