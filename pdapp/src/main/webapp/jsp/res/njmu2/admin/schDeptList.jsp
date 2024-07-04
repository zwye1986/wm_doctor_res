<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,

		"datas":[
			<c:forEach items="${schDeptList}" var="dict" varStatus="status">
				{
					"schDeptFlow":${pdfn:toJsonString(dict.schDeptFlow)},
					"schDeptName":${pdfn:toJsonString(dict.schDeptName)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}