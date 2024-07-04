<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"course": {
		"courseFlow":"${info.courseFlow}",
		"courseName":"${info.courseName}",
		"coursePeopleNum":"${info.coursePeopleNum}",
		"courseAddress":"${info.courseAddress}",
		"courseDemo":"${info.courseDemo}",
		"appointStartTime":"${info.appointStartTime}",
		"appointEndTime":"${info.appointEndTime}",
		"isReleased":"${info.isReleased}",
		"isScoreReleased":"${info.isScoreReleased}",
		"codeInfo":"${info.codeInfo}",
		"evaluate":{
			"evaluateFlow":"${courseEvaluate.evaluateFlow}",
			"evalContent":"${courseEvaluate.evalContent}"
		},
		"evaluateDetails":[
			<c:forEach items="${courseEvaList}" var="dict" varStatus="s">
			<c:set var="d" value="${courseDetailMap[dict.dictId]}"></c:set>
			{
				"dictId":"${dict.dictId}",
				"dictName":"${dict.dictName}",
				"recordFlow":"${d.recordFlow}",
				"evaluateFlow":"${d.evaluateFlow}",
				"evalScore":"${d.evalScore}"
			}
			<c:if test="${not s.last }">
			,
			</c:if>
			</c:forEach>
		]
	},
	"teas":[
		<c:forEach items="${teas}" var="bean" varStatus="s">
		{
			"recordFlow":"${bean.recordFlow}",
			"userFlow":"${bean.userFlow}",
			"userName":"${bean.userName}",
			"teachingCost":"${bean.teachingCost}",
			"evaluate":{
				<c:set var="teaEvaluate" value="${teaEvalMap[bean.userFlow]}"></c:set>
				"teaEvaluateFlow":"${teaEvaluate.teaEvaluateFlow}",
				"evalContent":"${teaEvaluate.evalContent}"
			},
			"evaluateDetails":[
				<c:forEach items="${teacherEvaList}" var="dict" varStatus="s">
					<c:set var="key" value="${bean.userFlow}${dict.dictId}"></c:set>
					<c:set var="d" value="${teaDetailMap[key]}"></c:set>
					{
						"dictId":"${dict.dictId}",
						"dictName":"${dict.dictName}",
						"recordFlow":"${d.recordFlow}",
						"teaEvaluateFlow":"${d.teaEvaluateFlow}",
						"evalScore":"${d.evalScore}"
					}
					<c:if test="${not s.last }">
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
	</c:if>
}