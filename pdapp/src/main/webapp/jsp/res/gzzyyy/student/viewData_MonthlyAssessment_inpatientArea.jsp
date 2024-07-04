<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="resDoctor" value="${pdfn:getObjByFlow('getDoctorByFlow',result.doctorFlow)}"/>
	"inputs":[
		{
			"inputId":"div1",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
				{
					"inputId":"doctorName",
					"inputType":"text",
					"lable":"姓名",
					"readonly":true,
					"required":true,
					"align":"left"
				}
			]
		},
		{
			"inputId":"div2",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
				{
					"inputId":"departmentName",
					"inputType":"text",
					"lable":"轮转科室",
					"readonly":true,
					"required":true,
					"align":"left"
				}
			]
		},
		{
			"inputId":"div3",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
				{
					"inputId":"schTime",
					"inputType":"text",
					"lable":"轮转时间",
					"readonly":true,
					"required":true,
					"align":"left"
				}
			]
		},
		{
			"inputId":"part00",
			"inputType":"group",
			"lable":"医德（10分）",
			"readonly":true,
			"required":true,
			"haveScore":true,
			"placeholder": "0~10",
			"verify": {
				"max": "10",
				"type": "int"
			},
			"items":[
				{
					"inputId":"responsibility",
					"inputType":"text",
					"lable":"工作责任心、医德医风",
					"haveScore":false,
					"tip":"差错或有效投诉一次扣3分，重大医疗纠纷扣10分"
				},
				{
					"inputId":"attitude",
					"inputType":"text",
					"lable":"团结协作、遵守制度",
					"haveScore":false,
					"tip":"遵守医院、科室相关制度，能按上级医师的要求认真工作"
				}
			]
		},
		{
			"inputId":"group2",
			"inputType":"group",
			"lable":"临床实践（70分）",
			"readonly":true,
			"required":true,
			"haveScore":false,
			"items":[
				{
					"inputId":"part01",
					"inputType":"text",
					"lable":"轮转时间(20分)",
					"tip":"在轮转科室全勤满分，累计缺一工作日扣2分.（病假≥10天，事假≥5天，该项不得分。累计事假≥10天，月度考核不合格）",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":true
				},
				{
					"inputId":"part02",
					"inputType":"text",
					"lable":"管床数(10分)",
					"tip":"病区管床≥3张、ICU≥1张，不达标不得分，不管床则月度考核不合格",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":true
				},
				{
					"inputId":"part03",
					"inputType":"text",
					"lable":"病房值班(20分)",
					"tip":"按各科安排参与病区值班，少值班1次扣10分（总值班数＜2次，该项不得分。孕7月后及产后1年不值夜班）",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":true
				},
				{
					"inputId":"part04",
					"inputType":"text",
					"lable":"医疗文书书写数量(10分)",
					"tip":"各科按大纲要求，不达标不给分",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":true
				},
				{
					"inputId":"part05",
					"inputType":"text",
					"lable":"医疗文书书写质量(10分)",
					"tip":"出现一份乙级病历扣5分，丙级病历不得分；抽查门诊病历，有缺项、漏项的每份扣1分",
					"placeholder": "0~10",
					"verify": {
						"max": "10",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":true
				}
			]
		},
		{
			"inputId":"group3",
			"inputType":"group",
			"lable":"考核（20分）",
			"readonly":true,
			"required":true,
			"haveScore":false,
			"items":[
					{
						"inputId":"part06",
						"inputType":"text",
						"lable":"出科技能考核(10分)",
						"tip":"按100分卷折计（闭卷考试）",
						"placeholder": "0~10",
						"verify": {
							"max": "10",
							 "type": "int"
						},
						"haveScore":true,
						"readonly":true
					},
					{
						"inputId":"lilunkaohe",
						"inputType":"text",
						"lable":"出科理论考核(10分)",
						"tip":"按100分卷折计（闭卷考试）",
						"placeholder": "0~10",
						"verify": {
							"max": "10",
							 "type": "int"
						},
						"haveScore":true,
						"readonly":true
					}
			]
		},
		{
			"inputId":"div4",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
				{
					"inputId":"part08",
					"inputType":"text",
					"lable":"总得分",
					"readonly":true,
					"required":true,
					"align":"right"
				}
			]
		},
		{
			"inputId":"div5",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
					{
						"inputId": "guideTeacher",
						"lable": "指导老师",
						"required":true,
						"inputType": "text",
						"readonly":true,
						"align":"left"
					},
					{
						"inputId": "teacherDate",
						"lable": "考核日期",
						"required":true,
						"inputType": "date",
						"readonly":true,
						"align":"right"
					}
			]
		}

		<c:if test="${not empty formDataMap.branchDirector }">
		,
		{
			"inputId":"div6",
			"inputType":"div",
			"readonly":true,
			"required":true,
			"items":[
					{
						"inputId": "branchDirector",
						"lable": "科主任",
						"required":true,
						"inputType": "text",
						"readonly":true,
						"align":"left"
					},
					{
						"inputId": "date",
						"lable": "考核日期",
						"required":true,
						"inputType": "date",
						"readonly":true,
						"align":"right"
					}
			]
		}
		</c:if>
	]
	,
	"values":[
				{
					"inputId":"doctorName",
					"value":${pdfn:toJsonString(empty formDataMap.doctorName ? resDoctor.doctorName:formDataMap.doctorName )}
				},
				{
					"inputId":"departmentName",
					"value":${pdfn:toJsonString(empty formDataMap.departmentName ? result.schDeptName:formDataMap.departmentName )}
				},
				<c:set var="schTime" value="${ formDataMap.schTime}"></c:set>
				<c:if test="${empty formDataMap.schTime}">

					<c:set var="schTime" value="${ result.schStartDate}至${result.schEndDate}"></c:set>
				</c:if>
				{
					"inputId":"schTime",
					"value":${pdfn:toJsonString(schTime)}
				},
				{
					"inputId":"part00",
					"value":${pdfn:toJsonString( formDataMap.part00 )}
				},
				{
					"inputId":"part01",
					"value":${pdfn:toJsonString( formDataMap.part01 )}
				},
				{
					"inputId":"part02",
					"value":${pdfn:toJsonString( formDataMap.part02 )}
				},
				{
					"inputId":"part03",
					"value":${pdfn:toJsonString( formDataMap.part03 )}
				},
				{
					"inputId":"part04",
					"value":${pdfn:toJsonString( formDataMap.part04 )}
				},
				{
					"inputId":"part05",
					"value":${pdfn:toJsonString( formDataMap.part05 )}
				},
				{
					"inputId":"part06",
					"value":${pdfn:toJsonString( formDataMap.part06 )}
				},
				{
					"inputId":"lilunkaohe",
					"value":${pdfn:toJsonString( formDataMap.lilunkaohe )}
				},
				{
					"inputId":"part08",
					"value":${pdfn:toJsonString( formDataMap.part08 )}
				},
				<c:set var="nowDate" value="${pdfn:getCurrDate()}"></c:set>
				{
					"inputId": "guideTeacher",
					"value":${pdfn:toJsonString(empty formDataMap.guideTeacher ? tea.userName :formDataMap.guideTeacher)}
				},
				{
					"inputId": "teacherDate",
					"value":${pdfn:toJsonString(empty formDataMap.teacherDate ? nowDate :formDataMap.teacherDate)}
				}
				<c:if test="${not empty formDataMap.branchDirector }">
					,
					{
						"inputId": "branchDirector",
						"value":${pdfn:toJsonString( formDataMap.branchDirector )}
					},
					{
					"inputId": "date",
					"value":${pdfn:toJsonString( empty formDataMap.date ? (nowDate) : formDataMap.date )}
					}
				</c:if>
	]