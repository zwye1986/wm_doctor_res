<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
    <c:if test="${resultId eq '200' }">
	    	<jsp:include page="schFuncList_Doctor.jsp"></jsp:include>
    </c:if>
}
