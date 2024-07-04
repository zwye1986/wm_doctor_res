<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"Col232",
             "label":"病人姓名"
         },
    	 {
            "headId": "Col231",
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
	        	"Col231":"${d.Col231}",
	        	"Col232":"${d.Col232}",
	        	"Col233":"${d.Col233}",
	        	"Col234":"${d.Col234}",
	        	"Col235":"${d.Col235}",
	        	"Col236":"${d.Col236}"
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