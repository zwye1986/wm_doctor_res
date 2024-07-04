<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount}
    ,
    <c:if test='${param.funcFlow eq "0001"}'>
    	<jsp:include page="dataList_0001.jsp"></jsp:include>
	</c:if>
	
    <c:if test='${param.funcFlow eq "0002"}'>
    	<jsp:include page="dataList_0002.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.funcFlow eq "0003" }'>
    	<jsp:include page="dataList_0003.jsp"></jsp:include>
    </c:if>
	
    <c:if test='${param.funcFlow eq "0004"}'>
     	<jsp:include page="dataList_0004.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.funcFlow eq "D001"}'>
    	<jsp:include page="dataList_D001.jsp"></jsp:include>
	</c:if>

    <c:if test='${param.funcFlow eq "D0011"}'>
    <jsp:include page="dataList_D0011.jsp"></jsp:include>
    </c:if>

    <c:if test='${param.funcFlow eq "D002"}'>
    	<jsp:include page="dataList_D002.jsp"></jsp:include>
	</c:if>
    
    <c:if test='${param.funcFlow eq "D003"}'>
    	<jsp:include page="dataList_D003.jsp"></jsp:include>
	</c:if>
    
    <c:if test='${param.funcFlow eq "D004"}'>
    	<jsp:include page="dataList_D004.jsp"></jsp:include>
	</c:if>
    
	</c:if>
}
