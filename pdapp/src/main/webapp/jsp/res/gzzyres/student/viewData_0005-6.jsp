<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	      <c:forEach items="${assessTmp}" var="tmp" varStatus="status">
	          {
	              "inputId":"${tmp.reqItemId}",
	              "label":"${tmp.reqItemName}",
	              "tip":"${tmp.reqItemName}",
	              "inputType":"title"
	          },
	          {
	              "inputId":"${tmp.reqItemId}_score",
	              "label":"分数",
				  "inputType":"progress",
				  "required":true,
				  "placeholder": "0~${tmp.reqItemMark}",
				  "verify": {
					  "min": "0",
					  "max": "${tmp.reqItemMark}",
				      "type": "int"
				  }
	          }
	          <c:if test='${not status.last}'>
	       ,
	       </c:if>
	      </c:forEach>
	  ]
	  <c:if test='${not empty marks}'>
	  ,
	  "values":[
	      <c:forEach items="${marks}" var="mark" varStatus="status">
	          {
	              "inputId":"${mark.reqItemId}_score",
	              "value":"${not empty mark.markDF ? pdfn:stringToInt(mark.markDF):''}"
	          } 
	          <c:if test='${not status.last}'>
	       ,
	       </c:if>     
	      </c:forEach>
	  ]
	  </c:if>