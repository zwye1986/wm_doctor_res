<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"Col273",
             "label":"病人姓名"
         },
    	 {
            "headId": "Col272",
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
	        	"Col271":"${d.Col271}",
	        	"Col272":"${d.Col272}",
	        	"Col273":"${d.Col273}",
	        	"Col274":"${d.Col274}",
	        	"Col275":"${d.Col275}",
	        	"Col276":"${d.Col276}",
	        	"Col277":"${d.Col277}"
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