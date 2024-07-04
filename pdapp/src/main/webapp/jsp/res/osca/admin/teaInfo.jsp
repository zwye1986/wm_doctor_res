<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${teaList}" var="data" varStatus="s">
	     	{
				"order": ${pdfn:toJsonString((param.pageIndex-1)*param.pageSize+s.count)},
				"userFlow": ${pdfn:toJsonString(data.userFlow)},
				"userName": ${pdfn:toJsonString(data.userName)},
				"userCode": ${pdfn:toJsonString(data.userCode)},
				"sexName": ${pdfn:toJsonString(data.sexName)},
				"titleName": ${pdfn:toJsonString(data.titleName)},
				"clinicalName": ${pdfn:toJsonString(data.clinicalName)},
				"speName": ${pdfn:toJsonString(data.speName)},
				"roomName":${pdfn:toJsonString(data.roomName)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}