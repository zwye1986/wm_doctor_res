<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"手术名称"
         },
    	 {
            "headId": "RecData3",
            "label": "操作时机"
        },
        {
            "headId": "RecData4",
            "label": "病人姓名"
        },
        {
            "headId":"RecData5",
            "label":"病历号"
        },
         {
             "headId":"RecData2",
             "label":"手术日期"
         }

    ],
    "datas": [
        <c:forEach items="${posSkills}" var="posSkill" varStatus="status">
            {
	        	"dataFlow":"${posSkill.dataFlow}",
	        	"RecData1":"${posSkill.RecData1}",
	        	"RecData2":"${posSkill.RecData2}",
	        	"RecData3":"${posSkill.RecData3}",
	        	"RecData4":"${posSkill.RecData4}",
	        	"RecData5":"${posSkill.RecData5}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]