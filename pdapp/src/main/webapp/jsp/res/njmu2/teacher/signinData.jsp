<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"data":[
        <c:forEach items="${signInMaps}" var="signInMap" varStatus="status">
            <c:set var="headImg" value="${uploadBaseUrl}/${empty signInMap.userHeadImg?'default.png':signInMap.userHeadImg}"/>
            {
            "userName": ${pdfn:toJsonString(signInMap.userName)},
            "signDate": ${pdfn:toJsonString(signInMap.signDate)},
            "doctorImg": ${pdfn:toJsonString(headImg)},
            "schDeptName": ${pdfn:toJsonString(signInMap.schDeptName)}
            }
            <c:if test="${!status.last}">
                ,
            </c:if>
        </c:forEach>
        ]
    </c:if>
}