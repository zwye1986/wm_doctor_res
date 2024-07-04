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
						"label":"问诊",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score1",
									"inputType":"text",
									"label":"一般项目：姓名、年龄、性别、职业 （1分）",
									"tip":"一般项目：姓名、年龄、性别、职业  ",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score1']}"
								},
								{
									"inputId":"score2",
									"inputType":"text",
									"label":"[现病史]起病情况与患病时间（2分）",
									"tip":"[现病史]起病情况与患病时间 ",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score2']}"
								},
								{
									"inputId":"score3",
									"inputType":"text",
									"label":"[现病史]主要症状特点（出现的部位、性质、持续时间、程度、加重与缓解）（4分）",
									"tip":"[现病史]主要症状特点（出现的部位、性质、持续时间、程度、加重与缓解）",
									"placeholder": "0~4",
									"verify": {
                                        "max":"4",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score3']}"
								},
								{
									"inputId":"score4",
									"inputType":"text",
									"label":"[现病史]病情的发展与演变（4分）",
									"tip":"[现病史]病情的发展与演变",
									"placeholder": "0~4",
									"verify": {
                                        "max":"4",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score4']}"
								},
								{
									"inputId":"score5",
									"inputType":"text",
									"label":"[现病史]诊治经过（诊疗单位、措施、用药情况及效果）（3分）",
									"tip":"[现病史]诊治经过（诊疗单位、措施、用药情况及效果）",
									"placeholder": "0~3",
									"verify": {
                                        "max":"3",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score5']}"
								},
								{
									"inputId":"score6",
									"inputType":"text",
									"label":"[现病史]伴随症状（包括重要的阴性症状）（3分）",
									"tip":"[现病史]伴随症状（包括重要的阴性症状）",
									"placeholder": "0~3",
									"verify": {
                                        "max":"3",
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
									"label":"[现病史]一般状况（精神、食欲、体重、二便、睡眠）（2分）",
									"tip":"[现病史]一般状况（精神、食欲、体重、二便、睡眠）",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
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
									"label":"既往史：健康状况、曾患疾病、外伤、手术、过敏史 （2分）",
									"tip":"既往史：健康状况、曾患疾病、外伤、手术、过敏史 ",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
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
									"label":"个人史：职业、酒烟史、月经生育史、婚姻史（2分）",
									"tip":"个人史：职业、酒烟史、月经生育史、婚姻史",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
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
									"label":"家族史：特别是本病相关的疾病（1分）",
									"tip":"家族史：特别是本病相关的疾病",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score10']}"
								},
								{
									"inputId":"score11",
									"inputType":"text",
									"label":"尊重患者、爱伤观念、与病人交流的能力（无暗示性语言、耐心聆听）、思路清晰（6分）",
									"tip":"尊重患者、爱伤观念、与病人交流的能力（无暗示性语言、耐心聆听）、思路清晰",
									"placeholder": "0~6",
									"verify": {
                                        "max":"6",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score11']}"
								}
					 	]
					 },
					 {
						"inputId":"group2",
						"inputType":"group",
						"label":"体格检查",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score12",
									"inputType":"text",
									"label":"T、P、R、BP （2分）",
									"tip":"T、P、R、BP  ",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score12']}"
								},
								{
									"inputId":"score13",
									"inputType":"text",
									"label":"一般状况（意识、发育、营养、体位、面容） （1分）",
									"tip":"一般状况（意识、发育、营养、体位、面容） ",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score13']}"
								},
								{
									"inputId":"score14",
									"inputType":"text",
									"label":"皮肤粘膜（水肿、黄疸、出血、皮疹、蜘蛛痣）（1分）",
									"tip":"皮肤粘膜（水肿、黄疸、出血、皮疹、蜘蛛痣）",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score14']}"
								},
								{
									"inputId":"score15",
									"inputType":"text",
									"label":"头部（毛发、瞳孔、结膜、巩膜、耳、鼻、口腔）（2分）",
									"tip":"头部（毛发、瞳孔、结膜、巩膜、耳、鼻、口腔）",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score15']}"
								},
								{
									"inputId":"score16",
									"inputType":"text",
									"label":"颈部（血管、气管、甲状腺）（2分）",
									"tip":"颈部（血管、气管、甲状腺）",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score16']}"
								},
								{
									"inputId":"score17",
									"inputType":"text",
									"label":"淋巴结（头颈部、锁骨上、腋窝、腹股沟） （2分）",
									"tip":"淋巴结（头颈部、锁骨上、腋窝、腹股沟） ",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score17']}"
								},
								{
									"inputId":"score18",
									"inputType":"text",
									"label":"肺部（2分）",
									"tip":"肺部",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score18']}"
								},
								{
									"inputId":"score19",
									"inputType":"text",
									"label":"心脏（2分）",
									"tip":"心脏",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score19']}"
								},
								{
									"inputId":"score20",
									"inputType":"text",
									"label":"腹部（2分）",
									"tip":"腹部",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score20']}"
								},
								{
									"inputId":"score21",
									"inputType":"text",
									"label":"脊柱与四肢（1分）",
									"tip":"脊柱与四肢",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score21']}"
								},
								{
									"inputId":"score22",
									"inputType":"text",
									"label":"神经系统（1分）",
									"tip":"神经系统",
									"placeholder": "0~1",
									"verify": {
                                        "max":"1",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score22']}"
								},
								{
									"inputId":"score23",
									"inputType":"text",
									"label":"专科查体部分 （4分）",
									"tip":"专科查体部分 ",
									"placeholder": "0~4",
									"verify": {
                                        "max":"4",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score23']}"
								},
								{
									"inputId":"score24",
									"inputType":"text",
									"label":"其他重要的阳性及阴性体征 （2分）",
									"tip":"其他重要的阳性及阴性体征 ",
									"placeholder": "0~2",
									"verify": {
                                        "max":"2",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score24']}"
								},
								{
									"inputId":"score25",
									"inputType":"text",
									"label":"熟练程度、爱伤观念、与病人交流的能力 （6分）",
									"tip":"熟练程度、爱伤观念、与病人交流的能力 ",
									"placeholder": "0~6",
									"verify": {
                                        "max":"6",
										"type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score25']}"
								}
					 	]
					 },
					 {
						"inputId":"group3",
						"inputType":"group",
						"label":"医嘱",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score26",
									"inputType":"text",
									"label":"[长期医嘱]护理  (1分)",
									"tip":"[长期医嘱]护理 ",
									"placeholder": "0~1",
									"verify": {
										"max": "1",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score26']}"
								},
								{
									"inputId":"score27",
									"inputType":"text",
									"label":"[长期医嘱]饮食(1分)",
									"tip":"[长期医嘱]饮食 ",
									"placeholder": "0~1",
									"verify": {
										"max": "1",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score27']}"
								},
								{
									"inputId":"score28",
									"inputType":"text",
									"label":"[长期医嘱]用药符合诊断(4分)",
									"tip":"[长期医嘱]用药符合诊断 ",
									"placeholder": "0~4",
									"verify": {
										"max": "4",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score28']}"
								},
								{
									"inputId":"score29",
									"inputType":"text",
									"label":"[长期医嘱]无重复用药 (2分)",
									"tip":"[长期医嘱]无重复用药  ",
									"placeholder": "0~2",
									"verify": {
										"max": "2",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score29']}"
								},
								{
									"inputId":"score30",
									"inputType":"text",
									"label":"[长期医嘱]无遗漏重要用药 (3分)",
									"tip":"[长期医嘱]无遗漏重要用药  ",
									"placeholder": "0~3",
									"verify": {
										"max": "3",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score30']}"
								},
								{
									"inputId":"score31",
									"inputType":"text",
									"label":"[长期医嘱]药物剂量和方法正确(4分)",
									"tip":"[长期医嘱]药物剂量和方法正确",
									"placeholder": "0~4",
									"verify": {
										"max": "4",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score31']}"
								},
								{
									"inputId":"score32",
									"inputType":"text",
									"label":"[临时医嘱]相关检验、检查合理(6分)",
									"tip":"[临时医嘱]相关检验、检查合理",
									"placeholder": "0~6",
									"verify": {
										"max": "6",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score32']}"
								},
								{
									"inputId":"score33",
									"inputType":"text",
									"label":"[临时医嘱]无过度检查、检验(2分)",
									"tip":"[临时医嘱]无过度检查、检验",
									"placeholder": "0~2",
									"verify": {
										"max": "2",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score33']}"
								},
								{
									"inputId":"score34",
									"inputType":"text",
									"label":"[临时医嘱]用药合理、规范（符合诊断、无重复用药、剂量与方法正确）(4分)",
									"tip":"[临时医嘱]用药合理、规范（符合诊断、无重复用药、剂量与方法正确）",
									"placeholder": "0~4",
									"verify": {
										"max": "4",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score34']}"
								},
								{
									"inputId":"score35",
									"inputType":"text",
									"label":"[临时医嘱]治疗合理(3分)",
									"tip":"[临时医嘱]治疗合理",
									"placeholder": "0~3",
									"verify": {
										"max": "3",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score35']}"
								}
					 	]
					 },
					 {
						"inputId":"group4",
						"inputType":"group",
						"label":"病历书写",
						"readonly":true,
						"required":true,
						"haveScore":false,
						"items":[
								{
									"inputId":"score36",
									"inputType":"text",
									"label":"诊断（正确、规范、无遗漏诊断）(2分)",
									"tip":"诊断（正确、规范、无遗漏诊断）",
									"placeholder": "0~2",
									"verify": {
										"max": "2",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score36']}"
								},
								{
									"inputId":"score37",
									"inputType":"text",
									"label":"主诉（准确）(4分)",
									"tip":"主诉（准确）",
									"placeholder": "0~4",
									"verify": {
										"max": "4",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score37']}"
								},
								{
									"inputId":"score38",
									"inputType":"text",
									"label":"现病史（完整、准确）(4分)",
									"tip":"现病史（完整、准确）",
									"placeholder": "0~4",
									"verify": {
										"max": "4",
										 "type": "int"
									},
									"haveScore":true,
                                    "readonly":true,
                                    "required":true,
                                    "value":"${itemScoreMap['score38']}"
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
