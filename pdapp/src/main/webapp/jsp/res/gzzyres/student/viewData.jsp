<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{ 
	"resultId": ${resultId}, 
	"resultType":${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
	    ,
	    <c:if test='${funcFlow eq "0001"}'>
		    <jsp:include page="viewData_0001.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0002"}'>
		    <jsp:include page="viewData_0002.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0003"}'>
		    <jsp:include page="viewData_0003.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0004"}'>
		    <jsp:include page="viewData_0004.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0005" || funcFlow eq "0006"}'>
		    <jsp:include page="viewData_0005-6.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0007"}'>
		    <jsp:include page="viewData_0007.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0008"}'>
		    <jsp:include page="viewData_0008.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0009"}'>
		    <jsp:include page="viewData_0009.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "0009_1"}'>
		    <jsp:include page="viewData_0009_1.jsp"></jsp:include>
		</c:if>
	    <c:if test='${funcFlow eq "D001"}'>
	    	<jsp:include page="viewData_D001.jsp"></jsp:include>
		</c:if>
		<c:if test='${funcFlow eq "D0011"}'>
		<jsp:include page="viewData_D0011.jsp"></jsp:include>
		</c:if>
	    <c:if test='${funcFlow eq "D002"}'>
	    	<jsp:include page="viewData_D002.jsp"></jsp:include>
		</c:if>
	    <c:if test='${funcFlow eq "D003"}'>
	    	<jsp:include page="viewData_D003.jsp"></jsp:include>
		</c:if>
	    <c:if test='${funcFlow eq "D004"}'>
	    	<jsp:include page="viewData_D004.jsp"></jsp:include>
		</c:if>
	    <c:if test='${funcFlow eq "D005"}'>
	    	<jsp:include page="viewData_D005.jsp"></jsp:include>
		</c:if>
	</c:if>
}
