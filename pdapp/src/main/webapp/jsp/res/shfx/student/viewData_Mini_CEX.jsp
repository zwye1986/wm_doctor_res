<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${(empty formDataMap.studentDegreeSatisfaction && isAudit)}">
		"action":{
			"save":"提交"
		},
	</c:if>
<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		{
			"inputId":"studentName",
			"inputType":"text",
			"label":"学员姓名",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"stuLevel",
			"inputType":"text",
			"label":"级别",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"professional",
			"inputType":"text",
			"label":"基地专业",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"stuSid",
			"inputType":"text",
			"label":"学号/工号",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"studentType",
			"inputType":"select",
			"label":"学员类型",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "本科生",
					"optionDesc": "本科生"
				},
				{
					"optionId": "硕士研究生",
					"optionDesc": "硕士研究生"
				},
				{
					"optionId": "博士研究生",
					"optionDesc": "博士研究生"
				}
			]
		},
		{
			"inputId":"teacherType",
			"inputType":"select",
			"label":"教师类型",
			"required":true, 	"readonly":true,		
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
			"inputId":"assessmentPlace",
			"inputType":"select",
			"label":"评估地点",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "门诊",
					"optionDesc": "门诊"
				},
				{
					"optionId": "急诊",
					"optionDesc": "急诊"
				},
				{
					"optionId": "普通病房",
					"optionDesc": "普通病房"
				},
				{
					"optionId": "监护病房",
					"optionDesc": "监护病房"
				},
				{
					"optionId": "其他",
					"optionDesc": "其他"
				}
			]
		},
		{
			"inputId":"patientName",
			"inputType":"text",
			"label":"病人姓名",
			"readonly":true
		},
		{
			"inputId":"age",
			"inputType":"text",
			"label":"年龄",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"sex",
			"inputType":"select",
			"label":"性别",
			"required":true, 	"readonly":true,		
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
			"inputId":"deptName",
			"inputType":"text",
			"label":"科室",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"qutpatientNum",
			"inputType":"text",
			"label":"住院号/门诊号",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"diagnosis",
			"inputType":"text",
			"label":"诊断",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"patientConsent",
			"inputType":"select",
			"label":"已获得病人(或家属)的同意",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "是",
					"optionDesc": "是"
				},
				{
					"optionId": "否",
					"optionDesc": "否"
				}
			]
		},
		{
			"inputId":"diagnosisKeynote",
			"inputType":"select",
			"label":"诊治重点",
			"required":true, 	"readonly":true,		
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
			"inputId":"observationTime",
			"inputType":"text",
			"label":"考核时间",
			"required":true, 	"readonly":true,
			"placeholder":"分钟"
		},
		{
			"inputId":"feedbackTime",
			"inputType":"text",
			"label":"反馈时间",
			"required":true, 	"readonly":true,
			"placeholder":"分钟"
		},
		{
			"inputId":"medicalInterview",
			"inputType":"select",
			"label":"病史采集",
			"tip":"病史采集",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"体格检查",
			"tip":"体格检查",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"人文素养",
			"tip":"人文素养",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"医患沟通",
			"tip":"医患沟通",
			"required":true, 	"readonly":true,
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"临床判断",
			"tip":"临床判断",
			"required":true, 	"readonly":true,
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"组织效能",
			"tip":"组织效能",
			"required":true, 	"readonly":true,		
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"label":"整体表现",
			"tip":"整体表现",
			"required":true, 	"readonly":true,
			"options":[
				{
					"optionId": "-",
					"optionDesc": "-"
				},
				{
					"optionId": "0",
					"optionDesc": "0"
				},
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
			"inputId":"assessmentTea",
			"inputType":"text",
			"label":"考核教师",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"assessmentDate",
			"inputType":"text",
			"label":"考核日期",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"degreeSatisfaction",
			"inputType":"select",
			"label":"教师对学员测评满意程度",
			"tip":"教师对学员测评满意程度",
			"required":true, 	"readonly":true,
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
			"readonly":true,
			"required":true
		},
		{
			"inputId":"studentDegreeSatisfaction",
			"inputType":"select",
			"label":"学生对此次测评满意程度",
			"tip":"学生对此次测评满意程度",
			"readonly":${!(empty formDataMap.studentDegreeSatisfaction && isAudit)},
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
		}
		<c:if test="${!empty formDataMap.teacherSign}">
			,
			{
				"inputId":"teacherSign",
				"inputType":"text",
				"label":"教师签字",
				"readonly":true,
				"required":true
			}
		</c:if>
			,
			{
				"inputId":"doctorComment",
				"inputType":"text",
				"label":"学生改进计划",
				"readonly":${!(empty formDataMap.studentDegreeSatisfaction && isAudit)},
				"required":true
			}
			,
			{
				"inputId":"studentSign",
				"inputType":"text",
				"label":"学生签字",
				"readonly":${!(empty formDataMap.studentDegreeSatisfaction && isAudit)},
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
			"inputId":"stuLevel",
			"value":${pdfn:toJsonString(formDataMap.stuLevel)}
		},
		{
			"inputId":"professional",
			"value":${pdfn:toJsonString(empty formDataMap.professional?resDoctor.trainingSpeName:formDataMap.professional)}
		},
		{
			"inputId":"stuSid",
			"value":${pdfn:toJsonString(formDataMap.stuSid)}
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
			"inputId":"assessmentPlace",
			"value":${pdfn:toJsonString(formDataMap.assessmentPlace)}
		},
		{
			"inputId":"patientName",
			"value":${pdfn:toJsonString(formDataMap.patientName)}
		},
		{
			"inputId":"sex",
			"value":${pdfn:toJsonString(formDataMap.sex)}
		},
		{
			"inputId":"age",
			"value":${pdfn:toJsonString(formDataMap.age)}
		},
		{
			"inputId":"deptName",
			"value":${pdfn:toJsonString(formDataMap.deptName)}
		},
		{
			"inputId":"qutpatientNum",
			"value":${pdfn:toJsonString(formDataMap.qutpatientNum)}
		},
		{
			"inputId":"diagnosis",
			"value":${pdfn:toJsonString(formDataMap.diagnosis)}
		},
		{
			"inputId":"patientConsent",
			"value":${pdfn:toJsonString(formDataMap.patientConsent)}
		},
		{
			"inputId":"diagnosisKeynote",
			"value":${pdfn:toJsonString(formDataMap.diagnosisKeynote)}
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
			"inputId":"assessmentTea",
			"value":${pdfn:toJsonString(formDataMap.assessmentTea)}
		},
		{
			"inputId":"assessmentDate",
			"value":${pdfn:toJsonString(formDataMap.assessmentDate)}
		},
		{
			"inputId":"degreeSatisfaction",
			"value":${pdfn:toJsonString(formDataMap.degreeSatisfaction)}
		},
		{
			"inputId":"teacherComment",
			"value":${pdfn:toJsonString(formDataMap.teacherComment)}
		},
		{
			"inputId":"studentDegreeSatisfaction",
			"value":${pdfn:toJsonString(formDataMap.studentDegreeSatisfaction)}
		}
		<c:if test="${!empty formDataMap.teacherSign}">
		,
		{
			"inputId":"teacherSign",
			"value":${pdfn:toJsonString(formDataMap.teacherSign)}
		}
		</c:if>
		,
		{
			"inputId":"studentSign",
			"value":${pdfn:toJsonString(empty formDataMap.studentSign?resDoctor.doctorName:formDataMap.studentSign)}
		}
		,
		{
			"inputId":"doctorComment",
			"value":${pdfn:toJsonString(formDataMap.doctorComment)}
		}
	]