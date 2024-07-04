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
			"inputId":"workNumber",
			"inputType":"text",
			"label":"工号",
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
					"optionId": "实习生",
					"optionDesc": "实习生"
				},
				{
					"optionId": "并轨研究生",
					"optionDesc": "并轨研究生"
				}
			]
		},
		{
			"inputId":"doctorType",
			"inputType":"select",
			"label":"人员类型",
			"readonly":${isAudit}, 		"required":false,
			"options":[
				{
					"optionId": "单位人",
					"optionDesc": "单位人"
				},
				{
					"optionId": "社会人",
					"optionDesc": "社会人"
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
			"inputId":"patientDesc",
			"inputType":"text",
			"label":"病人诊断",
			"readonly":${isAudit}, 		"required":true
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
			"inputId":"time",
			"inputType":"select",
			"label":"初复诊",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "初诊",
					"optionDesc": "初诊"
				},
				{
					"optionId": "复诊",
					"optionDesc": "复诊"
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
			"inputId":"bscj",
			"inputType":"text",
			"label":"病史采集",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"zd",
			"inputType":"text",
			"label":"诊断",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"treat",
			"inputType":"text",
			"label":"治疗",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"jkxj",
			"inputType":"text",
			"label":"健康宣教",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"bscjProj",
			"inputType":"select",
			"label":"1、病史采集：系统、完整、突出重点",
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
			"inputId":"tgjc",
			"inputType":"select",
			"label":"2、体格检查：次序、手法正确、适当处理病人及家属的不适感",
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
			"inputId":"yhgt",
			"inputType":"select",
			"label":"3、医患沟通：告知检查目的，建立良好关系与信赖感，适时回答病人提问",
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
			"inputId":"lcpd",
			"inputType":"select",
			"label":"4、临床判断：能综合体查和病史询问内容作出正确判断，选择与执行适当的诊断性检查；考虑治疗方法的风险与利益",
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
			"inputId":"zysy",
			"inputType":"select",
			"label":"5、专业素养：表现尊重、隐私保护、同理心、信任",
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
			"inputId":"zzxn",
			"inputType":"select",
			"label":"6、组织效能：设定轻重缓急；及时且简洁地处理病患事务；具备整合能力；熟悉诊疗常规与操作规程，有效地在系统中利用其他资源以提供最佳医疗服务。",
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
			"label":"7、整体表现：对于病人照护的效率上表现出判断力、整合力、有效性；判断力、整合力、爱心、有效率等整体评量",
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
			"label":"教师对测评满意度",
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
			"label":"学员对测评满意度",
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
			"inputId":"workNumber",
			"value":${pdfn:toJsonString(formDataMap.workNumber)}
		},
		{
			"inputId":"doctorType",
			"value":${pdfn:toJsonString(formDataMap.doctorType)}
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
			"inputId":"patientDesc",
			"value":${pdfn:toJsonString(formDataMap.patientDesc)}
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
			"inputId":"time",
			"value":${pdfn:toJsonString(formDataMap.time)}
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
			"inputId":"bscj",
			"value":${pdfn:toJsonString(formDataMap.bscj)}
		},
		{
			"inputId":"zd",
			"value":${pdfn:toJsonString(formDataMap.zd)}
		},
		{
			"inputId":"treat",
			"value":${pdfn:toJsonString(formDataMap.treat)}
		},
		{
			"inputId":"jkxj",
			"value":${pdfn:toJsonString(formDataMap.jkxj)}
		},
		{
			"inputId":"bscjProj",
			"value":${pdfn:toJsonString((empty formDataMap.bscjProj && empty formDataMap.bscjProjFlag)?'':(empty formDataMap.bscjProj?'否':formDataMap.bscjProj))}
		},
		{
			"inputId":"tgjc",
			"value":${pdfn:toJsonString((empty formDataMap.tgjc && empty formDataMap.tgjcFlag)?'':(empty formDataMap.tgjc?'否':formDataMap.tgjc))}
		},
		{
			"inputId":"yhgt",
			"value":${pdfn:toJsonString((empty formDataMap.yhgt && empty formDataMap.yhgtFlag)?'':(empty formDataMap.yhgt?'否':formDataMap.yhgt))}
		},
		{
			"inputId":"lcpd",
			"value":${pdfn:toJsonString((empty formDataMap.lcpd && empty formDataMap.lcpdFlag)?'':(empty formDataMap.lcpd?'否':formDataMap.lcpd))}
		},
		{
			"inputId":"zysy",
			"value":${pdfn:toJsonString((empty formDataMap.zysy && empty formDataMap.zysyFlag)?'':(empty formDataMap.zysy?'否':formDataMap.zysy))}
		},
		{
			"inputId":"zzxn",
			"value":${pdfn:toJsonString((empty formDataMap.zzxn && empty formDataMap.zzxnFlag)?'':(empty formDataMap.zzxn?'否':formDataMap.zzxn))}
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