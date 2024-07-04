<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData1",
	        "label": "操作名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"操作名称",
	        "readonly":true
	    },
	    <c:if test="${operateSkillCataName eq '其他' or operateSkillCataName eq '其它' }">
	    {
	        "inputId": "OtherSkill",
	        "label": "具体名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"具体名称"
	    },
		</c:if>
	    {
	        "inputId": "RecData2",
	        "label": "门诊号",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"门诊号"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData4",
	        "label": "住院号",
	        "inputType": "text",
	        "placeholder":"住院号"
	    },
        {
            "inputId": "RecData5",
            "label": "入院日期",
            "required": true,
            "inputType": "date",
            "placeholder":"入院日期"
        },
        {
            "inputId": "RecData6",
            "label": "操作人身份",
            "required": true,
            "inputType": "select",
            "options": [
               {
                   "optionId": "操作者",
                   "optionDesc": "操作者"
               },
               {
                   "optionId": "助手",
                   "optionDesc": "助手"
               },
               {
                   "optionId": "其他人员",
                   "optionDesc": "其他人员"
               }
           ]
        },
        {
            "inputId": "RecData7",
            "label": "主要诊断",
            "required": true,
            "inputType": "textarea",
            "placeholder":"主要诊断"
        }
     
     ]
     <c:if test='${empty operateSkill}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${operateSkillCataName}"
        }
    ]
     </c:if>
     <c:if test='${!empty operateSkill}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${operateSkill.RecData1}"
        },
        {
            "inputId": "RecData2",
            "value": "${operateSkill.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${operateSkill.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${operateSkill.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${operateSkill.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${operateSkill.RecData6}"
        },
        {
            "inputId":"RecData7",
            "value":"${operateSkill.RecData7}"
        },
        {
            "inputId":"OtherSkill",
            "value":"${operateSkill.OtherSkill}"
        }
    ]
     </c:if>