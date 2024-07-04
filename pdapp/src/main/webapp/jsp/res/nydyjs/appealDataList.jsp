<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"docFlow":"${param.docFlow}",
				"docName":"${bean.docName}",
				"schDeptName":"${bean.schDeptName}",
				"recId":"${bean.explanId}",
				"needNum":"${bean.needNum}",
				"havaAppealNum":"${bean.havaAppealNum}",
				"finishNum":"${bean.finishNum}",
				"type":"${bean.type}",
				"typeName": "${bean.typeName}",
				"appealObjId": "${bean.appealObjId}",
				"appealObjName": "${bean.appealObjName}",
				"appealNum": "${bean.appealNum}",
								"appealResult": "${bean.appealResult}",
				"appealReason": "${bean.appealReason}",
				"appealTime":"${bean.appealTime}",
				"checkStatus":"${bean.checkStatus}",
				"checkStatusName":"${bean.checkStatusName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}