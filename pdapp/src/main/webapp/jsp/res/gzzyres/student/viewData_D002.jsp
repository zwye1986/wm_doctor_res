<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData1",
	        "label": "病种",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病种",
	        "readonly":true
	    },
	    <c:if test="${caseClassCataName eq '其他' or caseClassCataName eq '其它' }">
	    {
	        "inputId": "OtherCaseClass",
	        "label": "具体名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"具体名称"
	    },
		</c:if>
	    {
	        "inputId": "RecData2",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "病历号",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病历号"
	    },
	    {
	        "inputId": "RecData4",
	        "label": "治疗措施",
	        "inputType": "text",
	        "placeholder":"治疗措施"
	    },
        {
            "inputId": "RecData5",
            "label": "类型",
            "required": true,
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['类型']}" var="b" varStatus="s">
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
            "inputId": "RecData6",
            "label": "是否主管",
            "required":true,
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
            "inputId": "RecData7",
            "label": "入院日期",
            "required": true,
            "inputType": "date",
            "placeholder":"入院日期"
        },
        {
            "inputId": "RecData10",
            "label": "诊断类型",
            "required": true,
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
            "inputId": "RecData9",
            "label": "是否抢救",
            "required":true,
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
            "inputId": "RecData11",
            "label": "备注",
            "required": false,
            "inputType": "text",
            "placeholder":"备注"
        }
     ]
     <c:if test='${empty caseClass}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${caseClassCataName}"
        }
    ]
     </c:if>
     <c:if test='${!empty caseClass}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${caseClass.RecData1}"
        },
        {
            "inputId": "RecData2",
            "value": "${caseClass.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${caseClass.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${caseClass.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${caseClass.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${caseClass.RecData6}"
        },
        {
            "inputId":"RecData7",
            "value":"${caseClass.RecData7}"
        },
        {
            "inputId":"RecData9",
            "value":"${caseClass.RecData9}"
        },
        {
            "inputId":"RecData10",
            "value":"${caseClass.RecData10}"
        },
        {
            "inputId":"RecData11",
            "value":"${caseClass.RecData11}"
        },
        {
            "inputId":"OtherCaseClass",
            "value":"${caseClass.OtherCaseClass}"
        }
    ]
     </c:if>