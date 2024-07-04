<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${doctList}" var="data" varStatus="s">
	     	{
				"order": ${pdfn:toJsonString((param.pageIndex-1)*param.pageSize+s.count)},
				"userFlow": ${pdfn:toJsonString(data.userFlow)},
				"userName": ${pdfn:toJsonString(data.userName)},
				"userCode": ${pdfn:toJsonString(data.userCode)},
				"sexName": ${pdfn:toJsonString(data.sexName)},
				"speName": ${pdfn:toJsonString(data.speName)},
				"sessionNumber": ${pdfn:toJsonString(data.sessionNumber)},
				"ticketNumber": ${pdfn:toJsonString(data.ticketNumber)},
				"idNo": ${pdfn:toJsonString(data.idNo)},
				"userPhone":${pdfn:toJsonString(data.userPhone)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}