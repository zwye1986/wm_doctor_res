<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="data" varStatus="s">
	     	{

			<c:set var="headImg" value="${uploadBaseUrl}/${empty data.userHeadImg?'default.png':data.userHeadImg}"/>
				"userName": ${pdfn:toJsonString(data.userName)},
				"headImg": ${pdfn:toJsonString(headImg)},
				"trainingSpeName": ${pdfn:toJsonString(data.speName)},
				"userPhone":${pdfn:toJsonString(data.userPhone)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}