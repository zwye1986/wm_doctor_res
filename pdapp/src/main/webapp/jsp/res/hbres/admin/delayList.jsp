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
			<c:set var="headImg" value="${uploadBaseUrl}/${empty userMap[bean.doctorFlow].userHeadImg?'default.png':userMap[bean.doctorFlow].userHeadImg}"/>
				"userName":"${userMap[bean.doctorFlow].userName}",
				"userHeadImg":"${headImg}",
				"trainingTypeName":"${bean.trainingTypeName}",
				"trainingSpeName":"${bean.trainingSpeName}",
				"sessionNumber":"${bean.sessionNumber}",
				"graduationYear":"${bean.graduationYear}",
				"delayreason":"${bean.delayreason}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}