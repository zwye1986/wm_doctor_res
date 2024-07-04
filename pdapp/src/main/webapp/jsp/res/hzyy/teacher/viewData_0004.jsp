<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
        <c:if test="${!read}">
            "action":{
                "save":"保存"
            },
        </c:if>
	"inputs":[
			{
	            "inputId":"t0",
	            "label":"出科小结",
	            "inputType":"title"
	         },
             {
                 "inputId":"BriefRequrie",
                 "label":"出科小结",
                 "inputType":"textarea"
             }
			,
	         {
	            "inputId":"t1",
	            "label":"日常评价表",
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
                 "label":"对该生的整体评价",
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
             },
	         {
	            "inputId":"t2",
	            "label":"带教老师鉴定意见",
	            "inputType":"title"
	         },
             {
                 "inputId":"SecAppraise",
                 "label":"鉴定意见",
                 "inputType":"textarea"
             },
             {
	            "inputId":"t3",
	            "label":"理论成绩",
	            "inputType":"title"
	         },
	         {
                 "inputId":"AssessmentMark",
                 "label":"理论成绩",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"请输入理论成绩分数"
             }
             ,
             {
	            "inputId":"t4",
	            "label":"考勤",
	            "inputType":"title"
	         },
	         {
                 "inputId":"SickLeaveDay",
                 "label":"请假",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"请假天数",
                 "verify": {
					"type": "int",
					"maxLength": 3
				 }
             },
	         {
                 "inputId":"AbsenteeismDay",
                 "label":"脱岗",
                 "inputType":"text",
                 "required":true,
                 "placeholder":"脱岗天数",
                 "verify": {
					"type": "int",
					"maxLength": 3
				 }
             }
         ]