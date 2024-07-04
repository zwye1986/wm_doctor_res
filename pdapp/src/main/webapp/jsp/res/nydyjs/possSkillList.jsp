<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
	"processMap":{
			"reqNum":"${processMap.reqNum}",
			"finishNum":"${processMap.finishNum}"
		},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
				"recId":"${bean.recId}",
				"docFlow":"${bean.docFlow}",
				"docName":"${bean.docName}",
				"operTime":"${bean.operTime}",
				"brName": "${bean.brName}",
				"brNo": "${bean.brNo}",
				"skillName": "${bean.skillName}",
				"demo": "${bean.demo}",
				"szzw": "${bean.szzw}",
				"czsj": "${bean.czsj}",
				"md": "${bean.md}",
				"sqsh": "${bean.sqsh}",
				"czTime": "${bean.czTime}",
				"ssgc": "${bean.ssgc}",
				"zwqk": "${bean.zwqk}",
				"mzff": "${bean.mzff}",
				"szcz": "${bean.szcz}",
				"tsqk": "${bean.tsqk}",
				"zdlx": "${bean.zdlx}",
				"fromId":"${bean.fromId}",
				"classId": "4",
				"auditName":"${bean.auditName}<c:if test="${not empty bean.auditDept}">（${bean.auditDept}）</c:if>",
				"auditDept":"${bean.auditDept}",
				"content":"${bean.content}",
				"pyId":"${bean.pyId}",
				"pyContent":"${bean.pyContent}",
				"cySecId":"${param.cySecId}",
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