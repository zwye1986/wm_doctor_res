<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "datas": [
    	<c:forEach items="${list}" var="data" varStatus="status">
	        {
	            "doctorFlow": ${pdfn:toJsonString(param.docFlow)},
	            "processFlow": ${pdfn:toJsonString(param.processFlow)},
	            "resultFlow": ${pdfn:toJsonString(param.resultFlow)},
	            "recTypeName": ${pdfn:toJsonString(data.recTypeName)},
	            "recTypeId": ${pdfn:toJsonString(data.recTypeId)},
	            "notAuditNum": ${pdfn:toJsonString(data.notAuditNum)}
	        }
			<c:if test="${!status.last}">
				,
			</c:if>
    	</c:forEach>
    ]
    </c:if>
}
