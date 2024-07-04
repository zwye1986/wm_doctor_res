<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"inputs":[
		{
			"inputId":"name",
			"inputType":"text",
			"label":"姓名：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"trainStage",
			"inputType":"text",
			"label":"培训阶段：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"trainYear",
			"inputType":"text",
			"label":"入培年份：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"trainSpe",
			"inputType":"text",
			"label":"培训专业：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"head",
			"inputType":"text",
			"label":"轮转科室名称：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"teacher",
			"inputType":"text",
			"label":"带教老师：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"beginTime",
			"inputType":"date",
			"label":"轮转开始时间：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"endTime",
			"inputType":"date",
			"label":"轮转结束时间：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"totalTime",
			"inputType":"text",
			"label":"轮转时长：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"testDate",
			"inputType":"date",
			"label":"考核时间：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"zzjl",
			"inputType":"title",
			"label":"组织纪律（5分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"有无旷工、迟到、早退、脱岗"
				}
			],
			"verify": {
				"max": "5",
				"type": "int"
			}
		},
		{
			"inputId":"ydyf",
			"inputType":"title",
			"label":"医德医风（5分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"医疗作风、遵守制度、服务态度、工作责任"
				}
			],
			"verify": {
				"max": "5",
				"type": "int"
			}
		},
		{
			"inputId":"xxtd",
			"inputType":"title",
			"label":"学习态度（5分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"参加科室学术活动、住院医师培训课程情况"
				}
			],
			"verify": {
				"max": "5",
				"type": "int"
			}
		},
		{
			"inputId":"bzqk",
			"inputType":"title",
			"label":"病种与操作（5分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"完成管理全程病人数和规定的病种数量情况"
				}
			],
			"verify": {
				"max": "5",
				"type": "int"
			}
		},
		{
			"inputId":"czqk",
			"inputType":"title",
			"label":"病种与操作（5分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"two",
					"label":"完成技能操作种类和数量情况"
				}
			],
			"verify": {
				"max": "5",
				"type": "int"
			}
		},
		{
			"inputId":"lczlnl",
			"inputType":"title",
			"label":"临床诊疗能力（20分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"病史采集与体格检查"
				},
				{
					"inputId":"two",
					"label":"实验室结果判读与分析"
				},
				{
					"inputId":"three",
					"label":"诊断与鉴别诊断"
				},
				{
					"inputId":"four",
					"label":"对疾病的综合处理能力"
				},
				{
					"inputId":"five",
					"label":"归纳、分析、总结以及表达能力"
				},
				{
					"inputId":"six",
					"label":"人文关怀与人际沟通能力"
				}
			],
			"verify": {
				"max": "20",
				"type": "int"
			}
		},
		{
			"inputId":"dsnl",
			"inputType":"title",
			"label":"动手能力（10分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"临床技能操作能力"
				},
				{
					"inputId":"two",
					"label":"人文关怀与人际沟通能力"
				}
			],
			"verify": {
				"max": "10",
				"type": "int"
			}
		},
		{
			"inputId":"blsx",
			"inputType":"title",
			"label":"病历书写（10分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"病历书写考核（原始分,附修改过的手写大病历和病历书写评分表）"
				}
			],
			"verify": {
				"max": "10",
				"type": "int"
			}
		},
		{
			"inputId":"yykh",
			"inputType":"title",
			"label":"英语考核（10分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"专业英语考核（原始分,附原始试卷和有修改评分的答案）"
				}
			],
			"verify": {
				"max": "10",
				"type": "int"
			}
		},
		{
			"inputId":"llkh",
			"inputType":"title",
			"label":"理论考核（25分）",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"one",
					"label":"专业理论考核（原始分，考核时间）"
				}
			],
			"verify": {
				"max": "25",
				"type": "int"
			}
		},
		{
			"inputId":"gradeScore",
			"inputType":"text",
			"label":"考核总成绩（100分）",
			"readonly":true,
			"required":true,
			"items":[],
			"verify": {
				"max": "100",
				"type": "int"
			}
		},
		{
			"inputId":"ylsg",
			"inputType":"title",
			"label":"医疗差错、事故",
			"readonly":${isAudit},
			"required":true,
			"items":[],
			"options": [
				{"optionId": ${pdfn:toJsonString('有')},"optionDesc": ${pdfn:toJsonString('有')}},
				{"optionId": ${pdfn:toJsonString('无')},"optionDesc": ${pdfn:toJsonString('无')}}
			]
		}
		{
			"inputId":"memo",
			"inputType":"textarea",
			"label":"考核评语",
			"readonly":${isAudit},
			"required":true
		},
		{
			"inputId":"signTime",
			"inputType":"date",
			"label":"日期",
			"readonly":${isAudit},
			"required":true
		}
	]
	,
	"values":[
		{
			"inputId":"name",
			"value":${pdfn:toJsonString(doctor.doctorName)}
		},
		{
			"inputId":"trainStage",
			"value":${pdfn:toJsonString(doctor.trainingTypeName)}
		},
		{
			"inputId":"trainYear",
			"value":${pdfn:toJsonString(doctor.sessionNumber)}
		},
		{
			"inputId":"trainSpe",
			"value":${pdfn:toJsonString(doctor.trainingSpeName)}
		},
		{
			"inputId":"head",
			"value":${pdfn:toJsonString(result.schDeptName)}
		},
		{
			"inputId":"teacher",
			"value":${pdfn:toJsonString(currRegProcess.teacherUserName)}
		},
		{
			"inputId":"beginTime",
			"value":${pdfn:toJsonString(currRegProcess.schStartDate)}
		},
		{
			"inputId":"endTime",
			"value":${pdfn:toJsonString(currRegProcess.schEndDate)}
		},
		{
			"inputId":"totalTime",
			"value":${pdfn:toJsonString(result.schMonth)}
		},
		{
			"inputId":"testDate",
			"value":${pdfn:toJsonString(formDataMap.testDate)}
		},
		{
			"inputId":"zzjl",
			"value":${pdfn:toJsonString(formDataMap.zzjl)}
		},
		{
			"inputId":"ydyf",
			"value":${pdfn:toJsonString(formDataMap.ydyf)}
		},
		{
			"inputId":"xxtd",
			"value":${pdfn:toJsonString(formDataMap.xxtd)}
		},
		{
			"inputId":"bzqk",
			"value":${pdfn:toJsonString(formDataMap.bzqk)}
		},
		{
			"inputId":"czqk",
			"value":${pdfn:toJsonString(formDataMap.czqk)}
		},
		{
			"inputId":"lczlnl",
			"value":${pdfn:toJsonString(formDataMap.lczlnl)}
		},
		{
			"inputId":"dsnl",
			"value":${pdfn:toJsonString(formDataMap.dsnl)}
		},
		{
			"inputId":"blsx",
			"value":${pdfn:toJsonString(formDataMap.blsx)}
		},
		{
			"inputId":"yykh",
			"value":${pdfn:toJsonString(formDataMap.yykh)}
		},
		{
			"inputId":"llkh",
			"value":${pdfn:toJsonString(formDataMap.llkh)}
		},
		{
			"inputId":"gradeScore",
			"value":${pdfn:toJsonString(formDataMap.gradeScore)}
		},
		{
			"inputId":"ylsg",
			"value":${pdfn:toJsonString(formDataMap.ylsg)}
		},
		{
			"inputId":"memo",
			"value":${pdfn:toJsonString(formDataMap.memo)}
		},
		{
			"inputId":"signTime",
			"value":${pdfn:toJsonString(empty formDataMap.signTime?pdfn:getCurrDate():formDataMap.signTime)}
		}
	]