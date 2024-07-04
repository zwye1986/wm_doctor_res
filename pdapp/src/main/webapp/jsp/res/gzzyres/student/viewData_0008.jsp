<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"inputs":[
        {
            "inputId":"DOPS_TecType",
            "label":"教师类型",
            "inputType":"select",
            "readonly":true,
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
            "inputId":"DOPS_Time",
            "label":"评估日期",
            "inputType":"datetime",
            "readonly": true,
            "placeholder":""
        },
        {
            "inputId":"DOPS_Address",
            "label":"评估地点",
            "inputType":"select",
            "readonly":true,
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
            "inputId":"DOPS_Name",
            "label":"病人姓名",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_Age",
            "label":"病人年龄",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_Sex",
            "label":"病人性别",
            "inputType":"select",
            "readonly":true,
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
            "inputId":"DOPS_PType",
            "label":"病人来源",
            "inputType":"select",
            "readonly":true,
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
            "inputId":"DOPS_PTypeState",
            "label":"病人来源类型",
            "inputType":"select",
            "readonly":true,
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
            "inputId":"DOPS_PDiagnosis",
            "label":"病人主要诊断",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_Operate",
            "label":"操作技能",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_Num",
            "label":"评估前学员执行操作例数",
            "inputType":"select",
            "readonly":true,
            "options": [
                {
                    "optionId": "0",
                    "optionDesc": "0"
                },
                {
                    "optionId": "1",
                    "optionDesc": "1-3"
                },
                {
                    "optionId": "2",
                    "optionDesc": "4"
                }
            ]
        },
        {
            "inputId":"DOPS_Level",
            "label":"技能复杂程度",
            "inputType":"select",
            "readonly":true,
            "options": [
                {
                    "optionId": "0",
                    "optionDesc": "低度"
                },
                {
                    "optionId": "1",
                    "optionDesc": "中度"
                },
                {
                    "optionId": "2",
                    "optionDesc": "高度"
                }
            ]
        }
        <c:if test="${outDops.DOPS_State eq 1 }">
        ,
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
                "inputType":"checkRadio",
                "readonly":true,
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
        <c:if test="${assessTmp.size()>0 }">
        ,
        </c:if>
        {
            "inputId":"DOPS_ReviewTime",
            "label":"评审观察时间",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_Feedback",
            "label":"指导反馈时间",
            "inputType":"text",
            "readonly":true
        },
        {
            "inputId":"DOPS_TecScore",
            "label":"教师对学员测评满意程度",
            "inputType":"checkRadio",
            "readonly":true,
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
            "inputId":"DOPS_TecMessage",
            "label":"教师的评语",
            "inputType":"textarea",
            "readonly":true
        }
        </c:if>
        <c:if test="${outDops.DOPS_State eq 0 }">
        ,
        {
            "inputId":"DOPS_StuScore",
            "label":"学员对此次测评满意程度",
            "inputType":"checkRadio",
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
        </c:if>
        <c:if test="${outDops.DOPS_State eq 1 }">
        ,
        {
            "inputId":"DOPS_StuScore",
            "label":"学员对此次测评满意程度",
            "inputType":"checkRadio",
            "readonly":true,
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
        </c:if>
    ]
    ,
    "values":[
    	 {
            "inputId":"DOPS_TecType",
            "value":${pdfn:toJsonString(outDops.DOPS_TecType)}
        },
        {
            "inputId":"DOPS_Time",
            "value":${pdfn:toJsonString(outDops.DOPS_Time)}
        },
        {
            "inputId":"DOPS_Address",
            "value":${pdfn:toJsonString(outDops.DOPS_Address)}
        },
        {
            "inputId":"DOPS_Name",
            "value":${pdfn:toJsonString(outDops.DOPS_Name)}
        },
        {
            "inputId":"DOPS_Age",
            "value":${pdfn:toJsonString(outDops.DOPS_Age)}
        },
        {
            "inputId":"DOPS_Sex",
            "value":${pdfn:toJsonString(outDops.DOPS_Sex)}
        },
        {
            "inputId":"DOPS_PType",
            "value":${pdfn:toJsonString(outDops.DOPS_PType)}
        },
        {
            "inputId":"DOPS_PTypeState",
            "value":${pdfn:toJsonString(outDops.DOPS_PTypeState)}
        },
        {
            "inputId":"DOPS_PDiagnosis",
            "value":${pdfn:toJsonString(outDops.DOPS_PDiagnosis)}
        },
        {
            "inputId":"DOPS_Operate",
            "value":${pdfn:toJsonString(outDops.DOPS_Operate)}
        },
        {
            "inputId":"DOPS_Num",
            "value":${pdfn:toJsonString(outDops.DOPS_Num)}
        },
        {
            "inputId":"DOPS_Level",
            "value":${pdfn:toJsonString(outDops.DOPS_Level)}
        },
        {
            "inputId":"DOPS_ReviewTime",
            "value":${pdfn:toJsonString(outDops.DOPS_ReviewTime)}
        },
        {
            "inputId":"DOPS_Feedback",
            "value":${pdfn:toJsonString(outDops.DOPS_Feedback)}
        },
        {
            "inputId":"DOPS_TecScore",
            "value":${pdfn:toJsonString(outDops.DOPS_TecScore)}
        },
        {
            "inputId":"DOPS_TecMessage",
            "value":${pdfn:toJsonString(outDops.DOPS_TecMessage)}
        },
        {
            "inputId":"DOPS_StuScore",
            "value":${pdfn:toJsonString(outDops.DOPS_StuScore)}
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
    ]