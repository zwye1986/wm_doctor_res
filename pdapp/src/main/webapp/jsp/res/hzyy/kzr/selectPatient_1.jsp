<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>

        ,"datas":[

                {
                    "inputId":"TrueName",
                    "inputType":"text",
                    "label":"住院医师",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.TrueName)}
                },
                {
                    "inputId":"HosSecName",
                    "inputType":"text",
                    "label":"科室",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(cycleInfo.HosSecName )}
                },
                {
                    "inputId":"Patient_Name",
                    "inputType":"text",
                    "label":"病人姓名",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_Name )}
                },
                {
                    "inputId":"Patient_No",
                    "inputType":"text",
                    "label":"住院号",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_No )}
                },
                {
                    "inputId":"Patient_Remark",
                    "inputType":"text",
                    "label":"主要诊断",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_Remark)}
                },
					 {
						"inputId":"group1",
						"inputType":"group",
						"label":"一、问诊",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score1",
									"inputType":"text",
									"label":"1、注意语言态度与患者沟通<br/>2、思路清晰 (5分)",
									"tip":"1、注意语言态度与患者沟通<br/>2、思路清晰 ",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                       "readonly":true,
                                       "required":true,
                                       "value":"${itemScoreMap['score1']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group2",
						"inputType":"group",
						"label":"二、体格检查",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score2",
									"inputType":"text",
									"label":"1、重要项目吴遗漏<br/>2、手法规范<br/>3、专科检查  (10分)",
									"tip":"1、重要项目吴遗漏<br/>2、手法规范<br/>3、专科检查  ",
									"placeholder": "0~10",
									"verify": {
										"max": "10",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score2']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group3",
						"inputType":"group",
						"label":"三、进一步检查",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score3",
									"inputType":"text",
									"label":"明确、完整、合理(10分)",
									"tip":"明确、完整、合理",
									"placeholder": "0~10",
									"verify": {
										"max": "10",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score3']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group4",
						"inputType":"group",
						"label":"四、初步诊断和临床思维",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score4",
									"inputType":"text",
									"label":"正确(20分)",
									"tip":"正确",
									"placeholder": "0~20",
									"verify": {
										"max": "20",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score4']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group5",
						"inputType":"group",
						"label":"五、诊治计划",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score5",
									"inputType":"text",
									"label":"正确、及时、合理 (20分)",
									"tip":"正确、及时、合理 ",
									"placeholder": "0~20",
									"verify": {
										"max": "20",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score5']}"
								}
					 	]
					 },
					 {
						"inputId":"group6",
						"inputType":"group",
						"label":"六、门诊病历",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score6",
									"inputType":"text",
									"label":"1、一般项目<br/>2、主诉<br/>3、现病史<br/>4、既往史(5分)",
									"tip":"1、一般项目<br/>2、主诉<br/>3、现病史<br/>4、既往史",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score6']}"
								},
								{
									"inputId":"score7",
									"inputType":"text",
									"label":"5、体格检查(5分)",
									"tip":"5、体格检查",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score7']}"
								},
								{
									"inputId":"score8",
									"inputType":"text",
									"label":"6、初步诊断和进一步检查(5分)",
									"tip":"6、初步诊断和进一步检查",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score8']}"
								},
								{
									"inputId":"score9",
									"inputType":"text",
									"label":"7、治疗（名称、剂量、用法）查(5分)",
									"tip":"7、治疗（名称、剂量、用法）",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score9']}"
								},
								{
									"inputId":"score10",
									"inputType":"text",
									"label":"8、记录完整、字迹清晰、签名(5分)",
									"tip":"8、记录完整、字迹清晰、签名",
									"placeholder": "0~5",
									"verify": {
										"max": "5",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score10']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group7",
						"inputType":"group",
						"label":"七、提问",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score11",
									"inputType":"text",
									"label":"考官可提问相关问题至少四个，如：鉴别诊断、病因等 (10分)",
									"tip":"考官可提问相关问题至少四个，如：鉴别诊断、病因等 ",
									"placeholder": "0~10",
									"verify": {
										"max": "10",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score11']}"
								}
					 	]
					 }
                     ,
					 {
						"inputId":"group8",
						"inputType":"group",
						"label":"八、考核用时 ",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score12",
									"inputType":"text",
									"label":"30分钟(每超过2分钟扣1分) ",
									"tip":"每超过2分钟扣1分 ",
									"placeholder": "0~100",
									"verify": {
										"max":"100",
										"type": "int",
                                        "isAdd":"N"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score12']}"
								}
					 	]
					 },
                {
                    "inputId":"Patient_Score",
                    "inputType":"text",
                    "label":"分数",
                    "readonly":true,
                    "required":true,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_Score )}
                },
                {
                    "inputId":"Patient_ExaminerOne",
                    "inputType":"text",
                    "label":"考官一",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_ExaminerOne)}
                },
                {
                    "inputId":"Patient_ExaminerTwo",
                    "inputType":"text",
                    "label":"考官二",
                    "readonly":true,
                    "required":false,
                    "value":${pdfn:toJsonString(outPatientMap.Patient_ExaminerTwo)}
                },
                {
                    "inputId":"imglist",
                    "inputType":"imgs",
                    "label":"附件上传",
                    "readonly":true,
                    "required":true,
                    "haveScore":false,
                    "max":"3",
                    "imgs":[
                        <c:forEach items="${fileList}" var="file" varStatus="itemStatus">
                        {
                            "fileFlow":${pdfn:toJsonString(file.AttachID)},
                            <c:set var="fileUrl" value="${baseUrl}/${file.AttachPath}/${file.AttachFileName}"></c:set>
                            "fileUrl":${pdfn:toJsonString(fileUrl)},
                            "fileName":${pdfn:toJsonString(file.AttachName)}
                        }
                        <c:if test="${(not itemStatus.last) }">
                        ,
                        </c:if>
                        </c:forEach>
                    ]
                }
        ]
