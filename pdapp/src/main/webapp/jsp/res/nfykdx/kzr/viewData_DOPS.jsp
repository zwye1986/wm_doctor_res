<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"提交"
	},
<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		{
			"inputId":"studentName",
			"inputType":"text",
			"label":"学员姓名",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"grade",
			"inputType":"text",
			"label":"年级",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"professional",
			"inputType":"text",
			"label":"专业",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"studentType",
			"inputType":"select",
			"label":"学员类型",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "实习生",
					"optionDesc": "实习生"
				},
				{
					"optionId": "住院医师",
					"optionDesc": "住院医师"
				},
				{
					"optionId": "研究生",
					"optionDesc": "研究生"
				},
				{
					"optionId": "八年制二级学科",
					"optionDesc": "八年制二级学科"
				},
				{
					"optionId": "进修生",
					"optionDesc": "进修生"
				}
			]
		},
		{
			"inputId":"teacherType",
			"inputType":"select",
			"label":"教师类型",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "高级职称",
					"optionDesc": "高级职称"
				},
				{
					"optionId": "中级职称",
					"optionDesc": "中级职称"
				},
				{
					"optionId": "初级职称",
					"optionDesc": "初级职称"
				}
			]
		},
		{
			"inputId":"assessmentDate",
			"inputType":"date",
			"label":"评估日期",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"assessmentPlace",
			"inputType":"select",
			"label":"评估地点",
			"readonly":true, 		"required":true,	
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
					"optionId": "手术室",
					"optionDesc": "手术室"
				}
			]
		},
		{
			"inputId":"patientName",
			"inputType":"text",
			"label":"病人姓名",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"age",
			"inputType":"text",
			"label":"年龄",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"sex",
			"inputType":"select",
			"label":"性别",
			"readonly":true, 		"required":true,	
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
			"inputId":"patientSource",
			"inputType":"select",
			"label":"病人来源",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "门诊病人",
					"optionDesc": "门诊病人"
				},
				{
					"optionId": "住院病人",
					"optionDesc": "住院病人"
				}
			]
		},
		{
			"inputId":"patientSourceType",
			"inputType":"select",
			"label":"病人类型",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "新病人",
					"optionDesc": "新病人"
				},
				{
					"optionId": "复诊病人",
					"optionDesc": "复诊病人"
				}
			]
		},
		{
			"inputId":"patientDiagnosis",
			"inputType":"textarea",
			"label":"病人主要诊断",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"operatingSkills",
			"inputType":"text",
			"label":"操作技能",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"studentSkillNum",
			"inputType":"select",
			"label":"评估前学员执行临床技能操作例数",
			"tip":"评估前学员执行临床技能操作例数",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "0",
					"optionDesc": "0"
				},
				{
					"optionId": "1-3",
					"optionDesc": "1-3"
				},
				{
					"optionId": "4",
					"optionDesc": "4"
				}
			]
		},
		{
			"inputId":"skillComplexDegree",
			"inputType":"select",
			"label":"技能复杂程度",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "低度",
					"optionDesc": "低度"
				},
				{
					"optionId": "中度",
					"optionDesc": "中度"
				},
				{
					"optionId": "高度",
					"optionDesc": "高度"
				}
			]
		},
		{
			"inputId":"skillLevel",
			"inputType":"select",
			"label":"1、对临床技能适应证、相关解剖结构的了解及操作步骤的熟练程度",
			"tip":"1、对临床技能适应证、相关解剖结构的了解及操作步骤的熟练程度",
			"readonly":true, 		"required":true,	
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
			"inputId":"consentForm",
			"inputType":"select",
			"label":"2、能详细告知病人并取得同意书",
			"tip":"2、能详细告知病人并取得同意书",
			"readonly":true, 		"required":true,	
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
			"inputId":"readyToWork",
			"inputType":"select",
			"label":"3、执行临床操作前的准备工作",
			"tip":"3、执行临床操作前的准备工作",
			"readonly":true, 		"required":true,	
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
			"inputId":"painAndStabilization",
			"inputType":"select",
			"label":"4、能给予病人适当的止痛和镇定",
			"tip":"4、能给予病人适当的止痛和镇定",
			"readonly":true, 		"required":true,	
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
			"inputId":"SkillAbility",
			"inputType":"select",
			"label":"5、执行临床操作的技术能力",
			"tip":"5、执行临床操作的技术能力",
			"readonly":true, 		"required":true,	
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
			"inputId":"asepticTechnique",
			"inputType":"select",
			"label":"6、无菌技术",
			"tip":"6、无菌技术",
			"readonly":true, 		"required":true,	
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
			"inputId":"seekAssistance",
			"inputType":"select",
			"label":"7、能视需要寻求协助",
			"tip":"7、能视需要寻求协助",
			"readonly":true, 		"required":true,	
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
			"inputId":"relatedDisposal",
			"inputType":"select",
			"label":"8、执行临床操作后的相关处置",
			"tip":"8、执行临床操作后的相关处置",
			"readonly":true, 		"required":true,	
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
			"inputId":"communicationSkills",
			"inputType":"select",
			"label":"9、与病人沟通的技巧",
			"tip":"9、与病人沟通的技巧",
			"readonly":true, 		"required":true,	
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
			"inputId":"feelProfessionalDegree",
			"inputType":"select",
			"label":"10、能否顾忌病人感受和专业程度",
			"tip":"10、能否顾忌病人感受和专业程度",
			"readonly":true, 		"required":true,	
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
			"inputId":"overallPerformance",
			"inputType":"select",
			"label":"11、执行临床操作技能的整体表现",
			"tip":"11、执行临床操作技能的整体表现",
			"readonly":true, 		"required":true,	
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
			"inputId":"observationTime",
			"inputType":"textarea",
			"label":"评审观察时间",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"feedbackTime",
			"inputType":"text",
			"label":"指导反馈时间",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"degreeSatisfaction",
			"inputType":"select",
			"label":"教师对学员测评满意程度",
			"tip":"教师对学员测评满意程度",
			"readonly":true, 		"required":true,	
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
			"inputId":"teacherComment",
			"inputType":"textarea",
			"label":"教师的评语",
			"readonly":true, 		"required":true
		}
			,
			{
				"inputId":"studentDegreeSatisfaction",
				"inputType":"select",
				"label":"学生对此次测评满意程度",
				"tip":"学生对此次测评满意程度",
				"readonly":true, 		"required":true,
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
			}
			,
			{
				"inputId":"teacherSign",
				"inputType":"text",
				"label":"教师签字",
				"readonly":true,
				"required":true
			}
			,
			{
				"inputId":"studentSign",
				"inputType":"text",
				"label":"学生签字",
				"readonly":true,
				"required":true
			}
	]
	,
	"values":[
		{
			"inputId":"studentName",
			"value":${pdfn:toJsonString(empty formDataMap.studentName?resDoctor.doctorName:formDataMap.studentName)}
		},
		{
			"inputId":"grade",
			"value":${pdfn:toJsonString(empty formDataMap.grade?resDoctor.sessionNumber:formDataMap.grade)}
		},
		{
			"inputId":"professional",
			"value":${pdfn:toJsonString(empty formDataMap.professional?resDoctor.trainingSpeName:formDataMap.professional)}
		},
		{
			"inputId":"studentType",
			"value":${pdfn:toJsonString(formDataMap.studentType)}
		},
		{
			"inputId":"teacherType",
			"value":${pdfn:toJsonString(formDataMap.teacherType)}
		},
		{
			"inputId":"assessmentDate",
			"value":${pdfn:toJsonString(formDataMap.assessmentDate)}
		},
		{
			"inputId":"assessmentPlace",
			"value":${pdfn:toJsonString(formDataMap.assessmentPlace)}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap.patientName)}
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
			"inputId":"patientSource",
			"value":${pdfn:toJsonString(formDataMap.patientSource)}
		},
		{
			"inputId":"patientSourceType",
			"value":${pdfn:toJsonString(formDataMap.patientSourceType)}
		},
		{
			"inputId":"patientDiagnosis",
			"value":${pdfn:toJsonString(formDataMap.patientDiagnosis)}
		},
		{
			"inputId":"operatingSkills",
			"value":${pdfn:toJsonString(formDataMap.operatingSkills)}
		},
		{
			"inputId":"studentSkillNum",
			"value":${pdfn:toJsonString(formDataMap.studentSkillNum)}
		},
		{
			"inputId":"skillComplexDegree",
			"value":${pdfn:toJsonString(formDataMap.skillComplexDegree)}
		},
		{
			"inputId":"skillLevel",
			"value":${pdfn:toJsonString(formDataMap.skillLevel)}
		},
		{
			"inputId":"consentForm",
			"value":${pdfn:toJsonString(formDataMap.consentForm)}
		},
		{
			"inputId":"readyToWork",
			"value":${pdfn:toJsonString(formDataMap.readyToWork)}
		},
		{
			"inputId":"painAndStabilization",
			"value":${pdfn:toJsonString(formDataMap.painAndStabilization)}
		},
		{
			"inputId":"SkillAbility",
			"value":${pdfn:toJsonString(formDataMap.SkillAbility)}
		},
		{
			"inputId":"asepticTechnique",
			"value":${pdfn:toJsonString(formDataMap.asepticTechnique)}
		},
		{
			"inputId":"seekAssistance",
			"value":${pdfn:toJsonString(formDataMap.seekAssistance)}
		},
		{
			"inputId":"relatedDisposal",
			"value":${pdfn:toJsonString(formDataMap.relatedDisposal)}
		},
		{
			"inputId":"communicationSkills",
			"value":${pdfn:toJsonString(formDataMap.communicationSkills)}
		},
		{
			"inputId":"feelProfessionalDegree",
			"value":${pdfn:toJsonString(formDataMap.feelProfessionalDegree)}
		},
		{
			"inputId":"overallPerformance",
			"value":${pdfn:toJsonString(formDataMap.overallPerformance)}
		},
		{
			"inputId":"observationTime",
			"value":${pdfn:toJsonString(formDataMap.observationTime)}
		},
		{
			"inputId":"feedbackTime",
			"value":${pdfn:toJsonString(formDataMap.feedbackTime)}
		},
		{
			"inputId":"degreeSatisfaction",
			"value":${pdfn:toJsonString(formDataMap.degreeSatisfaction)}
		},
		{
			"inputId":"teacherComment",
			"value":${pdfn:toJsonString(formDataMap.teacherComment)}
		}
			,
			{
				"inputId":"studentDegreeSatisfaction",
				"value":${pdfn:toJsonString(formDataMap.studentDegreeSatisfaction)}
			}
		,
		{
			"inputId":"teacherSign",
			"value":${pdfn:toJsonString(empty formDataMap.teacherSign ? tea.userName :formDataMap.teacherSign)}
		}
		,
		{
			"inputId":"studentSign",
			"value":${pdfn:toJsonString(formDataMap.studentSign)}
		}
	]