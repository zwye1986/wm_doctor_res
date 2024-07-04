<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveTeachPlan",
        "save":"保存"
    },
    "datas": [
            {
                "id": "CaDisID",
                "name": "主讲人类型：",
                "type": "text",
                "required":true,
                "readonly":false,
                "hidden":true,
                "value":${pdfn:toJsonString(detail.CaDisID)}
            },
            {
                "id": "CaDisMainSpeakerTypeId",
                "name": "主讲人类型：",
                "type": "pullDown",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"带教老师"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"轮转学员"
                    }
                ],
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(empty detail.CaDisMainSpeakerTypeId ? '0':detail.CaDisMainSpeakerTypeId)}
            },
            {
                "id": "HosSecId",
                "name": "科           室 ：",
                "type": "pullDown",
                "options":[
                    {
                        "optionId":"0",
                        "optionDesc":"本科室"
                    },
                    {
                        "optionId":"1",
                        "optionDesc":"其他科室"
                    }
                ],
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(empty HosSecId ? '0':HosSecId)}
            },
            {
                "id": "TecID",
                "name": "主    讲   人 ：",
                "type": "pullDown",
                "options":[
                    <c:forEach items="${users}" var="role" varStatus="r">
                    {
                        "optionId":${pdfn:toJsonString(role.userFlow)},
                        "optionDesc":${pdfn:toJsonString(role.userName)}
                    }
                    <c:if test="${!r.last}">
                    ,
                    </c:if>
                    </c:forEach>
                ],
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.TecID)}
            },
            {
                "id": "CaDisPlayClass",
                "name": "活 动 形 式 ：",
                "type": "pullDown",
                "options":[
                        <c:forEach items="${dicts}" var="role" varStatus="r">
                        {
                            "optionId":${pdfn:toJsonString(role.DicItemID)},
                            "optionDesc":${pdfn:toJsonString(role.DicItem)}
                        }
                        <c:if test="${!r.last}">
                        ,
                        </c:if>
                        </c:forEach>
                ],
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaDisPlayClass)}
            },
            {
                "id": "CaAddress",
                "name": "地           点 ：",
                "type": "text",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaAddress)}
            },
            {
                "id": "CaPeople",
                "name": "联 系 方 式 ：",
                "type": "text",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaPeople)}
            },
            {
                "id": "CaDisTime",
                "name": "开 始 时 间 ：",
                "type": "date",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaDisTime)}
            },
            {
                "id": "CaEndDisTime",
                "name": "结 束 时 间 ：",
                "type": "date",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaEndDisTime)}
            }
    ]
	</c:if>
}
