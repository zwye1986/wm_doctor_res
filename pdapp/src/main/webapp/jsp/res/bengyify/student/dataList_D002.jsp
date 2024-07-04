<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"head":[
         {
             "headId":"RecData1",
             "label":"病种"
         },
         <%--{--%>
             <%--"headId":"RecData2",--%>
             <%--"label":"ID号"--%>
         <%--},--%>
    	 {
            "headId": "RecData3",
            "label": "病人姓名"
        },
        {
            "headId": "RecData4",
            "label": "住院号"
        },
        {
            "headId":"RecData5",
            "label":"入院日期"
        }

    ],
    "datas": [
        <c:forEach items="${caseClasses}" var="caseClass" varStatus="status">
            {
	        	"dataFlow":"${caseClass.dataFlow}",
	        	"RecData1":"${caseClass.RecData1}",
	        	<%--"RecData2":"${caseClass.RecData2}",--%>
	        	"RecData3":"${caseClass.RecData3}",
	        	"RecData4":"${caseClass.RecData4}",
	        	"RecData5":"${caseClass.RecData5}"
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]