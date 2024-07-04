<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
        <c:if test="${!read}">
            ,
            "action":{
                "save":"保存"
            }
        </c:if>
        <c:if test='${param.patientTypeId eq "1"}'>
        <jsp:include page="selectPatient_1.jsp"></jsp:include>
        </c:if>
        <c:if test='${param.patientTypeId eq "2"}'>
        <jsp:include page="selectPatient_2.jsp"></jsp:include>
        </c:if>
	</c:if>
}
