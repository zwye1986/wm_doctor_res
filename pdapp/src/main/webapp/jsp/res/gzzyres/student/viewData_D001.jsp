<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	    {
	        "inputId": "RecData1",
	        "label": "病人姓名",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"病人姓名"
	    },
	    {
	        "inputId": "RecData2",
	        "label": "住院号",
	        "inputType": "text",
	        "placeholder":"住院号"
	    },
	    {
	        "inputId": "RecData3",
	        "label": "疾病名称",
	        "required": true,
	        "inputType": "text",
	        "placeholder":"疾病名称"
	    },
        {
            "inputId": "RecData4",
            "label": "操作时间",
            "required": true,
            "inputType": "date",
            "placeholder":"操作时间"
        },
        {
            "inputId": "RecData5",
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
            "inputId": "RecData6",
            "label": "诊断",
            "required": true,
            "inputType": "textarea",
            "placeholder":"诊断"
        }
     
     ]
     <c:if test='${!empty bigHistory}'>
     ,
      "values": [
        {
            "inputId": "RecData1",
            "value": "${bigHistory.RecData1}"
        },
        {
            "inputId": "RecData2",
            "value": "${bigHistory.RecData2}"
        },
        {
            "inputId": "RecData3",
            "value": "${bigHistory.RecData3}"
        },
        {
            "inputId": "RecData4",
            "value": "${bigHistory.RecData4}"
        },
        {
            "inputId": "RecData5",
            "value": "${bigHistory.RecData5}"
        },
        {
            "inputId":"RecData6",
            "value":"${bigHistory.RecData6}"
        }
    ]
     </c:if>