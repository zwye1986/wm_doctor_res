<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "datas": [
		<c:forEach items="${recruitList}" var="b" varStatus="s">
	     	{
				"userName": ${pdfn:toJsonString(userName)},
				"recruitFlow": ${pdfn:toJsonString(b.recruitFlow)},
				"catSpeName": ${pdfn:toJsonString(b.catSpeName)},
				"speName": ${pdfn:toJsonString(b.speName)},
				"recruitYear": ${pdfn:toJsonString(b.recruitYear)},
				"orgName": ${pdfn:toJsonString(b.orgName)},
				"speName": ${pdfn:toJsonString(b.speName)},
				"examResult": ${pdfn:toJsonString(b.examResult)},
				"auditionResult": ${pdfn:toJsonString(b.auditionResult)},
				"operResult": ${pdfn:toJsonString(b.operResult)},
				"auditStatusName": ${pdfn:toJsonString(b.auditStatusName)}
				<c:if test="${b.confirmFlag ne 'Y'}">
					,"confirmFlag": "未报到"
				</c:if>
				<c:if test="${b.confirmFlag eq 'Y'}">
					,"confirmFlag": "已报到"
				</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ],
	"dataCount": ${dataCount}
    </c:if>
}
