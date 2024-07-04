<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"hasNotReadInfo": ${pdfn:toJsonString(hasNotReadInfo)},
	"userInfo": {
		"userFlow": ${pdfn:toJsonString(userInfo.userFlow)},
		"userSex": ${pdfn:toJsonString(userInfo.sexName)},
		"userName": ${pdfn:toJsonString(userInfo.userName)},
		"userHeadImg": ${pdfn:toJsonString(userInfo.userHeadImg)},
		"deptFlow": ${pdfn:toJsonString(userInfo.deptFlow)},
		"deptName": ${pdfn:toJsonString(userInfo.deptName)}
		},
	"isNotSch":"${ccount+0}",
	"isCurrent":"${count+0}",
	"isChargeOrg":"${isChargeOrg}",
    "isActivity":${isActivity},
    "isAttendance":${isAttendance},
    "jzxxgl":${jzxxgl},
    "pxsjsh":${pxsjsh},
    "sxpjcx":${sxpjcx},

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