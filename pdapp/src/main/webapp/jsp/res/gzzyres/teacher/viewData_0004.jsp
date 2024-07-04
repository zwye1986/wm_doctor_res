<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
			<c:if test='${doctor.roleFlow == 4}'>
			{
			   "inputId":"t0",
			   "label":"实习鉴定",
			   "inputType":"title"
			},
			{
			    "inputId":"BriefRequrie",
			    "label":"个人小结",
			    "inputType":"textarea"
			}
			</c:if>
			<c:if test='${doctor.roleFlow == 5}'>
			{
	            "inputId":"t0",
	            "label":"出科小结",
	            "inputType":"title"
	         },
             {
                 "inputId":"BriefRequrie",
                 "label":"出科小结",
                 "inputType":"textarea"
             }
			</c:if>
			<c:if test='${doctor.roleFlow != 4 and doctor.roleFlow != 5 }'>
			{
	            "inputId":"t0",
	            "label":"出科小结",
	            "inputType":"title"
	         },
             {
                 "inputId":"BriefRequrie",
                 "label":"文献、综述、读书报告学习及撰写情况",
                 "inputType":"textarea"
             },
             {
                 "inputId":"GainsDefect",
                 "label":"本次轮转的收获与不足",
                 "inputType":"textarea"
             }
			</c:if>
			,
	         {
	            "inputId":"t1",
	            "label":"日常评价表",
	            "inputType":"title"
	         },
			{
				"inputId":"TrueName",
				"label":"姓名",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"username",
				"label":"${baseInfo.UserType eq '1'?'学号':'工号'}",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"HosSecName",
				"label":"轮转科室",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"RStartTime",
				"label":"培训开始时间",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"REndTime",
				"label":"培训结束时间",
				"inputType":"text",
				"readonly":true
			},
             {
	            "inputId":"t10",
	            "label":"考勤",
	            "inputType":"title"
	         },
			{
				"inputId":"shijia",
				"label":"事假",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"bingjia",
				"label":"病假",
				"inputType":"text",
				"readonly":true
			},
			{
				"inputId":"queqing",
				"label":"缺勤",
				"inputType":"text",
				"readonly":true
			},
             {
	            "inputId":"t11",
	            "label":"医德医风",
	            "inputType":"title"
	         },
			 {
				 "inputId":"Score1_title",
				 "label":"沟通技巧，处理医患关系的能力(5分)",
				 "tip":"沟通技巧，处理医患关系的能力(5分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score1",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score2_title",
				 "label":"医疗作风端正，廉洁行医(5分)",
				 "tip":"医疗作风端正，廉洁行医(5分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score2",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				}
			},
             {
	            "inputId":"t12",
	            "label":"工作态度",
	            "inputType":"title"
	         },
			 {
				 "inputId":"Score3_title",
				 "label":"团结协作，工作认真，责任心强，无医疗差错(5分)",
				 "tip":"团结协作，工作认真，责任心强，无医疗差错(5分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score3",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~5",
				"verify": {
					"max": "5",
				    "type": "int"
				}
			},
             {
	            "inputId":"t13",
	            "label":"医疗业务工作",
	            "inputType":"title"
	         },
			 {
				 "inputId":"Score4_title",
				 "label":"医疗文书-抽查住院病历、医嘱、处方等(15分)",
				 "tip":"医疗文书-抽查住院病历、医嘱、处方等(15分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score4",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score5_title",
				 "label":"体格检查-体格检查正确规范、及时发现阳性体征(10分)",
				 "tip":"体格检查-体格检查正确规范、及时发现阳性体征(10分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score5",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score6_title",
				 "label":"操作技术-临床技能操作正确、规范、熟练与否(15分)",
				 "tip":"操作技术-临床技能操作正确、规范、熟练与否(15分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score6",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score7_title",
				 "label":"诊治技能及思维-常见病诊断及鉴别诊断能力；结合病情分析检查报告的能力；辨证施治能力(15分)",
				 "tip":"诊治技能及思维-常见病诊断及鉴别诊断能力；结合病情分析检查报告的能力；辨证施治能力(15分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score7",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~15",
				"verify": {
					"max": "15",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score8_title",
				 "label":"查房-询问病情，检查病人，汇报病情及疑难问题，归纳上级医师的意见(10分)",
				 "tip":"查房-询问病情，检查病人，汇报病情及疑难问题，归纳上级医师的意见(10分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score8",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				}
			},
			 {
				 "inputId":"Score9_title",
				 "label":"病房管理-管理病人，处理急、危、重症及突发事件的能力(10分)",
				 "tip":"病房管理-管理病人，处理急、危、重症及突发事件的能力(10分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score9",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				}
			},
             {
	            "inputId":"t14",
	            "label":"临床教学活动参与情况",
	            "inputType":"title"
	         },
			 {
				 "inputId":"Score10_title",
				 "label":"小讲课/教学查房/病例讨论等科室组织的临床教学活动参与情况(10分)",
				 "tip":"小讲课/教学查房/病例讨论等科室组织的临床教学活动参与情况(10分)",
				 "inputType":"title"
			 },
			{
				"inputId":"Score10",
				"label":"分数",
				"inputType":"progress",
				"isSum":true,
				"required":true,
				"placeholder": "0~10",
				"verify": {
					"max": "10",
				    "type": "int"
				}
			},
	         {
                 "inputId":"TotalScore",
                 "label":"总分",
                 "inputType":"text",
                 "readonly":true,
                 "required":true,
				 "verify": {
				 	"max": "100",
				    "type": "int"
				}
             },
             {
                 "inputId":"Status",
                 "label":"轮转考核结果",
                 "inputType":"select",
                 "required":true,
                 "options": [
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
                 "inputId":"Teacher",
                 "label":"带教签名",
                 "inputType":"text",
                 "readonly":true,
                 "required":true
			 },
			 {
                 "inputId":"Professer",
                 "label":"科主任签名",
                 "inputType":"text",
                 "readonly":true,
                 "required":true
			 },
			 {
                 "inputId":"CheckDate",
                 "label":"考核时间",
                 "inputType":"text",
                 "readonly":true,
                 "required":true
			 },
             {
                 "inputId":"total_label",
                 "label":"对该生的整体评价",
                 "inputType":"title"
             },
             {
                 "inputId":"total_score",
                 "label":"分数",
				 "inputType":"progress",
				 "required":true,
				 "placeholder": "1~9",
				 "verify": {
					 "min": "1",
					 "max": "9",
				     "type": "int"
				 }
             },
	         {
	            "inputId":"t2",
	            "label":"带教老师鉴定意见",
	            "inputType":"title"
	         },
             {
                 "inputId":"SecAppraise",
                 "label":"鉴定意见",
                 "inputType":"textarea"
             },
             {
	            "inputId":"t3",
	            "label":"理论成绩",
	            "inputType":"title"
	         },
	         {
                 "inputId":"AssessmentMark",
                 "label":"理论成绩",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"请输入理论成绩分数"
             }
             ,
             {
	            "inputId":"t4",
	            "label":"考勤",
	            "inputType":"title"
	         },
	         {
                 "inputId":"SickLeaveDay",
                 "label":"请假",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"请假天数",
                 "verify": {
					"type": "int",
					"maxLength": 3
				 }
             },
	         {
                 "inputId":"AbsenteeismDay",
                 "label":"脱岗",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"脱岗天数",
                 "verify": {
					"type": "int",
					"maxLength": 3
				 }
             }
         ]
     ,
     "values":[
			{
			    "inputId":"BriefRequrie",
			    "value":${pdfn:toJsonString(outSecBrief.BriefRequrie)}
			},
			{
			    "inputId":"GainsDefect",
			    "value":${pdfn:toJsonString(outSecBrief.GainsDefect)}
			},
			{
			    "inputId":"SecAppraise",
			    "value":${pdfn:toJsonString(outSecBrief.SecAppraise)}
			},

			{
				"inputId":"TrueName",
			    "value":${pdfn:toJsonString(baseInfo.TrueName)}
			},
			{
				"inputId":"username",
			    "value":${pdfn:toJsonString(baseInfo.username)}
			},
			{
				"inputId":"HosSecName",
			    "value":${pdfn:toJsonString(baseInfo.HosSecName)}
			},
			{
				"inputId":"RStartTime",
			    "value":${pdfn:toJsonString(baseInfo.RStartTime)}
			},
			{
				"inputId":"REndTime",
				"value":${pdfn:toJsonString(baseInfo.REndTime)}
			},
			{
				"inputId":"shijia",
				"value":${pdfn:toJsonString(empty shijia ? '0':shijia)}
			},
			{
				"inputId":"bingjia",
				"value":${pdfn:toJsonString(empty bingjia ? '0':bingjia)}
			},
			{
				"inputId":"queqing",
				"value":${pdfn:toJsonString(empty queqing ? '0':queqing)}
			},
			{
				"inputId":"Score1",
				"value":${pdfn:toJsonString(dailyInfo.Score1)}
			},
			{
				"inputId":"Score2",
				"value":${pdfn:toJsonString(dailyInfo.Score2)}
			},
			{
				"inputId":"Score3",
				"value":${pdfn:toJsonString(dailyInfo.Score3)}
			},
			{
				"inputId":"Score4",
				"value":${pdfn:toJsonString(dailyInfo.Score4)}
			},
			{
				"inputId":"Score5",
				"value":${pdfn:toJsonString(dailyInfo.Score5)}
			},
			{
				"inputId":"Score6",
				"value":${pdfn:toJsonString(dailyInfo.Score6)}
			},
			{
				"inputId":"Score7",
				"value":${pdfn:toJsonString(dailyInfo.Score7)}
			},
			{
				"inputId":"Score8",
				"value":${pdfn:toJsonString(dailyInfo.Score8)}
			},
			{
				"inputId":"Score9",
				"value":${pdfn:toJsonString(dailyInfo.Score9)}
			},
			{
				"inputId":"Score10",
				"value":${pdfn:toJsonString(dailyInfo.Score10)}
			},
	         {
                 "inputId":"TotalScore",
				"value":${pdfn:toJsonString(dailyInfo.TotalScore)}
             },
             {
                 "inputId":"Status",
				"value":${pdfn:toJsonString(dailyInfo.Status)}
             },
			 {
                 "inputId":"Teacher",
				"value":${pdfn:toJsonString(empty dailyInfo.Teacher ? user.userName:dailyInfo.Teacher)}
			 },
			 {
                 "inputId":"Professer",
				"value":${pdfn:toJsonString(empty dailyInfo.Professer ? Professer:dailyInfo.Professer)}
			 },
			<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
			 {
                 "inputId":"CheckDate",
				"value":${pdfn:toJsonString(empty dailyInfo.CheckDate ? currDate:dailyInfo.CheckDate)}
			 },
			{
			    "inputId":"total_score",
			    "value":${pdfn:toJsonString(examInfo.ExamInfoDF)}
			},
			<c:if test="${not empty cycleOutSectionRecord.AssessmentMark}">
			{
			    "inputId":"AssessmentMark",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.AssessmentMark)}
			},
			</c:if>
			<c:if test="${empty cycleOutSectionRecord or empty cycleOutSectionRecord.AssessmentMark}">
			{
			    "inputId":"AssessmentMark",
			    "value":${pdfn:toJsonString(theoryScore)}
			},
			</c:if>
			{
			    "inputId":"SickLeaveDay",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.SickLeaveDay)}
			},
			{
			    "inputId":"AbsenteeismDay",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.AbsenteeismDay)}
			    }
			]