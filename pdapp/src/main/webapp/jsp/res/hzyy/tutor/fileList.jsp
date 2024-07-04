<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
	,
	<c:if test="${param.dataType eq 'stuImgList'}">
		<jsp:include page="stuImgList.jsp"></jsp:include>
	</c:if>
	<c:if test="${param.dataType ne 'stuImgList'}">
		<jsp:include page="notStuImgList.jsp"></jsp:include>
	</c:if>
	</c:if>
}