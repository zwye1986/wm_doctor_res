<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"version":"2.0",
	"inputs":[

		{
			"inputId":"name",
			"inputType":"text",
			"label":"学员姓名：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"trainMajor",
			"inputType":"text",
			"label":"培训专业：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"轮转科室名称：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"cycleTimeQ",
			"inputType":"date",
			"label":"轮转开始时间：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"cycleTimeH",
			"inputType":"date",
			"label":"轮转结束时间：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"schMonth",
			"inputType":"text",
			"label":"轮转时长：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"auditTime",
			"inputType":"date",
			"label":"考核时间：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"teacherName",
			"inputType":"text",
			"label":"带教老师：",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"kq",
			"inputType":"title",
			"label":"1、考勤(5分)",
			"tip":"旷工，记0分；满勤，记5分；迟到或早退或脱岗一次，扣1分，直至扣完",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"kqLevel",
					"inputType":"radio",
					"label":"组织纪律、有无旷工、迟到、早退、脱岗位",
					"readonly":${isAudit},
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 5,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"ydyf",
			"inputType":"title",
			"label":"2、医德医风(5分)",
			"tip":"带教老师根据住院医师平时的工作表现进行综合评价",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"fwtdLevel",
					"inputType":"radio",
					"label":"服务态度、爱护伤病员观念",
					"readonly":${isAudit},
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"gzzrxLevel",
					"inputType":"radio",
					"label":"工作责任心、无差错",
					"readonly":${isAudit},
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"ylzfLevel",
					"inputType":"radio",
					"label":"医疗作风、廉洁行医",
					"readonly":${isAudit},
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"tjxzLevel",
					"inputType":"radio",
					"label":"团结协作、遵守制度",
					"readonly":${isAudit},
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 5,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"zbwcqk",
			"inputType":"title",
			"label":"3、指标完成情况(20分)",
			"tip":"带教老师根据住院医师平时的工作表现进行综合评价",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"bzblslLevel",
					"inputType":"radio",
					"label":"病种、例数、手术治疗数量（门诊）",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"glbrsLevel",
					"inputType":"radio",
					"label":"管理病人数（病房）",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"glbrsLevel2",
					"inputType":"radio",
					"label":"参加轮科科室组织的业务学习、病例讨论及各项教学活动",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"glbrsLevel3",
					"inputType":"radio",
					"label":"必轮科室完成手写2份大病历",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"glbrsLevel4",
					"inputType":"radio",
					"label":"阅读指定的书籍",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"glbrsLevel5",
					"inputType":"radio",
					"label":"值班",
					"readonly":true,
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 20,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"jbjn",
			"inputType":"title",
			"label":"4、基本技能(10分)",
			"tip":"带教老师根据住院医师轮转时实际完成情况进行综合评价",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"ylwjsxLevel",
					"inputType":"radio",
					"label":"医疗文件书写质量（门诊处方、各类检查申请单、病历书写）",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"tgjcLevel",
					"inputType":"radio",
					"label":"体格检查",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"sshjnczLevel",
					"inputType":"radio",
					"label":"手术或技能操作",
					"readonly":true,
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 10,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"zznl",
			"inputType":"title",
			"label":"5、诊治能力(10分)",
			"tip":"带教老师根据住院医师轮转时实际完成情况进行综合评价",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"cjbzdLevel",
					"inputType":"radio",
					"label":"常见病诊断和鉴别",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"jwzbrczLevel",
					"inputType":"radio",
					"label":"急、危重病人的处置或抢救",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"jhbqfxjcbgLevel",
					"inputType":"radio",
					"label":"结合病情分析、检查、报告",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"zhczLevel",
					"inputType":"radio",
					"label":"综合处置",
					"readonly":true,
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 10,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"lcswnl",
			"inputType":"title",
			"label":"6、临床思维能力(10分)",
			"tip":"带教老师根据住院医师轮转时实际完成情况进行综合评价",
			"readonly":${isAudit},
			"required":true,
			"items":[
				{
					"inputId":"gnnlLevel",
					"inputType":"radio",
					"label":"归纳能力(掌握病例特点、分析深入、语言表达精练、推理有逻辑性、思维正确",
					"readonly":true,
					"required":false,
					"options":[
					]
				},
				{
					"inputId":"fxnlLevel",
					"inputType":"radio",
					"label":"分析能力(理论和实践的结合)",
					"readonly":true,
					"required":false,
					"options":[
					]
				}
			],
			"verify": {
                "max": 10,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"ylccsgLevel",
			"inputType":"title",
			"label":"医疗差错事故：",
			"readonly":${isAudit},
			"required":true,
			"items":[
			],
			"options":[
                  {
                        "optionId": "1",
                        "optionDesc": "有"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "无"
                  }
			]
		},
		{

			"inputId":"xj",
			"inputType":"text",
			"label":"小计(60分)",
			"tip":"（日常考核合格线：42分）",
			"readonly":true,
			"required":true,
			"verify": {
                "max": 60,
                "type": "int"
           	},
			"options":[
			]
		},
		{
			"inputId":"totalScore",
			"inputType":"number",
			"label":"理论成绩：",
			"readonly":${isAudit},
			"required":true
		},
		{
			"inputId":"zyyycj",
			"inputType":"text",
			"label":"专业英语成绩：",
			"readonly":${isAudit},
			"required":true
		},
		{
			"inputId":"lcjncj",
			"inputType":"number",
			"label":"临床技能考试成绩：",
			"readonly":${isAudit},
			"required":true
		},
		{
			"inputId":"blsxcj",
			"inputType":"number",
			"label":"病历书写成绩：",
			"readonly":${isAudit},
			"required":true
		},
		{
			"inputId":"zCj",
			"inputType":"title",
			"label":"考核总成绩（以上考核项目中有一项不合格视为本次轮科不合格）：",
			"readonly":${isAudit},
			"required":true,
			"items":[
			],
			"options":[
                  {
                        "optionId": "1",
                        "optionDesc": "合格"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "不合格"
                  }
			]
		}
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
		,
		{
			"inputId":"deptHeadAutograth",
			"inputType":"textarea",
			"label":"所轮科室（年度）考核评语：",
			"readonly":false,
			"required":true
		}
		,
		{
			"inputId":"headExpertName",
			"inputType":"text",
			"label":"考核组长签名：",
			"readonly":false,
			"required":true
		}
		,
		{
			"inputId":"headMemberName",
			"inputType":"text",
			"label":"考核成员签名：",
			"readonly":false,
			"required":true
		}
	</c:if>
	]
	,
	"values":[
		{
			"inputId":"name",
			"value":${pdfn:toJsonString(empty formDataMap.name?resDoctor.doctorName:formDataMap.name)}
		},
		{
			"inputId":"trainMajor",
			"value":${pdfn:toJsonString(empty formDataMap.trainMajor?resDoctor.trainingSpeName:formDataMap.trainMajor)}
		},
		{
			"inputId":"deptName",
			"value":${pdfn:toJsonString(empty formDataMap.deptName?currRegProcess.schDeptName:formDataMap.deptName)}
		},
		{
			"inputId":"cycleTimeQ",
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeQ?currRegProcess.schStartDate:formDataMap.cycleTimeQ)}
		},
		{
			"inputId":"cycleTimeH",
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeH?currRegProcess.schEndDate:formDataMap.cycleTimeH)}
		},
		{
			"inputId":"schMonth",
			"value":${pdfn:toJsonString(empty formDataMap.schMonth ? result.schMonth:formDataMap.schMonth )}
		},
		{
			"inputId":"auditTime",
			"value":${pdfn:toJsonString(formDataMap.auditTime)}
		},
		{
			"inputId":"teacherName",
			"value":${pdfn:toJsonString(empty formDataMap.teacherName? tea.userName:formDataMap.teacherName)}
		},
		{
			"inputId":"kq",
			"value":${pdfn:toJsonString(formDataMap.kq)}
		},
		{
			"inputId":"ydyf",
			"value":${pdfn:toJsonString(formDataMap.ydyf)}
		},
		{
			"inputId":"zbwcqk",
			"value":${pdfn:toJsonString(formDataMap.zbwcqk)}
		},
		{
			"inputId":"jbjn",
			"value":${pdfn:toJsonString(formDataMap.jbjn)}
		},
		{
			"inputId":"zznl",
			"value":${pdfn:toJsonString(formDataMap.zznl)}
		},
		{
			"inputId":"lcswnl",
			"value":${pdfn:toJsonString(formDataMap.lcswnl)}
		},
		{
			"inputId":"ylccsgLevel",
			"value":${pdfn:toJsonString(formDataMap.ylccsgLevel_id)}
		},
		{
			"inputId":"xj",
			"value":${pdfn:toJsonString(formDataMap.xj)}
		},
		<c:choose>
			<c:when test="${!testTypeFlag}">
				<c:set var="totalScore" value="${formDataMap['totalScore']}"/>
			</c:when>
			<c:when test="${testTypeFlag}">
				<c:set var="totalScore" value="${empty formDataMap['totalScore'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['totalScore']}"/>
			</c:when>
		</c:choose>
		{
			"inputId":"totalScore",
			"value":${pdfn:toJsonString(totalScore)}
		},
		{
			"inputId":"zyyycj",
			"value":${pdfn:toJsonString(formDataMap.zyyycj)}
		},
		{
			"inputId":"lcjncj",
			"value":${pdfn:toJsonString(formDataMap.lcjncj)}
		},
		{
			"inputId":"blsxcj",
			"value":${pdfn:toJsonString(formDataMap.blsxcj)}
		},
		{
			"inputId":"zCj",
			"value":${pdfn:toJsonString(formDataMap.zCj_id)}
		}
		<c:if test="${!empty formDataMap.deptHeadAutograth}">
		,
		{
			"inputId":"deptHeadAutograth",
			"value":${pdfn:toJsonString(formDataMap.deptHeadAutograth)}
		},
		{
			"inputId":"headExpertName",
			"value":${pdfn:toJsonString(formDataMap.headExpertName)}
		},
		{
			"inputId":"headMemberName",
			"value":${pdfn:toJsonString(formDataMap.headMemberName)}
		}
	</c:if>
		
	]