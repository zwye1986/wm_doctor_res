<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
	"dataCount":2
    <c:if test="${resultId eq '200' }">
	    ,
		<jsp:include page="student/cataList_${param.funcFlow}.jsp"></jsp:include>
    </c:if>
}