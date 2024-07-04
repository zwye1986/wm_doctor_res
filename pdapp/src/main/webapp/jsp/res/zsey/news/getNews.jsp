<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": 1,
    "pageSize": 10,
    "dataCount": ${dataCount},
    "datas": [
    	<c:forEach items="${infoList}" var="info" varStatus="status">
            <c:if test="${empty info.titleImg}">
                <c:set var="titleImg" value="${hostUrl}"/>
            </c:if>
            <c:if test="${not empty info.titleImg}">
                <c:set var="titleImg" value="${uploadBaseUrl}/titleImages/${info.titleImg}"/>
            </c:if>

	        {
	            "recordFlow": ${pdfn:toJsonString(info.infoFlow)},
	            "resNoticeTitle": ${pdfn:toJsonString(info.infoTitle)},
	            "noticePicPath": ${pdfn:toJsonString(titleImg)},
	            "isRead": "${empty isReadMap[info.infoFlow]?'N':'Y'}",
	            "createTime": ${pdfn:toJsonString(info.infoTime)}
	        }
        <c:if test="${!status.last}">
	        ,
        </c:if>
    	</c:forEach>
    ]
    </c:if>
}
