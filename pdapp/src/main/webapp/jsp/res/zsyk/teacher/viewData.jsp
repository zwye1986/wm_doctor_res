<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	    <c:if test='${param.funcFlow eq "0001"}'>
	    	<jsp:include page="viewData_0001.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "00021"}'>
	     	<jsp:include page="viewData_00021.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "00022"}'>
	    	<jsp:include page="viewData_00022.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "00023"}'>
	    	<jsp:include page="viewData_00023.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "0003"}'>
	     	<jsp:include page="viewData_0003.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "0004"}'>
	     	<jsp:include page="viewData_0004.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "0005"}'>
	     	<jsp:include page="viewData_0005.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "0006"}'>
	    	<jsp:include page="viewData_0006.jsp"></jsp:include>
	    </c:if>
	    <c:if test='${param.funcFlow eq "0007"}'>
	    	<jsp:include page="viewData_0007.jsp"></jsp:include>
	    </c:if>
   	</c:if>
}
