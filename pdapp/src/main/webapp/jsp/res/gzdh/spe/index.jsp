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
	depts:[
		<c:forEach items="${depts}" var="dict" varStatus="s">
			{
				"deptFlow":"${dict.deptFlow}",
				"deptName":"${dict.deptName}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
	],
	"searchData":[
			{
				dataId:"sessionNumber",
				dataName:"年级",
				datas:[]
			},
			{
				dataId:"doctorTypeId",
				dataName:"学员类型",
				datas:[
					<c:forEach items="${doctorTypes}" var="dict" varStatus="s">
						{
							"dictId":"${dict.id}",
							"dictName":"${dict.name}",
							<c:set var="dicts" value="${dictMap[dict.id]}"></c:set>
							"dicts":[
								<c:forEach items="${dicts}" var="dict" varStatus="s1">
									{
										"id":"${dict.deptFlow}",
										"name":"${dict.deptName}"
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
		],
		"studentListSearchData":[
			{
				dataId:"sessionNumber",
				dataName:"年级",
				datas:[]
			},
			{
				dataId:"doctorTypeId",
				dataName:"学员类型",
				datas:[
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
				dataId:"deptFlow",
				dataName:"科室名称",
				datas:[
					<c:forEach items="${depts}" var="dict" varStatus="s">
						{
							"dictId":"${dict.deptFlow}",
							"dictName":"${dict.deptName}"
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