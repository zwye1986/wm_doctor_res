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
			"readonly":true, 		"required":true
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
			"inputId":"diagnosis",
			"inputType":"text",
			"label":"诊断",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"severity",
			"inputType":"select",
			"label":"病情严重情况",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "轻",
					"optionDesc": "轻"
				},
				{
					"optionId": "中",
					"optionDesc": "中"
				},
				{
					"optionId": "重",
					"optionDesc": "重"
				}
			]
		},
		{
			"inputId":"diagnosisKeynote",
			"inputType":"select",
			"label":"诊治重点",
			"readonly":true, 		"required":true,	
			"options":[
				{
					"optionId": "病史采集",
					"optionDesc": "病史采集"
				},
				{
					"optionId": "诊断",
					"optionDesc": "诊断"
				},
				{
					"optionId": "治疗",
					"optionDesc": "治疗"
				},
				{
					"optionId": "健康宣传",
					"optionDesc": "健康宣传"
				}
			]
		},
		{
			"inputId":"medicalInterview",
			"inputType":"select",
			"label":"1、医疗面谈(称呼病人/自我介绍/病人陈述病史/适当提问/适当回应)",
			"tip":"1、医疗面谈(称呼病人/自我介绍/病人陈述病史/适当提问/适当回应)",
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
			"inputId":"physicalExamination",
			"inputType":"select",
			"label":"2、体格检查(告知检查目的/重点检查/操作正确/处理病人的不适)",
			"tip":"2、体格检查(告知检查目的/重点检查/操作正确/处理病人的不适)",
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
			"inputId":"humanisticCare",
			"inputType":"select",
			"label":"3、人文关怀(尊重和关心/建立良好关系/满足病人适当的需求)",
			"tip":"3、人文关怀(尊重和关心/建立良好关系/满足病人适当的需求)",
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
			"inputId":"clinicalJudgment",
			"inputType":"select",
			"label":"4、临床判断(归纳病史/判读检查结果/鉴别诊断/诊疗思维/预判治疗情况)",
			"tip":"4、临床判断(归纳病史/判读检查结果/鉴别诊断/诊疗思维/预判治疗情况)",
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
			"label":"5、沟通技能(解释检查、治疗理由/解释检查和临床相关性/健康宣教和咨询)",
			"tip":"5、沟通技能(解释检查、治疗理由/解释检查和临床相关性/健康宣教和咨询)",
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
			"inputId":"organization",
			"inputType":"select",
			"label":"6、组织效能(能按合理顺序处理，及时且适时，历练而简洁)",
			"tip":"6、组织效能(能按合理顺序处理，及时且适时，历练而简洁)",
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
			"inputId":"holisticCare",
			"inputType":"select",
			"label":"7、整体关怀(综合评价受试者的表现)",
			"tip":"7、整体关怀(综合评价受试者的表现)",
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
			"inputType":"text",
			"label":"评审观察时间",
			"readonly":true, 		"required":true,	
			"placeholder":"分钟"
		},
		{
			"inputId":"feedbackTime",
			"inputType":"text",
			"label":"指导反馈时间",
			"readonly":true, 		"required":true,	
			"placeholder":"分钟"
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
				"readonly":true, 		"required":true
			}
			,
			{
				"inputId":"studentSign",
				"inputType":"text",
				"label":"学生签字",
				"readonly":true, 		"required":true
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
			"inputId":"diagnosis",
			"value":${pdfn:toJsonString(formDataMap.diagnosis)}
		},
		{
			"inputId":"severity",
			"value":${pdfn:toJsonString(formDataMap.severity)}
		},
		{
			"inputId":"diagnosisKeynote",
			"value":${pdfn:toJsonString(formDataMap.diagnosisKeynote)}
		},
		{
			"inputId":"medicalInterview",
			"value":${pdfn:toJsonString(formDataMap.medicalInterview)}
		},
		{
			"inputId":"physicalExamination",
			"value":${pdfn:toJsonString(formDataMap.physicalExamination)}
		},
		{
			"inputId":"humanisticCare",
			"value":${pdfn:toJsonString(formDataMap.humanisticCare)}
		},
		{
			"inputId":"clinicalJudgment",
			"value":${pdfn:toJsonString(formDataMap.clinicalJudgment)}
		},
		{
			"inputId":"communicationSkills",
			"value":${pdfn:toJsonString(formDataMap.communicationSkills)}
		},
		{
			"inputId":"organization",
			"value":${pdfn:toJsonString(formDataMap.organization)}
		},
		{
			"inputId":"holisticCare",
			"value":${pdfn:toJsonString(formDataMap.holisticCare)}
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