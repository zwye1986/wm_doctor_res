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
				"userFlow":"${bean.userFlow}",
				"userName":"${bean.userName}",
				"userHeadImg":"${headImg}",
				"trainingSpeName":"${bean.speName}",
				"trainingTypeName":"${bean.catSpeName}",
				"doctorTypeName":"${bean.doctorTypeName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}