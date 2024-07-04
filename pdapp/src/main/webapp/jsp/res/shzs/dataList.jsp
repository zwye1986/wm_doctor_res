<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
	"dataCount":${dataCount}
    <c:if test="${resultId eq '200' }">
	    ,
		<jsp:include page="/jsp/res/formDataList/dataList.jsp"></jsp:include>
    </c:if>
}