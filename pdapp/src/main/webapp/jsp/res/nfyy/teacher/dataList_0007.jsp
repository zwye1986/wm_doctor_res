<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"CaName",
             "label":"活动名称"
         },
    	 {
            "headId": "CaCount",
            "label": "参加活动人数"
        },
        {
            "headId": "FCaDisIDCount",
            "label": "评价活动人数"
        }

    ],
    "datas": [
        <c:forEach items="${activitys}" var="activity" varStatus="status">
            {
	        	"dataFlow":"${activity.CaDisID}",
	        	"CaName":"${activity.CaName}",
	        	"CaCount":"${activity.CaCount}",
	        	"FCaDisIDCount":"${activity.FCaDisIDCount}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]