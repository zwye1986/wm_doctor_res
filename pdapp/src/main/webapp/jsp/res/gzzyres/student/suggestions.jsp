<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"pageIndex": "${pageIndex}",
	"pageSize": "${pageSize}",
	"dataCount": "${dataCount}",
    "datas": [
		<c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="s">
			{
			"trainingOpinionFlow":"${trainingOpinion.Record_Flow}",
			"opinionUserContent":${pdfn:toJsonString(trainingOpinion.Opinion_User_Content)},
			"evaTime":"${trainingOpinion.Eva_Time}",
			"adminReplyName":"",
			"adminReplyId":"${trainingOpinion.IsReply}",
			"opinionReplyContent":${pdfn:toJsonString(trainingOpinion.Opinion_Reply_Content)},
			"replyTime":"${trainingOpinion.Reply_Time}"
			}
			<c:if test="${not s.last}">
				,
			</c:if>
		</c:forEach>
    ]
    </c:if>
}
