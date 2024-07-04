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
			"label": "360度评估是一种广泛应用于各行业的评估方法，常用于全方位考核调查对象的具体情况，通常完成360度评估表的评估者是上级同行，下属，病人及亲属等。为全面了解我院住培医师情况，特制定此表。请您用1-5分来表示符合程度，并根据自己了解的情况如实回答，谢谢您的合作。"
		},
		{
			"inputId":"title1",
			"inputType":"title",
			"label": "被评价人情况"
		},
		{
			"inputId":"studentName",
			"inputType":"text",
			"label":"住院医师姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"stuDeptName",
			"inputType":"text",
			"label":"所在科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.stuDeptName?doctorSchProcess.deptName:formDataMap.stuDeptName)}
		},
		{
			"inputId":"year",
			"inputType":"select",
			"label":"培训第几年",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "第一年"
				},
				{
					"optionId": "2",
					"optionDesc": "第二年"
				},
				{
					"optionId": "3",
					"optionDesc": "第三年"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.year)}
		},
		{
			"inputId":"title2",
			"inputType":"title",
			"label":"评价人情况"
		},
		{
			"inputId":"evlUserName",
			"inputType":"text",
			"label":"评价人姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.evlUserName)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"所在科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.deptName)}
		},
		{
			"inputId":"workYear",
			"inputType":"select",
			"label":"工作年限",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1--5年"
				},
				{
					"optionId": "2",
					"optionDesc": "5--10年"
				},
				{
					"optionId": "3",
					"optionDesc": "10年以上"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.workYear)}
		},
		{
			"inputId":"title3",
			"inputType":"title",
			"label":"下表描述医师的行为与其他住培医师相比，请把此住培医师的表现用1-5分体现。"
		},
		{
			"inputId":"part1",
			"inputType":"text",
			"label":"1.在工作中对同事病人表示尊重",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
			"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part1)}
		},
		{
			"inputId":"part2",
			"inputType":"text",
			"label":"2.能够有效的与同事病人交流沟通",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part2)}
		},
		{
			"inputId":"part3",
			"inputType":"text",
			"label":"3.能够协调控制自身压力，不影响工作",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part3)}
		},
		{
			"inputId":"part4",
			"inputType":"text",
			"label":"4.在工作中能够及时完成临床诊疗工作",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part4)}
		},
		{
			"inputId":"part5",
			"inputType":"text",
			"label":"5.注重良好职业形象的树立",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part5)}
		},
		{
			"inputId":"part6",
			"inputType":"text",
			"label":"6.理论知识水平能够应对工作",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part6)}
		},
		{
			"inputId":"part7",
			"inputType":"text",
			"label":"7.操作技能水平能够应对工作",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part7)}
		},
		{
			"inputId":"part8",
			"inputType":"text",
			"label":"8.医师能和护理人员配合完成工作",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part8)}
		},
		{
			"inputId":"part9",
			"inputType":"text",
			"label":"9.该医师建立正确的无菌观念",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part9)}
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"10.该医师能遵守相关制度原则",
			"verify": {
				"max": "5",
				"min": "1",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"isTotal":true,
			"label":"总得分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.part11?0:formDataMap.part11)}
		}
	]