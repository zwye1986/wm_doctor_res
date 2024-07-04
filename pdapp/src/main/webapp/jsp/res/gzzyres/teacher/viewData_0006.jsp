<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
             {
                 "inputId":"Mini_TecType",
                 "label":"教师类型",
                 "inputType":"select",
                 "required":true,
                 "options": [
                     {
                         "optionId": "2",
                         "optionDesc": "高级职称"
                     },
                     {
                         "optionId": "1",
                         "optionDesc": "中级职称"
                     },
                     {
                         "optionId": "0",
                         "optionDesc": "初级职称"
                     }
                 ]
             },
             {
                 "inputId":"Mini_Time",
                 "label":"评估日期",
                 "inputType":"datetime",
                 "required": true,
                 "placeholder":""
             },
             {
                 "inputId":"Mini_Address",
                 "label":"评估地点",
                 "inputType":"select",
                 "required":true,
                 "options": [
                     {
                         "optionId": "0",
                         "optionDesc": "病房"
                     },
                     {
                         "optionId": "1",
                         "optionDesc": "门诊"
                     },
                     {
                         "optionId": "2",
                         "optionDesc": "急诊"
                     },
                     {
                         "optionId": "3",
                         "optionDesc": "手术室"
                     }
                 ]
             },
             {
                 "inputId":"Mini_Name",
                 "label":"病人姓名",
                 "inputType":"text",
                 "required":true
             },
             {
                 "inputId":"Mini_Age",
                 "label":"病人年龄",
                 "inputType":"text",
                 "required":true,
                 "verify": {
					"type": "int",
					"maxLength": 2
				 }
             },
             {
                 "inputId":"Mini_Sex",
                 "label":"病人性别",
                 "inputType":"select",
                 "required": true,
                 "options": [
                     {
                         "optionId": "1",
                         "optionDesc": "男"
                     },
                     {
                         "optionId": "0",
                         "optionDesc": "女"
                     }
                 ]
             },
             {
                 "inputId":"Mini_PType",
                 "label":"病人来源",
                 "inputType":"select",
                 "required": true,
                 "options": [
                     {
                         "optionId": "0",
                         "optionDesc": "门诊病人"
                     },
                     {
                         "optionId": "1",
                         "optionDesc": "住院病人"
                     }
                 ]
             },
             {
                 "inputId":"Mini_PTypeState",
                 "label":"病人来源类型",
                 "inputType":"select",
                 "required": true,
                 "options": [
                     {
                         "optionId": "0",
                         "optionDesc": "新病人"
                     },
                     {
                         "optionId": "1",
                         "optionDesc": "复诊病人"
                     }
                 ]
             },
             {
                 "inputId":"Mini_PDiagnosis",
                 "label":"诊断",
                 "inputType":"text",
                 "required":true
             },
             {
                 "inputId":"Mini_Operate",
                 "label":"病情严重程度",
                 "inputType":"select",
                 "required":true,
                 "options": [
                     {
                         "optionId": "1",
                         "optionDesc": "轻"
                     },
                     {
                         "optionId": "2",
                         "optionDesc": "中"
                     },
                     {
                         "optionId": "3",
                         "optionDesc": "重"
                     }
                 ]
             },
             {
                 "inputId":"Mini_Num",
                 "label":"诊治重点",
                 "inputType":"select",
                 "required": true,
                 "options": [
                     {
                         "optionId": "1",
                         "optionDesc": "病史采集"
                     },
                     {
                         "optionId": "2",
                         "optionDesc": "诊断"
                     },
                     {
                         "optionId": "3",
                         "optionDesc": "治疗"
                     },
                     {
                         "optionId": "4",
                         "optionDesc": "健康宣传"
                     }
                 ]
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
                      "inputType":"progress",
                      "required":true,
                      "placeholder": "1~9",
                      "verify": {
                          "min": "1",
                          "max": "9",
                          "type": "int"
                      }
                 }
                 <c:if test='${not status.last}'>
	             ,
	             </c:if>
             </c:forEach>
             <c:if test="${assessTmp.size()>0 }">
             ,
             </c:if>
             {
                 "inputId":"Mini_ReviewTime",
                 "label":"评审观察时间",
                 "inputType":"text",
                 "required":true,
                 "verify": {
					"type": "int",
					"maxLength": 3
				 }
             },
             {
                 "inputId":"Mini_Feedback",
                 "label":"指导反馈时间",
                 "inputType":"text",
                 "required":true,
                 "verify": {
					"type": "int",
					"maxLength": 3
				}
             },
             {
                 "inputId":"Mini_TecScore",
                 "label":"教师对学员测评满意程度",
                      "inputType":"progress",
                      "required":true,
                      "placeholder": "1~9",
                      "verify": {
                          "min": "1",
                          "max": "9",
                          "type": "int"
                      }
             },
             {
                 "inputId":"Mini_TecMessage",
                 "label":"教师的评语",
                 "inputType":"textarea",
                 "required":true
             }
             <c:if test="${outMiniCex.Mini_State eq 1 }">
             ,
             {
                 "inputId":"Mini_StuScore",
                 "label":"学员对此次测评满意程度",
                 "readonly":true,
                 "inputType":"progress",
                 "required":true,
                 "placeholder": "1~9",
                 "verify": {
                     "min": "1",
                     "max": "9",
                     "type": "int"
                 }
             }
             </c:if>
         ]
         ,
         "values":[
         	 {
                 "inputId":"Mini_TecType",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_TecType)}
             },
             {
                 "inputId":"Mini_Time",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Time)}
             },
             {
                 "inputId":"Mini_Address",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Address)}
             },
             {
                 "inputId":"Mini_Name",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Name)}
             },
             {
                 "inputId":"Mini_Age",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Age)}
             },
             {
                 "inputId":"Mini_Sex",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Sex)}
             },
             {
                 "inputId":"Mini_PType",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_PType)}
             },
             {
                 "inputId":"Mini_PTypeState",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_PTypeState)}
             },
             {
                 "inputId":"Mini_PDiagnosis",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_PDiagnosis)}
             },
             {
                 "inputId":"Mini_Operate",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Operate)}
             },
             {
                 "inputId":"Mini_Num",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Num)}
             },
             {
                 "inputId":"Mini_ReviewTime",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_ReviewTime)}
             },
             {
                 "inputId":"Mini_Feedback",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_Feedback)}
             },
             {
                 "inputId":"Mini_TecScore",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_TecScore)}
             },
             {
                 "inputId":"Mini_TecMessage",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_TecMessage)}
             },
             {
                 "inputId":"Mini_StuScore",
                 "value":${pdfn:toJsonString(outMiniCex.Mini_StuScore)}
             },
	         <c:forEach items="${marks}" var="mark" varStatus="status">
                 {
                     "inputId":"${mark.ReqItemID}_score",
                     "value":"${not empty mark.MarkDF ? pdfn:stringToInt(mark.MarkDF):''}"
                 }
                 <c:if test='${not status.last}'>
	             ,
	             </c:if>
             </c:forEach>
         ]