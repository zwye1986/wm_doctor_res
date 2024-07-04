<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
			<c:if test='${doctor.roleFlow == 4}'>
			{
			   "inputId":"t0",
			   "label":"实习鉴定",
			   "inputType":"title"
			},
			{
			    "inputId":"BriefRequrie",
			    "label":"个人小结",
			    "inputType":"textarea"
			}
			</c:if>
			<c:if test='${doctor.roleFlow == 5}'>
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
			</c:if>
			<c:if test='${doctor.roleFlow != 4 and doctor.roleFlow != 5 }'>
			{
	            "inputId":"t0",
	            "label":"出科小结",
	            "inputType":"title"
	         },
             {
                 "inputId":"BriefRequrie",
                 "label":"文献、综述、读书报告学习及撰写情况",
                 "inputType":"textarea"
             },
             {
                 "inputId":"GainsDefect",
                 "label":"本次轮转的收获与不足",
                 "inputType":"textarea"
             }
			</c:if>
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
     ,
     "values":[
			{
			    "inputId":"BriefRequrie",
			    "value":${pdfn:toJsonString(outSecBrief.BriefRequrie)}
			},
			{
			    "inputId":"GainsDefect",
			    "value":${pdfn:toJsonString(outSecBrief.GainsDefect)}
			},
			{
			    "inputId":"SecAppraise",
			    "value":${pdfn:toJsonString(outSecBrief.SecAppraise)}
			},
			<c:forEach items="${marks}" var="mark" varStatus="status">
			    {
			        "inputId":"${mark.ReqItemID}_score",
			        "value":"${pdfn:stringToInt(mark.MarkDF)}"
			    } 
			    <c:if test='${not status.last}'>
			 ,
			 </c:if>     
			</c:forEach>
			<c:if test="${marks.size()>0 }">
			,
			</c:if>
			{
			    "inputId":"total_score",
			    "value":${pdfn:toJsonString(examInfo.ExamInfoDF)}
			},
			<c:if test="${not empty cycleOutSectionRecord.AssessmentMark}">
			{
			    "inputId":"AssessmentMark",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.AssessmentMark)}
			},
			</c:if>
			<c:if test="${empty cycleOutSectionRecord or empty cycleOutSectionRecord.AssessmentMark}">
			{
			    "inputId":"AssessmentMark",
			    "value":${pdfn:toJsonString(theoryScore)}
			},
			</c:if>
			{
			    "inputId":"SickLeaveDay",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.SickLeaveDay)}
			},
			{
			    "inputId":"AbsenteeismDay",
			    "value":${pdfn:toJsonString(cycleOutSectionRecord.AbsenteeismDay)}
			    }
			]