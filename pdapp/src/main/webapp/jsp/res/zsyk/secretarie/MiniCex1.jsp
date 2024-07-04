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
                "id": "Mini_TecType",
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
                "value":${pdfn:toJsonString(miniData.Mini_TecType)}
            },
            {
                "id": "Mini_Time",
                "name": "评估日期：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Time)}
            },
            {
                "id": "Mini_Address",
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
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Address)}
            },
            {
                "id": "Mini_Name",
                "name": "病人姓名：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Name)}
            },
            {
                "id": "Mini_Age",
                "name": "年        龄：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Age)}
            },
            {
                "id": "Mini_Sex",
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
                "value":${pdfn:toJsonString(miniData.Mini_Sex)}
            },
            {
                "id": "Mini_PType1",
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
                "value":${pdfn:toJsonString(miniData.Mini_PType)}
            },
            {
                "id": "Mini_PTypeState",
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
                "value":${pdfn:toJsonString(miniData.Mini_PTypeState)}
            },
            {
                "id": "Mini_PDiagnosis",
                "name": "诊        断：",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_PDiagnosis)}
            },
            {
                "id": "Mini_Operate",
                "name": "病情严重程度：",
                "type": "option",
                "options":[
                    {
                        "optionId":"1",
                        "optionDesc":"轻"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"中"
                    },
                    {
                        "optionId":"3",
                        "optionDesc":"重"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Operate)}
            },
            {
                "id": "Mini_Num",
                "name": "诊治重点：",
                "type": "option",
                "options":[
                    {
                        "optionId":"1",
                        "optionDesc":"病史采集"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"诊断"
                    },
                    {
                        "optionId":"3",
                        "optionDesc":"治疗"
                    },
                    {
                        "optionId":"4",
                        "optionDesc":"健康宣传"
                    }
                ],
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_Num)}
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
                "id": "Mini_ReviewTime",
                "name": "评审观察时间：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":"${empty miniData.Mini_ReviewTime ? 0:miniData.Mini_ReviewTime}分"
            },
            {
                "id": "Mini_Feedback",
                "name": "指导反馈时间：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":"${empty miniData.Mini_Feedback ? 0:miniData.Mini_Feedback}分"
            },
            {
                "id": "Mini_TecScore",
                "name": "教师对学员测评满意程度",
                "type": "select2",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_TecScore)}
            },
            {
                "id": "Mini_TecMessage",
                "name": "教师的评语",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_TecMessage)}
            },
            {
                "id": "Mini_StuScore",
                "name": "学员对此处测评满意程度",
                "type": "select2",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.Mini_StuScore)}
            },
            {
                "id": "TecName",
                "name": "教师签名",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.TecName)}
            },
            {
                "id": "StuName",
                "name": "学员签名",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(miniData.StuName)}
            }
    ]
	</c:if>
}
