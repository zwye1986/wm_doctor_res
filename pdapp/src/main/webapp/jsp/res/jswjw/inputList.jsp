<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
   	    <c:if test="${fn:length(resultList)>0}">
    	{
            "inputId": "resultFlow",
            "label": "轮转科室",
            "required":true,
            "inputType": "select",
            "options": [
					{
						"optionId": "",
						"optionDesc":"请选择..."
					}
					<c:if test="${not empty resultList }">
						,
					</c:if>
	                <c:forEach items="${resultList}" var="result" varStatus="status">
						{
		                    "optionId": ${pdfn:toJsonString(result.resultFlow)},
		                    "optionDesc": "${result.schDeptName}(${result.schStartDate}~${result.schEndDate})"
		                }
						<c:if test="${not status.last }">
							,
						</c:if>
					</c:forEach>
	            ]
        },
        </c:if>
    	<c:if test="${param.dataType eq 'mr'}">
    		<jsp:include page="inputMr.jsp">
				<jsp:param name="isChargeOrg" value="${isChargeOrg}"></jsp:param>
			</jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'disease'}">
    		<jsp:include page="inputDisease.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'skill'}">
    		<jsp:include page="inputSkill.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'operation'}">
    		<jsp:include page="inputOperation.jsp"></jsp:include>
    	</c:if>
		<c:if test="${param.dataType eq 'languageTeaching'}">
			<jsp:include page="languageTeaching.jsp"></jsp:include>
		</c:if>
    	<c:if test="${param.dataType eq 'activity'}">
    		<jsp:include page="inputActivity.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'summary'}">
    		<jsp:include page="inputSummary.jsp"></jsp:include>
    	</c:if>
    ]
    </c:if>
}
