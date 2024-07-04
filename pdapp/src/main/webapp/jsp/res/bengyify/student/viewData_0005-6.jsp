<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
	  		 {
	              "inputId":"00000",
	              "label":"填写说明:有待加强 1~3|合格4~6|优良 7~9",
	              "tip":"填写说明:有待加强 1~3|合格4~6|优良 7~9",
	              "inputType":"title"
	          },
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
	              "inputType":"select",
	              "required":true,
	              "options": [
	                  <c:forEach var="score" begin="1" end="9" step="1" varStatus="scorestatus">
	                      {
	                          "optionId": "${score}",
	                          "optionDesc": "${score}"
	                      }
	                      <c:if test='${not scorestatus.last}'>
	                   ,
	                   </c:if>
	                  </c:forEach>
	              ]
	          }
	          <c:if test='${not status.last}'>
	       ,
	       </c:if>
	      </c:forEach>
	      ,
	      {
	          "inputId":"total_label",
	          <c:if test='${param.funcFlow eq "0005"}'>
	          "label":"对科室的整体评价",
	          </c:if>
	          <c:if test='${param.funcFlow eq "0006"}'>
	          "label":"对老师的整体评价",
	          </c:if>
	          "inputType":"title"
	      },
	      {
	          "inputId":"total_score",
	          "label":"分数",
	          "inputType":"select",
	          "required":true,
	          "options": [
	              <c:forEach var="score" begin="1" end="9" step="1" varStatus="scorestatus">
	                  {
	                      "optionId": "${score}",
	                      "optionDesc": "${score}"
	                  }
	                  <c:if test='${not scorestatus.last}'>
	               ,
	               </c:if>
	              </c:forEach>
	          ]
	      }
	  ]
	  <c:if test='${not empty marks}'>
	  ,
	  "values":[
	      <c:forEach items="${marks}" var="mark" varStatus="status">
	          {
	              "inputId":"${mark.reqItemId}_score",
	              "value":"${pdfn:stringToInt(mark.markDF)}"
	          } 
	          <c:if test='${not status.last}'>
	       ,
	       </c:if>     
	      </c:forEach>
	      ,
	      {
	          "inputId":"total_score",
	          "value":${pdfn:toJsonString(examInfo.ExamInfoDF)}
	      }
	  ]
	  </c:if>