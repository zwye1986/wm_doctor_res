<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		
		{
			"inputId":"name",
			"inputType":"text",
			"label":"学员姓名",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"sessional",
			"inputType":"text",
			"label":"届别：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"trainMajor",
			"inputType":"text",
			"label":"培训专业",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"轮转科室名称",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"cycleTimeQ",
			"inputType":"date",
			"label":"轮转开始时间",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"cycleTimeH",
			"inputType":"date",
			"label":"轮转结束时间",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"kaoqing",
			"inputType":"date",
			"label":"考勤",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"attendance",
			"inputType":"date",
			"label":"全勤",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"leave",
			"inputType":"date",
			"label":"事假",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"sickLeave",
			"inputType":"date",
			"label":"病假",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"materLeave",
			"inputType":"date",
			"label":"产假",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"absenteeism",
			"inputType":"date",
			"label":"旷工",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"zsgjflfg",
			"inputType":"select",
			"label":"遵守国家法律法规、医院规章制度",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"lxgwzz",
			"inputType":"select",
			"label":"履行岗位职责",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"ybrwzx",
			"inputType":"select",
			"label":"以病人为中心，体现人文关怀",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"rjgtbdnl",
			"inputType":"select",
			"label":"人际（医患）沟通和表达能力",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"tjxzjsxm",
			"inputType":"select",
			"label":"团结协作精神",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"jbllzwcd",
			"inputType":"select",
			"label":"临床基本知识、基本理论掌握程度",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"njbjnzwcd",
			"inputType":"select",
			"label":"临床基本技能掌握程度",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"lcswnl",
			"inputType":"select",
			"label":"临床思维能力",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"lczlnl",
			"inputType":"select",
			"label":"临床诊疗能力",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"jjclnl",
			"inputType":"select",
			"label":"危重病人的识别及紧急处理能力",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "1",
					"optionDesc": "优"
				},
				{
					"optionId": "2",
					"optionDesc": "良"
				},
				{
					"optionId": "3",
					"optionDesc": "中"
				},
				{
					"optionId": "4",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"blsywc",
			"inputType":"text",
			"label":"病历应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"blsyjwc",
			"inputType":"text",
			"label":"病历已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"blswcbl",
			"inputType":"text",
			"label":"病历完成比例",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"bzsywc",
			"inputType":"text",
			"label":"病种应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"bzsyjwc",
			"inputType":"text",
			"label":"病种已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"bzswcbl",
			"inputType":"text",
			"label":"病种完成比例",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"czsywc",
			"inputType":"text",
			"label":"操作应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"czsyjwc",
			"inputType":"text",
			"label":"操作已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"czswcbl",
			"inputType":"text",
			"label":"操作完成比例",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"blsywc",
			"inputType":"text",
			"label":"参加手术应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"blsyjwc",
			"inputType":"text",
			"label":"参加手术已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"blswcbl",
			"inputType":"select",
			"label":"参加手术完成比例",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"jxcb",
			"inputType":"text",
			"label":"教学查房",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"nwzbltl",
			"inputType":"text",
			"label":"疑难、危重病例讨论",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"xsjz",
			"inputType":"text",
			"label":"学术讲座",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"swbltl",
			"inputType":"text",
			"label":"死亡病例讨论",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"theoreResult",
			"inputType":"text",
			"label":"理论成绩",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"skillName",
			"inputType":"text",
			"label":"技能考核名称",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"score",
			"inputType":"text",
			"label":"得分",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"examiner1",
			"inputType":"text",
			"label":"考官1",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"examiner2",
			"inputType":"text",
			"label":"考官2",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"theoreResult",
			"inputType":"text",
			"label":"理论成绩",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"szkskhxzztpj",
			"inputType":"select",
			"label":"所在科室考核小组总体评价",
			"tip":"所在科室考核小组总体评价",
			"readonly":${isAudit}, 		"required":true,	
			"options":[
				{
					"optionId": "1",
					"optionDesc": "通过"
				},
				{
					"optionId": "0",
					"optionDesc": "不通过"
				}
			]
		}
		,
		{
			"inputId":"teacherSign",
			"inputType":"text",
			"label":"带教老师签名",
			"readonly":${isAudit}, 		"required":true
		}
		,
		{
			"inputId":"teacherDate",
			"inputType":"text",
			"label":"带教老师签名日期",
			"readonly":${isAudit}, 		"required":true
		}
		,
		{
			"inputId":"directorName",
			"inputType":"text",
			"label":"科主任签名",
			"readonly":${isAudit}, 		"required":true
		}
		,
		{
			"inputId":"directorDate",
			"inputType":"text",
			"label":"科主任签名日期",
			"readonly":${isAudit}, 		"required":true
		}
		
	]
	,
	"values":[
		{
			"inputId":"name",
			"value":${pdfn:toJsonString(empty formDataMap.name?resDoctor.doctorName:formDataMap.name)}
		},
		{
			"inputId":"sessional",
			"value":${pdfn:toJsonString(empty formDataMap.sessional?resDoctor.sessionNumber:formDataMap.sessional)}
		},
		{
			"inputId":"trainMajor",
			"value":${pdfn:toJsonString(empty formDataMap.trainMajor?resDoctor.trainingSpeName:formDataMap.trainMajor)}
		},
		{
			"inputId":"deptName",
			"value":${pdfn:toJsonString(formDataMap.deptName)}
		},
		{
			"inputId":"cycleTimeQ",
			"value":${pdfn:toJsonString(formDataMap.cycleTimeQ)}
		},
		{
			"inputId":"cycleTimeH",
			"value":${pdfn:toJsonString(formDataMap.cycleTimeH)}
		},
		{
			"inputId":"kaoqing",
			"value":${pdfn:toJsonString(formDataMap.kaoqing)}
		},
		{
			"inputId":"attendance",
			"value":${pdfn:toJsonString(formDataMap.attendance)}
		},
		{
			"inputId":"leave",
			"value":${pdfn:toJsonString(formDataMap.leave)}
		},
		{
			"inputId":"sickLeave",
			"value":${pdfn:toJsonString(formDataMap.sickLeave)}
		},
		{
			"inputId":"materLeave",
			"value":${pdfn:toJsonString(formDataMap.materLeave)}
		},
		{
			"inputId":"absenteeism",
			"value":${pdfn:toJsonString(formDataMap.absenteeism)}
		},
		{
			"inputId":"zsgjflfg",
			"value":${pdfn:toJsonString(formDataMap.zsgjflfg_id)}
		},
		{
			"inputId":"lxgwzz",
			"value":${pdfn:toJsonString(formDataMap.lxgwzz_id)}
		},
		{
			"inputId":"ybrwzx",
			"value":${pdfn:toJsonString(formDataMap.ybrwzx_id)}
		},
		{
			"inputId":"rjgtbdnl",
			"value":${pdfn:toJsonString(formDataMap.rjgtbdnl_id)}
		},
		{
			"inputId":"tjxzjsxm",
			"value":${pdfn:toJsonString(formDataMap.tjxzjsxm_id)}
		},
		{
			"inputId":"jbllzwcd",
			"value":${pdfn:toJsonString(formDataMap.jbllzwcd_id)}
		},
		{
			"inputId":"njbjnzwcd",
			"value":${pdfn:toJsonString(formDataMap.njbjnzwcd_id)}
		},
		{
			"inputId":"lcswnl",
			"value":${pdfn:toJsonString(formDataMap.lcswnl_id)}
		},
		{
			"inputId":"lczlnl",
			"value":${pdfn:toJsonString(formDataMap.lczlnl_id)}
		},
		{
			"inputId":"jjclnl",
			"value":${pdfn:toJsonString(formDataMap.jjclnl_id)}
		},
		{
			"inputId":"blsywc",
			"value":${pdfn:toJsonString(formDataMap.blsywc)}
		},
		{
			"inputId":"blsyjwc",
			"value":${pdfn:toJsonString(formDataMap.blsyjwc)}
		},
		{
			"inputId":"blswcbl",
			"value":${pdfn:toJsonString(formDataMap.blswcbl)}
		},
		{
			"inputId":"bzsywc",
			"value":${pdfn:toJsonString(formDataMap.bzsywc)}
		},
		{
			"inputId":"bzsyjwc",
			"value":${pdfn:toJsonString(formDataMap.bzsyjwc)}
		},
		{
			"inputId":"bzswcbl",
			"value":${pdfn:toJsonString(formDataMap.bzswcbl)}
		},
		{
			"inputId":"czsywc",
			"value":${pdfn:toJsonString(formDataMap.czsywc)}
		},
		{
			"inputId":"czsyjwc",
			"value":${pdfn:toJsonString(formDataMap.czsyjwc)}
		},
		{
			"inputId":"czswcbl",
			"value":${pdfn:toJsonString(formDataMap.czswcbl)}
		},
		{
			"inputId":"blsywc",
			"value":${pdfn:toJsonString(formDataMap.blsywc)}
		},
		{
			"inputId":"blsyjwc",
			"value":${pdfn:toJsonString(formDataMap.blsyjwc)}
		},
		{
			"inputId":"blswcbl",
			"value":${pdfn:toJsonString(formDataMap.blswcbl)}
		},
		{
			"inputId":"jxcb",
			"inputType":${pdfn:toJsonString(formDataMap.jxcb)}
		},
		{
			"inputId":"nwzbltl",
			"value":${pdfn:toJsonString(formDataMap.nwzbltl)}
		},
		{
			"inputId":"xsjz",
			"inputType":${pdfn:toJsonString(formDataMap.xsjz)}
		},
		{
			"inputId":"swbltl",
			"value":${pdfn:toJsonString(formDataMap.swbltl)}
		},
		{
			"inputId":"theoreResult",
			"value":${pdfn:toJsonString(formDataMap.theoreResult)}
		},
		{
			"inputId":"skillName",
			"value":${pdfn:toJsonString(formDataMap.skillName)}
		},
		{
			"inputId":"score",
			"value":${pdfn:toJsonString(formDataMap.score)}
		},
		{
			"inputId":"examiner1",
			"value":${pdfn:toJsonString(formDataMap.examiner1)}
		},
		{
			"inputId":"examiner2",
			"value":${pdfn:toJsonString(formDataMap.examiner2)}
		},
		{
			"inputId":"szkskhxzztpj",
			"value":${pdfn:toJsonString(formDataMap.szkskhxzztpj_id)}
		}
		,{
			"inputId":"teacherSign",
			"value":${pdfn:toJsonString(formDataMap.teacherSign)}
		}
		,{
			"inputId":"teacherDate",
			"value":${pdfn:toJsonString(formDataMap.teacherDate)}
		}
		,{
			"inputId":"directorName",
			"value":${pdfn:toJsonString(formDataMap.directorName)}
		}
		,{
			"inputId":"directorDate",
			"value":${pdfn:toJsonString(formDataMap.directorDate)}
		}
		
	]