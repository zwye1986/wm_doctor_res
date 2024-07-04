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
				"recId":"${bean.recId}",
				"docFlow":"${bean.docFlow}",
				"schDeptName":"${bean.schDeptName}",
				"type": "${bean.typeName}",
				"speakerName":"${bean.speakerName}",
				"cySecId":"${bean.cySecId}",
				"activityTime":"${bean.activityTime}",
				"activityContent":${pdfn:toJsonString(bean.content)},
				"timeLength":"${bean.timeLength}",
				"demo":"${bean.demo}",
				"content":"${bean.shContent}",
				"auditName":"${bean.auditName}<c:if test="${not empty bean.auditDept}">（${bean.auditDept}）</c:if>",
				"auditDept":"${bean.auditDept}",
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