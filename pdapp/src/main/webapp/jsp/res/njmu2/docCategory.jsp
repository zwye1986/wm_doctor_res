<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
	    ,

		"datas":[
			<c:forEach items="${list}" var="dict" varStatus="status">
				{
					"dictId":${pdfn:toJsonString(dict.id)},
					"dictName":${pdfn:toJsonString(dict.name)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}