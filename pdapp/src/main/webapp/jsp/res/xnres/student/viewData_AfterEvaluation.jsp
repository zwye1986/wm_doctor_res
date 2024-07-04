<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test="${!isAudit}">
		"action":{
			"save":"提交"
		},
	</c:if>
	"inputs":[
				{
					"inputId":"departmentName",
					"inputType":"text",
					"label":"轮转科室",
					"readonly":true,
					"required":true,
					"align":"left"
				},
				{
					"inputId":"april",
					"inputType":"text",
					"label":"轮转时间",
					"readonly":true,
					"required":true,
					"align":"left"
				},
				{
					"inputId":"responsibility",
					"inputType":"text",
					"label":"工作责任心,无差错(20分)",
					"tip":"差错一次扣10分,重大医疗纠纷扣20分",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"attitude",
					"inputType":"text",
					"label":"服务态度,沟通能力(10分)",
					"tip":"有效投诉一次扣5分",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"doctor",
					"inputType":"text",
					"label":"医德医风,廉洁行医(10分)",
					"tip":"违规一次扣5分",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"unite",
					"inputType":"text",
					"label":"团结协作,遵守制度(10分)",
					"tip":"违规一次扣2分",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"subject",
					"inputType":"text",
					"label":"轮转时间和科目(20分)",
					"tip":"轮转时间每少1天扣一分",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"disease",
					"inputType":"text",
					"label":"掌握病症数量(30分)",
					"tip":"抽查登记册记录及病历,每少1病症扣2分",
					"placeholder": "0~30",
					"verify": {
						"max": "30",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"diseases",
					"inputType":"text",
					"label":"掌握病种数量(30分)",
					"tip":"抽查登记册记录及病历,每少1病种扣2分,参与诊疗轮转科室病例数不少于10例,每少1例扣1分(医技科室参照此标准打分)",
					"placeholder": "0~30",
					"verify": {
						"max": "30",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"quality",
					"inputType":"text",
					"label":"医疗文书书写质量(30分)",
					"tip":"甲级病历率≥90%,每下降5%扣1分,丙级病例不得分;门诊处方,化验单,放射申请单抽查每发现1份不合格的扣0.1分",
					"placeholder": "0~30",
					"verify": {
						"max": "30",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"skill",
					"inputType":"text",
					"label":"掌握轮转科室要求的基本技能(30分)",
					"tip":"科室考核小组根据平时表现打分",
					"placeholder": "0~30",
					"verify": {
						"max": "30",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
					{
						"inputId":"theoryAssessment",
						"inputType":"text",
						"label":"出科理论考核(25分)",
						"tip":"按100分卷折计",
						"placeholder": "0~25",
						"verify": {
							"max": "25",
							 "type": "int"
						},
						"readonly":${isAudit}
					},
					{
						"inputId":"readingNotes",
						"inputType":"text",
						"label":"读书笔记(《培训细则》规定书目)(15分)",
						"tip":"科室考核小组根据实际情况打分",
						"placeholder": "0~15",
						"verify": {
							"max": "15",
							 "type": "int"
						},
						"readonly":${isAudit}
					},
					{
						"inputId":"activity",
						"inputType":"text",
						"label":"参加医院规定的培训和学术活动(10分)",
						"tip":"到会率≥75%,每下降5%扣2分",
						"placeholder": "0~10",
						"verify": {
							"max": "10",
							 "type": "int"
						},
						"readonly":${isAudit}
					},
				{
					"inputId":"attendance",
					"inputType":"text",
					"label":"培训期间除享受国家法定的节假日(不含产假)外,事假每天扣除总学分1分（满分60分）",
					"tip":"培训期间除享受国家法定的节假日(不含产假)外,事假每天扣除总学分1分（满分60分）",
					"readonly":${isAudit}
				},
		{
			"inputId":"ability",
			"inputType":"select",
			"label":"教学能力",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "优",
					"optionDesc": "优"
				},
				{
					"optionId": "良",
					"optionDesc": "良"
				},
				{
					"optionId": "中",
					"optionDesc": "中"
				},
				{
					"optionId": "差",
					"optionDesc": "差"
				}
			]
		},
		{
			"inputId":"situation",
			"inputType":"select",
			"label":"参加各种科研情况",
			"readonly":${isAudit}, 		"required":true,
			"options":[
				{
					"optionId": "有",
					"optionDesc": "有"
				},
				{
					"optionId": "无",
					"optionDesc": "无"
				}
			]
		},
		{
			"inputId": "opinion",
			"label": "所在科室意见",
			"required":true,
			"inputType": "textarea",
			"readonly":${isAudit}
		},
		{
			"inputId": "guideTeacher",
			"label": "指导老师签名",
			"required":true,
			"inputType": "text",
			"readonly":${isAudit}
		},
		<c:if test="${not empty formDataMap.branchDirector }">
		{
			"inputId": "branchDirector",
			"label": "科主任签名",
			"required":true,
			"inputType": "text",
			"readonly":${isAudit}
		},
		</c:if>
		{
			"inputId": "date",
			"label": "时间",
			"required":true,
			"inputType": "date",
			"readonly":${isAudit}
		}

	]
	,
	"values":[
				{
					"inputId":"departmentName",
					"value":${pdfn:toJsonString(empty formDataMap.departmentName ? result.schDeptName:formDataMap.departmentName )}
				},
				{
					"inputId":"april",
					"value":${pdfn:toJsonString(empty formDataMap.april ? result.schMonth:formDataMap.april )}
				},
				{
					"inputId":"responsibility",
					"value":${pdfn:toJsonString( formDataMap.responsibility )}
				},
				{
					"inputId":"attitude",
					"value":${pdfn:toJsonString( formDataMap.attitude )}
				},
				{
					"inputId":"doctor",
					"value":${pdfn:toJsonString( formDataMap.doctor )}
				},
				{
					"inputId":"unite",
					"value":${pdfn:toJsonString( formDataMap.unite )}
				},
				{
					"inputId":"subject",
					"value":${pdfn:toJsonString( formDataMap.subject )}
				},
				{
					"inputId":"disease",
					"value":${pdfn:toJsonString( formDataMap.disease )}
				},
				{
					"inputId":"diseases",
					"value":${pdfn:toJsonString( formDataMap.diseases )}
				},
				{
					"inputId":"quality",
					"value":${pdfn:toJsonString( formDataMap.quality )}
				},
				{
					"inputId":"skill",
					"value":${pdfn:toJsonString( formDataMap.skill )}
				},
				<c:choose>
					<c:when test="${!testTypeFlag}">
						<c:set var="theoreResult" value="${formDataMap['theoryAssessment']}"/>
					</c:when>
					<c:when test="${testTypeFlag}">
						<c:set var="theoreResult" value="${empty formDataMap['theoryAssessment'] ? (empty outScore.theoryScore ? '':outScore.theoryScore*0.25):formDataMap['theoryAssessment']}}"/>
					</c:when>
				</c:choose>
				{
					"inputId":"theoryAssessment",
					"value":${pdfn:toJsonString( theoreResult )}
				},
				{
					"inputId":"readingNotes",
					"value":${pdfn:toJsonString( formDataMap.readingNotes )}
				},
				{
					"inputId":"activity",
					"value":${pdfn:toJsonString( formDataMap.activity )}
				},
				{
					"inputId":"attendance",
					"value":${pdfn:toJsonString( formDataMap.attendance )}
				}
		,
		{
			"inputId":"ability",
			"value":${pdfn:toJsonString( formDataMap.ability )}
		},
		{
			"inputId":"situation",
			"value":${pdfn:toJsonString( formDataMap.situation )}
		},
		{
			"inputId": "opinion",
			"value":${pdfn:toJsonString( formDataMap.opinion )}
		},
		{
			"inputId": "guideTeacher",
			"value":${pdfn:toJsonString( formDataMap.guideTeacher )}
		},
		<c:if test="${not empty formDataMap.branchDirector }">
		{
			"inputId": "branchDirector",
			"value":${pdfn:toJsonString( formDataMap.branchDirector )}
		},
		</c:if>
		{
			"inputId": "date",
			"value":${pdfn:toJsonString( formDataMap.date )}
		}
	]