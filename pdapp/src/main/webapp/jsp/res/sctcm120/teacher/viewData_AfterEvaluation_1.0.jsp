<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		
		{
			"inputId":"name",
			"inputType":"text",
			"label":"学员姓名",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"sessional",
			"inputType":"text",
			"label":"届别：",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"trainMajor",
			"inputType":"text",
			"label":"培训专业",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"deptName",
			"inputType":"text",
			"label":"轮转科室名称",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"cycleTimeQ",
			"inputType":"date",
			"label":"轮转开始时间",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"cycleTimeH",
			"inputType":"date",
			"label":"轮转结束时间",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"quan",
			"inputType":"text",
			"label":"考勤",
			"readonly":true,
			"required":false
		},
		<c:if test="${schDoctorAbsenceList>0}">

			{
				"inputId":"leave",
				"inputType":"text",
				"label":"事假",
				"readonly":true, 		"required":true
			},
			{
				"inputId":"sickLeave",
				"inputType":"text",
				"label":"病假",
				"readonly":true, 		"required":true
			},
			{
				"inputId":"materLeave",
				"inputType":"text",
				"label":"产假",
				"readonly":true, 		"required":true
			},
			{
				"inputId":"absenteeism",
				"inputType":"text",
				"label":"旷工",
				"readonly":true, 		"required":true
			},
		</c:if>
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
			"inputId":"zsgjflfg_name",
			"label":"遵守国家法律法规、医院规章制度",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"lxgwzz_name",
			"label":"履行岗位职责",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"ybrwzx_name",
			"label":"以病人为中心，体现人文关怀",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"rjgtbdnl_name",
			"label":"人际（医患）沟通和表达能力",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"tjxzjsxm_name",
			"label":"团结协作精神",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"jbllzwcd_name",
			"label":"临床基本知识、基本理论掌握程度",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"njbjnzwcd_name",
			"label":"临床基本技能掌握程度",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"lcswnl_name",
			"label":"临床思维能力",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"lczlnl_name",
			"label":"临床诊疗能力",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
		},
		{
			"inputId":"jjclnl_name",
			"label":"危重病人的识别及紧急处理能力",
			"readonly":${isAudit},
			"required": true,
			"inputType": "text",
			"isHidden": true
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
			"readonly":true, 		"required":true,
			"verify": {
				"type": "per"
			}
		},
		{
			"inputId":"mzsywc",
			"inputType":"text",
			"label":"门诊病例数应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"mzsyjwc",
			"inputType":"text",
			"label":"门诊病例数已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"mzswcbl",
			"inputType":"text",
			"label":"门诊病例数完成比例",
			"readonly":true, 		"required":true,
			"verify": {
				"type": "per"
			}
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
			"readonly":true, 		"required":true,
			"verify": {
				"type": "per"
			}
		},
		{
			"inputId":"hdsywc",
			"inputType":"text",
			"label":"参加活动应完成",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"hdsyjwc",
			"inputType":"text",
			"label":"参加活动已完成",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"hdswcbl",
			"inputType":"text",
			"label":"参加活动完成比例",
			"readonly":true, 		"required":true,
			"verify": {
				"type": "per"
			}
		},
		{
			"inputId":"hdxs",
			"inputType":"text",
			"label":"参加活动形式",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"totalScore",
			"inputType":"text",
			"label":"理论成绩",
			"readonly":${empty formDataMap['totalScore'] ? (empty outScore.theoryScore ? false:true):true},
			"required":true,
			"placeholder": "0~100",
			"verify": {
				"max": "100",
				"type": "int"
			}
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
			"readonly":${isAudit},
			"required":true
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
		},
		{
			"inputId": "szkskhxzztpj_name",
			"label": "所在科室考核小组总体评价",
			"required": true,
			"inputType": "text",
			"isHidden": true
		}
		,
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
		},
		{
			"inputId": "szkskhxzztpj_name",
			"label": "所在科室考核小组总体评价",
			"required": true,
			"inputType": "text",
			"isHidden": true
		}

		<c:if test="${not empty formDataMap.directorName}">
			,
			{
				"inputId":"isAgree",
				"inputType":"select",
				"label":"是否同意出科",
				"tip":"是否同意出科",
				"readonly":true, 		"required":false,
				"options":[
					{
						"optionId": "Y",
						"optionDesc": "同意"
					},
					{
						"optionId": "N",
						"optionDesc": "不同意"
					}
				]
			},
			{
				"inputId": "isAgree_name",
				"label": "是否同意出科",
				"required": true,
				"inputType": "text",
				"readonly":true, 		"required":false
			}
		</c:if>
		,
		{
			"inputId":"teacherName",
			"inputType":"text",
			"label":"带教老师签名",
			"readonly":true, 		"required":true
		}
		,
		{
			"inputId":"teacherDate",
			"inputType":"text",
			"label":"带教老师签名日期",
			"readonly":true, 		"required":true
		}
		<c:if test="${not empty formDataMap.directorName}">
		,
		{
			"inputId":"directorName",
			"inputType":"text",
			"label":"科主任签名",
			"readonly":true, 		"required":false
		}
		,
		{
			"inputId":"directorDate",
			"inputType":"text",
			"label":"科主任签名日期",
			"readonly":true, 		"required":false
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
			"inputId":"sessional",
			"value":${pdfn:toJsonString(empty formDataMap.sessional?resDoctor.sessionNumber:formDataMap.sessional)}
		},
		{
			"inputId":"trainMajor",
			"value":${pdfn:toJsonString(empty formDataMap.trainMajor?resDoctor.trainingSpeName:formDataMap.trainMajor)}
		},
		{
			"inputId":"deptName",
			"value":${pdfn:toJsonString(empty formDataMap.deptName ?doctorSchProcess.schDeptName:formDataMap.deptName)}
		},
		{
			"inputId":"cycleTimeQ",
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeQ ?doctorSchProcess.schStartDate:formDataMap.cycleTimeQ)}
		},
		{
			"inputId":"cycleTimeH",
			"value":${pdfn:toJsonString(empty formDataMap.cycleTimeH ?doctorSchProcess.schEndDate:formDataMap.cycleTimeH)}
		},
		{
			"inputId":"quan",
			<c:if test="${schDoctorAbsenceList>0}">
				"value":"非全勤"
			</c:if>
			<c:if test="${schDoctorAbsenceList<=0}">
				"value":"全勤"
			</c:if>
		},
		<c:if test="${schDoctorAbsenceList>0}">
			{
				"inputId":"leave",
				"value":${pdfn:toJsonString(maps.leave)}
			},
			{
				"inputId":"sickLeave",
				"value":${pdfn:toJsonString(maps.sickleave)}
			},
			{
				"inputId":"materLeave",
				"value":${pdfn:toJsonString(maps.maternityleave)}
			},
			{
				"inputId":"absenteeism",
				"value":${pdfn:toJsonString(maps.marriage)}
			},
		</c:if>
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
			"inputId":"zsgjflfg_name",
			"value":${pdfn:toJsonString(formDataMap.zsgjflfg_name)}
		},
		{
			"inputId":"lxgwzz_name",
			"value":${pdfn:toJsonString(formDataMap.lxgwzz_name)}
		},
		{
			"inputId":"ybrwzx_name",
			"value":${pdfn:toJsonString(formDataMap.ybrwzx_name)}
		},
		{
			"inputId":"rjgtbdnl_name",
			"value":${pdfn:toJsonString(formDataMap.rjgtbdnl_name)}
		},
		{
			"inputId":"tjxzjsxm_name",
			"value":${pdfn:toJsonString(formDataMap.tjxzjsxm_name)}
		},
		{
			"inputId":"jbllzwcd_name",
			"value":${pdfn:toJsonString(formDataMap.jbllzwcd_name)}
		},
		{
			"inputId":"njbjnzwcd_name",
			"value":${pdfn:toJsonString(formDataMap.njbjnzwcd_name)}
		},
		{
			"inputId":"lcswnl_name",
			"value":${pdfn:toJsonString(formDataMap.lcswnl_name)}
		},
		{
			"inputId":"lczlnl_name",
			"value":${pdfn:toJsonString(formDataMap.lczlnl_name)}
		},
		{
			"inputId":"jjclnl_name",
			"value":${pdfn:toJsonString(formDataMap.jjclnl_name)}
		},
		<c:set var="processFlow" value="${currRegProcess.processFlow}"/>
		<c:set var="ck" value="CaseRegistry"/>
		<c:set var="dk" value="CaseRecord"/>
		<c:set var="sk" value="SkillRegistry"/>
		<c:set var="ok" value="CampaignRegistry"/>
		<c:set var="req" value="ReqNum"/>
		<c:set var="finish" value="Finished"/>

		<c:set var="key" value="${processFlow}${ck}${req}"/>
		{
			"inputId":"blsywc",
			"value":${pdfn:toJsonString(empty formDataMap.blsywc ? perMap[key]:formDataMap.blsywc)}
		},
		<c:set var="key" value="${processFlow}${ck}${finish}"/>
		{
			"inputId":"blsyjwc",
			"value":${pdfn:toJsonString(empty formDataMap.blsyjwc ? perMap[key]:formDataMap.blsyjwc)}
		},
		<c:set var="key" value="${processFlow}${ck}"/>
		{
			"inputId":"blswcbl",
			"value":${pdfn:toJsonString(empty formDataMap.blsywc ? perMap[key]:formDataMap.blsywc)}
		},
		<c:set var="key" value="${processFlow}${dk}${req}"/>
		{
			"inputId":"mzsywc",
			"value":${pdfn:toJsonString(empty formDataMap.mzsywc ? perMap[key]:formDataMap.mzsywc)}
		},
		<c:set var="key" value="${processFlow}${dk}${finish}"/>
		{
			"inputId":"mzsyjwc",
			"value":${pdfn:toJsonString(empty formDataMap.mzsyjwc ? perMap[key]:formDataMap.mzsyjwc)}
		},
		<c:set var="key" value="${processFlow}${dk}"/>
		{
			"inputId":"mzswcbl",
			"value":${pdfn:toJsonString(empty formDataMap.mzswcbl ? perMap[key]:formDataMap.mzswcbl)}
		},
		<c:set var="key" value="${processFlow}${sk}${req}"/>
		{
			"inputId":"czsywc",
			"value":${pdfn:toJsonString(empty formDataMap.czsywc ? perMap[key]:formDataMap.czsywc)}
		},
		<c:set var="key" value="${processFlow}${sk}${finish}"/>
		{
			"inputId":"czsyjwc",
			"value":${pdfn:toJsonString(empty formDataMap.czsyjwc ? perMap[key]:formDataMap.czsyjwc)}
		},
		<c:set var="key" value="${processFlow}${sk}"/>
		{
			"inputId":"czswcbl",
			"value":${pdfn:toJsonString(empty formDataMap.czswcbl ? perMap[key]:formDataMap.czswcbl)}
		},
		<c:set var="key" value="${processFlow}${ok}${req}"/>
		{
			"inputId":"hdsywc",
			"value":${pdfn:toJsonString(empty formDataMap.hdsywc ? perMap[key]:formDataMap.hdsywc)}
		},
		<c:set var="key" value="${processFlow}${ok}${finish}"/>
		{
			"inputId":"hdsyjwc",
			"value":${pdfn:toJsonString(empty formDataMap.hdsyjwc ? perMap[key]:formDataMap.hdsyjwc)}
		},
		<c:set var="key" value="${processFlow}${ok}"/>
		{
			"inputId":"hdswcbl",
			"value":${pdfn:toJsonString(empty formDataMap.hdswcbl ? perMap[key]:formDataMap.hdswcbl)}
		},
		{
			"inputId":"hdxs",
			"value":${pdfn:toJsonString(hdxs)}
		},
		<c:set var="theoreResult" value="${empty formDataMap['totalScore'] ? (empty outScore.theoryScore ? '':outScore.theoryScore):formDataMap['totalScore']}"/>
		{
			"inputId":"totalScore",
			"value":${pdfn:toJsonString(theoreResult)}
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
		},
		{
			"inputId":"szkskhxzztpj_name",
			"value":${pdfn:toJsonString(formDataMap.szkskhxzztpj_name)}
		}
		<c:if test="${not empty formDataMap.directorName}">
			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
			},
			{
				"inputId":"isAgree_name",
				"value":${pdfn:toJsonString(formDataMap.isAgree_name)}
			}
		</c:if>
		,{
			"inputId":"teacherName",
			"value":${pdfn:toJsonString(!empty formDataMap.teacherName?formDataMap.teacherName:tea.userName)}
		}
		,{
			"inputId":"teacherDate",
			"value":${pdfn:toJsonString(!empty formDataMap.teacherDate?formDataMap.teacherDate:(pdfn:getCurrDate()))}
		}
		<c:if test="${not empty formDataMap.directorName}">
			,{
				"inputId":"directorName",
				"value":${pdfn:toJsonString(formDataMap.directorName)}
			}
			,{
				"inputId":"directorDate",
				"value":${pdfn:toJsonString(formDataMap.directorDate)}
			}
		</c:if>
	]