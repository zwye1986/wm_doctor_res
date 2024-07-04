<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"sessionNumber": "${sessionNumber}",
	"TrainingNum": ${TrainingNum},
	"GraduationNum": ${GraduationNum},
	"WaitGraduationNum": ${WaitGraduationNum},
	"SpeChange": ${SpeChange},
	"BaseChange": ${BaseChange},
	"Delay": ${Delay},
	"ReturnTraining": ${ReturnTraining},
	"totalCount": ${totalCount},
	"max": ${max},
    "orgSpes": [
		<c:forEach items="${orgSpes}" var="bean" varStatus="s">
	     	{
			<c:set var="key" value="${bean.speTypeId}${bean.speId}"></c:set>
				"speTypeId":"${bean.speTypeId}",
				"speTypeName":"${bean.speTypeName}",
				"speId":"${bean.speId}",
				"speName":"${bean.speName}",
				"count":"${empty totalCountMap[key] ? 0:totalCountMap[key]}"
			}
			<c:if test="${not s.last }">
				,
			</c:if>
     </c:forEach>
    ],
	"searchData":[
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