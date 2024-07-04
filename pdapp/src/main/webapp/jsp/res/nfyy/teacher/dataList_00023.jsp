<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"name",
             "label":"活动名称"
         },
    	 {
            "headId": "time",
            "label": "开始时间"
        },
        {
            "headId": "endTime",
            "label": "结束时间"
        },
        {
            "headId":"mainSpeaker",
            "label":"主讲人"
        }

    ],
    "datas": [
        <c:forEach items="${activitys}" var="activity" varStatus="status">
            {
	        	"dataFlow":"${activity.CaDisID}",
	        	"name":"${activity.CaName}",
	        	"time":"${fn:substring(activity.CaDisTime , 0 , 16)}",
	        	"endTime":"${fn:substring(activity.CaEndDisTime , 0 , 16)}",
	        	"mainSpeaker":"${activity.CaDisMainSpeaker}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]