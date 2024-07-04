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
			"readonly":${isAudit},
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
					"tip":"差错或有效投诉一次扣3分，重大医疗纠纷扣10分",
					"haveScore":false
				},
				{
					"inputId":"attitude",
					"inputType":"text",
					"lable":"团结协作、遵守制度",
					"tip":"遵守医院、科室相关制度，能按上级医师的要求认真工作",
					"haveScore":false
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
					"lable":"轮转时间(30分)",
					"tip":"在轮转科室全勤满分，除按手续请的病、事假外，累计缺一工作日扣5分",
					"placeholder": "0~30",
					"verify": {
						"max": "30",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":${isAudit}
				},
				{
					"inputId":"part02",
					"inputType":"text",
					"lable":"医技科室操作或门诊跟诊(20分)",
					"tip":"门诊或医技科室轮转的病例数≥200例",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":${isAudit}
				},
				{
					"inputId":"part03",
					"inputType":"text",
					"lable":"门诊试诊或出具诊断报告(20分)",
					"tip":"各科按大纲要求，未达标不给分",
					"placeholder": "0~20",
					"verify": {
						"max": "20",
						 "type": "int"
					},
					"haveScore":true,
					"readonly":${isAudit}
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
						"inputId":"part04",
						"inputType":"text",
						"lable":"出科技能考核(10分)",
						"tip":"按100分卷折计（闭卷考试）",
						"placeholder": "0~10",
						"verify": {
							"max": "10",
							 "type": "int"
						},
						"haveScore":true,
						"readonly":${isAudit}
					},
					{
						"inputId":"part05",
						"inputType":"text",
						"lable":"出科理论考核(10分)",
						"tip":"按100分卷折计（闭卷考试）",
						"placeholder": "0~10",
						"verify": {
							"max": "10",
							 "type": "int"
						},
						"haveScore":true,
						"readonly":${isAudit}
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
					"inputId":"part06",
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
						"readonly":${isAudit},
						"align":"left"
					},
					{
						"inputId": "teacherDate",
						"lable": "考核日期",
						"required":true,
						"inputType": "date",
						"readonly":${isAudit},
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
						"readonly":${isAudit},
						"align":"left"
					},
					{
						"inputId": "date",
						"lable": "考核日期",
						"required":true,
						"inputType": "date",
						"readonly":${isAudit},
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