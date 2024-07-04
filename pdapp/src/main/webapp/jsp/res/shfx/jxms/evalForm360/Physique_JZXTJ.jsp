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
			"inputId":"orgName",
			"inputType":"text",
			"label":"所属培训基地",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.orgName?resDoctor.orgName:formDataMap.orgName)}
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
			"value":"15分钟"
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
			"label":"血压计放置位置正确（4分）"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
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
			"label":"血压带绑扎部位正确、松紧度适宜（4分）"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
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
			"label":"听诊器胸件放置位置部位正确（4分）"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
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
			"label":"测量过程流畅（4分）"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
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
			"label":"终结复原（2分）"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
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
			"label":"读数结果（2分）"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
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
			"label":"病人体位和医生站位正确（5分）"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
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
			"label":"观察颈部有无异常隆起，嘱患者做吞咽动作，观察颈部有无有随吞咽上下活动的肿块（8分）"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
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
			"label":"正确完成一种方法得10分；了解2种方法加5分（15分）：医生站位和检查手法正确1）检查者站在患者身后，拇指置于患者颈部，双手示、中、环指置于胸锁乳突肌中点内侧的气管两侧；中指在甲状腺表面轻轻滑动，了解甲状腺情况2）检查者坐在患者面前，双手拇指置于胸锁乳突肌中点的内侧气管的两侧，以一手指将气管推向对侧，另一手扣摸对侧甲状腺。"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "15",
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
			"inputId":"t9",
			"inputType":"title",
			"label":"嘱患者做吞咽动作，了解有无随吞咽动作上下活动的肿块（8分）"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"reason09",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason09)}
		},
		{
			"inputId":"t10",
			"inputType":"title",
			"label":"能提到甲状腺区有无震颤（8分）"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"reason10",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"检查颈部淋巴结：气罐区（颈前区）、胸锁乳突肌内侧深面（胸锁乳突肌区）、胸锁乳突肌外侧锁骨上凹（颈后区）有无肿大淋巴结（8分）"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part11)}
		},
		{
			"inputId":"reason11",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason11)}
		},
		{
			"inputId":"t12",
			"inputType":"title",
			"label":"将听诊器置于甲状腺区仔细听有无血管杂音（8分）"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},
		{
			"inputId":"reason12",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason12)}
		},
		{
			"inputId":"t20",
			"inputType":"title",
			"label":"结果提问：阳性体征分析（5分）"
		},
		{
			"inputId":"part20",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part20)}
		},
		{
			"inputId":"reason20",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason20)}
		},
		{
			"inputId":"t21",
			"inputType":"title",
			"label":"专科检查（10分）检查内容根据病种及考生学科拟定"
		},
		{
			"inputId":"text21",
			"inputType":"text",
			"label":"内容",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.text21)}
		},
		{
			"inputId":"part21",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part21)}
		},
		{
			"inputId":"reason21",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason21)}
		},
		{
			"inputId":"t22",
			"inputType":"title",
			"label":"整体印象（5分）包括熟练程度和时间掌握情况"
		},
		{
			"inputId":"part22",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part22)}
		},
		{
			"inputId":"reason22",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason22)}
		},
		{
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"得分",
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