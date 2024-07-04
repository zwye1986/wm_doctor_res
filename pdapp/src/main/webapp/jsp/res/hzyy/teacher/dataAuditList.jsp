<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount":${dataCount},
    <c:if test='${param.dataTypeId eq "mr"}'>
    	<jsp:include page="dataAuditList_mr.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.dataTypeId eq "disease" }'>
    	<jsp:include page="dataAuditList_disease.jsp"></jsp:include>
    </c:if>
		
    <c:if test='${param.dataTypeId eq "skill"}'>
     	<jsp:include page="dataAuditList_skill.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.dataTypeId eq "operation"}'>
     	<jsp:include page="dataAuditList_operation.jsp"></jsp:include>
    </c:if>
    <c:if test='${param.dataTypeId eq "disciple"}'>
     	<jsp:include page="dataAuditList_disciple.jsp"></jsp:include>
    </c:if>
	
	</c:if>
}
