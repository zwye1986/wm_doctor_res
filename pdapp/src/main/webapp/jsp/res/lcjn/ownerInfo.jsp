<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
        "userInfo": {
            "userFlow": ${pdfn:toJsonString(user.userFlow)},
            "userCode": ${pdfn:toJsonString(user.userCode)},
			"userName":${pdfn:toJsonString(user.userName)},
			"sexId":${pdfn:toJsonString(user.sexId)},
			"sexName":${pdfn:toJsonString(user.sexName)},
			"idNo":${pdfn:toJsonString(user.idNo)},
			"userPhone":${pdfn:toJsonString(user.userPhone)},
			"userEmail":${pdfn:toJsonString(user.userEmail)},
			"orgName":${pdfn:toJsonString(user.orgName)},
			"deptName":${pdfn:toJsonString(user.deptName)},
			"titleId":${pdfn:toJsonString(user.titleId)},
			"titleName":${pdfn:toJsonString(user.titleName)},
			"lcjnSpeId":${pdfn:toJsonString(user.lcjnSpeId)},
			"lcjnSpeName":${pdfn:toJsonString(user.lcjnSpeName)}
		},
		"titleList" :
		[
			<c:forEach items="${titleList}" var="bean" varStatus="s">
				{
					"titleId":"${bean.dictId}",
					"titleName":"${bean.dictName}"
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		],
		"speList" :
		[

			<c:forEach items="${speList}" var="bean" varStatus="s">
				{
					"speId":"${bean.dictId}",
					"speName":"${bean.dictName}"
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		]
    </c:if>
}