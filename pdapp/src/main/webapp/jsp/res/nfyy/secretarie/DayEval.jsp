<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "action":{
        "url":"saveDayEval",
        "save":"保存"
    },
    "datas": [
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
            },
        </c:forEach>
        {
            "id": "ExamInfoDf",
            "name": "对该生的整体评价：",
            "type": "select2",
            "required":true,
            "readonly":false,
            "value":${pdfn:toJsonString(dayEval.ExamInfoDf)}
        }
    ]
	</c:if>
}
