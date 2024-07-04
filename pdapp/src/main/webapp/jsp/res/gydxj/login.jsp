<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"roles":[
			<c:forEach items="${roles}" var="role" varStatus="s">
				{
				"roleFlow":"${role.roleFlow}",
				"roleId":"${role.roleId}",
				"roleName":"${role.roleName}"
				}
				<c:if test="${not s.last }">
					,
				</c:if>
			</c:forEach>
		],
	    <c:if test="${isDoctor}">
		    "userInfo": {
				"roleFlow": ${pdfn:toJsonString(roleFlow)},
				"roleId": ${pdfn:toJsonString(roleId)},
				"roleName": ${pdfn:toJsonString(roleName)},
				"sid": ${pdfn:toJsonString(eduUser.sid)},
		        "userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
				"userName": ${pdfn:toJsonString(userinfo.userName)},
				"sexId": ${pdfn:toJsonString(userinfo.sexId)},
				"sexName": ${pdfn:toJsonString(userinfo.sexName)},
				"nameSpell": ${pdfn:toJsonString(eduUser.nameSpell)},
				"userBirthday": ${pdfn:toJsonString(userinfo.userBirthday)},
				"cretTypeId": ${pdfn:toJsonString(userinfo.cretTypeId)},
				"cretTypeName": ${pdfn:toJsonString(userinfo.cretTypeName)},
				"idNo": ${pdfn:toJsonString(userinfo.idNo)},
				"nationId": ${pdfn:toJsonString(userinfo.nationId)},
				"nationName": ${pdfn:toJsonString(userinfo.nationName)},
				"period": ${pdfn:toJsonString(eduUser.period)},
				"classId": ${pdfn:toJsonString(eduUser.classId)},
				"className": ${pdfn:toJsonString(eduUser.className)},
				"majorId": ${pdfn:toJsonString(eduUser.majorId)},
				"majorName": ${pdfn:toJsonString(eduUser.majorName)},
				"trainTypeId": ${pdfn:toJsonString(eduUser.trainTypeId)},
				"trainTypeName": ${pdfn:toJsonString(eduUser.trainTypeName)},
				"is5plus3": ${pdfn:toJsonString(eduUser.is5plus3)}
		    } ,
	    </c:if>
		<c:if test="${isTeacher}">
			"userInfo": {
			"roleFlow": ${pdfn:toJsonString(roleFlow)},
			"roleId": ${pdfn:toJsonString(roleId)},
			"roleName": ${pdfn:toJsonString(roleName)},
			"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
			"userName": ${pdfn:toJsonString(userinfo.userName)},
			"sexId": ${pdfn:toJsonString(userinfo.sexId)},
			"sexName": ${pdfn:toJsonString(userinfo.sexName)},
			"userBirthday": ${pdfn:toJsonString(userinfo.userBirthday)},
			"cretTypeId": ${pdfn:toJsonString(userinfo.cretTypeId)},
			"cretTypeName": ${pdfn:toJsonString(userinfo.cretTypeName)},
			"idNo": ${pdfn:toJsonString(userinfo.idNo)},
			"nationId": ${pdfn:toJsonString(userinfo.nationId)},
			"nationName": ${pdfn:toJsonString(userinfo.nationName)}
			} ,
		</c:if>
		<c:if test="${isAdmin}">
			"userInfo": {
			"roleFlow": ${pdfn:toJsonString(roleFlow)},
			"roleId": ${pdfn:toJsonString(roleId)},
			"roleName": ${pdfn:toJsonString(roleName)},
			"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
			"userName": ${pdfn:toJsonString(userinfo.userName)},
			"sexId": ${pdfn:toJsonString(userinfo.sexId)},
			"sexName": ${pdfn:toJsonString(userinfo.sexName)},
			"userBirthday": ${pdfn:toJsonString(userinfo.userBirthday)},
			"cretTypeId": ${pdfn:toJsonString(userinfo.cretTypeId)},
			"cretTypeName": ${pdfn:toJsonString(userinfo.cretTypeName)},
			"idNo": ${pdfn:toJsonString(userinfo.idNo)},
			"nationId": ${pdfn:toJsonString(userinfo.nationId)},
			"nationName": ${pdfn:toJsonString(userinfo.nationName)}
			} ,
		</c:if>
		"noticeList":[
		<c:forEach items="${noticeList}" var="no" varStatus="i">
			{
			"infoFlow":${pdfn:toJsonString(no.infoFlow)},
			"infoTitle":${pdfn:toJsonString(no.infoTitle)},
			"infoTargetFlow":${pdfn:toJsonString(no.infoTargetFlow)},
			"infoTypeId":${pdfn:toJsonString(no.infoTypeId)},
			"infoTypeName":${pdfn:toJsonString(no.infoTypeName)},
			"infoContent":${pdfn:toJsonString(no.infoContent)},
			"createTime":${pdfn:toJsonString(no.createTime)}
			}
			<c:if test="${!i.last}">
				,
			</c:if>
		</c:forEach>
		]
    </c:if>
}