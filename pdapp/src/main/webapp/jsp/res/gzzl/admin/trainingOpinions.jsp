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
				<c:set var="headImg" value="${uploadBaseUrl}/${empty userMap[bean.opinionUserFlow].userHeadImg?'default.png':userMap[bean.opinionUserFlow].userHeadImg}"/>
				"userName":"${userMap[bean.opinionUserFlow].userName}",
				"userPhone":"${userMap[bean.opinionUserFlow].userPhone}",
				"userHeadImg":${pdfn:toJsonString(headImg)},
				"trainingOpinionFlow":"${bean.trainingOpinionFlow}",
				"opinionUserName":"${bean.opinionUserName}",
				"opinionUserFlow":"${bean.opinionUserFlow}",
				"opinionUserContent":${pdfn:toJsonString(bean.opinionUserContent)},
				"evaTime":"${bean.evaTime}",
				"opinionReplyUserFlow":"${bean.opinionReplyUserFlow}",
				"opinionReplyName":"${bean.opinionReplyName}",
				"opinionReplyContent":${pdfn:toJsonString(bean.opinionReplyContent)},
				"evaTime":"${pdfn:transDate(bean.evaTime)}",
				"replyTime":"${pdfn:transDate(bean.replyTime)}",
				<c:if test="${empty bean.replyTime}">
					"statusId":"NotRepaly",
					"statusName":"未回复"
				</c:if>
				<c:if test="${not empty bean.replyTime}">
					"statusId":"Repalyed",
					"statusName":"已回复"
				</c:if>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}