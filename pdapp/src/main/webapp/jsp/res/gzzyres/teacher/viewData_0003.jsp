<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
         {
             "inputId":"eval",
             "label":"过程评价",
             "inputType":"textarea",
             "required": true,
             "placeholder":"请输入评价"
         }
     ]
     ,
     "values":[
	     {
	         "inputId":"eval",
	         "value":${pdfn:toJsonString(evalInfo.Eval)}
	     }
     ]