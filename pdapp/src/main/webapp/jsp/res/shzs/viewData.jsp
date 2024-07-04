<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)},
    "viewPath": ${pdfn:toJsonString(viewPath)}
    <c:if test="${resultId eq '200' }">
	    ,
		<jsp:include page="/jsp/${viewPath}.jsp"></jsp:include>
    </c:if>
}