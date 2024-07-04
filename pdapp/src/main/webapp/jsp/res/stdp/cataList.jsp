<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
	"dataCount":2
    <c:if test="${resultId eq '200' }">
    ,
    	<c:if test="${param.funcFlow eq 'S003'}">
    		<jsp:include page="student/cataList_S003.jsp"></jsp:include>
    	</c:if>
    </c:if>
}