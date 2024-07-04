<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"Col253",
             "label":"病人姓名"
         },
    	 {
            "headId": "Col252",
            "label": "门诊号"
        },
        {
            "headId": "RecDate",
            "label": "填写日期"
        }

    ],
    "datas": [
        <c:forEach items="${datas}" var="d" varStatus="status">
            {
	        	"dataFlow":"${d.RecID}",
	        	"CheckStatusName":"${d.CheckStatusName}",
	        	"CheckStatus":"${d.CheckStatus}",
	        	"FromID":"${d.FromID}",
	        	"RecDate":"${d.RecDate}",
	        	"DocCount":"${d.DocCount}",
	        	"DocGRPID":"${d.DocGRPID}",
	        	"Col251":"${d.Col251}",
	        	"Col252":"${d.Col252}",
	        	"Col253":"${d.Col253}",
	        	"Col254":"${d.Col254}",
	        	"Col255":"${d.Col255}",
	        	"Col256":"${d.Col256}",
	        	"Col257":"${d.Col257}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	],
    "finishMap":{
        "reqNum":"${finishMap.RequireNumber+0}",
        "finishNum":"${finishMap.Finish+0}",
        "noFinishNum":"${(finishMap.RequireNumber-finishMap.Finish+0)<0?0:(finishMap.RequireNumber-finishMap.Finish+0)}"
    }