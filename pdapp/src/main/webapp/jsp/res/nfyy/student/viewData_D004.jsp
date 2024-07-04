<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData1",
	        "label": "手术名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"手术名称",
	        "readonly":true
	    },
	    <c:if test="${posSkillCataName eq '其他' or posSkillCataName eq '其它' }">
	    {
	        "inputId": "OtherPOSSkill",
	        "label": "具体名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"具体名称"
	    },
		</c:if>
	    {
	        "inputId": "RecData2",
	        "label": "ID号",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"ID号"
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
                   "optionId": "主刀",
                   "optionDesc": "主刀"
               },
               {
                   "optionId": "一助",
                   "optionDesc": "一助"
               },
               {
                   "optionId": "二助",
                   "optionDesc": "二助"
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
     <c:if test='${empty posSkill}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${posSkillCataName}"
        }
    ]
     </c:if>
     <c:if test='${!empty posSkill}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${posSkill.RecData1}"
        },
        {
            "inputId": "RecData2",
            "value": "${posSkill.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${posSkill.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${posSkill.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${posSkill.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${posSkill.RecData6}"
        },
        {
            "inputId":"RecData7",
            "value":"${posSkill.RecData7}"
        },
        {
            "inputId":"OtherPOSSkill",
            "value":"${posSkill.OtherPOSSkill}"
        }
    ]
     </c:if>