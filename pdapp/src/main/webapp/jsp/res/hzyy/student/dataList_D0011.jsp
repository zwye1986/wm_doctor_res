<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"门诊号"
         },
    	 {
            "headId": "RecData2",
            "label": "姓名"
        },
        {
            "headId": "RecData3",
            "label": "门诊日期"
        },
        {
            "headId":"RecData4",
            "label":"诊断"
        }

    ],
    "datas": [
        <c:forEach items="${outPatients}" var="outPatient" varStatus="status">
            {
	        	"dataFlow":"${outPatient.dataFlow}",
	        	"RecData1":"${outPatient.RecData1}",
	        	"RecData2":"${outPatient.RecData2}",
	        	"RecData3":"${outPatient.RecData3}",
	        	"RecData4":"${outPatient.RecData4}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]