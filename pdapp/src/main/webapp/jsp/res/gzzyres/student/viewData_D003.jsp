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
	        "label": "操作日期",
	        "required": true,
            "inputType": "date",
	        "placeholder":"操作日期"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "操作时机",
	        "inputType": "text",
	        "placeholder":"操作时机"
	    },
	    {
	        "inputId": "RecData4",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
        {
            "inputId": "RecData5",
            "label": "病历号",
            "required": true,
	        "inputType": "text",
            "placeholder":"病历号"
        },
        {
            "inputId": "RecData6",
            "label": "目的",
	        "inputType": "text",
            "placeholder":"目的"
        },
        {
            "inputId": "RecData7",
            "label": "是否执行者",
            "inputType": "radio",
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
            "inputId": "RecData8",
            "label": "是否助手",
            "inputType": "radio",
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
            "inputId": "RecData9",
            "label": "是否成功",
            "inputType": "radio",
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
            "inputId": "RecData10",
            "label": "失败原因",
	        "inputType": "text",
            "placeholder":"失败原因"
        },
        {
            "inputId": "RecData11",
            "label": "操作类别",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['操作类别']}" var="b" varStatus="s">
                   {
                       "optionId": "${b.DicItem}",
                       "optionDesc": "${b.DicItem}"
                   }
                    <c:if test="${!s.last}">
                        ,
                    </c:if>
                </c:forEach>
           ]
        },
        {
            "inputId": "RecData12",
            "label": "诊断类型",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['诊断类型']}" var="b" varStatus="s">
                   {
                       "optionId": "${b.DicItem}",
                       "optionDesc": "${b.DicItem}"
                   }
                    <c:if test="${!s.last}">
                        ,
                    </c:if>
                </c:forEach>
           ]
        },
        {
            "inputId": "RecData13",
            "label": "备注",
            "inputType": "text",
            "placeholder":"备注"
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
            "inputId":"RecData8",
            "value":"${operateSkill.RecData8}"
        },
        {
            "inputId":"RecData9",
            "value":"${operateSkill.RecData9}"
        },
        {
            "inputId":"RecData10",
            "value":"${operateSkill.RecData10}"
        },
        {
            "inputId":"RecData11",
            "value":"${operateSkill.RecData11}"
        },
        {
            "inputId":"RecData12",
            "value":"${operateSkill.RecData12}"
        },
        {
            "inputId":"RecData13",
            "value":"${operateSkill.RecData13}"
        },
        {
            "inputId":"OtherSkill",
            "value":"${operateSkill.OtherSkill}"
        }
    ]
     </c:if>