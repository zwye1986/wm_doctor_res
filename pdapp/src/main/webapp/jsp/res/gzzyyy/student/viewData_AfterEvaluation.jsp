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
					"label":"轮转科室名称",
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
					"label":"工作责任心,无差错(5分)",
					"tip":"差错一次扣1分,重大医疗纠纷扣5分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"attitude",
					"inputType":"text",
					"label":"服务态度,沟通能力(5分)",
					"tip":"有效投诉一次扣2分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"doctor",
					"inputType":"text",
					"label":"医德医风,廉洁行医(5分)",
					"tip":"违规一次扣2分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"unite",
					"inputType":"text",
					"label":"团结协作,遵守制度(5分)",
					"tip":"违规一次扣1分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
                {
                    "inputId":"compassionateLeave",
                    "inputType":"text",
                    "label":"事假",
                    "required":true,
                    "readonly":${isAudit}
                },
                {
                    "inputId":"sickLeave",
                    "inputType":"text",
                    "label":"病假",
                    "required":true,
                    "readonly":${isAudit}
                },
                {
                    "inputId":"noReasonLeave",
                    "inputType":"text",
                    "label":"缺勤",
                    "required":true,
                    "readonly":${isAudit}
                },
				{
					"inputId":"subject",
					"inputType":"text",
					"label":"轮转时间和科目(5分)",
					"tip":"轮转时间每少1天扣0.2分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"disease",
					"inputType":"text",
					"label":"学习病种、掌握的技术操作及操作例数(10分)",
					"tip":"核查培训内容登记表及病历，每少1病种扣1分，每少1例技术操作扣0.5分（医技科室参照此标准打分）",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"quality",
					"inputType":"text",
					"label":"医疗文书书写质量(5分)",
					"tip":"平时文书5分：甲级病历率≥90%，每下降5%扣1分，出现丙级病历扣5分；门诊处方、化验单、各类申请单抽查每发现1份不合格的扣0.5分",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
				{
					"inputId":"quality2",
					"inputType":"text",
					"label":"医疗文书书写质量(5分)",
					"tip":"手写病历5分：带教老师批改，按100分折算",
					"placeholder": "0~5",
					"verify": {
						"max": "5",
						 "type": "int"
					},
					"readonly":${isAudit}
				},
                {
                    "inputId":"miniCex",
                    "inputType":"text",
                    "label":"迷你临床演练(20分)",
                    "tip":"按评分表得分折计",
                    "placeholder": "0~20",
                    "verify": {
                        "max": "20",
                        "type": "int"
                    },
                    "readonly":true
                },
                {
                    "inputId":"skill",
                    "inputType":"text",
                    "label":"临床操作技能考核(10分)",
                    "tip":"按评分表得分折计",
                    "placeholder": "0~10",
                    "verify": {
                    "max": "10",
                    "type": "int"
                    },
                    "readonly":true
                },
					{
						"inputId":"theoryAssessment",
						"inputType":"text",
						"label":"出科理论考核(20分)",
						"tip":"按100分卷折计",
						"placeholder": "0~20",
						"verify": {
							"max": "20",
							 "type": "int"
						},
						"readonly":${isAudit}
					},
					{
						"inputId":"activity",
						"inputType":"text",
						"label":"参与科室的教学活动(5分)",
						"tip":"指导初级医学生进行医疗学习活动能力",
						"placeholder": "0~5",
						"verify": {
							"max": "5",
							 "type": "int"
						},
						"readonly":${isAudit}
					},
				{
					"inputId":"totalScore2",
					"inputType":"text",
					"label":"合计",
                    "required":true,
					"readonly":true
				},
                {
                    "inputId":"isPassed",
                    "inputType":"select",
                    "label":"出科考核结果",
                    "required":true,
                    "readonly":${isAudit},
                    "options":[
                    {
                        "optionId": "通过",
                        "optionDesc": "通过"
                    },
                    {
                        "optionId": "未通过",
                        "optionDesc": "未通过"
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
                "inputId":"compassionateLeave",
                "value":${pdfn:toJsonString( formDataMap.compassionateLeave )}
                },
                {
                "inputId":"sickLeave",
                "value":${pdfn:toJsonString( formDataMap.sickLeave )}
                },
                {
                "inputId":"noReasonLeave",
                "value":${pdfn:toJsonString( formDataMap.noReasonLeave )}
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
					"inputId":"quality",
					"value":${pdfn:toJsonString( formDataMap.quality )}
				},
                {
                "inputId":"quality2",
                "value":${pdfn:toJsonString( formDataMap.quality )}
                },
                {
                "inputId":"miniCex",
				"value":${pdfn:toJsonString((empty formDataMap.miniScore)? miniScore:formDataMap.miniScore)}
                },
                {
                "inputId":"skill",
				"value":${pdfn:toJsonString((empty formDataMap.dopsScore)? dopsScore:formDataMap.dopsScore)}
                },
				<c:choose>
					<c:when test="${!testTypeFlag}">
						<c:set var="theoreResult" value="${formDataMap['theoryAssessment']}"/>
					</c:when>
					<c:when test="${testTypeFlag}">
						<c:set var="theoreResult" value="${empty formDataMap['theoryAssessment'] ? (empty outScore.theoryScore ? '':(outScore.theoryScore.toString()+0)*0.2*10/10):formDataMap['theoryAssessment']}}"/>
					</c:when>
				</c:choose>
				{
					"inputId":"theoryAssessment",
					"value":${pdfn:toJsonString( theoreResult )}
				},
				{
					"inputId":"activity",
					"value":${pdfn:toJsonString( formDataMap.activity )}
				},
                {
					"inputId":"totalScore2",
					"value":${pdfn:toJsonString( formDataMap.totalScore2 )}
				},
				{
					"inputId":"isPassed",
					"value":${pdfn:toJsonString( formDataMap.isPassed)}
				}
		,
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