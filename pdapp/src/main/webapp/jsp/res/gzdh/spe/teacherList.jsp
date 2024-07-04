<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"dataCount": "${dataCount}",
		"doctorList": [
		<c:forEach items="${list}" var="user" varStatus="status">
			<c:set var="headImg" value="${uploadBaseUrl}/${empty user.userHeadImg?'default.png':user.userHeadImg}"/>
			{
				"userFlow": ${pdfn:toJsonString(user.userFlow)},
				"userName": ${pdfn:toJsonString(user.userName)},
				"headImg": ${pdfn:toJsonString(headImg)},
				"deptFlow": ${pdfn:toJsonString(user.deptFlow)},
				"deptName" : ${pdfn:toJsonString(user.deptName)},
				"levelId" : ${pdfn:toJsonString(user.levelId)}
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}