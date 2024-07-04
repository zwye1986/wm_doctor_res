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
				"userName":"${bean.docName}",
				"schStartDate":"${bean.startTime}",
				"schEndDate":"${bean.endTime}",
				"schScore": <c:if test="${empty bean.schScore }">"暂无"</c:if><c:if test="${not empty bean.schScore }">"${bean.schScore}"</c:if>,
				"docFlow":"${bean.docFlow}",
				"cySecId":"${bean.cySecId}",
				"hosCySecId":"${bean.hosCySecId}",
				"hosSecId":"${bean.hosSecId}",
				"headId":"${bean.headId}",
				"recId":"${bean.afterId}",
				"schDeptName":"${bean.schDeptName}",
				"sessionNumber":"${bean.sessionNumber}",
				"spName":"${bean.spName}",
				"afterEvaId":"${bean.afterEvaId}",
				"workId":"${bean.workId}",
				"schStatus":
						"${bean.schStatus}",
				"schStatusName":
					<c:if test="${bean.schStatus!='1'}">
						"轮转中"
					</c:if>
					<c:if test="${bean.schStatus=='1'}">
						"已出科"
					</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}