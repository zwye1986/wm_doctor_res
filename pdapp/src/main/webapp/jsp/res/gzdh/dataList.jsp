<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)},
    "pageIndex":1,
	"pageSize":10,
    <c:if test="${resultId eq '200' }">
        "dataCount":${dataCount},
		<jsp:include page="${path}/dataList_${param.funcFlow}.jsp"></jsp:include>
    </c:if>
}