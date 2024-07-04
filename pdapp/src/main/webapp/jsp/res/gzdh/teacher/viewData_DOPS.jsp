<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"inputs":[
		{
			"inputId":"teacher",
			"inputType":"text",
			"label":"教师",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"titleName",
			"inputType":"select",
			"label":"教师职称",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "主任医师",
					"optionDesc": "主任医师"
				},
				{
					"optionId": "副主任医师",
					"optionDesc": "副主任医师"
				},
				{
				"optionId": "主治医师",
				"optionDesc": "主治医师"
				},
				{
				"optionId": "高年资住院医师",
				"optionDesc": "高年资住院医师"
				}
			]
		},
		{
			"inputId":"doctor",
			"inputType":"text",
			"label":"学员姓名",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"spe",
			"inputType":"text",
			"label":"专业",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"trainType",
			"inputType":"select",
			"label":"学员类型",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "住院医师",
					"optionDesc": "住院医师"
				},
				{
					"optionId": "硕士研究生",
					"optionDesc": "硕士研究生"
				},
				{
					"optionId": "博士研究生",
					"optionDesc": "博士研究生"
				},
				{
					"optionId": "实习医师",
					"optionDesc": "实习医师"
				},
				{
					"optionId": "其他",
					"optionDesc": "其他"
				}
			]
		},
		{
			"inputId":"year",
			"inputType":"text",
			"label":"住院医师第几年",
			"readonly":${isAudit}, 		"required":false
		},
		{
			"inputId":"operateName",
			"inputType":"text",
			"label":"临床操作名称",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"operateLevel",
			"inputType":"select",
			"label":"临床操作难度",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "简易",
					"optionDesc": "简易"
				},
				{
					"optionId": "一般",
					"optionDesc": "一般"
				},
				{
					"optionId": "困难",
					"optionDesc": "困难"
				}
			]
		},
		{
			"inputId":"assessTime",
			"inputType":"date",
			"label":"评估时间",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"assessPlace",
			"inputType":"select",
			"label":"评估地点",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "病房",
					"optionDesc": "病房"
				},
				{
					"optionId": "门诊",
					"optionDesc": "门诊"
				},
				{
					"optionId": "急诊",
					"optionDesc": "急诊"
				},
				{
					"optionId": "ICU",
					"optionDesc": "ICU"
				},
				{
					"optionId": "其他",
					"optionDesc": "其他"
				}
			]
		},
		{
			"inputId":"age",
			"inputType":"text",
			"label":"病人年龄",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"sex",
			"inputType":"select",
			"label":"性别",
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
			]
		},
		{
			"inputId":"xgzs",
			"inputType":"select",
			"label":"1、相关知识：操作适应症、禁忌症等",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"czqdzqty",
			"inputType":"select",
			"label":"2、操作前的知情同意",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"sqzb",
			"inputType":"select",
			"label":"3、术前准备",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"jnsld",
			"inputType":"select",
			"label":"4、技能熟练度/技术",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"wjgn",
			"inputType":"select",
			"label":"5、无菌观念",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"czhcl",
			"inputType":"select",
			"label":"6、操作完成后的处理：安全地置放医疗器材；记录程序，样本的标记和术后教育；安排适当的后续医疗措施。",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"gtjq",
			"inputType":"select",
			"label":"7、沟通技巧：探究病患的观点；不使用专业术语；开放和诚实；同理心；与病患共同决定病患的医疗处置计划。",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"zysyjrw",
			"inputType":"select",
			"label":"8、专业素养及人文：展现尊重、同情、同理，建立信任感；致力于病患的舒适感需求；尊重个人资料保密性；行为合乎伦理标准；体察法律体制；体察个人能力的极限。",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"ssxqxz",
			"inputType":"select",
			"label":"9、适时寻求协助",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"ztbx",
			"inputType":"select",
			"label":"10、整体表现",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			],
			"assessShowFlag":true
		},
		{
			"inputId":"seeTime",
			"inputType":"text",
			"label":"观察时间",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"backTime",
			"inputType":"text",
			"label":"反馈时间",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"teacherFeel",
			"inputType":"select",
			"label":"教师对此次测评满意程度",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			]
		},
		{
			"inputId":"doctorFeel",
			"inputType":"select",
			"label":"学员对此次测评满意程度",
			"readonly":${isAudit},
			"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "1"
				},
				{
					"optionId": "2",
					"optionDesc": "2"
				},
				{
					"optionId": "3",
					"optionDesc": "3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				},
				{
					"optionId": "5",
					"optionDesc": "5"
				},
				{
					"optionId": "6",
					"optionDesc": "6"
				},
				{
					"optionId": "7",
					"optionDesc": "7"
				},
				{
					"optionId": "8",
					"optionDesc": "8"
				},
				{
					"optionId": "9",
					"optionDesc": "9"
				}
			]
		},
		{
			"inputId":"bxlhxm",
			"inputType":"text",
			"label":"表现良好项目",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"jyjqxm",
			"inputType":"text",
			"label":"建议加强项目",
			"readonly":${isAudit}, 		"required":true
		}
	]
	,
	"values":[
		{
			"inputId":"teacher",
			"value":${pdfn:toJsonString(formDataMap.teacher)}
		},
		{
			"inputId":"titleName",
			"value":${pdfn:toJsonString(formDataMap.titleName)}
		},
		{
			"inputId":"doctor",
			"value":${pdfn:toJsonString(formDataMap.doctor)}
		},
		{
			"inputId":"spe",
			"value":${pdfn:toJsonString(formDataMap.spe)}
		},
		{
			"inputId":"year",
			"value":${pdfn:toJsonString(formDataMap.year)}
		},
		{
			"inputId":"trainType",
			"value":${pdfn:toJsonString(formDataMap.trainType)}
		},
		{
			"inputId":"operateName",
			"value":${pdfn:toJsonString(formDataMap.operateName)}
		},
		{
			"inputId":"operateLevel",
			"value":${pdfn:toJsonString(formDataMap.operateLevel)}
		},
		{
			"inputId":"assessTime",
			"value":${pdfn:toJsonString(formDataMap.assessTime)}
		},
		{
			"inputId":"assessPlace",
			"value":${pdfn:toJsonString(formDataMap.assessPlace)}
		},
		{
			"inputId":"age",
			"value":${pdfn:toJsonString(formDataMap.age)}
		},
		{
			"inputId":"sex",
			"value":${pdfn:toJsonString(formDataMap.sex)}
		},
		{
			"inputId":"xgzs",
			"value":${pdfn:toJsonString((empty formDataMap.xgzs && empty formDataMap.xgzsFlag)?'':(empty formDataMap.xgzs?'否':formDataMap.xgzs))}
		},
		{
			"inputId":"czqdzqty",
			"value":${pdfn:toJsonString((empty formDataMap.czqdzqty && empty formDataMap.czqdzqtyFlag)?'':(empty formDataMap.czqdzqty?'否':formDataMap.czqdzqty))}
		},
		{
			"inputId":"sqzb",
			"value":${pdfn:toJsonString((empty formDataMap.sqzb && empty formDataMap.sqzbFlag)?'':(empty formDataMap.sqzb?'否':formDataMap.sqzb))}
		},
		{
			"inputId":"jnsld",
			"value":${pdfn:toJsonString((empty formDataMap.jnsld && empty formDataMap.jnsldFlag)?'':(empty formDataMap.jnsld?'否':formDataMap.jnsld))}
		},
		{
			"inputId":"wjgn",
			"value":${pdfn:toJsonString((empty formDataMap.wjgn && empty formDataMap.wjgnFlag)?'':(empty formDataMap.wjgn?'否':formDataMap.wjgn))}
		},
		{
			"inputId":"czhcl",
			"value":${pdfn:toJsonString((empty formDataMap.czhcl && empty formDataMap.czhclFlag)?'':(empty formDataMap.czhcl?'否':formDataMap.czhcl))}
		},
		{
			"inputId":"gtjq",
			"value":${pdfn:toJsonString((empty formDataMap.gtjq && empty formDataMap.gtjqFlag)?'':(empty formDataMap.gtjq?'否':formDataMap.gtjq))}
		},
		{
			"inputId":"zysyjrw",
			"value":${pdfn:toJsonString((empty formDataMap.zysyjrw && empty formDataMap.zysyjrwFlag)?'':(empty formDataMap.zysyjrw?'否':formDataMap.zysyjrw))}
		},
		{
			"inputId":"ssxqxz",
			"value":${pdfn:toJsonString((empty formDataMap.ssxqxz && empty formDataMap.ssxqxzFlag)?'':(empty formDataMap.ssxqxz?'否':formDataMap.ssxqxz))}
		},
		{
			"inputId":"ztbx",
			"value":${pdfn:toJsonString((empty formDataMap.ztbx && empty formDataMap.ztbxFlag)?'':(empty formDataMap.ztbx?'否':formDataMap.ztbx))}
		},
		{
			"inputId":"seeTime",
			"value":${pdfn:toJsonString(formDataMap.seeTime)}
		},
		{
			"inputId":"backTime",
			"value":${pdfn:toJsonString(formDataMap.backTime)}
		},
		{
			"inputId":"teacherFeel",
			"value":${pdfn:toJsonString(formDataMap.teacherFeel)}
		},
		{
			"inputId":"doctorFeel",
			"value":${pdfn:toJsonString(formDataMap.doctorFeel)}
		},
		{
			"inputId":"bxlhxm",
			"value":${pdfn:toJsonString(formDataMap.bxlhxm)}
		},
		{
			"inputId":"jyjqxm",
			"value":${pdfn:toJsonString(formDataMap.jyjqxm)}
		}
	]