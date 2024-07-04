<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
	"dataCount":2
    <c:if test="${resultId eq '200' }">
    ,
	   	<c:if test="${param.funcFlow eq 'S001'}">
	   		<jsp:include page="student/dataList_S001.jsp"></jsp:include>
	   	</c:if>
	   	<c:if test="${param.funcFlow eq 'S002'}">
	   		<jsp:include page="student/dataList_S002.jsp"></jsp:include>
	   	</c:if>
	   	<c:if test="${param.funcFlow eq 'S003'}">
	   		<jsp:include page="student/dataList_S003.jsp"></jsp:include>
	   	</c:if>
	   	<c:if test="${param.funcFlow eq 'S004'}">
	   		<jsp:include page="student/dataList_S004.jsp"></jsp:include>
	   	</c:if>
	   	<c:if test="${param.funcFlow eq 'S005'}">
	   		<jsp:include page="student/dataList_S005.jsp"></jsp:include>
	   	</c:if>
	   	<c:if test="${param.funcFlow eq 'S006'}">
	   		<jsp:include page="student/dataList_S006.jsp"></jsp:include>
	   	</c:if>
	   		<c:if test="${param.funcFlow eq 'S007'}">
	   		<jsp:include page="student/dataList_S007.jsp"></jsp:include>
	   	</c:if>
   		<c:if test="${param.funcFlow eq 'T002'}">
	   		<jsp:include page="teacher/dataList_T002.jsp"></jsp:include>
	   	</c:if> 
    </c:if>
}