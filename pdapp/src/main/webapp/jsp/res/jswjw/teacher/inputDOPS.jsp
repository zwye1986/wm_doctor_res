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
        "inputId": "recFlow",
        "label": "出科考核表流水号",
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
        "inputId": "operUserFlow",
        "label": "学员流水号",
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
        "label": "综合",
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
        "inputType": "text",
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
        "optionId":"手术室",
        "optionDesc":"手术室"
        }
        ]
        },
        {
        "inputId": "patientName",
        "label": "病人姓名",
        "required": false,
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
        "required": false,
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
        "label": "病人来源",
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
        "inputId": "patientDiagnosis",
        "label": "病人主要诊断",
        "required": false,
        "inputType": "text",
        "isHidden": false
        },
        {
        "inputId": "operatingSkills",
        "label": "操作技能",
        "required": false,
        "inputType": "text",
        "isHidden": false
        },
        {
        "inputId": "studentSkillNum",
        "label": "评估前学员执行临床技能操作例数",
        "required": true,
        "inputType": "checkbox",
        "isHidden": false,
        "options": [
        {
        "optionId": "0",
        "optionDesc": "0"
        },
        {
        "optionId": "1-3",
        "optionDesc": "1-3"
        },
        {
        "optionId": "4",
        "optionDesc": "4"
        }
        ]
        },
        {
        "inputId": "skillComplexDegree",
        "label": "技能复杂程度",
        "required": true,
        "inputType": "checkbox",
        "isHidden": false,
        "options": [
        {
        "optionId": "低度",
        "optionDesc": "低度"
        },
        {
        "optionId": "中度",
        "optionDesc": "中度"
        },
        {
        "optionId": "高度",
        "optionDesc": "高度"
        }
        ]
        },
        {
        "inputId": "skillLevel",
        "label": "对临床技能适应证、相关解剖结构的了解及操作步骤的熟练程度：",
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
        "inputId": "consentForm",
        "label": "能详细告知病人并取得同意书",
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
        "inputId": "readyToWork",
        "label": "执行临床操作前的准备工作",
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
        "inputId": "painAndStabilization",
        "label": "能给予病人适当的止痛和镇定",
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
        "inputId": "SkillAbility",
        "label": "执行临床操作的技术能力",
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
        "inputId": "asepticTechnique",
        "label": "无菌技术",
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
        "inputId": "seekAssistance",
        "label": "能视需要寻求协助",
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
        "inputId": "relatedDisposal",
        "label": "执行临床操作后的相关处置",
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
        "label": "与病人沟通的技巧",
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
        "inputId": "feelProfessionalDegree",
        "label": "能否顾忌病人感受和专业程度",
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
        "inputId": "overallPerformance",
        "label": "执行临床操作技能的整体表现",
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
        "required": false,
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

