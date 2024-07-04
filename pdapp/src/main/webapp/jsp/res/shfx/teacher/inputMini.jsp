<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8" %>
      {
            "inputId": "formFileName",
            "label": "表单名称",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "schDeptFlow",
            "label": "轮转科室流水号",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "operUserFlow",
            "label": "学员流水号",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "roleFlag",
            "label": "角色ID",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "recFlow",
            "label": " 迷你临床演练评估量化表流水号",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "processFlow",
            "label": "过程流水号",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "ZongHe",
            "label": "总和",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "studentName",
            "label": "学员姓名",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "stuLevel",
            "label": "级别",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "professional",
            "label": "基地专业",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "stuSid",
            "label": "学号/工号",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
	  {
            "inputId": "studentType",
            "label": "学员类型",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "本科生",
                        "optionDesc": "本科生"
                  },
                  {
                        "optionId": "硕士研究生",
                        "optionDesc": "硕士研究生"
                  },
                  {
                        "optionId": "博士研究生",
                        "optionDesc": "博士研究生"
                  }
            ]
      },
	  {
            "inputId": "teacherType",
            "label": "教师类型",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "高级职称",
                        "optionDesc": "高级职称"
                  },
                  {
                        "optionId": "中级职称",
                        "optionDesc": "中级职称"
                  },
                  {
                        "optionId": "初级职称",
                        "optionDesc": "初级职称"
                  }
            ]
      },
	  {
            "inputId": "assessmentPlace",
            "label": "评估地点",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "门诊",
                        "optionDesc": "门诊"
                  },
                  {
                        "optionId": "急诊",
                        "optionDesc": "急诊"
                  },
                  {
                        "optionId": "普通病房",
                        "optionDesc": "普通病房"
                  },
                  {
                        "optionId": "监护病房",
                        "optionDesc": "监护病房"
                  },
                  {
                        "optionId": "其他",
                        "optionDesc": "其他"
                  }
            ]
      },
      {
            "inputId": "patientName",
            "label": "病人姓名",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
	  {
            "inputId": "sex",
            "label": "性别",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "男",
                        "optionDesc": "男"
                  },
                  {
                        "optionId": "女",
                        "optionDesc": "女"
                  }
            ]
      },
      {
            "inputId": "age",
            "label": "年龄",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "deptName",
            "label": "科室",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "qutpatientNum",
            "label": "住院号/门诊号",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "diagnosis",
            "label": "诊断",
            "required": true,
            "inputType": "label",
            "isHidden": false
      },
	  {
            "inputId": "patientConsent",
            "label": "已获得病人(或家属)的同意",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "是",
                        "optionDesc": "是"
                  },
                  {
                        "optionId": "否",
                        "optionDesc": "否"
                  }
            ]
      },
	  {
            "inputId": "diagnosisKeynote",
            "label": "诊治重点",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "病史采集",
                        "optionDesc": "病史采集"
                  },
                  {
                        "optionId": "诊断",
                        "optionDesc": "诊断"
                  },
                  {
                        "optionId": "治疗",
                        "optionDesc": "治疗"
                  },
                  {
                        "optionId": "健康宣传",
                        "optionDesc": "健康宣传"
                  }
            ]
      },
      {
            "inputId": "observationTime",
            "label": "考核时间",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "feedbackTime",
            "label": "反馈时间",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
	  {
            "inputId": "medicalInterview",
            "label": "病史采集",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "physicalExamination",
            "label": "体格检查",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "humanisticCare",
            "label": "人文素养",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "communicationSkills",
            "label": "医患沟通",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "clinicalJudgment",
            "label": "临床判断",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "organization",
            "label": "组织效能",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
	  {
            "inputId": "holisticCare",
            "label": "整体表现",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "-",
                        "optionDesc": "-"
                  },
                  {
                        "optionId": "0",
                        "optionDesc": "0"
                  },
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
      {
            "inputId": "assessmentTea",
            "label": "考核教师",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "assessmentDate",
            "label": "考核日期",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
	  {
            "inputId": "degreeSatisfaction",
            "label": "教师对学员测评满意程度",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      },
      {
            "inputId": "teacherComment",
            "label": "教师的评语",
            "required": true,
            "inputType": "textarea",
            "isHidden": false
      },
      {
            "inputId": "teacherSign",
            "label": "教师签字",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "doctorComment",
            "label": "学生改进计划",
            "required": true,
            "inputType": "textarea",
            "isHidden": true
      },
      {
            "inputId": "studentSign",
            "label": "学生签字",
            "required": true,
            "inputType": "label",
            "isHidden": false
      },
	  {
            "inputId": "studentDegreeSatisfaction",
            "label": "学生对此次测评满意程度",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "1",
                        "optionDesc": "1"
                  },
                  {
                        "optionId": "2",
                        "optionDesc": "2"
                  },
                  {
                        "optionId": "3",
                        "optionDesc": "3"
                  },
                  {
                        "optionId": "4",
                        "optionDesc": "4"
                  },
                  {
                        "optionId": "5",
                        "optionDesc": "5"
                  },
                  {
                        "optionId": "6",
                        "optionDesc": "6"
                  },
                  {
                        "optionId": "7",
                        "optionDesc": "7"
                  },
                  {
                        "optionId": "8",
                        "optionDesc": "8"
                  },
                  {
                        "optionId": "9",
                        "optionDesc": "9"
                  }
            ]
      }
