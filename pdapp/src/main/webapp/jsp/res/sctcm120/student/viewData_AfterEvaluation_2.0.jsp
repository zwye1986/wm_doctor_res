<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
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
			"inputId":"zsgjflfg",
			"inputType":"text",
			"label":"遵守国家法律法规、医院规章制度（1分）",
			"readonly":true,
			"required":true,
			"placeholder": "0~1",
			"verify": {
				"max": "1",
				"type": "double"
			}
		},
		{
			"inputId":"lxgwzz",
			"inputType":"text",
			"label":"履行岗位职责（1分）",
			"readonly":true,
			"required":true,
			"placeholder": "0~1",
			"verify": {
				"max": "1",
				"type": "double"
			}
		},
		{
			"inputId":"ybrwzx",
			"inputType":"text",
			"label":"以病人为中心，体现人文关怀（1分）",
			"readonly":true,
			"required":true,
			"placeholder": "0~1",
			"verify": {
				"max": "1",
				"type": "double"
			}
		},
		{
			"inputId":"rjgtbdnl",
			"inputType":"text",
			"label":"人际（医患）沟通和表达能力（1分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~1",
			"verify": {
				"max": "1",
				"type": "double"
			}
		},
		{
			"inputId":"tjxzjsxm",
			"inputType":"text",
			"label":"团结协作精神(1分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~1",
			"verify": {
				"max": "1",
				"type": "double"
			}
		},
		{
			"inputId":"jbllzwcd",
			"inputType":"text",
			"label":"文书书写（每个月手写2份大病历：入院记录+首次病程记录，需带教老师修改签字，规培秘书审核完后病历原件交由学员留存备查）(15分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~15",
			"verify": {
				"max": "15",
				"type": "double"
			}
		},
		{
			"inputId":"lczlnl",
			"inputType":"text",
			"label":"临床诊疗能力（5分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~5",
			"verify": {
				"max": "5",
				"type": "double"
			}
		},
		{
			"inputId":"jjclnl",
			"inputType":"text",
			"label":"危重病人的识别及紧急处理能力（3分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~3",
			"verify": {
				"max": "3",
				"type": "double"
			}
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
			"inputId":"blsscore",
			"inputType":"text",
			"label":"病历完成得分（15分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~15",
			"verify": {
				"max": "15",
				"type": "double"
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
			"inputId":"mzsscore",
			"inputType":"text",
			"label":"门诊病例完成得分（15分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~15",
			"verify": {
				"max": "15",
				"type": "double"
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
			"inputId":"czsscore",
			"inputType":"text",
			"label":"操作完成得分（10分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~10",
			"verify": {
				"max": "10",
				"type": "double"
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
			"inputId":"hdsscore",
			"inputType":"text",
			"label":"参加活动完成得分（10分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~10",
			"verify": {
				"max": "10",
				"type": "double"
			}
		},
		{
			"inputId":"ylsg",
			"inputType":"text",
			"label":"医疗事故（5分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~5",
			"verify": {
				"max": "5",
				"type": "double"
			}
		},
		{
			"inputId":"pxkhdjsc",
			"inputType":"text",
			"label":"培训考核登记手册（5分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~5",
			"verify": {
				"max": "5",
				"type": "double"
			}
		},
		{
			"inputId":"kq",
			"inputType":"text",
			"label":"考勤：组织纪律、有无旷工、请假、迟到、早退、脱岗（12分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~12",
			"verify": {
				"max": "12",
				"type": "double"
			}
		},
		{
			"inputId":"hj",
			"inputType":"text",
			"label":"合计（100分）",
			"readonly":true, 		"required":true,
			"placeholder": "0~100",
			"verify": {
				"max": "100",
				"type": "double"
			}
		},
		{
			"inputId":"totalScore",
			"inputType":"text",
			"label":"理论成绩",
			"readonly":true,
			"required":true,
			"placeholder": "0~100",
			"verify": {
				"max": "100",
				"type": "int"
			}
		},
		{
			"inputId":"examiner11",
			"inputType":"text",
			"label":"考官1",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"examiner22",
			"inputType":"text",
			"label":"考官2",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"skillName",
			"inputType":"text",
			"label":"技能考核名称",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"score",
			"inputType":"text",
			"label":"得分",
			"readonly":true,
			"required":true
		},
		{
			"inputId":"examiner1",
			"inputType":"text",
			"label":"考官1",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"examiner2",
			"inputType":"text",
			"label":"考官2",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"szkskhxzztpj",
			"inputType":"select",
			"label":"所在科室考核小组总体评价",
			"tip":"所在科室考核小组总体评价",
			"readonly":true, 		"required":true,	
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
			"inputId":"zsgjflfg",
			"value":${pdfn:toJsonString(formDataMap.zsgjflfg)}
		},
		{
			"inputId":"lxgwzz",
			"value":${pdfn:toJsonString(formDataMap.lxgwzz)}
		},
		{
			"inputId":"ybrwzx",
			"value":${pdfn:toJsonString(formDataMap.ybrwzx)}
		},
		{
			"inputId":"rjgtbdnl",
			"value":${pdfn:toJsonString(formDataMap.rjgtbdnl)}
		},
		{
			"inputId":"tjxzjsxm",
			"value":${pdfn:toJsonString(formDataMap.tjxzjsxm)}
		},
		{
			"inputId":"jbllzwcd",
			"value":${pdfn:toJsonString(formDataMap.jbllzwcd)}
		},
		{
			"inputId":"lczlnl",
			"value":${pdfn:toJsonString(formDataMap.lczlnl)}
		},
		{
			"inputId":"jjclnl",
			"value":${pdfn:toJsonString(formDataMap.jjclnl)}
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
		<c:set var="blswcbl" value="${empty formDataMap.blswcbl ? perMap[key]:formDataMap.blswcbl}"/>
		{
			"inputId":"blswcbl",
			"value":${pdfn:toJsonString(blswcbl)}
		},
		{
			"inputId":"blsscore",
			"value":${pdfn:toJsonString(formDataMap.blsscore)}
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
		<c:set var="mzswcbl" value="${empty formDataMap.mzswcbl ? perMap[key]:formDataMap.mzswcbl}"/>
		{
			"inputId":"mzswcbl",
			"value":${pdfn:toJsonString(mzswcbl)}
		},
		{
			"inputId":"mzsscore",
			"value":${pdfn:toJsonString(formDataMap.mzsscore)}
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
		<c:set var="czswcbl" value="${empty formDataMap.czswcbl ? perMap[key]:formDataMap.czswcbl}"/>
		{
			"inputId":"czswcbl",
			"value":${pdfn:toJsonString(czswcbl)}
		},
		{
			"inputId":"czsscore",
			"value":${pdfn:toJsonString(formDataMap.czsscore)}
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
		<c:set var="hdswcbl" value="${empty formDataMap.hdswcbl ? perMap[key]:formDataMap.hdswcbl}"/>
		{
			"inputId":"hdswcbl",
			"value":${pdfn:toJsonString(hdswcbl)}
		},
		{
			"inputId":"hdsscore",
			"value":${pdfn:toJsonString(formDataMap.hdsscore)}
		},
		{
			"inputId":"ylsg",
			"value":${pdfn:toJsonString(formDataMap.ylsg)}
		},
		{
			"inputId":"pxkhdjsc",
			"value":${pdfn:toJsonString(formDataMap.pxkhdjsc)}
		},
		{
			"inputId":"kq",
			"value":${pdfn:toJsonString(formDataMap.kq)}
		},
		{
			"inputId":"hj",
			"value":${pdfn:toJsonString(formDataMap.hj)}
		},
		{
			"inputId":"totalScore",
			"value":${pdfn:toJsonString(formDataMap['totalScore'])}
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
			"inputId":"examiner11",
			"value":${pdfn:toJsonString(formDataMap.examiner11)}
		},
		{
			"inputId":"examiner22",
			"value":${pdfn:toJsonString(formDataMap.examiner22)}
		},
		{
			"inputId":"szkskhxzztpj",
			"value":${pdfn:toJsonString(formDataMap.szkskhxzztpj_id)}
		}
		<c:if test="${not empty formDataMap.directorName}">
			,
			{
				"inputId":"isAgree",
				"value":${pdfn:toJsonString(formDataMap.isAgree_id)}
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