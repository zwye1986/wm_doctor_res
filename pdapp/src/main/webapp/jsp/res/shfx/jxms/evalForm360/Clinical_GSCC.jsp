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
			"label":"病人准备：①核对病人信息（2 分）；②向病人解释穿刺目的（2分）；③消除紧张感、协助患者准备（2 分）"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"病人准备：根据定位点病人应取仰卧位或侧卧位（4分）"
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
			"inputId":"t2",
			"inputType":"title",
			"label":"消毒铺巾：定位：髂前上棘穿刺点位于髂前上棘后 1-2cm（6分）"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"消毒铺巾：①常规消毒术区 3 次（3 分）；②直径 15cm 逐步缩小（3 分）"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part03)}
		},
		{
			"inputId":"t4",
			"inputType":"title",
			"label":"消毒铺巾：戴无菌①帽子②口罩③手套（5分）"
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
			"label":"消毒铺巾：铺无菌洞巾。（由助手固定，请考官替代）（5分）"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part05)}
		},
		{
			"inputId":"t6",
			"inputType":"title",
			"label":"麻醉穿刺：①检查器械（2 分）②检查穿刺针是否通畅（2 分）③针芯是否配套（2 分）④将骨髓穿刺针固定在适当的长度上（髂骨 1.5cm，胸骨 1cm）（2 分）"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"麻醉穿刺：①核对局麻药物名称（3 分）②用 2%利多卡因局部麻醉（3 分）"
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
			"label":"麻醉穿刺：①左手拇指和食指固定穿刺部位，右手持骨髓穿刺针与骨面垂直刺入（5 分）②当穿刺针针尖接触坚硬的骨质后，沿穿刺针的针体长轴左右旋转穿刺针，并向前推进，缓缓刺入骨质（5 分）③当突然感到穿刺阻力消失，且穿刺针已固定在骨内时，表示穿刺针已进入骨髓腔（5 分）"
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
			"inputId":"t9",
			"inputType":"title",
			"label":"麻醉穿刺：①拔出穿刺针针芯（3 分）②并见针芯带有血迹，接上干燥灭菌注射器（3 分）③用适当的力量抽取骨髓液 0.1-0.2ml（3 分）"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "9",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"t10",
			"inputType":"title",
			"label":"麻醉穿刺：①将注射器水平移至载玻片上方（2 分）②迅速将骨髓液滴在载玻片上（2 分）③立即涂片（2 分）"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"穿刺结束：①抽完后重新插入针芯（2 分）②拔出穿刺针（2 分）③消毒后适当按压后覆盖无菌纱布（2 分）"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part11)}
		},
		{
			"inputId":"t12",
			"inputType":"title",
			"label":"穿刺结束：送血涂片送检（常规、生化、培养、病理及其他）（5分）"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},{
			"inputId":"t13",
			"inputType":"title",
			"label":"穿刺结束：嘱病人静卧、告诉病人有不适立即通知医护人员（2分）"
		},
		{
			"inputId":"part13",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part13)}
		},
		{
			"inputId":"t14",
			"inputType":"title",
			"label":"穿刺结束：整理物品（2分）"
		},
		{
			"inputId":"part14",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part14)}
		},
		{
			"inputId":"t15",
			"inputType":"title",
			"label":"提问（6 分）"
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
			"label":"人文关怀：体现在整个操作过程中（3 分）"
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
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"总分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.skillScore?100:formDataMap.skillScore)}
		}
	]