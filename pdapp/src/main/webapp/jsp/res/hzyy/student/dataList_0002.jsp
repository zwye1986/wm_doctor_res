<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"hosId",
             "label":"门诊号"
         },
    	 {
            "headId": "name",
            "label": "名称"
        },
        {
            "headId": "time",
            "label": "时间"
        }

    ],
   "datas": [
            <c:forEach var="studyInfo" items="${studyInfos}" varStatus="status">
	        {
	        	"dataFlow":"${studyInfo.dataFlow}",
	        	"hosId":"${studyInfo.no}",
	        	"name":"${studyInfo.name}",
	        	"time":"${fn:substring(studyInfo.dateTime , 0 , 10)}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
	        </c:forEach>
	]