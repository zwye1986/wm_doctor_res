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
            <c:forEach var="medicalInfo" items="${medicalInfos}" varStatus="status">
	        {
	        	"dataFlow":"${medicalInfo.dataFlow}",
	        	"hosId": ${pdfn:toJsonString(medicalInfo.no)},
	        	"name":"${medicalInfo.name}",
	        	"time":"${fn:substring(medicalInfo.inDateTime , 0 , 10)}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
	        </c:forEach>
	]