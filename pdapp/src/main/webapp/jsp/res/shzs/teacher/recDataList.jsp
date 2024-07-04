<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	<c:if test="${resultId eq '200' }">
	,
	<c:set var="finishkey" value="${param.processFlow}${param.recType}Finished"></c:set>
	<c:set var="reqkey" value="${param.processFlow}${param.recType}ReqNum"></c:set>
	"finishNum":"${processPerMap[finishkey]}",
	"reqNum":"${processPerMap[reqkey]}",
	"notAuditNum": ${notAuditNum},
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount": ${dataCount},
	"head":[
        <c:forEach var="v" items="${views}" varStatus="s">
            {
                "headId":"${v.itemName}",
                "label":"${v.title}"
            }
            <c:if test="${!s.last}">
                ,
            </c:if>
        </c:forEach>
	],
	"datas":[
		<c:forEach items="${dataList}" var="recMap" varStatus="status">
			<c:set var="auditStatusId" value="${recMap.auditStatusId}"/>
			<c:set var="auditName" value="未审核"/>
			<c:set var="auditId" value="Saved"/>
			<c:if test="${!empty auditStatusId}">
				<c:set var="auditName" value="已审核"/>
				<c:set var="auditId" value="Audited"/>
			</c:if>
			{

                <c:forEach var="v" items="${views}" varStatus="s">
                    "${v.itemName}":${pdfn:toJsonString(recMap[v.itemName])},
                </c:forEach>
                "dataFlow":${pdfn:toJsonString(recMap.dataFlow)},
                "auditName":${pdfn:toJsonString(auditName)},
                "auditId":${pdfn:toJsonString(auditId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]
</c:if>
}