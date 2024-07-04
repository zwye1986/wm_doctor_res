<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		"save":"提交评分"
	},
	"inputs":[
			  {
	              "inputId":"00000",
	              "label":"医德医风",
	              "tip":"医德医风",
	              "inputType":"title"
	          },
	          {
	              "inputId":"00f2bb34480043c5b7161e6443ac8f6a",
	              "label":"仪表端庄，举止文明，带教中能言传身教、以身作则，具有良好职业道德和医疗作风",
	              "tip":"仪表端庄，举止文明，带教中能言传身教、以身作则，具有良好职业道德和医疗作风",
	              "inputType":"title"
	          },
	          {
	              "inputId":"00f2bb34480043c5b7161e6443ac8f6a_score",
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
	          },
	          {
	              "inputId":"00f2bb34480043c5b7161e6443ac8f6a_lostReason",
	              "label":"扣分原因",
	              "inputType":"text",
	              "required":false
	          }
	          ,
	          {
	              "inputId":"7d7cce93aafd4bdfb25336105bac5dbb",
	              "label":"能及时签字认可培训内容,并对完成情况做出适当评价,提出建议",
	              "tip":"能及时签字认可培训内容,并对完成情况做出适当评价,提出建议",
	              "inputType":"title"
	          },
	          {
	              "inputId":"7d7cce93aafd4bdfb25336105bac5dbb_score",
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
	          },
	          {
	              "inputId":"7d7cce93aafd4bdfb25336105bac5dbb_lostReason",
	              "label":"扣分原因",
	              "inputType":"text",
	              "required":false
	          }
	     		,
	     	{
	              "inputId":"00001",
	              "label":"带教水平",
	              "tip":"带教水平",
	              "inputType":"title"
	          },
	    	  {
	              "inputId":"8f616bb3fc8f415d9be78ee757620195",
	              "label":"重视临床带教工作，能按照实习大纲要求妥善安排实习内容",
	              "tip":"重视临床带教工作，能按照实习大纲要求妥善安排实习内容",
	              "inputType":"title"
	          },
	          {
	              "inputId":"8f616bb3fc8f415d9be78ee757620195_score",
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
	          },
	          {
	              "inputId":"8f616bb3fc8f415d9be78ee757620195_lostReason",
	              "label":"扣分原因",
	              "inputType":"text",
	              "required":false
	          }
		      ,
		      {
		          "inputId":"total_label",
		          "label":"总分",
		          "inputType":"title"
		      },
		      {
		          "inputId":"total_score",
		          "label":"分数",
		          "inputType":"text",
		          "required":true
		      }
	  ]
	  ,
	  "values":[
          {
              "inputId":"00f2bb34480043c5b7161e6443ac8f6a_score",
              "value":"90"
          } 
	      ,
	      {
              "inputId":"00f2bb34480043c5b7161e6443ac8f6a_lostReason",
              "value":"老师总是迟到"
          } 
	      ,
	      {
              "inputId":"8f616bb3fc8f415d9be78ee757620195_score",
              "value":"70"
          } 
	      ,
	      {
              "inputId":"8f616bb3fc8f415d9be78ee757620195_lostReason",
              "value":"老师总是迟到"
          } 
	      ,
	      {
	          "inputId":"total_score",
	          "value":80
	      }
	  ]
