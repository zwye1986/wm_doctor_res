<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"操作名称"
         },
         {
             "headId":"RecData4",
             "label":"病人姓名"
         },
    	 {
            "headId": "RecData3",
            "label": "操作时机"
        },
        {
            "headId": "RecData5",
            "label": "病历号"
        },
        {
            "headId":"RecData2",
            "label":"操作日期"
        }

    ],
    "datas": [
        <c:forEach items="${operateSkills}" var="operateSkill" varStatus="status">
            {
	        	"dataFlow":"${operateSkill.dataFlow}",
	        	"RecData1":"${operateSkill.RecData1}",
	        	"RecData2":"${operateSkill.RecData2}",
	        	"RecData3":"${operateSkill.RecData3}",
	        	"RecData4":"${operateSkill.RecData4}",
	        	"RecData5":"${operateSkill.RecData5}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]