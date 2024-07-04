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
			"inputId":"orgName",
			"inputType":"text",
			"label":"所属培训基地",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.orgName?resDoctor.orgName:formDataMap.orgName)}
		},
		{
			"inputId":"studentSid",
			"inputType":"text",
			"label":"工号",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.studentSid)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"考核科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.deptName?doctorSchProcess.deptName:formDataMap.deptName)}
		},
		{
			"inputId":"ssmc",
			"inputType":"text",
			"label":"手术、操作名称",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.ssmc)}
		},
		{
			"inputId":"hzxx",
			"inputType":"text",
			"label":"患者姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.hzxx)}
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"患者准备：平卧位，充分暴露手术切口（5分）"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"操作准备：检视伤口，告知患者换药拆线目的；戴好帽子、口罩（头发、鼻子不外露）；洗手（15分）"
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
			"label":"材料准备：两支换药碗、两把镊子、酒精或碘伏棉球、适量的敷料、剪刀或刀片等（10分）"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"操作进行中的无菌操作观念（可提问）（10分）"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"t4",
			"inputType":"title",
			"label":"手术基本技术：揭开胶布：用手移去敷料，如伤口处有粘连的敷料或引流物，用镊子夹起，将其放置在盛污物的换药碗内；观察伤口愈合情况，是否符合拆下条件（10分）"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"t5",
			"inputType":"title",
			"label":"手术基本技术：一把镊子接触伤口，另一把传递换药碗中的清洁物品；操作过程中镊子头部均应低于手持部避免污染（15分）"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "15",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"t6",
			"inputType":"title",
			"label":"手术基本技术：消毒棉球消毒伤口及周围皮肤 2-3 遍（由内向外，消毒范围）；拆线后消毒 1-2 遍（5分）"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"手术基本技术：拆线：持剪及提起线结、拆线的方法（15分）"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "15",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"t8",
			"inputType":"title",
			"label":"手术基本技术：无菌敷料覆盖并固定，粘贴胶布的方向应与躯干长轴垂直（5分）"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"t9",
			"inputType":"title",
			"label":"手术基本技术：爱伤观念、沟通技巧（10分）"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"成绩",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"总分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.skillScore?0:formDataMap.skillScore)}
		},
		{
			"inputId":"teacherName",
			"inputType":"text",
			"label":"考官签名",
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