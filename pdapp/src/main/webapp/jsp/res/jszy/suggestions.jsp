<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
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
		<c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
			{
			"trainingOpinionFlow":"${trainingOpinion.trainingOpinionFlow}",
			"opinionUserContent":${pdfn:toJsonString(trainingOpinion.opinionUserContent)},
			"opinionReplyContent":${pdfn:toJsonString(trainingOpinion.opinionReplyContent)},
			"evaTime":"${pdfn:transDate(trainingOpinion.evaTime)}",
			"replyTime":"${pdfn:transDate(trainingOpinion.replyTime)}",
			"adminReplyId":
				<c:if test="${empty trainingOpinion.opinionReplyContent}">
					"NotReply",
				</c:if>
				<c:if test="${!empty trainingOpinion.opinionReplyContent}">
					"Replyed",
				</c:if>
			"adminReplyName":
				<c:if test="${empty trainingOpinion.opinionReplyContent}">
					"管理员未回复"
				</c:if>
				<c:if test="${!empty trainingOpinion.opinionReplyContent}">
					"管理员已回复"
				</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
    ]
    </c:if>
}
