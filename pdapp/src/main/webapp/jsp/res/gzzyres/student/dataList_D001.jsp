<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"病人姓名"
         },
    	 {
            "headId": "RecData2",
            "label": "住院号"
        },
        {
            "headId": "RecData3",
            "label": "疾病名称"
        },
        {
            "headId":"RecData4",
            "label":"操作时间"
        }

    ],
    "datas": [
        <c:forEach items="${bigHistorys}" var="bigHistory" varStatus="status">
            {
	        	"dataFlow":"${bigHistory.dataFlow}",
	        	"RecData1":"${bigHistory.RecData1}",
	        	"RecData2":"${bigHistory.RecData2}",
	        	"RecData3":"${bigHistory.RecData3}",
	        	"RecData4":"${bigHistory.RecData4}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]