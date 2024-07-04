<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    <c:if test='${param.funcFlow eq "00021"}'>
    	<jsp:include page="dataList_00021.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.funcFlow eq "00022" }'>
    	<jsp:include page="dataList_00022.jsp"></jsp:include>
    </c:if>
		
    <c:if test='${param.funcFlow eq "00023"}'>
     	<jsp:include page="dataList_00023.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.funcFlow eq "0007"}'>
     	<jsp:include page="dataList_0007.jsp"></jsp:include>
    </c:if>
	
	</c:if>
}
