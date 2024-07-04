<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"hasNotReadInfo": ${pdfn:toJsonString(hasNotReadInfo)},
	"userInfo": {
		"userFlow": ${pdfn:toJsonString(userinfo.userFlow)},
		"userSex": ${pdfn:toJsonString(userinfo.sexName)},
		"userName": ${pdfn:toJsonString(userinfo.userName)},
		"userHeadImg": ${pdfn:toJsonString(userinfo.userHeadImg)},
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
		],
	"isSch":"${studentNum}",
	"isCurrent":"${isCurrent}",
	"count":"${noAuditNumber}",
	"isActivity":${isActivity},
	"isAttendance":${isAttendance},
	"jzxxgl":${jzxxgl},
	"pxsjsh":${pxsjsh},
	"sxpjcx":${sxpjcx},
	"datas":[
		{
			"dataFlow":"1",
			"dataType":"fiveData",
			"title":"数据审核",
			"items":[]
		},
		{
			"dataFlow":"2",
			"dataType":"after",
			"title":"出科审核表",
			"items":[]
		},
		{
			"dataFlow":"3",
			"dataType":"docEval",
			"title":"学员考评",
			"items":[]
		},
		{
			"dataFlow":"4",
			"dataType":"skillTest",
			"title":"技能测评",
			"items":[
				{
					"dataFlow":"3",
					"dataType":"miniCex",
					"title":"MINI-CEX"
				},
				{
					"dataFlow":"4",
					"dataType":"dops",
					"title":"DOPS"
				}
			]
		}
	],

	"searchData":[
			{
				dataId:"sessionNumber",
				dataName:"年级",
				datas:[
						{
							"dictId":"",
							"dictName":"全部"
						}
				]
			},
			{
				dataId:"doctorTypeId",
				dataName:"人员类型",
				datas:[
						{
							"dictId":"",
							"dictName":"全部"
						}
						<c:if test="${not empty doctorTypes}">
							,
						</c:if>
					<c:forEach items="${doctorTypes}" var="dict" varStatus="s">
						{
							"dictId":"${dict.id}",
							"dictName":"${dict.name}"
						}
						<c:if test="${not s.last }">
							,
						</c:if>
					</c:forEach>
				]
			},
			{
				dataId:"trainingTypeId",
				dataName:"培训类别",
				datas:[
						{
							"dictId":"",
							"dictName":"全部",
							"dicts":[
								{
									"id":"",
									"name":"全部"
								}
							]

						}
					<c:if test="${not empty trainingTypes}">
						,
					</c:if>
					<c:forEach items="${trainingTypes}" var="dict" varStatus="s">
						{
							"dictId":"${dict.id}",
							"dictName":"${dict.name}",
							<c:set var="dicts" value="${dictMap[dict.id]}"></c:set>
							"dicts":[
									{
										"id":"",
										"name":"全部"
									}
									<c:if test="${not empty dicts}">
										,
									</c:if>
								<c:forEach items="${dicts}" var="dict" varStatus="s1">
									{
										"id":"${dict.dictId}",
										"name":"${dict.dictName}"
									}
									<c:if test="${not s1.last }">
										,
									</c:if>
								</c:forEach>
							]
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