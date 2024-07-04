<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"病种名称"
         },
         {
             "headId":"RecData2",
             "label":"病人姓名"
         },
    	 {
            "headId": "RecData3",
            "label": "病历号"
        },
        {
            "headId": "RecData4",
            "label": "治疗措施"
        },
        {
            "headId":"RecData7",
            "label":"入院日期"
        }

    ],
    "datas": [
        <c:forEach items="${caseClasses}" var="caseClass" varStatus="status">
            {
	        	"dataFlow":"${caseClass.dataFlow}",
	        	"RecData1":"${caseClass.RecData1}",
	        	"RecData2":"${caseClass.RecData2}",
	        	"RecData3":"${caseClass.RecData3}",
	        	"RecData4":"${caseClass.RecData4}",
	        	"RecData7":"${caseClass.RecData7}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]