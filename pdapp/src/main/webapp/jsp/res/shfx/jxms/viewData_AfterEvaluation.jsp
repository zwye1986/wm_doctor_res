<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"提交"
	},
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
			"inputId":"bssx",
			"inputType":"text",
			"label":"病史书写",
			"percent":"0.1",
"verify": {
"max": "100",
"type": "float"
},
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"bssx_Label",
			"inputType":"text",
			"label":"病史书写（按10%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"ydyf",
			"inputType":"text",
			"label":"医德医风",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.15",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"ydyf_Label",
			"inputType":"text",
			"label":"医德医风（按15%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"cqqk",
			"inputType":"text",
			"label":"出勤情况",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.1",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"cqqk_Label",
			"inputType":"text",
			"label":"出勤情况（按10%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"lzjh",
			"inputType":"text",
			"label":"完成轮转计划、登记手册填写",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.1",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"lzjh_Label",
			"inputType":"text",
			"label":"完成轮转计划、登记手册填写（按10%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"theoreResult",
			"inputType":"text",
			"label":"理论考试",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.2",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"theoreResult_Label",
			"inputType":"text",
			"label":"理论考试（按20%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"MedicalHistoryScore",
			"inputType":"text",
			"label":"病史采集",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.05",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"MedicalHistoryScore_Label",
			"inputType":"text",
			"label":"病史采集（按5%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"PhysiqueScore",
			"inputType":"text",
			"label":"体格检查",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.05",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"PhysiqueScore_Label",
			"inputType":"text",
			"label":"体格检查（按5%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"CaseAnalysisScore",
			"inputType":"text",
			"label":"病例分析",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.15",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"CaseAnalysisScore_Label",
			"inputType":"text",
			"label":"病例分析（按15%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"ClinicalScore",
			"inputType":"text",
			"label":"临床操作",
"verify": {
"max": "100",
"type": "float"
},
			"percent":"0.1",
			"readonly":${isAudit}, 		"required":true
		},
		{
			"inputId":"ClinicalScore_Label",
			"inputType":"text",
			"label":"临床操作（按10%折算后）",
			"readonly":true, 		"required":true
		},
		{
			"inputId":"ckkhScore",
			"inputType":"text",
			"label":"总分",
			"readonly":true, 		"required":true
		},
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
		,
		{
			"inputId":"professionalBase",
			"inputType":"text",
			"label":"基地秘书签名",
			"readonly":true, 		"required":false
		}
		,
		{
			"inputId":"baseDate",
			"inputType":"text",
			"label":"基地秘书签名日期",
			"readonly":true, 		"required":false
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
			"inputId":"bssx",
			"value":${pdfn:toJsonString(formDataMap.bssx)}
		},
		{
			"inputId":"bssx_Label",
			"value":${pdfn:toJsonString(formDataMap.bssx_Label)}
		},
		{
			"inputId":"ydyf",
			"value":${pdfn:toJsonString(formDataMap.ydyf)}
		},
		{
			"inputId":"ydyf_Label",
			"value":${pdfn:toJsonString(formDataMap.ydyf_Label)}
		},
		{
			"inputId":"cqqk",
			"value":${pdfn:toJsonString(formDataMap.cqqk)}
		},
		{
			"inputId":"cqqk_Label",
			"value":${pdfn:toJsonString(formDataMap.cqqk_Label)}
		},
		{
			"inputId":"lzjh",
			"value":${pdfn:toJsonString(formDataMap.lzjh)}
		},
		{
			"inputId":"lzjh_Label",
			"value":${pdfn:toJsonString(formDataMap.lzjh_Label)}
		},
		{
			"inputId":"theoreResult",
			"value":${pdfn:toJsonString(formDataMap.theoreResult)}
		},
		{
			"inputId":"theoreResult_Label",
			"value":${pdfn:toJsonString(formDataMap.theoreResult_Label)}
		},
		{
		"inputId":"MedicalHistoryScore",
		"value":${pdfn:toJsonString(empty formDataMap.MedicalHistoryScore?MedicalHistoryScore:formDataMap.MedicalHistoryScore)}
		},
		{
		"inputId":"MedicalHistoryScore_Label",
		"value":${pdfn:toJsonString(empty formDataMap.MedicalHistoryScore_Label?MedicalHistoryScore_Label:formDataMap.MedicalHistoryScore_Label)}
		},
		{
		"inputId":"PhysiqueScore",
		"value":${pdfn:toJsonString(empty formDataMap.PhysiqueScore ? PhysiqueScore:formDataMap.PhysiqueScore)}
		},
		{
		"inputId":"PhysiqueScore_Label",
		"value":${pdfn:toJsonString(empty formDataMap.PhysiqueScore_Label?PhysiqueScore_Label:formDataMap.PhysiqueScore_Label)}
		},
		{
		"inputId":"CaseAnalysisScore",
		"value":${pdfn:toJsonString(empty formDataMap.CaseAnalysisScore?CaseAnalysisScore:formDataMap.CaseAnalysisScore)}
		},
		{
		"inputId":"CaseAnalysisScore_Label",
		"value":${pdfn:toJsonString(empty formDataMap.CaseAnalysisScore_Label?CaseAnalysisScore_Label:formDataMap.CaseAnalysisScore_Label)}
		},
		{
		"inputId":"ClinicalScore",
		"value":${pdfn:toJsonString(empty formDataMap.ClinicalScore?ClinicalScore:formDataMap.ClinicalScore)}
		},
		{
		"inputId":"ClinicalScore_Label",
		"value":${pdfn:toJsonString(empty formDataMap.ClinicalScore_Label?ClinicalScore_Label:formDataMap.ClinicalScore_Label)}
		},
		{
			"inputId":"ckkhScore",
			"value":${pdfn:toJsonString(formDataMap.ckkhScore)}
		}
		,{
			"inputId":"teacherName",
			"value":${pdfn:toJsonString(formDataMap.teacherName)}
		}
		,{
			"inputId":"teacherDate",
			"value":${pdfn:toJsonString(formDataMap.teacherDate)}
		}
		,{
			"inputId":"directorName",
			"value":${pdfn:toJsonString(!empty formDataMap.directorName?formDataMap.directorName:tea.userName)}
		}
		,{
			"inputId":"directorDate",
			"value":${pdfn:toJsonString(!empty formDataMap.directorDate?formDataMap.directorDate:(pdfn:getCurrDate()))}
		}
		,{
			"inputId":"professionalBase",
			"value":${pdfn:toJsonString(formDataMap.professionalBase)}
		}
		,{
			"inputId":"baseDate",
			"value":${pdfn:toJsonString(formDataMap.baseDate)}
		}
	]