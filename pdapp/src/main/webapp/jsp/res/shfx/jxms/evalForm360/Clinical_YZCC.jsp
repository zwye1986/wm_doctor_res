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
			"label":"病人准备：①核对病人信息（2 分）；②向病人解释穿刺目的（2分）；③消除紧张感（2 分）"
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
			"label":"病人准备：①协助病人侧卧于硬板床上（3 分）②背部与床面垂直（3 分）③使病人头向前胸部屈曲（3 分）④两手抱膝紧贴腹部，使躯干成弓形（3 分）⑤脊柱尽量后凸以增宽椎间隙（3 分）"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "15",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part01)}
		},
		{
			"inputId":"t2",
			"inputType":"title",
			"label":"消毒铺巾：定位：①以髂嵴连线与后正中线的交点处为穿刺点（相当于第 3、4 腰椎棘突间隙）（4 分）②也可在上一或下一腰椎见间隙进行（2 分）"
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
			"label":"麻醉穿刺：①检查器械（2 分）②检查穿刺针是否通畅、针芯是否配套（4 分）"
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
			"label":"麻醉穿刺：①左手固定局部皮肤部位，右手持穿刺针以垂直背部刺入（3 分）②针尖可稍倾向头部方向（3 分）③当突然两次突破感（穿过棘间韧带和硬脊膜）后可将针芯慢慢抽出，见脑脊液流出（3 分）"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "9",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part08)}
		},
		{
			"inputId":"t9",
			"inputType":"title",
			"label":"麻醉穿刺：用无菌试管接取缓慢放出的脑脊液 2-5ml（5分）"
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
			"label":"麻醉穿刺：将抽出液送化验（常规、生化、培养及病理）、记量（5分）"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part10)}
		},
		{
			"inputId":"t11",
			"inputType":"title",
			"label":"穿刺结束后处理：①抽完后重新插入针芯②拔出穿刺针③消毒后适当按压后覆盖无菌纱布、稍用力压迫片刻。（6分）"
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
			"label":"穿刺结束后处理：用胶布固定覆盖术口（3分）"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part12)}
		},
		{
			"inputId":"t13",
			"inputType":"title",
			"label":"穿刺结束后处理：术后嘱病人保持去枕平卧体位 4-6 小时（3分）"
		},
		{
			"inputId":"part13",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part13)}
		},
		{
			"inputId":"t14",
			"inputType":"title",
			"label":"穿刺结束后处理：告诉病人有不适立即通知医护人员（2分）"
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
			"label":"穿刺结束后处理：整理物品（3分）"
		},
		{
			"inputId":"part15",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part15)}
		},
		{
			"inputId":"t18",
			"inputType":"title",
			"label":"提问（6 分）"
		},
		{
			"inputId":"part18",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part18)}
		},
		{
			"inputId":"t19",
			"inputType":"title",
			"label":"人文关怀：体现在整个操作过程中（3 分）"
		},
		{
			"inputId":"part19",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
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
		},
		{
			"inputId":"kprqm",
			"inputType":"text",
			"label":"考评人签名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.kprqm)}
		}
	]