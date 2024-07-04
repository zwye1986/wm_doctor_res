<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${tarinNotices}" var="tarinNotice" varStatus="s">
	     	{
				<c:if test="${empty tarinNotice.noticePicPath}">
					<c:set var="noticePicPath" value="${hostUrl}"/>
				</c:if>
				<c:if test="${not empty tarinNotice.noticePicPath}">
					<c:set var="noticePicPath" value="${uploadBaseUrl}/${tarinNotice.noticePicPath}"/>
				</c:if>
				"recordFlow":"${tarinNotice.recordFlow}",
				"resNoticeTitle":"${tarinNotice.resNoticeTitle}",
				"createTime":"${pdfn:transDateTime(tarinNotice.createTime)}",
				"resNoticeContent":${pdfn:toJsonString(tarinNotice.resNoticeContent)},
				"resNotice":${pdfn:toJsonString(pdfn:cutString(tarinNotice.resNoticeContent,18,true,1))},
				"noticePicPath":${pdfn:toJsonString(noticePicPath)}
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}
