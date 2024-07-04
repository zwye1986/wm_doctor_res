<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "datas": [
            {
                "id": "truename",
                "name": "学员姓名：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(stuInitData.truename)}
            },
            {
                "id": "StartYear",
                "name": "年        级：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(stuInitData.StartYear)}
            },
            {
                "id": "SpName",
                "name": "专        业：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(stuInitData.SpName)}
            },
            {
                "id": "UserType",
                "name": "学员类型：",
                "type": "option",
                "options":[
                    <c:forEach items="${roles}" var="role" varStatus="r">
                        {
                            "optionId":${pdfn:toJsonString(role.RoleID)},
                            "optionDesc":${pdfn:toJsonString(role.RoleName)}
                        }
                        <c:if test="${!r.last}">
                        ,
                        </c:if>
                    </c:forEach>
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(stuInitData.UserType)}
            },
            <%--{--%>
                <%--"id": "SecName",--%>
                <%--"name": "标准科室：",--%>
                <%--"type": "text",--%>
                <%--"required":false,--%>
                <%--"readonly":true,--%>
                <%--"value":${pdfn:toJsonString(secName)}--%>
            <%--},--%>
            {
                "id": "DOPS_TecType",
                "name": "教师类型：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"初级职称"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"中级职称"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"高级职称"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_TecType)}
            },
            {
                "id": "DOPS_Time",
                "name": "评估日期：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Time)}
            },
            {
                "id": "DOPS_Address",
                "name": "评估地点：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"病房"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"门诊"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"急诊"
                    },
                    {
                        "optionId":"3",
                        "optionDesc":"手术室"
                    },
                    {
                        "optionId":"4",
                        "optionDesc":"检查室"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Address)}
            },
            {
                "id": "DOPS_Name",
                "name": "病人姓名：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Name)}
            },
            {
                "id": "DOPS_Age",
                "name": "年        龄：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Age)}
            },
            {
                "id": "DOPS_Sex",
                "name": "性        别：",
                "type": "option",
                "options":[
                    {
                        "optionId":"1",
                        "optionDesc":"男"
                    },
                    {
                        "optionId":"0",
                        "optionDesc":"女"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Sex)}
            },
            {
                "id": "DOPS_PType1",
                "name": "病人来源1：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"门诊病人"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"住院病人"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_PType)}
            },
            {
                "id": "DOPS_PTypeState",
                "name": "病人来源2：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"新病人"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"复诊病人"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_PTypeState)}
            },
            {
                "id": "DOPS_PDiagnosis",
                "name": "病人主要诊断：",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_PDiagnosis)}
            },
            {
                "id": "DOPS_Operate",
                "name": "操作技能：",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Operate)}
            },
            {
                "id": "DOPS_Num",
                "name": "评估前学员执行操作例数：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"0"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"1-3"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":">4"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Num)}
            },
            {
                "id": "DOPS_Level",
                "name": "技能复杂程度：",
                "type": "option",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"低度"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"中度"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"高度"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_Level)}
            },
            <c:forEach items="${items}" var="item" varStatus="s">
                <c:set var="key" value="${item.ReqItemID}"></c:set>
                <c:set var="result" value="${resultMap[key]}"></c:set>
                {
                    "id": "${item.ReqItemID},${result.ExamInfoID}",
                    "name": "${item.ReqItemName}",
                    "type": "select",
                    "required":false,
                    "readonly":true,
                    "value":${pdfn:toJsonString(result.MarkDF)}
                }
                ,
            </c:forEach>
            {
                "id": "DOPS_ReviewTime",
                "name": "评审观察时间：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":"${empty dopsData.DOPS_ReviewTime ? 0:dopsData.DOPS_ReviewTime}分"
            },
            {
                "id": "DOPS_Feedback",
                "name": "指导反馈时间：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":"${empty dopsData.DOPS_Feedback ? 0:dopsData.DOPS_Feedback}分"
            },
            {
                "id": "DOPS_TecScore",
                "name": "教师对学员测评满意程度",
                "type": "select2",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_TecScore)}
            },
            {
                "id": "DOPS_TecMessage",
                "name": "教师的评语",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_TecMessage)}
            },
            {
                "id": "DOPS_StuScore",
                "name": "学员对此处测评满意程度",
                "type": "select2",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.DOPS_StuScore)}
            },
            {
                "id": "TecName",
                "name": "教师签名",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.TecName)}
            },
            {
                "id": "StuName",
                "name": "学员签名",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(dopsData.StuName)}
            }
    ]
	</c:if>
}
