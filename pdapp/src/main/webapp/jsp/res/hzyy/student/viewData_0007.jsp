<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
         {
             "inputId":"briefRequrie",
             "label":"自我鉴定",
             "inputType":"textarea",
             "required": true,
             "placeholder":"请输入自我评价"
         }
     ]
     <c:if test='${not empty evaluation}'>
     ,
     "values":[
         {
             "inputId":"briefRequrie",
             "value":${pdfn:toJsonString(evaluation.briefRequrie)}
         }
     ]
     </c:if>