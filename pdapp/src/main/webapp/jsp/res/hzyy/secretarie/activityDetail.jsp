<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveActivityEval",
        "save":"保    存"
    },
    "datas": [
            {
                "id": "CaDisID",
                "name": "主键：",
                "type": "text",
                "required":true,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(detail.CaDisID)}
            },
            {
                "id": "UmpireScore",
                "name": "评    分：",
                "type": "pullDown",
                "options":[
                    {
                        "optionId":"1",
                        "optionDesc":"1"
                    },
                    {
                        "optionId":"2",
                        "optionDesc":"2"
                    },
                    {
                        "optionId":"3",
                        "optionDesc":"3"
                    },
                    {
                        "optionId":"4",
                        "optionDesc":"4"
                    },
                    {
                        "optionId":"5",
                        "optionDesc":"5"
                    },
                    {
                        "optionId":"6",
                        "optionDesc":"6"
                    },
                    {
                        "optionId":"7",
                        "optionDesc":"7"
                    },
                    {
                        "optionId":"8",
                        "optionDesc":"8"
                    },
                    {
                        "optionId":"9",
                        "optionDesc":"9"
                    },
                    {
                        "optionId":"10",
                        "optionDesc":"10"
                    }
                ],
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.UmpireScore)}
            },
            {
                "id": "TrueName",
                "name": "评分人：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(empty detail.TrueName ? user.userName:detail.TrueName)}
            }
    ]
	</c:if>
}
