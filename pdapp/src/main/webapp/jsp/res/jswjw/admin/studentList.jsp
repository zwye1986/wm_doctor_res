<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${list}" var="bean" varStatus="s">
	     	{
			<c:set var="headImg" value="${uploadBaseUrl}/${empty bean.userHeadImg?'default.png':bean.userHeadImg}"/>
				"userName":"${bean.userName}",
				"userHeadImg":"${headImg}",
				"docFlow":"${bean.userFlow}",
				"deptFlow":"${bean.deptFlow}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}