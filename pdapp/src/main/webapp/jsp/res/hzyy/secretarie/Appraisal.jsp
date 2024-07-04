<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveAppraisal",
        "save":"保存"
    },
    "datas": [
            {
                "id": "truename",
                "name": "轮转科室：",
                "type": "text",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(appraisalData.HosSecName)}
            },
            {
                "id": "BriefID",
                "name": "出科小结ID：",
                "type": "textarea",
                "required":true,
                "readonly":true,
                "hidden":true,
                "value":${pdfn:toJsonString(appraisalData.BriefID)}
            },
            {
                "id": "BriefRequrie",
                "name": "个人小结：",
                "type": "textarea",
                "required":false,
                "readonly":true,
                "value":${pdfn:toJsonString(appraisalData.BriefRequrie)}
            },
            <c:forEach items="${items}" var="item" varStatus="s">
                <c:set var="key" value="${item.ReqItemID}"></c:set>
                <c:set var="result" value="${resultMap[key]}"></c:set>
                {
                    "id": "${item.ReqItemID},${result.ExamInfoID}",
                    "name": "${item.ReqItemName}",
                    "type": "select",
                    "required":true,
                    "readonly":false,
                    "value":${pdfn:toJsonString(result.MarkDF)}
                }
                ,
            </c:forEach>
            {
                "id": "ExamInfoDf",
                "name": "对该生的整体评价：",
                "type": "select2",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(appraisalData.ExamInfoDf)}
            },
            {
                "id": "TecAppraise",
                "name": "带教老师审核意见：",
                "type": "textarea",
                "required":true,
                "readonly":true,
                <%--"value":"sdsd"--%>
                "value":${pdfn:toJsonString(appraisalData.TecAppraise)}
            },
            {
                "id": "SecretaryContent",
                "name": "教学秘书审核意见：",
                "type": "textarea",
                "required":true,
                "readonly":false,
                "value":${pdfn:toJsonString(appraisalData.SecretaryContent)}
            }
    ]
	</c:if>
}
