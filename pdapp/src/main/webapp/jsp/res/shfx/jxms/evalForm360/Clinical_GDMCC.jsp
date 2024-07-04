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
			"inputId":"studentSid",
			"inputType":"text",
			"label":"工号",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.studentSid)}
		},
		{
			"inputId":"speName",
			"inputType":"text",
			"label":"专业",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.speName)}
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"考核科室",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.deptName?doctorSchProcess.deptName:formDataMap.deptName)}
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"适应症：1、需采集动脉血液标本或某些特殊检查（2 分）漏一项扣1分"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part00)}
		},
		{
			"inputId":"t1",
			"inputType":"title",
			"label":"适应症：2.急救时需加压输血输液（2分）漏一项扣1分"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"t2",
			"inputType":"title",
			"label":"适应症：3、用于区域性化疗（2分）漏一项扣1分"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part02)}
		},
		{
			"inputId":"t3",
			"inputType":"title",
			"label":"手术前准备：1、了解、熟悉病人病情。与病人或家属谈话，做好解释工作，争取清醒病人配合（8分）未与患者或家属沟通扣8分，沟通不佳，酌情扣分"
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
			"label":"手术前准备：2、备皮（6分）选股动脉未备皮，扣2分"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part04)}
		},
		{
			"inputId":"t5",
			"inputType":"title",
			"label":"手术前准备：3、物品准备：无菌持物钳浸于消毒溶液罐内，75%酒精或 10%碘伏消毒液，无菌纱布及罐、消毒棉签，0.1%肾上腺素、笔、砂轮，注射器、针头、抗凝剂、试管、弯盘: ,注射器针头回收器，需要时准备输液或输血用物（10分）漏一项扣1分，扣完为止"
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
			"inputId":"t6",
			"inputType":"title",
			"label":"操作方法：1、准备洗手、戴口罩帽子（3分）未戴口罩帽子扣1分，未洗手扣1分"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part06)}
		},
		{
			"inputId":"t7",
			"inputType":"title",
			"label":"操作方法：2、备齐用物携至床旁，查对床号、姓名治疗项目等，向患者或家属解释股动脉注射的目的、方法（5 分）漏一项扣1分"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part07)}
		},
		{
			"inputId":"t8",
			"inputType":"title",
			"label":"操作方法：3、协助病人取仰卧位、下肢伸直略外展外旋（5分）体位不当扣5分"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"t9",
			"inputType":"title",
			"label":"操作方法：4、检查注射器的包装、有效期等，再次插队，常规消毒穿刺位皮肤（5分）未检查核对扣5分"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part09)}
		},
		{
			"inputId":"t10",
			"inputType":"title",
			"label":"操作方法：5、术者消毒左手中指和食指，在腹股沟韧带下方内侧，左手食指和中指触及股功脉搏动最明显处并固定，右乎持注 JfJ 器垂直刺入动脉或者与动脉走向呈 40 度角刺入。见回血后用右手固定注射器，左手抽动活塞，按需要采集标本或者接上输血输液器（30分）定位欠佳，多次定位扣5分;未戴手套或手消毒扣10分;反复穿刺扣5分;置管穿刺扣5分;据熟练程度适当扣分"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "30",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"操作方法：6、抽血或输入完毕，迅速拔针，局部用 3-5根消毒棉签或纱布加压按5分钟以上（10分）形成血肿扣5分"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part11)}
		},
		{
			"inputId":"t12",
			"inputType":"title",
			"label":"操作方法：7、协助病人取舒适卧位，整理用物（10分）漏做此项扣10分"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "10",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},{
			"inputId":"t13",
			"inputType":"title",
			"label":"操作方法：8、消毒洗手（2分）未消毒洗手扣2分"
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
			"inputId":"part14",
			"inputType":"text",
			"label":"总扣分",
			"verify": {
				"max": "100",
				"type": "float"
			},
"scoreType":"totalSubtract",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part14)}
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
			"inputId":"teacherName",
			"inputType":"text",
			"label":"评卷人",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.teacherName)}
		}
	]