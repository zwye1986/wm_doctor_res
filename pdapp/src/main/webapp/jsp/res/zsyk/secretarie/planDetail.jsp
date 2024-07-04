<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "datas": [
            {
                "id": "CaDisMainSpeakerType",
                "name": "主讲人类型：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaDisMainSpeakerType)}
            },
            {
                "id": "CaDisMainSpeaker",
                "name": "主   讲   人  ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaDisMainSpeaker)}
            },
            {
                "id": "HosSecName",
                "name": "科           室 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.HosSecName)}
            },
            {
                "id": "DicItem",
                "name": "活 动 形 式 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.DicItem)}
            },
            {
                "id": "CaName",
                "name": "活 动 名 称 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaName)}
            },
            {
                "id": "CaDisTime",
                "name": "开 始 时 间 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaDisTime)}
            },
            {
                "id": "CaEndDisTime",
                "name": "结 束 时 间 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaEndDisTime)}
            },
            {
                "id": "CaAddress",
                "name": "地           点 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaAddress)}
            },
            {
                "id": "CaPeople",
                "name": "联 系 方 式 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaPeople)}
            },
            {
                "id": "CheckStatus",
                "name": "活 动 状 态 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(detail.CheckStatus )}
            },
            {
                "id": "CheckStatusName",
                "name": "活 动 状 态 ：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CheckStatusName )}
            },
            {
                "id": "CaCount",
                "name": "参 加 学 员 ：",
                "type": "click",
                "required":false,
                "readonly":false,
                "value":${pdfn:toJsonString(detail.CaCount)}
            },
            {
                "id": "CaDisContent",
                "name": "简           介 ：",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(detail.CaDisContent)}
            }
    ]
	</c:if>
}
