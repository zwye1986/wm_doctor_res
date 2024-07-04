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
			"inputId":"hzxx",
			"inputType":"text",
			"label":"患者姓名、床号及住院号",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.hzxx)}
		},
		{
			"inputId":"ssmc",
			"inputType":"text",
			"label":"手术名称",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.ssmc)}
		},
		{
			"inputId":"khsj_label",
			"inputType":"text",
			"label":"时间",
			"readonly":true,
			"value":"视手术情况而定"
		},
		{
			"inputId":"mf_label",
			"inputType":"text",
			"label":"满分",
			"readonly":true,
			"value":"100分"
		},
		{
			"inputId":"title0",
			"inputType":"title",
			"label":"一、无菌操作（40 分）"
		},
		{
			"inputId":"t0",
			"inputType":"title",
			"label":"刷手：刷手顺序及范围（分段交替向上） 2分"
		},
		{
			"inputId":"part00",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"刷手：刷手重点部位（6 步洗手法） 2分"
		},
		{
			"inputId":"part01",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"刷手：冲洗时顺序及手臂保护 2分"
		},
		{
			"inputId":"part02",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"刷手：擦手臂时无菌操作 2分"
		},
		{
			"inputId":"part03",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"刷手：刷手后是否接触了有菌物品及处理 1分"
		},
		{
			"inputId":"part04",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "1",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"刷手：刷手时间 1分"
		},
		{
			"inputId":"part05",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "1",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"穿衣：提衣动作 2分"
		},
		{
			"inputId":"part06",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"穿衣：递腰动作 2分"
		},
		{
			"inputId":"part07",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"穿衣：手是否接触有菌区 2分"
		},
		{
			"inputId":"part08",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"穿衣：穿衣时置手规范 2分"
		},
		{
			"inputId":"part09",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"戴手套：提取手套 2分"
		},
		{
			"inputId":"part10",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"戴手套：戴手套时无菌概念 2分"
		},
		{
			"inputId":"part11",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"label":"戴手套：手套腕部外翻部位 2分"
		},
		{
			"inputId":"part12",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
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
			"inputId":"t13",
			"inputType":"title",
			"label":"戴手套：手套扎手术衣袖口 2分"
		},
		{
			"inputId":"part13",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part13)}
		},
		{
			"inputId":"reason13",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason13)}
		},
		{
			"inputId":"t14",
			"inputType":"title",
			"label":"消毒及铺巾：消毒钳持拿 2分"
		},
		{
			"inputId":"part14",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part14)}
		},
		{
			"inputId":"reason14",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason14)}
		},
		{
			"inputId":"t15",
			"inputType":"title",
			"label":"消毒及铺巾：消毒顺序和范围 2分"
		},
		{
			"inputId":"part15",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part15)}
		},
		{
			"inputId":"reason15",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason15)}
		},
		{
			"inputId":"t16",
			"inputType":"title",
			"label":"消毒及铺巾：铺巾顺序、铺巾后有无移动 2分"
		},
		{
			"inputId":"part16",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part16)}
		},
		{
			"inputId":"reason16",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason16)}
		},
		{
			"inputId":"t17",
			"inputType":"title",
			"label":"手术进行中的无菌操作观念（十项要求、可提问） 8分"
		},
		{
			"inputId":"part17",
			"inputType":"text",
			"label":"得分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"add",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.part17)}
		},
		{
			"inputId":"reason17",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason17)}
		},
		{
			"inputId":"title1",
			"inputType":"title",
			"label":"二、手术基本技术——切开、缝合、结扎、止血（满分 60分）"
		},
		{
			"inputId":"t21",
			"inputType":"title",
			"label":"切开：安装刀片的方法 2分"
		},
		{
			"inputId":"score1",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score1)}
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
			"label":"切开：执刀姿势 2分"
		},
		{
			"inputId":"score2",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score2)}
		},
		{
			"inputId":"reason22",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason22)}
		},
		{
			"inputId":"t23",
			"inputType":"title",
			"label":"切开：切开操作规范（皮肤紧绷、按层切开）（可用电刀） 4分"
		},
		{
			"inputId":"score3",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score3)}
		},
		{
			"inputId":"reason23",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason23)}
		},
		{
			"inputId":"t24",
			"inputType":"title",
			"label":"切开：切开的深浅度 2分"
		},
		{
			"inputId":"score4",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score4)}
		},
		{
			"inputId":"reason24",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason24)}
		},
		{
			"inputId":"t25",
			"inputType":"title",
			"label":"打结：递线及绕线方法 2分"
		},
		{
			"inputId":"score5",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "2",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score5)}
		},
		{
			"inputId":"reason25",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason25)}
		},
		{
			"inputId":"t26",
			"inputType":"title",
			"label":"打结：打结手法（是否有滑结、假结） 4分"
		},
		{
			"inputId":"score6",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score6)}
		},
		{
			"inputId":"reason26",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason26)}
		},
		{
			"inputId":"t27",
			"inputType":"title",
			"label":"打结：打结质量（牢靠、松紧等） 3分"
		},
		{
			"inputId":"score7",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score7)}
		},
		{
			"inputId":"reason27",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason27)}
		},
		{
			"inputId":"t28",
			"inputType":"title",
			"label":"打结：打结速度 1分"
		},
		{
			"inputId":"score8",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "1",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score8)}
		},
		{
			"inputId":"reason28",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason28)}
		},
		{
			"inputId":"t29",
			"inputType":"title",
			"label":"剪（拆）线：持剪（及提起线结）的方法 3分"
		},
		{
			"inputId":"score9",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score9)}
		},
		{
			"inputId":"reason29",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason29)}
		},
		{
			"inputId":"t30",
			"inputType":"title",
			"label":"剪（拆）线：剪（拆）线方法（”靠、滑、侧、剪”等） 4分"
		},
		{
			"inputId":"score10",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "4",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score10)}
		},
		{
			"inputId":"reason30",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason30)}
		},
		{
			"inputId":"t31",
			"inputType":"title",
			"label":"剪（拆）线：线尾留长 3分"
		},
		{
			"inputId":"score11",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score11)}
		},
		{
			"inputId":"reason31",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason31)}
		},
		{
			"inputId":"t32",
			"inputType":"title",
			"label":"缝合：合理选择器材 3分"
		},
		{
			"inputId":"score12",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score12)}
		},
		{
			"inputId":"reason32",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason32)}
		},
		{
			"inputId":"t33",
			"inputType":"title",
			"label":"缝合：夹针方法及熟练程度 3分"
		},
		{
			"inputId":"score13",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "3",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score13)}
		},
		{
			"inputId":"reason33",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason33)}
		},
		{
			"inputId":"t34",
			"inputType":"title",
			"label":"缝合：缝合技巧（进针、出针、针距、边距等） 8分"
		},
		{
			"inputId":"score14",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "8",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score14)}
		},
		{
			"inputId":"reason34",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason34)}
		},
		{
			"inputId":"t35",
			"inputType":"title",
			"label":"缝合：常用缝合方法掌握情况 6分"
		},
		{
			"inputId":"score15",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "6",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score15)}
		},
		{
			"inputId":"reason35",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason35)}
		},
		{
			"inputId":"t36",
			"inputType":"title",
			"label":"爱伤观念（5 分）"
		},
		{
			"inputId":"score16",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score16)}
		},
		{
			"inputId":"reason36",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason36)}
		},
		{
			"inputId":"t37",
			"inputType":"title",
			"label":"沟通技能（5 分）"
		},
		{
			"inputId":"score17",
			"inputType":"text",
			"label":"扣分",
			"verify": {
				"max": "5",
				"type": "float"
			},
"scoreType":"subtract",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.score17)}
		},
		{
			"inputId":"reason37",
			"inputType":"text",
			"label":"扣分理由",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.reason37)}
		},
		{
			"inputId":"skillScore",
			"inputType":"text",
"isTotal":true,
			"label":"得分",
			"readonly":true, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.skillScore?60:formDataMap.skillScore)}
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