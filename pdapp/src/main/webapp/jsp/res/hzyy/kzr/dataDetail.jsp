<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	 <c:if test="${resultId eq '200' }">
    ,
    <c:if test='${param.dataTypeId eq "mr"}'>
    	<jsp:include page="dataDetail_mr.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.dataTypeId eq "disease" }'>
    	<jsp:include page="dataDetail_disease.jsp"></jsp:include>
    </c:if>
		
    <c:if test='${param.dataTypeId eq "skill"}'>
     	<jsp:include page="dataDetail_skill.jsp"></jsp:include>
    </c:if>
    
    <c:if test='${param.dataTypeId eq "operation"}'>
     	<jsp:include page="dataDetail_operation.jsp"></jsp:include>
    </c:if>
    <c:if test='${param.dataTypeId eq "disciple"}'>
     	<jsp:include page="dataDetail_disciple.jsp"></jsp:include>
    </c:if>
	
	</c:if>
}
