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
			"label":"考核住院医师",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"考核科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.deptName?doctorSchProcess.deptName:formDataMap.deptName)}
		},
		{
			"inputId":"date",
			"inputType":"date",
			"label":"日期",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.date?pdfn:getCurrDate():formDataMap.date)}
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"病人及物品准备：1、戴帽子、口罩；准备物品，清点物品是否齐全（5 分）"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"病人及物品准备：2、核对病人信息（3 分）；向病人解释滞留目的（5 分）消除紧张感（3 分）"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "11",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"t2",
			"inputType":"title",
			"label":"病人及物品准备：3、根据选择穿刺部位，摆好体位。体位：根据不同的穿刺点，选择不同的体位，颈内及锁骨下静脉穿刺要求垫高肩背部，股静脉穿刺要求患者穿刺侧肢轻度外旋（5 分）"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"消毒铺巾：1、常规消毒术去皮肤 2 遍（5 分）；直径 15cm 逐步减少（3 分）"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"t4",
			"inputType":"title",
			"label":"消毒铺巾：2、打开无菌静脉穿刺包，戴无菌手套（5 分）"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"t5",
			"inputType":"title",
			"label":"消毒铺巾：3、铺无菌洞巾 （3分）"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"t6",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：1、检查器械，包括：穿刺针是否通畅（2 分），并将肝素钠盐水充满导管（2分），抽取利多卡因局麻药（静脉导管包有助手打开，利多卡因安剖瓶有助手打开）（2 分）"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：2、局麻（是否有皮丘），局部浸润麻醉，进针后先回抽（是否回血）（3 分），然后在注射局麻药物，麻醉完毕，后按压少许（局麻过程中有无爱伤观念）（3 分）"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"t8",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：3、试探血管，初学者或者无把握，可在局麻时试探血管位置（3 分）"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"t9",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：4、穿刺针内少许肝素钠盐水，负压进针（2 分），待进入血管后，固定穿刺针（2 分），由助手传递导丝，进导丝时慢，如果不顺可拔出导丝后，适当调整穿刺针位置。（2 分）"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"t10",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：5、导丝进入后，根据不同部位，选择导丝的深度，退穿刺针，注意导丝不要内穿刺针同时带出（4 分）"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：6、扩皮，扩皮时注意出血，有无意识减少出血，并注意不要将导丝带出（4分）"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part11)}
		},
		{
			"inputId":"t12",
			"inputType":"title",
			"label":"穿刺、留置深静脉导管：7、留置导管，根据穿刺部位不同选择导管深度（6 分）"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},
		{
			"inputId":"t14",
			"inputType":"title",
			"label":"穿刺结束：1、肝素钠盐水冲管并肝素帽封管（3分）"
		},
		{
			"inputId":"part14",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part14)}
		},
		{
			"inputId":"t15",
			"inputType":"title",
			"label":"穿刺结束：2、固定导管，包括管道的蝶形夹（3 分）和缝合固定（3 分）"
		},
		{
			"inputId":"part15",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part15)}
		},
		{
			"inputId":"t16",
			"inputType":"title",
			"label":"穿刺结束：3、再次消毒穿刺部位，擦干皮肤，盖辅料（3 分）"
		},
		{
			"inputId":"part16",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part16)}
		},
		{
			"inputId":"t17",
			"inputType":"title",
			"label":"穿刺结束：4、瞩患者注意事项，包括局部运动和皮肤卫生及不适及时通知医护人员（4分）"
		},
		{
			"inputId":"part17",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part17)}
		},
		{
			"inputId":"t18",
			"inputType":"title",
			"label":"提问（7 分）"
		},
		{
			"inputId":"part18",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "7",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part18)}
		},
		{
			"inputId":"t19",
			"inputType":"title",
			"label":"人文关怀：体现在整个操作过程中（5 分）"
		},
		{
			"inputId":"part19",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part19)}
		},
		{
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"总分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.skillScore?100:formDataMap.skillScore)}
		}
	]