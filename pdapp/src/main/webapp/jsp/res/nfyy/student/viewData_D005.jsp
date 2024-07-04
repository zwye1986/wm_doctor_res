<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:if test='${doctor.roleFlow == 5}'>
		"inputs":[
		    {
		        "inputId": "BriefRequrie",
		        "label": "个人小结",
		        "required": true,
		        "inputType": "textarea",
		        "placeholder":"个人小结"
		    }
		 ]
	</c:if>
	<c:if test='${doctor.roleFlow != 5}'>
	  	"inputs":[
        {
            "inputId": "BriefRequrie",
            "label": "文献、综述、读书报告学习及撰写情况",
            "required": true,
            "inputType": "textarea",
            "placeholder":"文献、综述、读书报告学习及撰写情况"
        },
        {
            "inputId": "GainsDefect",
            "label": "本次轮转的收获与不足",
            "required": true,
            "inputType": "textarea",
            "placeholder":"本次轮转的收获与不足"
        }
     
     ]
	</c:if>
    <c:if test='${!empty outSecBrief}'>
    ,
     "values": [
       {
           "inputId": "BriefRequrie",
           "value": "${outSecBrief.BriefRequrie}"
       },
       {
           "inputId": "GainsDefect",
           "value": "${outSecBrief.GainsDefect}"
       }
   ]
    </c:if>