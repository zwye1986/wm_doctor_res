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
			"inputId":"title0",
			"inputType":"title",
			"label": "有关你的医师的信息"
		},
		{
			"inputId":"studentName",
			"inputType":"text",
			"label":"姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"sessionNumber",
			"inputType":"text",
			"label":"届别：",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.sessionNumber?resDoctor.sessionNumber:formDataMap.sessionNumber)}
		},
		{
			"inputId":"trainingSpeName",
			"inputType":"text",
			"label":"培训专业",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.trainingSpeName?resDoctor.trainingSpeName:formDataMap.trainingSpeName)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"轮转科室名称",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.deptName ?doctorSchProcess.schDeptName:formDataMap.deptName)}
		},
		{
			"inputId":"cycleTimeQ",
			"inputType":"date",
			"label":"轮转开始时间",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeQ ?doctorSchProcess.schStartDate:formDataMap.cycleTimeQ)}
		},
		{
			"inputId":"cycleTimeH",
			"inputType":"date",
			"label":"轮转结束时间",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeH ?doctorSchProcess.schEndDate:formDataMap.cycleTimeH)}
		},
		{
			"inputId":"title0",
			"inputType":"title",
			"label": "一、医德医风人际沟通团队合作"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"遵守国家法律法规、医院规章制度",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"履行岗位职责",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"以病人为中心，体现人文关怀",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"人际（医患）沟通和表达能力",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"团结协作精神",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"title1",
			"inputType":"title",
			"label": "二、临床综合能力"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"临床基本知识、基本理论掌握程度",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"临床基本技能掌握程度",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"临床思维能力",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"临床诊疗能力",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"危重病人的识别及紧急处理能力",
			"verify": {
			"max": "10",
			"type": "float"
			},
"scoreType":"add",
			"placeholder": "0~10分",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"part10",
			"inputType":"text",
"isTotal":true,
			"label":"总得分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.part10?0:formDataMap.part10)}
		}
	]