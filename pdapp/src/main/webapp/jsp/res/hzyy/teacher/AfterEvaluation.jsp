<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
       <c:if test="${!read}">
            ,"action":{
                "save":"保存"
            }
        </c:if>
	,"datas":[
                {
                    "inputId":"truename",
                    "inputType":"text",
                    "label":"姓　　名",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(afterEva.truename)}
                },
                {
                    "inputId":"SpName",
                    "inputType":"text",
                    "label":"专　　业",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(afterEva.SpName )}
                },
                {
                    "inputId":"starttime",
                    "inputType":"text",
                    "label":"轮转开始时间",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(afterEva.starttime )}
                },
                {
                    "inputId":"endtime",
                    "inputType":"text",
                    "label":"轮转结束时间",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(afterEva.endtime )}
                },
                {
                    "inputId":"hossecname",
                    "inputType":"text",
                    "label":"轮转科室",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(afterEva.hossecname )}
                },
                {
                    "inputId":"TecDate",
                    "inputType":"text",
                    "label":"考核时间",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString( empty cycleOutInfo.TecDate?pdfn:getCurrDate():cycleOutInfo.TecDate )}
                },
                {
                    "inputId":"TecName",
                    "inputType":"text",
                    "label":"带教老师",
                    "readonly":${read},
                    "required":true,
                    "value":${pdfn:toJsonString( afterEva.TecName )}
                },
				{
					"inputId":"group1",
					"inputType":"group",
					"label":"政治思想与组织纪律",
					"readonly":true,
					"required":true,
					"haveScore":false,
					"items":[
							{
								"inputId":"ZZSX",
								"inputType":"text",
								"label":"遵纪守法，无旷工、迟到、早退、脱岗、无故不参加学生活动(3分)",
								"tip":"遵纪守法，无旷工、迟到、早退、脱岗、无故不参加学生活动",
								"placeholder": "0~3",
								"verify": {
									"max": "3",
									 "type": "int"
								},
								"haveScore":true,
                                "readonly":${read},
                                "required":true,
                                "value":"${cycleOutInfo['ZZSX']}"
							}
				 	]
				 },
				{
					"inputId":"group2",
					"inputType":"group",
					"label":"医德医风",
					"readonly":true,
					"required":true,
					"haveScore":false,
					"items":[
							{
								"inputId":"YDYF_BZ",
								"inputType":"text",
								"label":"完成病种、例数数量情况 (3分)",
								"tip":"完成病种、例数数量情况 ",
								"placeholder": "0~3",
								"verify": {
									"max": "3",
									 "type": "int"
								},
								"haveScore":true,
                                "readonly":${read},
                                "required":true,
                                "value":"${cycleOutInfo['YDYF_BZ']}"
							},
							{
								"inputId":"YDYF_CZ",
								"inputType":"text",
								"label":"完成技能操作数量情况(4分)",
								"tip":"完成技能操作数量情况",
								"placeholder": "0~4",
								"verify": {
									"max": "4",
									 "type": "int"
								},
								"haveScore":true,
                                "readonly":${read},
                                "required":true,
                                "value":"${cycleOutInfo['YDYF_CZ']}"
							}
				 	]
				 },
                <c:if test="${afterEva.SectionType eq '0'}">
                        {
                            "inputId":"CKLLKH",
                            "inputType":"text",
                            "label":"出科理论考核 (40分)",
                            "tip":"出科理论考核 ",
                            "placeholder": "0~40",
                            "verify": {
                                "max": "40",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":${read},
                            "required":true,
                            "value":"${empty cycleOutInfo['CKLLKH'] ?afterEva['TheoryScore']:cycleOutInfo['CKLLKH']}"
                        },
                        <c:if test="${isNeed eq 'Y'}">
                        {
                            "inputId":"JZBR",
                            "inputType":"text",
                            "label":"接诊病人(20分)",
                            "tip":"接诊病人",
                            "placeholder": "0~20",
                            "verify": {
                                "max": "20",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['JZBR'] ?afterEva['Patient_Score']:cycleOutInfo['JZBR']}"
                        },
                        {
                            "inputId":"CKJNCJ_Name",
                            "inputType":"text",
                            "label":"出科技能名称",
                            "tip":"出科技能名称",
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ_Name'] ?afterEva['Skill_Name']:cycleOutInfo['CKJNCJ_Name']}"
                        },
                        {
                            "inputId":"CKJNCJ",
                            "inputType":"text",
                            "label":"出科技能成绩(20分)",
                            "tip":"出科技能成绩",
                            "placeholder": "0~20",
                            "verify": {
                                "max": "20",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ'] ?afterEva['Skill_Score']:cycleOutInfo['CKJNCJ']}"
                        },
                        </c:if>
                        <c:if test="${isNeed ne 'Y'}">
                        {
                            "inputId":"JZBR",
                            "inputType":"text",
                            "label":"接诊病人(0分)",
                            "tip":"接诊病人",
                            "placeholder": "0~0",
                            "verify": {
                                "max": "0",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"0"
                        },
                        {
                            "inputId":"CKJNCJ_Name",
                            "inputType":"text",
                            "label":"出科技能名称",
                            "tip":"出科技能名称",
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ_Name'] ?afterEva['Skill_Name']:cycleOutInfo['CKJNCJ_Name']}"
                        },
                        {
                            "inputId":"CKJNCJ",
                            "inputType":"text",
                            "label":"出科技能成绩(40分)",
                            "tip":"出科技能成绩",
                            "placeholder": "0~40",
                            "verify": {
                                "max": "40",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ'] ?afterEva['Skill_Score']:cycleOutInfo['CKJNCJ']}"
                        },
                        </c:if>
                </c:if>
                <c:if test="${afterEva.SectionType eq '1'}">

                        {
                            "inputId":"CKLLKH",
                            "inputType":"text",
                            "label":"出科理论考核 (50分)",
                            "tip":"出科理论考核 ",
                            "placeholder": "0~50",
                            "verify": {
                                "max": "50",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":${read},
                            "required":true,
                            "value":"${empty cycleOutInfo['CKLLKH'] ?afterEva['TheoryScore']:cycleOutInfo['CKLLKH']}"
                        },
                        {
                            "inputId":"CKJNCJ_Name",
                            "inputType":"text",
                            "label":"出科技能名称",
                            "tip":"出科技能名称",
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ_Name'] ?afterEva['Skill_Name']:cycleOutInfo['CKJNCJ_Name']}"
                        },
                        {
                            "inputId":"CKJNCJ",
                            "inputType":"text",
                            "label":"出科技能成绩(30分)",
                            "tip":"出科技能成绩",
                            "placeholder": "0~30",
                            "verify": {
                                "max": "30",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":true,
                            "required":true,
                            "value":"${empty cycleOutInfo['CKJNCJ'] ?afterEva['Skill_Score']:cycleOutInfo['CKJNCJ']}"
                        },
                </c:if>
                <c:if test="${afterEva.SectionType eq '2'}">

                        {
                            "inputId":"CKLLKH",
                            "inputType":"text",
                            "label":"出科理论考核 (80分)",
                            "tip":"出科理论考核 ",
                            "placeholder": "0~80",
                            "verify": {
                                "max": "80",
                                "type": "float"
                            },
                            "haveScore":true,
                            "readonly":${read},
                            "required":true,
                            "value":"${empty cycleOutInfo['CKLLKH'] ?afterEva['TheoryScore']:cycleOutInfo['CKLLKH']}"
                        },
                </c:if>
                {
                    "inputId":"JXHD",
                    "inputType":"text",
                    "label":"参加教学活动 (10分)<br/>参加例数：${jxhd}例",
                    "tip":"参加教学活动 (10分)<br/>参加例数：${jxhd}例",
                    "placeholder": "0~10",
                    "verify": {
                        "max": "10",
                        "type": "int"
                    },
                    "haveScore":true,
                    "readonly":${read},
                    "required":true,
                    "value":"${cycleOutInfo['JXHD'] }"
                },
                {
                    "inputId":"Score",
                    "inputType":"text",
                    "label":"考核总成绩",
                    "tip":"考核总成绩",
                    "placeholder": "0~100",
                    "verify": {
                        "max": "100",
                        "type": "float"
                    },
                    "haveScore":false,
                    "readonly":true,
                    "required":true,
                    "value":"${cycleOutInfo['Score'] }"
                },
                {
                    "inputId":"YLCC",
                    "inputType":"select",
                    "label":"医疗差错、事故",
                    "tip":"医疗差错、事故",
                    "options":[
                        {
                            "optionId":"0",
                            "optionDesc":"无"
                        },
                        {
                            "optionId":"1",
                            "optionDesc":"有"
                        }
                    ],
                    "readonly":${read},
                    "required":true,
                    "value":"${cycleOutInfo['YLCC']}"
                },
                {
                    "inputId":"TecComment",
                    "inputType":"textarea",
                    "label":"带教老师评语",
                    "tip":"带教老师评语",
                    "readonly":${read},
                    "required":true,
                    "value":"${cycleOutInfo['TecComment'] }"
                }
                ,
                {
                    "inputId":"ProfesserComment",
                    "inputType":"textarea",
                    "label":"教学秘书/科主任评语",
                    "tip":"教学秘书/科主任评语",
                    "readonly":true,
                    "required":false,
                    "value":"${cycleOutInfo['ProfesserComment'] }"
                }
         ]
    </c:if>
}