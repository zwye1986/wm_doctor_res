<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"userInfo": {
		"userFlow": ${pdfn:toJsonString(userInfo.userFlow)},
		"userSex": ${pdfn:toJsonString(userInfo.sexName)},
		"userName": ${pdfn:toJsonString(userInfo.userName)},
		"userHeadImg": ${pdfn:toJsonString(userInfo.userHeadImg)}
		},
	"isCurrent":"${count}",
	"isChargeOrg":"${isChargeOrg}"
  </c:if>
}