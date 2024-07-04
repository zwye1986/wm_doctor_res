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
			<c:set var="headImg" value="${uploadBaseUrl}/${empty bean.sysUser.userHeadImg?'default.png':bean.sysUser.userHeadImg}"/>
				"userName":"${bean.sysUser.userName}",
				"userHeadImg":"${headImg}",
				"historyTrainingSpeName":"${bean.historyTrainingSpeName}",
				"trainingSpeName":"${bean.trainingSpeName}",
				"orgFlow":"${bean.orgFlow}",
				"orgName":"${bean.orgName}",
				"historyOrgFlow":"${bean.historyOrgFlow}",
				"historyOrgName":"${bean.historyOrgName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ]
    </c:if>
}