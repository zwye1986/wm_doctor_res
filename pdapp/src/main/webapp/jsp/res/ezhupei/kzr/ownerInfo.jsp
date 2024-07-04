<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"userInfo": {
		"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		"userSexName": ${pdfn:toJsonString(userinfo.sexName)},
		"userSexId": ${pdfn:toJsonString(userinfo.sexId)},
		"userCode": ${pdfn:toJsonString(userinfo.userCode)},
		"userName": ${pdfn:toJsonString(userinfo.userName)},
		"userPhone": ${pdfn:toJsonString(userinfo.userPhone)},
		"userEmail": ${pdfn:toJsonString(userinfo.userEmail)},
		"certificateId": ${pdfn:toJsonString(userinfo.certificateId)},
		"idNo": ${pdfn:toJsonString(userinfo.idNo)},
		"userHeadImg": ${pdfn:toJsonString(userinfo.userHeadImg)},
		"orgFlow": ${pdfn:toJsonString(userinfo.orgFlow)},
		"orgName": ${pdfn:toJsonString(userinfo.orgName)},
		"deptFlow": ${pdfn:toJsonString(userinfo.deptFlow)},
		"deptName": ${pdfn:toJsonString(userinfo.deptName)}
		},
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
	]
  </c:if>
}