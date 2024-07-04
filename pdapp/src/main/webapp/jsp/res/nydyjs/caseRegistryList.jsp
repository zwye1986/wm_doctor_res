<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"processMap":{
			"bhId":"${processMap.bhId}",
			"tableId":"${processMap.tableId}",
			"cySecId":"${processMap.cySecId}",
			"reqNum":"${processMap.reqNum}",
			"finishNum":"${processMap.finishNum}",
			"checkCount":"${processMap.checkCount}",
			"balance":"${processMap.balance}"
		},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"recId":"${bean.recId}",
				"fromId":"${bean.fromId}",
				"operTime":"${bean.operTime}",
				"brName": "${bean.brName}",
				"brNo": "${bean.brNo}",
				"type": "${bean.type}",
				"docFlow":"${param.docFlow}",
				"classId": "1",
				"cySecId":"${param.cySecId}",
				"content":"${bean.content}",
				"pyId":"${bean.pyId}",
				"pyContent":"${bean.pyContent}",
				"checkStatus":
						"${bean.checkStatus}",
				"checkStatusName":
						<c:if test="${bean.checkStatus=='0' or empty bean.checkStatus}">
							"未审核"
						</c:if>
						<c:if test="${bean.checkStatus=='1'}">
							"审核通过"
						</c:if>
						<c:if test="${bean.checkStatus=='2'}">
							"审核不通过"
						</c:if>
						<c:if test="${bean.checkStatus=='3'}">
							"退回"
						</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}