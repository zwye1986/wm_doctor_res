<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "inputs": [
    	<c:if test="${param.dataType eq 'mr'}">
    		<jsp:include page="inputMr.jsp"></jsp:include>
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
    	<c:if test="${param.dataType eq 'activity'}">
    		<jsp:include page="inputActivity.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'summary'}">
    		<jsp:include page="inputSummary.jsp"></jsp:include>
    	</c:if>
    ]
    </c:if>
}
