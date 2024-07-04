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
            "label": "手术日期",
            "required": true,
            "inputType": "date",
            "placeholder":"手术日期"
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
	        "label": "术前术后诊断",
	        "inputType": "text",
	        "placeholder":"术前术后诊断"
	    },
	    {
	        "inputId": "RecData8",
	        "label": "手术过程",
	        "inputType": "text",
	        "placeholder":"手术过程"
	    },
        {
            "inputId": "RecData9",
            "label": "掌握情况",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['掌握情况']}" var="b" varStatus="s">
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
            "inputId": "RecData10",
            "label": "麻醉方法",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['麻醉方法']}" var="b" varStatus="s">
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
            "inputId": "RecData11",
            "label": "术中操作",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['术中操作']}" var="b" varStatus="s">
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
            "label": "特殊情况",
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['特殊情况']}" var="b" varStatus="s">
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
            "label": "术中职务",
            "required": true,
            "inputType": "select",
            "options": [
                <c:forEach items="${itemMap['术中职务']}" var="b" varStatus="s">
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
            "inputId": "RecData14",
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
            "inputId": "RecData15",
            "label": "备注",
            "inputType": "text",
            "placeholder":"备注"
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
        },,
        {
            "inputId":"RecData8",
            "value":"${posSkill.RecData8}"
        },
        {
            "inputId":"RecData9",
            "value":"${posSkill.RecData9}"
        },
        {
            "inputId":"RecData10",
            "value":"${posSkill.RecData10}"
        },
        {
            "inputId":"RecData11",
            "value":"${posSkill.RecData11}"
        },
        {
            "inputId":"RecData12",
            "value":"${posSkill.RecData12}"
        },
        {
            "inputId":"RecData13",
            "value":"${posSkill.RecData13}"
        },
        {
            "inputId":"RecData14",
            "value":"${posSkill.RecData14}"
        },
        {
            "inputId":"RecData15",
            "value":"${posSkill.RecData15}"
        },
        {
            "inputId":"OtherPOSSkill",
            "value":"${posSkill.OtherPOSSkill}"
        }
    ]
     </c:if>