<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 	"head":[
         {
             "headId":"tecName",
             "label":"讲解人"
         },
    	 {
            "headId": "dateTime",
            "label": "讲解时间"
        }

    ],
    "datas": [
    	<c:if test='${not empty enteredDeptEdu}'>
	        {
	        	"dataFlow":"${enteredDeptEdu.ursdId}",
	        	"tecName":"${enteredDeptEdu.tecName}",
	        	"dateTime":"${enteredDeptEdu.dateTime}"
	        }
	    </c:if>
	]
 --%>