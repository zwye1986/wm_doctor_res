<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
     	<c:forEach items="${activityUsers}" var="user" varStatus="status">
            {
	             "inputId": "${user.CaDisID}",
	             "label": "${user.TrueName}",
	             "inputType": "select",
                 "options": [
                     {
                         "optionId": "Y",
                         "optionDesc": "参加"
                     },
                     {
                         "optionId": "N",
                         "optionDesc": "未参加"
                     }
                 ]
	         }
            <c:if test='${not status.last}'>
	         ,
	        </c:if>     
        </c:forEach>
     ]
     ,
     "values": [
        <c:forEach items="${activityUsers}" var="user" varStatus="status">
            {
	             "inputId": "${user.CaDisID}",
	             "value": ""
	         }
            <c:if test='${not status.last}'>
	         ,
	        </c:if>     
        </c:forEach>
    ]