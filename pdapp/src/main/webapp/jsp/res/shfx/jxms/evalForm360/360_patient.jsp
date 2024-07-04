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
			"label":"1、医师姓名",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"seeTimes",
			"inputType":"select",
			"label":"2、去年一年你看这位医师的次数",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1次"
				},
				{
					"optionId": "2",
					"optionDesc": "2-3次"
				},
				{
					"optionId": "3",
					"optionDesc": "超过3次"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.seeTimes)}
		},
		{
			"inputId":"receptionMode",
			"inputType":"select",
			"label":"3、这位医师接待你的方式",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "住院患者"
				},
				{
					"optionId": "2",
					"optionDesc": "门诊患者"
				},
				{
					"optionId": "3",
					"optionDesc": "二者兼有"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.receptionMode)}
		},
		{
			"inputId":"title1",
			"inputType":"title",
			"label":"下表描述你的医师的行为，请用此标准将你的医师的表现填至右侧，依据你看医师时的评价。"
		},
		{
			"inputId":"care1",
			"inputType":"select",
			"label":"A对患者的关怀：1、提高健康维护（谈论有关预防性事宜，如戒烟、控制体重、饮酒、锻炼等等）",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care1)}
		},
		{
			"inputId":"care2",
			"inputType":"select",
			"label":"A对患者的关怀：2、规范地询问我正在服用的（非）处方药",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care2)}
		},
		{
			"inputId":"care3",
			"inputType":"select",
			"label":"A对患者的关怀：3、清楚地解释我的医疗问题",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care3)}
		},
		{
			"inputId":"care4",
			"inputType":"select",
			"label":"A对患者的关怀：4、清楚地解释我的治疗选择",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care4)}
		},
		{
			"inputId":"care5",
			"inputType":"select",
			"label":"A对患者的关怀：5、告诉我药物的副作用",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care5)}
		},
		{
			"inputId":"care6",
			"inputType":"select",
			"label":"A对患者的关怀：6、告诉我何时复诊",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care6)}
		},
		{
			"inputId":"care7",
			"inputType":"select",
			"label":"A对患者的关怀：7、清楚地解释以后如何避免我的问题",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.care7)}
		},
		{
			"inputId":"morality1",
			"inputType":"select",
			"label":"B职业道德：1、对我表示尊重",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.morality1)}
		},
		{
			"inputId":"morality2",
			"inputType":"select",
			"label":"B职业道德：2、对我有礼貌",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.morality2)}
		},
		{
			"inputId":"interpersonal1",
			"inputType":"select",
			"label":"C人际关系和交流能力：1、聆听我说的话",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal1)}
		},
		{
			"inputId":"interpersonal2",
			"inputType":"select",
			"label":"C人际关系和交流能力：2、在我身上花费足够的时间",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal2)}
		},
		{
			"inputId":"interpersonal3",
			"inputType":"select",
			"label":"C人际关系和交流能力：3、对我的问题感兴趣",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal3)}
		},
		{
			"inputId":"interpersonal4",
			"inputType":"select",
			"label":"C人际关系和交流能力：4、彻底回答我的问题",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal4)}
		},
		{
			"inputId":"interpersonal5",
			"inputType":"select",
			"label":"C人际关系和交流能力：5、帮助解决我的恐惧和焦虑",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal5)}
		},
		{
			"inputId":"interpersonal6",
			"inputType":"select",
			"label":"C人际关系和交流能力：6、和我谈论治疗方案",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal6)}
		},
		{
			"inputId":"interpersonal7",
			"inputType":"select",
			"label":"C人际关系和交流能力：7、在合理的时间内回复我的信息",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.interpersonal7)}
		},
		{
			"inputId":"practice1",
			"inputType":"select",
			"label":"D基于系统的实践：1、在需要的时候向专家咨询",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.practice1)}
		},
		{
			"inputId":"practice2",
			"inputType":"select",
			"label":"D基于系统的实践：2、有效利用你的外院资料作为额外信息",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "1",
				"optionDesc": "从不1"
				},
				{
				"optionId": "2",
				"optionDesc": "有时2"
				},
				{
				"optionId": "3",
				"optionDesc": "一般3"
				},
				{
				"optionId": "4",
				"optionDesc": "经常4"
				},
				{
				"optionId": "5",
				"optionDesc": "总是/一直5"
				},
				{
				"optionId": "6",
				"optionDesc": "无法评估6"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.practice2)}
		},
		{
			"inputId":"summary1",
			"inputType":"select",
			"label":"总结：1、我愿意找这位医生看病",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "Y",
				"optionDesc": "是"
				},
				{
				"optionId": "N",
				"optionDesc": "否"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.summary1)}
		},
		{
			"inputId":"summary2",
			"inputType":"select",
			"label":"总结：2、我愿意让我的朋友找这位医生看病",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "Y",
				"optionDesc": "是"
				},
				{
				"optionId": "N",
				"optionDesc": "否"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.summary2)}
		},
		{
			"inputId":"summary3",
			"inputType":"select",
			"label":"总结：3、我对医生很满意",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "Y",
				"optionDesc": "是"
				},
				{
				"optionId": "N",
				"optionDesc": "否"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.summary3)}
		},
		{
			"inputId":"summary4",
			"inputType":"textarea",
			"label":"总结：4、你最喜欢这位医生的什么地方？",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.summary4)}
		},
		{
			"inputId":"summary5",
			"inputType":"textarea",
			"label":"总结：5、你希望你的医生做些什么其他的（不同的）？",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.summary5)}
		},
		{
			"inputId":"summary6",
			"inputType":"textarea",
			"label":"总结：6、其他评价",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.summary6)}
		},
		{
			"inputId":"sex",
			"inputType":"select",
			"label":"你的信息：性别",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
				"optionId": "男",
				"optionDesc": "男"
				},
				{
				"optionId": "女",
				"optionDesc": "女"
				}
			],
			"value":${pdfn:toJsonString(formDataMap.sex)}
		},
		{
			"inputId":"age",
			"inputType":"text",
			"label":"年龄",
			"readonly":${isAudit}, 		"required":true,
			"value":${pdfn:toJsonString(formDataMap.age)}
		}
	]