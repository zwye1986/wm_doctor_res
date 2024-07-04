<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs": [
        {
            "inputId":"rsdId",
            "label":"入科教育",
            "inputType": "url",
            "path":"${nfyyFuncFlow0001HttpUrl}?UCSID=${param.schDeptFlow}"
        },
        {
            "inputId": "tecName",
            "label": "讲解人",
            "required": true,
            "inputType": "text",
            "placeholder":"讲解人"
        },
        {
            "inputId": "dateTime",
            "label": "讲解时间",
            "required": true,
            "inputType": "date",
            "placeholder":"讲解时间"
        }
     ]
     <c:if test='${!empty enteredDeptEdu}'>
     ,
     "values":[
         {
             "inputId":"tecName",
             "value":"${enteredDeptEdu.tecName}"
         },
         {
             "inputId":"dateTime",
             "value":"${fn:substring(enteredDeptEdu.dateTime , 0 , 10)}"
         }
     ]
     </c:if>