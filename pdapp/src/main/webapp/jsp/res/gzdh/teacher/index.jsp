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
	"hasNotReadInfo":"${hasNotReadInfo}",
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
	"depts":[
		<c:forEach items="${depts}" var="dept" varStatus="s">
			{
			"deptFlow":"${dept.deptFlow}",
			"deptName":"${dept.deptName}"
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
	],
	"searchData":[
			{
				dataId:"sessionNumber",
				dataName:"年级",
				datas:[]
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
			},
			{
				dataId:"statusId",
				dataName:"状态",
				datas:[
					{
						id:"NotEntered",
						name:"未入科",
					},
					{
						id:"Entering",
						name:"轮转中"
					},
					{
						id:"Exited",
						name:"已出科"
					}
				]
			}
		]
  </c:if>
}