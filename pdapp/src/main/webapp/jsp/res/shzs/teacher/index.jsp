<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"userInfo": {
		"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		"userSex": ${pdfn:toJsonString(userinfo.sexName)},
		"userName": ${pdfn:toJsonString(userinfo.userName)},
		"userHeadImg": ${pdfn:toJsonString(userinfo.userHeadImg)}
		},
	"isSch":"${isSch}",
	"isCurrent":"${isCurrent}",
	"count":"${noAuditNumber}",
	"roles":[
		<c:forEach items="${roles}" var="role" varStatus="s">
			{
			"roleId":"${role.roleId}",
			"roleName":"${role.roleName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
	],
	"datas":[
		{
			"dataFlow":"1",
			"dataType":"fiveData",
			"title":"数据审核"
		},
		{
			"dataFlow":"2",
			"dataType":"AfterSummary,AfterEvaluation",
			"title":"出科考核"
		},
		{
			"dataFlow":"3",
			"dataType":"Mini_CEX",
			"title":"MINI-CEX"
		},
		{
			"dataFlow":"4",
			"dataType":"DOPS",
			"title":"DOPS"
		}
	]
  </c:if>
}