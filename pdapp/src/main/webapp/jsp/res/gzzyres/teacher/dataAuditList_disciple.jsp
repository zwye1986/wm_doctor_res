<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"Col322",
             "label":"病人姓名"
         },
    	 {
            "headId": "Col321",
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
	        	"Col321":"${d.Col321}",
	        	"Col322":"${d.Col322}",
	        	"Col323":"${d.Col323}",
	        	"Col324":"${d.Col324}",
	        	"Col325":"${d.Col325}",
	        	"Col326":"${d.Col326}",
	        	"Col327":"${d.Col327}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	],
    "finishMap":{
        "reqNum":"${finishMap.RequireNumber+0}",
        "finishNum":"${finishMap.Finish+0}"
    }