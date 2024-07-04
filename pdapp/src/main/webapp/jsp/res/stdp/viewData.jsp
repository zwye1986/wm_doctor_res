<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    	<c:if test="${param.funcFlow eq 'S001'}">
    		<jsp:include page="student/viewData_S001.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S002'}">
    		<jsp:include page="student/viewData_S002.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S003'}">
    		<jsp:include page="student/viewData_S003.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S004'}">
    		<jsp:include page="student/viewData_S004.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S005'}">
    		<jsp:include page="student/viewData_S005.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S006'}">
    		<jsp:include page="student/viewData_S006.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S007'}">
    		<jsp:include page="student/viewData_S007.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'S008' or param.funcFlow eq 'S009'}">
    		<jsp:include page="student/viewData_S008-9.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'T002'}">
    		<jsp:include page="student/viewData_S001.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.funcFlow eq 'T004'}">
    		<jsp:include page="teacher/viewData_T004.jsp"></jsp:include>
    	</c:if>
    </c:if>
}