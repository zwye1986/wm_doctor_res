<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"Col263",
             "label":"病人姓名"
         },
    	 {
            "headId": "Col262",
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
	        	"Col261":"${d.Col261}",
	        	"Col262":"${d.Col262}",
	        	"Col263":"${d.Col263}",
	        	"Col264":"${d.Col264}",
	        	"Col265":"${d.Col265}",
	        	"Col266":"${d.Col266}",
	        	"Col267":"${d.Col267}"
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