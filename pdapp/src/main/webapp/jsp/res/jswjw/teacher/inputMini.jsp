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
            "inputId": "grade",
            "label": "年级",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "professional",
            "label": "专业",
            "required": true,
            "inputType": "label",
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
                        "optionId": "实习生",
                        "optionDesc": "实习生"
                  },
                  {
                        "optionId": "住院医师",
                        "optionDesc": "住院医师"
                  },
                  {
                        "optionId": "研究生",
                        "optionDesc": "研究生"
                  },
                  {
                        "optionId": "八年制二级学科",
                        "optionDesc": "八年制二级学科"
                  },
                  {
                        "optionId": "进修生",
                        "optionDesc": "进修生"
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
            "inputId": "assessmentDate",
            "label": "评估日期",
            "required": true,
            "inputType": "date",
            "isHidden": false
      },
	  {
            "inputId": "assessmentPlace",
            "label": "评估地点",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "病房",
                        "optionDesc": "病房"
                  },
                  {
                        "optionId": "门诊",
                        "optionDesc": "门诊"
                  },
                  {
                        "optionId": "急诊",
                        "optionDesc": "急诊"
                  },
                  {
                        "optionId": "手术室",
                        "optionDesc": "手术室"
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
            "inputId": "age",
            "label": "年龄",
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
            "inputId": "patientSource",
            "label": "病人来源",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "门诊病人",
                        "optionDesc": "门诊病人"
                  },
                  {
                        "optionId": "住院病人",
                        "optionDesc": "住院病人"
                  }
            ]
      },
	  {
            "inputId": "patientSourceType",
            "label": "病人类型",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "新病人",
                        "optionDesc": "新病人"
                  },
                  {
                        "optionId": "复诊病人",
                        "optionDesc": "复诊病人"
                  }
            ]
      },
      {
            "inputId": "diagnosis",
            "label": "诊断",
            "required": true,
            "inputType": "label",
            "isHidden": false
      },
	  {
            "inputId": "severity",
            "label": "病情严重情况",
            "required": true,
            "inputType": "checkbox",
            "isHidden": false,
            "options": [
                  {
                        "optionId": "轻",
                        "optionDesc": "轻"
                  },
                  {
                        "optionId": "中",
                        "optionDesc": "中"
                  },
                  {
                        "optionId": "重",
                        "optionDesc": "重"
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
            "inputId": "medicalInterview",
                  "label": "1、医疗面谈(称呼病人/自我介绍/病人陈述病史/适当提问/适当回应)",
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
            "inputId": "physicalExamination",
            "label": "2、体格检查(告知检查目的/重点检查/操作正确/处理病人的不适)",
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
            "inputId": "humanisticCare",
            "label": "3、人文关怀(尊重和关心/建立良好关系/满足病人适当的需求)",
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
            "inputId": "clinicalJudgment",
            "label": "4、临床判断(归纳病史/判读检查结果/鉴别诊断/诊疗思维/预判治疗情况)",
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
            "inputId": "communicationSkills",
            "label": "5、沟通技能(解释检查、治疗理由/解释检查和临床相关性/健康宣教和咨询)",
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
            "inputId": "organization",
            "label": "6、组织效能(能按合理顺序处理，及时且适时，历练而简洁)",
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
            "inputId": "holisticCare",
            "label": "7、整体关怀(综合评价受试者的表现)",
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
            "inputId": "observationTime",
            "label": "评审观察时间",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "feedbackTime",
            "label": "指导反馈时间",
            "required": true,
            "inputType": "label",
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
            "inputType": "text",
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
      },
      {
            "inputId": "teacherSign",
            "label": "教师签字",
            "required": true,
            "inputType": "text",
            "isHidden": false
      },
      {
            "inputId": "studentSign",
            "label": "学生签字",
            "required": true,
            "inputType": "text",
            "isHidden": true
      },
      {
            "inputId": "studentSign1",
            "label": "学生签字",
            "required": true,
            "inputType": "label",
            "isHidden": false
      }
