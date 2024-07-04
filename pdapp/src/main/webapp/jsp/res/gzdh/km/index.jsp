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
	"auditSize":"${auditSize}",
	"kqSize":"${kqSize}",
	"hasNotReadInfo":"${hasNotReadInfo}",
	"isChargeOrg":"${isChargeOrg}",
	"searchData":[
			{
				dataId:"sessionNumber",
				dataName:"年级",
				datas:[]
			},
			{
				dataId:"schDeptFlow",
				dataName:"轮转科室",
				datas:[
					<c:forEach items="${schDepts}" var="dict" varStatus="s">
						{
						"dictId":"${dict.schDeptFlow}",
						"dictName":"${dict.schDeptName}"
						}
						<c:if test="${not s.last }">
							,
						</c:if>
					</c:forEach>
				]
			},
			{
				dataId:"speId",
				dataName:"专业",
				datas:[
					<c:forEach items="${dicts}" var="dict" varStatus="s">
						{
						"dictId":"${dict.dictId}",
						"dictName":"${dict.dictName}"
						}
						<c:if test="${not s.last }">
							,
						</c:if>
					</c:forEach>
				]
			}
		]
  </c:if>
}